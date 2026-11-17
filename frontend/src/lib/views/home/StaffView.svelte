<script>
    import Dialog from "#lib/components/Dialog.svelte";
    import { Settings, X } from "@lucide/svelte";
    import TwoColumnView from "#lib/components/TwoColumnView.svelte";
    import { resolve } from "$app/paths"
    import { authClient } from "#lib/auth-client.ts"
    import BloodTypeAmounts from "#lib/components/BloodTypeAmounts.svelte"

    const session = authClient.useSession()

    const covjek = {
        ime: "Ime",
        prezime: "Prezime",
        rezerviran: false
    }
</script>

<section id="upperhalf">
    <div class="staff-dashboard-greeting">
        <h1>{$session.data?.user.name}</h1>

        <div class="button-console">
            <button class="center-button"><a href={resolve("/center")}>CENTAR</a></button>
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

                <form action="" class="platform-login-form">
                    <input type="text" name="ime" id="ime" placeholder="Ime" value={covjek.ime} required>
                    <input type="text" name="prezime" id="prezime" placeholder="Prezime" value={covjek.prezime} required>
                    
                    <br>

                    <label for="spol">Spol</label>
                    <select name="spol" id="spol" required>
                        <option selected disabled value>Odaberite...</option>
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
                        <option selected disabled value>Odaberite...</option>
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

                    <button type="submit">Spremi</button>
                </form>

                {#snippet close()}
                    <X size={24} />
                {/snippet}
            </Dialog>
        </div>
    </div>
    <p>Djelatnik transfuzijskog centra</p>

    <!-- <BloodTypeAmounts levels={[1, 2, 3, 4, 1, 2, 2, 1]} /> -->
</section>
<section id="lowerhalf">
    <TwoColumnView>
        {#snippet leftColumn()}
            <div class="donations">
                <p>TERMIN</p>
            </div>
        {/snippet}

        {#snippet rightColumn()}
            <p>DOZE</p>
        {/snippet}
    </TwoColumnView>
</section>

<style>
    #upperhalf {
        height: 50vh;
        display: flex;
        flex-direction: column;
        justify-content: end;
        margin-block-end: 4rem;
        
        .staff-dashboard-greeting {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .center-button {
            background-color: transparent;
            color: var(--color-foreground);
            padding: 0.6rem 1.5rem;
            margin-block-start: 0.15rem;
            border: 3px solid var(--color-foreground);
            border-radius: 36px;
            font-weight: 700;
            transition: all 150ms ease-in-out;

            a {
                text-decoration: none;

                &:visited {
                    color: var(--color-foreground);
                }
            }
        }
    }

    #lowerhalf {
        height: 20vh;
    }
</style>