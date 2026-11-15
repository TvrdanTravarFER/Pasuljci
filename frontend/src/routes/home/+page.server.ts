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

        const isInAppUser = Number((await client.query(`SELECT COUNT(*) FROM AppUser WHERE OAuthSubject = '${sessionUser.id}' AND OAuthProvider = 'google';`)).rows[0].count)

        if (!isInAppUser) {
            const names = sessionUser.name.split(" ")

            await client.query(`INSERT INTO AppUser (Email, OAuthProvider, OAuthSubject, FirstName, LastName, Role) VALUES ('${sessionUser.email}', 'google', '${sessionUser.id}', '${names[0]}', '${names[1]}', 'DONOR');`)

            const ID = (await client.query(`SELECT UserID FROM AppUser WHERE OAuthSubject = '${sessionUser.id}';`)).rows[0].userid

            await client.query(`INSERT INTO Donor (UserID) VALUES (${ID});`)
        }

        // more bit tehnicki vise providera al to nije implementirano
        const loggedUserRole = (await client.query(`SELECT Role FROM AppUser WHERE OAuthSubject = '${sessionUser.id}' AND OAuthProvider = 'google';`)).rows[0].role

        await client.end()
        
        return {
            userRole: loggedUserRole
        }
    }
    else {
        error(403, "Neovlasten pristup")
    }
}