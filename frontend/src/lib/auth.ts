import { betterAuth } from "better-auth"
import { sveltekitCookies } from "better-auth/svelte-kit"
import { Pool } from "pg"
import { getRequestEvent } from "$app/server"

import dotenv from "dotenv"
dotenv.config()

export const auth = betterAuth({
    database: new Pool({
        connectionString: "postgres://krvnaveza_app:4@localhost:5432/krvnaveza",
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