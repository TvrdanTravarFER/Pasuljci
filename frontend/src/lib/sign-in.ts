import { authClient } from "./auth-client"

await authClient.signIn.social({
    provider: "google",
    callbackURL: "/home",
    errorCallbackURL: "/",
})