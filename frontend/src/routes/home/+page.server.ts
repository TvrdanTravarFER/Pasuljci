import { Pool } from "pg"
import { auth } from "#lib/auth.ts"
import { error } from "@sveltejs/kit"

import type { PageServerLoad } from "./$types"

export const load: PageServerLoad = async ({ request }) => {
    const pool = new Pool({
        user: process.env.DB_USER,
        password: process.env.DB_PASSWORD,
        host: process.env.DB_URL,
        port: 5432,
        database: "krvnaveza",
        ssl: true
    })

    const session_res = await auth.api.getSession({ headers: request.headers })

    
    if (session_res) {
        const sessionUser = session_res.user
        const client = await pool.connect()
        
        const isInAppUser = Number((await client.query(`SELECT COUNT(*) FROM AppUser WHERE OAuthSubject = '${sessionUser.id}' AND OAuthProvider = 'google';`)).rows[0].count)
        if (!isInAppUser) {
            const names = sessionUser.name.split(" ")
            
            await client.query(`INSERT INTO AppUser (Email, OAuthProvider, OAuthSubject, FirstName, LastName, Role) VALUES ('${sessionUser.email}', 'google', '${sessionUser.id}', '${names[0]}', '${names[1]}', 'DONOR');`)
            
            const ID = (await client.query(`SELECT UserID FROM AppUser WHERE OAuthSubject = '${sessionUser.id}';`)).rows[0].userid
            
            await client.query(`INSERT INTO Donor (UserID) VALUES (${ID});`)
        }
        
        // more bit tehnicki vise providera al to nije implementirano
        const loggedUserRole = (await client.query(`SELECT Role FROM AppUser WHERE OAuthSubject = '${sessionUser.id}' AND OAuthProvider = 'google';`)).rows[0].role

        client.release()
        
        if (loggedUserRole === "DONOR") {
            const client = await pool.connect()

            const activeAppointment = (await client.query(`SELECT AppointmentStart, AppointmentEnd, AppointmentStatus, CenterName, Center.Address FROM Appointment NATURAL JOIN AppUser NATURAL JOIN Center WHERE OAuthSubject = '${sessionUser.id}' AND AppointmentStatus IN ('ARRIVED', 'RESERVED', 'SCREENING', 'DONATING')`)).rows[0]

            client.release()

            console.log(activeAppointment)

            return {
                userRole: loggedUserRole,
                activeAppointment: activeAppointment
            }
        }

        return {
            userRole: loggedUserRole
        }
    }
    else {
        error(403, "Neovlasten pristup")
    }
}