<script lang="ts">
    import { MapLibre, Marker } from "svelte-maplibre-gl"
	import Navbar from "#lib/components/Navbar.svelte"
    import TwoColumnView from "#lib/components/TwoColumnView.svelte"
    import BloodTypeAmounts from "#lib/components/BloodTypeAmounts.svelte";

    const hasMap: boolean = true
</script>

<Navbar />
<main>
    <section class="greeting">
        <h1 class="greeting-headline">Dobrodošli</h1>
        <p class="greeting-description">Ovo će uskoro biti glavna stranica web platforme <i>KrvnaVeza</i>, koja umrežava i povezuje darivatelje krvi s transfuzijskim centrima i organizatorima akcija darivanja krvi.</p>
    </section>
    <section class="greeting">
        <h1 class="greeting-headline">Glavna stranica</h1>
        <p class="greeting-description">Lorem ipsum dolor sit amet consectetur adipisicing elit. Exercitationem illo iste natus possimus amet commodi recusandae, consequatur neque illum laborum a eius eaque? Alias et laborum suscipit! Expedita voluptates minus ad quis earum facere dolorem obcaecati hic porro facilis ratione aspernatur sapiente dicta, vero inventore iste officia ut, aperiam ab voluptas perspiciatis fuga at. Ad dignissimos numquam iusto non debitis natus sequi ratione quam veniam vel, placeat praesentium nulla perspiciatis quisquam voluptatem obcaecati mollitia quod at nesciunt maiores inventore. Architecto quo eos harum earum illum doloremque error, quod soluta et fuga neque autem minima voluptatem laboriosam dignissimos quia a totam?</p>
    </section>
    <section id="map">
        <h2>Ostanite u toku.</h2>
        <p>U tren oka provjerite stanje krvnih zaliha, kao i nadolazeće akcije darivanja krvi diljem Hrvatske, na karti dolje. Registracija nije nužna.</p>

        <!-- todo: ovo dvoje u kartice? ili neki drugaciji view da nije samo jedno ispod drugog -->
        <!-- mozda fiksni scroll view, kasnije -->
        <BloodTypeAmounts levels={[1, 2, 3, 4, 1, 2, 2, 1]} />
    
        <!-- todo: ovdje ide karta. dat req prema apiju za kartu i preko nekog bool (tj uvjeta) provjerit, i na osnovu toga renderat ili kartu ili staticnu listu -->

        <!-- todo: UC-01 4a nema najavljenih akcija -->
        {#if hasMap}
            <TwoColumnView>
                {#snippet leftColumn()}
                    <MapLibre
                        class="map-view"
                        style="https://tiles.openfreemap.org/styles/liberty"
                        zoom={3}
                        center={{ lng: -43, lat: -22 }}>
    
                        <Marker lnglat={[-43.20556, -22.91111,]}/>
                    </MapLibre>
                {/snippet}

                {#snippet rightColumn()}
                    <p>data blablabla</p>
                {/snippet}
            </TwoColumnView>
        {:else}
            <div class="map-data-list-view">ovo je kad nema mapeeeeeeee</div>
        {/if}
    
        <!-- todo: ne znam konkretno kako radi mapa. ali recimo kad se odabere mjesto, moze se renderat dole zaliha krvi za svaku grupu. mozda 2 column layout gdje je lijevo mapa a desno detalji? na mobitelu se moze dat neki popup ili tooltip na neki nacin, to ce se vidjet. -->
    </section>
</main>

<style>
    .greeting-headline {
        font-size: 4rem;
        font-weight: 700;
        letter-spacing: -0.5px;
    }

    .greeting-description {
        font-size: 1.25rem;
    }

    #map {
        margin-block-start: 5rem;

        h2 {
            font-size: 2.25rem;
        }
    }

    p {
        line-height: 1.5;
    }

    main {
		margin-inline: 2rem;
	}

	@media (min-width: 1024px) {
        main {
            margin-inline: 16vw;
            margin-block: 15vh 25vh;
        }
	}
</style>
