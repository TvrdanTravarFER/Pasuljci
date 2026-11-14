import { auth } from "#lib/auth.ts"
import { svelteKitHandler } from "better-auth/svelte-kit"
import { building } from "$app/env"

import type { Handle } from "@sveltejs/kit/hooks"

export const handle: Handle = async ({ event, resolve }) => {
    return svelteKitHandler({ event, resolve, auth, building })
}