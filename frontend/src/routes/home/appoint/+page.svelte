<script>
    import TwoColumnView from "#lib/components/TwoColumnView.svelte";
</script>

<main>
    <TwoColumnView>
        {#snippet leftColumn()}
            <form action="">
                <label for="centar">Centar</label>
                <br>
                <input type="text" name="centar" id="centar">

                <br>

                <label for="datum">Datum</label>
                <br>
                <input type="date" name="datum" id="datum">

                <br>

                <button type="submit">Pretraži</button>
            </form>
        {/snippet}

        {#snippet rightColumn()}
            <ul>
                <li>Termin 1 komponenta</li>
                <li>Termin 2 komponenta</li>
                <li>Termin 3 komponenta</li>
            </ul>
        {/snippet}
    </TwoColumnView>

    <p>Ili mozda ocete u centar? Evo centar</p>
</main>