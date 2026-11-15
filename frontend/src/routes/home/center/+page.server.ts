import { Client } from "pg"
import { auth } from "#lib/auth.ts"
import { error } from "@sveltejs/kit"

import type { PageServerLoad } from "./$types"

export const load: PageServerLoad = async ({ request }) => {
    const client = new Client({
        user: "krvnaveza_app",
        password: "4",
        host: "localhost",
        port: 5432,
        database: "krvnaveza",
    })

    type StaffData = {
        centerid: number
        centername: string
        address: string
        lat: number
        lng: number
    }

    type CenterWorktime = {
        centerid: number
        dayofweek: number
        workstart: string
        workend: string
    }

    type Appointment = {
        centerid: number
        centerappointmentstart: Date
        centerappointmentend: Date
        capacity: number
    }

    type AppointmentString = Omit<Appointment, "centerappointmentstart" | "centerappointmentend"> & { centerappointmentstart: string, centerappointmentend: string }

    const session_res = await auth.api.getSession({ headers: request.headers })

    if (session_res) {
        client.connect()
    
        const staffData: StaffData = (await client.query(`SELECT CenterID, CenterName, Address, Lat, Lng FROM AppUser NATURAL JOIN Staff NATURAL JOIN Center WHERE OAuthSubject = '${session_res.user.id}';`)).rows[0]

        const centerWorktimes: CenterWorktime[] = (await client.query(`SELECT * FROM CenterWorktime WHERE CenterID = ${staffData.centerid};`)).rows

        const stringAppointments: AppointmentString[] = (await client.query(`SELECT * FROM CenterAppointmentSlot WHERE CenterID = ${centerWorktimes[0].centerid} ORDER BY CenterAppointmentStart ASC;`)).rows
    
        client.end()

        const appointments: Appointment[] = stringAppointments.map(app => {
            return {
                centerid: app.centerid,
                centerappointmentstart: new Date(app.centerappointmentstart),
                centerappointmentend: new Date(app.centerappointmentend),
                capacity: app.capacity
            }
        })

        return {
            staffData: staffData,
            centerWorktimes: centerWorktimes,
            appointments: appointments
        }
    }
    else {
        error(403, "Neovlasten pristup")
    }
}