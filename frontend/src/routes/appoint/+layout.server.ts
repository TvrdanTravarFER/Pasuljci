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
        database: "krvnaveza",
        ssl: true
    })

    const session_res = await auth.api.getSession({ headers: request.headers })

    if (session_res) {
        client.connect()

        const centers: { centername: string }[] = (await client.query("SELECT centername FROM center;")).rows

        client.end()

        return {
            centerNames: centers.map(center => center.centername),
        }
    }
    else {
        error(403, "Neovlasten pristup")
    }
}