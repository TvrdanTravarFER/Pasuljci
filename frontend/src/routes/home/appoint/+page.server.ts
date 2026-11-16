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
                user: "krvnaveza_app",
                password: "4",
                host: "localhost",
                port: 5432,
                database: "krvnaveza",
            })

            client.connect()

            type SlotsInCriteria = {
                centername: string
                address: string
                centerappointmentstart: Date
                centerappointmentend: Date
                capacity: number
            }

            const slotsInCriteria: SlotsInCriteria[] = (await client.query(`SELECT CenterName, Address, CenterAppointmentStart, CenterAppointmentEnd, Capacity FROM CenterAppointmentSlot NATURAL JOIN Center WHERE CenterName = '${url.searchParams.get("centername")}' AND CenterAppointmentStart::DATE = '${url.searchParams.get("date")}';`)).rows

            client.end()

            return {
                slots: slotsInCriteria
            }
        }
    }

    else {
        error(403, "Neovlasten pristup")
    }
}