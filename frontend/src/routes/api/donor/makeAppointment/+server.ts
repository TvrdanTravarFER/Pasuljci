import { error, redirect } from "@sveltejs/kit"
import { auth } from "#lib/auth.ts"
import { Client } from "pg"

import type { RequestHandler } from "@sveltejs/kit"

export const POST: RequestHandler = async ({ request }) => {
    const session_res = await auth.api.getSession({ headers: request.headers })

    if (session_res) {
        const client = new Client({
            user: "krvnaveza_app",
            password: "4",
            host: "localhost",
            port: 5432,
            database: "krvnaveza",
        })

        const data = await request.formData()

        const ineligible = false
        
        // treba dinamicki
        const questionNumbers = ["1", "2", "3", "4", "5"]
        const presentAnswers = Array.from(data.entries().filter(entry => entry[0].startsWith("q")).map(entry => entry[0]))
        const answers: [string, string][] = questionNumbers.map(num => {
            return [`q${num}`, String(presentAnswers.includes(`q${num}`))]
        })

        console.log(answers)

        const appstart = new Date(String(data.get("h_appstart")))
        const append = new Date(String(data.get("h_append")))
        const now = new Date(Date.now())

        const appointmentStart = `${appstart.toLocaleDateString("en-US")} ${appstart.toLocaleTimeString("hr-HR")}`
        const appointmentEnd = `${append.toLocaleDateString("en-US")} ${append.toLocaleTimeString("hr-HR")}`
        const createdAt = `${now.toLocaleDateString("en-US")} ${now.toLocaleTimeString("hr-HR")}`

        client.connect()

        const donorID: number = Number((await client.query(`SELECT UserID from AppUser NATURAL JOIN Donor WHERE OAuthSubject = '${session_res.user.id}';`)).rows[0].userid)
        const centerID: number = (await client.query(`SELECT CenterID FROM Center WHERE CenterName = '${data.get("h_centername")}'`)).rows[0].centerid

        const appointmentQueryText = `INSERT INTO Appointment (DonorID, CenterID, AppointmentStart, AppointmentEnd, CreatedAt, StatusChangedAt) VALUES (${donorID}, ${centerID}, TIMESTAMPTZ '${appointmentStart}', TIMESTAMPTZ '${appointmentEnd}', TIMESTAMPTZ '${createdAt}', TIMESTAMPTZ '${createdAt}');`
        await client.query(appointmentQueryText)

        const appointmentID: number = (await client.query(`SELECT AppointmentID FROM Appointment WHERE DonorID = ${donorID} AND CenterID = ${centerID};`)).rows[0].appointmentid

        await client.query(`INSERT INTO SelfAssessment (AppointmentID, LikelyIneligible) VALUES (${appointmentID}, ${String(ineligible)});`)

        // decrement mjesta
        // await client.query(``) count *

        let answersQueryText = `INSERT INTO SelfAssessmentAnswer VALUES`
        answers.forEach(answer => {
            answersQueryText += ` (${appointmentID}, ${answer[0].replace("q", "")}, ${answer[1]})`
        })
        answersQueryText = answersQueryText.replaceAll(") (", "), (")
        answersQueryText += ";"

        await client.query(answersQueryText)

        client.end()

        redirect(303, "/home/appoint")
    }
    else {
        error(403, "Neovlasten pristup")
    }
}