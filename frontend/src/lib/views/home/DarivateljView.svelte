<script lang="ts">
    import { resolve } from "$app/paths"
    import TwoColumnView from "#lib/components/TwoColumnView.svelte";
    import Dialog from "#lib/components/Dialog.svelte";
    import { Settings, X } from "@lucide/svelte";
    import { authClient	} from "#lib/auth-client.ts"
    // import { router } from "better-auth/api"

    // Dok se ne napravi baza podataka
    const session = authClient.useSession()
    const rezerviran: boolean = true

    const googleSignOut = async () => {
        await authClient.signOut({
            // fetchOptions: {
            //     onSuccess: () => {
            //         router.push("/")
            //     }
            // }
        })
    }
</script>

<section id="upperhalf">
    <div class="donor-dashboard-greeting">
        <h1 class="donor-name">{$session.data?.user.name}</h1>

        {#if rezerviran}
            <div class="donor-reserved-subtitle">
                <p class="donor-reserved-subtitle-info-message">Rezervirani ste za termin (taj i taj).</p>
                <Dialog>
                    {#snippet trigger()}
                        Provjerite detalje ovdje.
                    {/snippet}

                    {#snippet title()}
                        <h2 class="dialog-title">Vaša rezervacija</h2>
                    {/snippet}

                    {#snippet description()}
                        <div></div>
                    {/snippet}

                    <p>Gdje, kad</p>
                    <p>jos nesto mozda i dunno</p>

                    <button>Želim otkazati rezervaciju</button>

                    {#snippet close()}
                        <X size={24} />
                    {/snippet}
                </Dialog>
            </div>
        {:else}
            <p class="donor-not-reserved-subtitle">Niste rezervirani za termin.
                <button>
                    <a href={resolve("/home/appoint")}>Rezervirajte termin ovdje</a>
                </button>
            </p>
        {/if}
    </div>

    <div class="donor-dashboard-buttons">
        <Dialog>
            {#snippet trigger()}
                <Settings size={24} />
            {/snippet}

            {#snippet title()}
                <h2 class="dialog-title">Postavke računa</h2>
            {/snippet}

            {#snippet description()}
                <div></div>
            {/snippet}

            <h2>Osobni podaci</h2>
            <form action="" class="platform-login-form">
                <input type="text" name="ime" id="ime" placeholder="Ime" required>
                <input type="text" name="prezime" id="prezime" placeholder="Prezime" required>
                
                <br>

                <label for="spol">Spol</label>
                <select name="spol" id="spol" required>
                    <option value="Muškarac">Muškarac</option>
                    <option value="Žena">Žena</option>
                </select>
                
                <br>

                <input type="text" name="mjesto_stanovanja" id="mjesto_stanovanja" placeholder="Mjesto stanovanja">
                
                <br>

                <!-- Treba verifikacija za ovo -->
                <input type="text" name="broj_telefona" id="broj_telefona" placeholder="Broj telefona">
                
                <br>

                <input type="email" name="email" id="email" placeholder="E-adresa" pattern={String.raw`^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$`}>
                <br>

                <label for="krvna_grupa">Krvna grupa</label>
                <br>
                <select name="krvna_grupa" id="krvna_grupa" required>
                    <option value="AB+">AB+</option>
                    <option value="AB-">AB-</option>
                    <option value="A+">A+</option>
                    <option value="A-">A-</option>
                    <option value="B+">B+</option>
                    <option value="B-">B-</option>
                    <option value="O+">O+</option>
                    <option value="O-">O-</option>
                </select>

                <br>

                <p>Krvna grupa nije potvrđena od strane djelatnika.</p>

                <br>

                <h2>Obavijesti</h2>
                <label for="nacin_primanja_obavijesti">Obavijesti me putem:</label>
                <br>
                <select name="nacin_primanja_obavijesti" id="nacin_primanja_obavijesti" required>
                    <option value="email">E-poštom</option>
                    <option value="mobitel">Porukom na mobitelu</option>
                </select>

                <br>

                <label for="">Najveća udaljenost za obavijesti o akcijama (u kilometrima):</label>
                <br>
                <input type="number" name="raspon_za_obavijesti" id="raspon_za_obavijesti" placeholder="npr. 50">

                <br>

                <button type="submit">Spremi</button>
            </form>

            {#snippet close()}
                <X size={24} />
            {/snippet}
        </Dialog>
        <button class="sign-out-button" onclick={googleSignOut}>Odjavi se</button>
    </div>
</section>
<section id="lowerhalf">
    <TwoColumnView>
        {#snippet leftColumn()}
            <div class="donations">
                <button><a href={resolve("/home/donations")}>Povijest darivanja</a></button>
            </div>
        {/snippet}

        {#snippet rightColumn()}
            <div>Nesto jos</div>
        {/snippet}
    </TwoColumnView>
</section>

<style>
    #upperhalf {
        height: 50vh;
        display: flex;
        align-items: end;
        justify-content: space-between;
        margin-block-end: 4rem;

        .donor-name {
            font-size: 3rem;
            margin-block-end: 1rem;
        }

        .donor-reserved-subtitle {
            display: flex;
        }

        .donor-reserved-subtitle-info-message {
            margin-inline-end: 0.15rem;
        }

        .donor-dashboard-buttons {
            display: flex;
            flex-direction: column;
            align-items: end;
            gap: 1.5rem;
        }
    }

    #lowerhalf {
        height: 50vh;
    }

    button {
        background-color: var(--color-accent);
        color: var(--color-background);
        padding: 0.6rem 1.5rem;
        margin-block-start: 0.15rem;
        border: 3px solid var(--color-accent);
        border-radius: 36px;
        font-weight: 700;
        transition: all 150ms ease-in-out;
    }
</style>
