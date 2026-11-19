import { error } from "@sveltejs/kit"
import { auth } from "#lib/auth.ts"
import { Client } from "pg"

import type { PageServerLoad } from "./$types"

export const load: PageServerLoad = async ({ request, url }) => {
    const session_res = await auth.api.getSession({ headers: request.headers })

    if (session_res) {
        const isSearchQuery = url.searchParams.get("centername")?.trim() ?? ""

        if (!isSearchQuery) return {
            slots: []
        }
        else {
            const client = new Client({
                user: process.env.DB_USER,
                password: process.env.DB_PASSWORD,
                host: process.env.DB_URL,
                port: 5432,
                database: "krvnaveza_db",
                ssl: { rejectUnauthorized: false }
            })

            client.connect()

            type SlotsInCriteria = {
                centername: string
                address: string
                centerappointmentstart: Date
                centerappointmentend: Date
                capacity: number
            }

            type SelfAssessmentQuestion = {
                questionid: number
                question: string
                disqualifyingquestion: boolean
            }

            const slotsInCriteria: SlotsInCriteria[] = (await client.query(`SELECT CenterName, Address, CenterAppointmentStart, CenterAppointmentEnd, Capacity FROM CenterAppointmentSlot NATURAL JOIN Center WHERE CenterName = '${url.searchParams.get("centername")}' AND CenterAppointmentStart::DATE = '${url.searchParams.get("date")}';`)).rows

            const selfAssessmentQuestions: (Omit<SelfAssessmentQuestion, "disqualifyingquestion"> & { disqualifyingquestion: string })[] = (await client.query("SELECT * FROM SelfAssessmentQuestion;")).rows

            client.end()

            return {
                slots: slotsInCriteria,
                selfAssessmentQuestions: selfAssessmentQuestions.map(q => {
                    return {
                        questionid: q.questionid,
                        question: q.question,
                        disqualifyingquestion: q.question === 't'
                    }
                })
            }
        }
    }

    else {
        error(403, "Neovlasten pristup")
    }
}