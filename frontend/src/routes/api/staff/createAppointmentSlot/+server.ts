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

        // todo vremenska zona

        const appointmentStart = new Date(`${data.get("date")}T${data.get("starttime")}:00Z`)
        const appointmentEnd = new Date(`${data.get("date")}T${data.get("endtime")}:00Z`)

        const appointmentStartString = `${appointmentStart.toISOString()}`.replace("T", " ").replace("Z", "")
        const appointmentEndString = `${appointmentEnd.toISOString()}`.replace("T", " ").replace("Z", "")

        const queryText = `INSERT INTO CenterAppointmentSlot VALUES (${data.get("centerid")}, TIMESTAMP '${appointmentStartString}', TIMESTAMP '${appointmentEndString}', ${data.get("capacity")});`

        console.log(data)
        console.log(appointmentStartString, appointmentEndString)
        
        client.connect()

        await client.query(queryText)

        client.end()

        redirect(303, "/home/center")
    }
    else {
        error(403, "Neovlasten pristup")
    }
}