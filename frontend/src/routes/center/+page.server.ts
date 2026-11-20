import { Client } from "pg"
import { auth } from "#lib/auth.ts"
import { error } from "@sveltejs/kit"

import type { PageServerLoad } from "./$types"

export const load: PageServerLoad = async ({ request }) => {
    const client = new Client({
        user: process.env.DB_USER,
        password: process.env.DB_PASSWORD,
        host: process.env.DB_URL,
        port: 5432,
        database: "krvnaveza_db",
        ssl: { rejectUnauthorized: false }
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
        // filledspots: number
    }

    type AppointmentString = Omit<Appointment, "centerappointmentstart" | "centerappointmentend"> & { centerappointmentstart: string, centerappointmentend: string }

    const session_res = await auth.api.getSession({ headers: request.headers })

    // treba ovdje provjerit dal je uopce korisnik djelatnik.
    if (session_res) {
        client.connect()
    
        const staffData: StaffData = (await client.query(`SELECT CenterID, CenterName, Address, Lat, Lng FROM AppUser NATURAL JOIN Staff NATURAL JOIN Center WHERE OAuthSubject = '${session_res.user.id}';`)).rows[0]

        const centerWorktimes: CenterWorktime[] = (await client.query(`SELECT * FROM CenterWorktime WHERE CenterID = ${staffData.centerid};`)).rows

        const stringAppointments: AppointmentString[] = (await client.query(`SELECT * FROM CenterAppointmentSlot WHERE CenterID = ${centerWorktimes[0].centerid} ORDER BY CenterAppointmentStart ASC;`)).rows

        // popunjena mjesta

        const centerID = (await client.query(`SELECT CenterID FROM AppUser NATURAL JOIN Staff WHERE OAuthSubject = '${session_res.user.id}'`)).rows[0].centerid
        const appointmentReservations = (await client.query(`SELECT FirstName, LastName, AppointmentStart, AppointmentEnd, AppointmentStatus FROM Appointment JOIN AppUser ON CenterID = '${centerID}' AND DonorID = UserID;`)).rows

        client.end()

        const appointments: Appointment[] = stringAppointments.map(app => {
            return {
                centerid: app.centerid,
                centerappointmentstart: new Date(app.centerappointmentstart),
                centerappointmentend: new Date(app.centerappointmentend),
                capacity: app.capacity,
                // filledspots: app.filledspots
            }
        })

        return {
            staffData: staffData,
            centerWorktimes: centerWorktimes,
            appointments: appointments,
            appointmentReservations: appointmentReservations
        }
    }
    else {
        error(403, "Neovlasten pristup")
    }
}