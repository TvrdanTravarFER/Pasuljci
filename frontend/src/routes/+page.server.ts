import { Client } from "pg"

import type { PageServerLoad } from "./$types"

export const load: PageServerLoad = async () => {
    const client = new Client({
        user: "krvnaveza_readonly",
        password: "4",
        host: "localhost",
        port: 5432,
        database: "krvnaveza",
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