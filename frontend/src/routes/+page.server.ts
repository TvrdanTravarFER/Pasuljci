import { Client } from "pg"

import type { PageServerLoad } from "./$types"

import dotenv from "dotenv"
dotenv.config()

export const load: PageServerLoad = async () => {
    const client = new Client({
        user: process.env.DB_USER,
        password: process.env.DB_PASSWORD,
        host: process.env.DB_URL,
        port: 5432,
        database: "krvnaveza_db",
        ssl: { rejectUnauthorized: false }
    })

    await client.connect()

    const dbres_centers = await client.query("SELECT * FROM Center;")
    const dbres_centersthresholds = await client.query("SELECT centerid, bloodtype, lowthreshold, criticalthreshold FROM Center NATURAL JOIN CenterBloodThreshold;")

    await client.end()

    return {
        centers: JSON.stringify(dbres_centers),
        thresholds: JSON.stringify(dbres_centersthresholds)
    }
}