import { betterAuth } from "better-auth"
import { sveltekitCookies } from "better-auth/svelte-kit"
import { Pool } from "pg"
import { getRequestEvent } from "$app/server"

export const auth = betterAuth({
    database: new Pool({
        connectionString: process.env.DB_CONNECTION_LINK,
        ssl: { rejectUnauthorized: false }
    }),
    socialProviders: {
        google: {
            clientId: process.env.GOOGLE_CLIENT_ID!,
            clientSecret: process.env.GOOGLE_CLIENT_SECRET!,
            prompt: "select_account",
        },
    },
    plugins: [sveltekitCookies(getRequestEvent)],
    advanced: {
        database: {
            joins: true,
        }
    }
})