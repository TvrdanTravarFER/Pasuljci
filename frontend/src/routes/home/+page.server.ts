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

    const session_res = await auth.api.getSession({ headers: request.headers })

    if (session_res) {
        const sessionUser = session_res.user

        await client.connect()

        const isInAppUser_res = await client.query(`SELECT COUNT(*) FROM AppUser WHERE OAuthSubject = '${sessionUser.id}' AND OAuthProvider = 'google';`)
        const isInAppUser = Number(isInAppUser_res.rows[0].count)

        console.log(isInAppUser)

        if (!isInAppUser) {
            const names = sessionUser.name.split(" ")

            const insertAppUser = await client.query(`INSERT INTO AppUser (Email, OAuthProvider, OAuthSubject, FirstName, LastName, Role) VALUES ('${sessionUser.email}', 'google', '${sessionUser.id}', '${names[0]}', '${names[1]}', 'DONOR');`)
            
            // console.log("uso sam")
            console.log(insertAppUser)

            const userID_res = await client.query(`SELECT UserID FROM AppUser WHERE OAuthSubject = '${sessionUser.id}';`)
            const ID = userID_res.rows[0].userid

            console.log(userID_res)
            console.log(`ID: ${ID}`)

            const insertDonors = await client.query(`INSERT INTO Donor (UserID) VALUES (${ID});`)

            console.log(insertDonors)
        }

        // more bit tehnicki vise providera al to nije implementirano
        const db_res = await client.query(`SELECT Role FROM AppUser WHERE OAuthSubject = '${sessionUser.id}' AND OAuthProvider = 'google';`)

        await client.end()

        const loggedUserRole = db_res.rows[0].role
        
        return {
            userRole: loggedUserRole
        }
    }
    else {
        error(403, "Neovlasten pristup")
    }
}