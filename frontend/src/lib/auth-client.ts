import { createAuthClient } from "better-auth/svelte"
import { goto, refreshAll } from "$app/navigation"
import { resolve } from "$app/paths"

export const authClient = createAuthClient({
    baseURL: "https://krvnaveza.onrender.com"
})

export const googleSignOut = async () => {
    await authClient.signOut({
        fetchOptions: {
            onSuccess: async () => {
                await refreshAll()
                await goto(resolve("/"))
            }
        }
    })
}