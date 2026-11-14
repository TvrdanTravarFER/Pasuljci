<script>
    import Dialog from "#lib/components/Dialog.svelte";
    import { Settings, X } from "@lucide/svelte";
    import TwoColumnView from "#lib/components/TwoColumnView.svelte";
    import { resolve } from "$app/paths"

    // Dok se ne napravi baza podataka
    const covjek = {
        ime: "Ime",
        prezime: "Prezime",
        rezerviran: false
    }
</script>

<section id="upperhalf">
    <h1>{`${covjek.ime} ${covjek.prezime}`}</h1>

    {#if covjek.rezerviran}
        <p>Rezervirani ste za termin (taj i taj).</p>
                <Dialog>
            {#snippet trigger()}
                <button>Provjerite detalje ovdje.</button>
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
    {:else}
        <p>Niste rezervirani za termin. <button><a href={resolve("/home/appoint")}>Rezervirajte termin ovdje</a></button></p>
    {/if}

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
    </button>
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
    }

    #lowerhalf {
        height: 50vh;
    }
</style>
