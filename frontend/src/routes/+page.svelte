<script lang="ts">
    import { MapLibre, Marker, Popup } from "svelte-maplibre-gl"
	import Navbar from "#lib/components/Navbar.svelte"
    import TwoColumnView from "#lib/components/TwoColumnView.svelte"
    import BloodTypeAmounts from "#lib/components/BloodTypeAmounts.svelte"

    import type { PageProps } from "./$types"

    type CenterData = {
        centerid: number
        centername: string
        address: string
        lat: number
        lng: number
    }

    type CenterThresholdData = {
        centerid: number
        bloodtype: string
        lowthreshold: number
        criticalthreshold: number
    }

    let { data }: PageProps = $props()

    const centerData: CenterData[] = JSON.parse((() => data)().centers).rows
    const thresholdData: CenterThresholdData[] = JSON.parse((() => data)().thresholds).rows

    const hasMap: boolean = true
    const hasActions: boolean = true
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

        <BloodTypeAmounts levels={[1, 2, 3, 4, 1, 2, 2, 1]} />

        <!-- todo: UC-01 4a nema najavljenih akcija -->
        {#if hasMap}
        <TwoColumnView>
        {#snippet leftColumn()}
            <MapLibre
                class="map-view"
                style="https://tiles.openfreemap.org/styles/liberty"
                zoom={6}
                center={{ lng: 16.5, lat: 44.5 }}>

                {#each centerData as cent (cent.centerid)}
                <Marker lnglat={[cent.lng, cent.lat,]}>
                    <Popup>
                        <span class="center-name">{cent.centername}</span>
                        <ul class="center-thresholds">
                            {#each thresholdData.filter(thr => thr.centerid === cent.centerid) as threshold (threshold.bloodtype)}
                                <li class="center-threshold">{`${threshold.bloodtype}: nisko ${threshold.lowthreshold}, kriticno ${threshold.criticalthreshold}`}</li>
                            {/each}
                        </ul>
                    </Popup>
                </Marker>
                {/each}
            </MapLibre>
        {/snippet}

        {#snippet rightColumn()}
        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Veniam, ullam? Neque suscipit voluptatum nemo debitis aspernatur aperiam dolorem pariatur, adipisci repellat labore. Quidem ea assumenda molestias, incidunt excepturi odio nesciunt adipisci impedit sed non natus soluta aliquid officiis veritatis nisi perspiciatis. Adipisci aliquam fugit impedit alias, quos eligendi doloremque provident unde sunt deleniti tempora optio eum repellendus esse natus iste, officia, ipsam maxime. Omnis labore velit aut rerum maxime amet tempore dolorem dicta accusamus! Aspernatur optio placeat tempore, tempora voluptate, corporis laboriosam expedita in, totam consequuntur aut eius. Omnis similique numquam, debitis laboriosam iusto inventore. Dignissimos voluptate a aliquid inventore.</p>
        {/snippet}
        </TwoColumnView>
        {:else}
        <div class="map-data-list-view">ovo je kad nema mapeeeeeeee</div>
        {/if}
    
        {#if !hasActions}
        <p>Nema akcija za darivanje trenutno</p>
        {/if}
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

    .center-name {
        font-weight: 700;
    }

    .center-thresholds {
        list-style-type: none;
        padding-inline-start: 0;
        margin-block-end: 0;
    }
</style>
