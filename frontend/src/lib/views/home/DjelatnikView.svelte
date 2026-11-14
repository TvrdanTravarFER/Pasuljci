<script>
    import Dialog from "#lib/components/Dialog.svelte";
    import { Settings, X } from "@lucide/svelte";
    import TwoColumnView from "#lib/components/TwoColumnView.svelte";
    import { resolve } from "$app/paths"

    const covjek = {
        ime: "Ime",
        prezime: "Prezime",
        rezerviran: false
    }
</script>

<section id="upperhalf">
    <h1>Djelatnik transfuzijskog centra</h1>

    <button><a href={resolve("/home/center")}>CENTAR</a></button>

    <button class="settings">
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
                <input type="text" name="ime" id="ime" placeholder="Ime" value={covjek.ime} required>
                <input type="text" name="prezime" id="prezime" placeholder="Prezime" value={covjek.prezime} required>
                
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

                <button type="submit">Spremi</button>
            </form>

            {#snippet close()}
                <X size={24} />
            {/snippet}
        </Dialog>
    </button>

    <p>mozda stanje krvi ovdje</p>
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
    }

    #lowerhalf {
        height: 50vh;
    }
</style>