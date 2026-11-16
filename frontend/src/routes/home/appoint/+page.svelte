<script lang="ts">
    import TwoColumnView from "#lib/components/TwoColumnView.svelte"
    import Dialog from "#lib/components/Dialog.svelte"
    import { numberToDow } from "#lib/utilities/dayOfWeek.ts"
    import { X } from "@lucide/svelte"

    import type { PageProps } from "./$types"

    const { data }: PageProps = $props()
</script>

<main>
    <TwoColumnView>
        {#snippet leftColumn()}
            <form class="search-slots-form" action="/home/appoint" method="GET">
                <label for="centername">Centar</label>
                <select name="centername" id="centername" required>
                    <option selected disabled value>Odaberite centar</option>
                    {#each data.centerNames as centerName (centerName)}
                        <option value={centerName}>{centerName}</option>
                    {/each}
                </select>

                <br>

                <label for="date">Datum</label>
                <input type="date" name="date" id="date" required>

                <br>

                <button type="submit">Pretraži</button>
            </form>
        {/snippet}

        {#snippet rightColumn()}
            {#if data.slots.length !== 0}
            <h2>Dostupni termini</h2>
            <ul class="available-appointment-slots">
                {#each data.slots as slot (slot)}
                    <li class="appointment-slot">
                        <h3>
                            {numberToDow(slot.centerappointmentstart.getDay())},
                            {slot.centerappointmentstart.toLocaleDateString("hr-HR")}, od
                            {slot.centerappointmentstart.toLocaleTimeString("hr-HR")} do
                            {slot.centerappointmentend.toLocaleTimeString("hr-HR")}
                        </h3>
                        <p class="appointment-slot-center">{slot.centername}</p>
                        <p>Slobodno mjesta: {slot.capacity}</p>

                        <Dialog>
                            {#snippet trigger()}
                                Prijavi se
                            {/snippet}

                            {#snippet title()}
                                <h2>Prijava za termin darivanja</h2>
                            {/snippet}

                            {#snippet description()}
                                Kako bismo procijenili jeste li podobni za darivanje krvi, molimo Vas, ispunite kratki upitnik za procjenu.
                            {/snippet}

                            <form action="/api/donor/makeAppointment" method="POST" class="self-assessment-form">
                                <input type="hidden" name="h_centername" id="h_centername" value={slot.centername}>
                                <input type="hidden" name="h_appstart" id="h_appstart" value={slot.centerappointmentstart}>
                                <input type="hidden" name="h_append" id="h_append" value={slot.centerappointmentend}>
                                {#each data.selfAssessmentQuestions as q (q.questionid)}
                                    <div class="self-assessment-question">
                                        <label for={`q${q.questionid}`}>{q.question}</label>
                                        <input type="checkbox" name={`q${q.questionid}`} id={`q${q.questionid}`}>
                                        <br>
                                    </div>
                                {/each}

                                <button type="submit">Prijavi se</button>
                            </form>

                            {#snippet close()}
                                <X size={24}/>
                            {/snippet}
                        </Dialog>
                    </li>
                {/each}
            </ul>
            {:else}
            <p>Nema slobodnih mjesta. Odaberite drugi centar ili datum...</p>
            {/if}
        {/snippet}
    </TwoColumnView>
</main>

<style>
    .search-slots-form {
        margin-block-start: 2rem;


    }
    /* todo stupci na uzim ekranima */
    .available-appointment-slots {
        padding-inline-start: 0;
        list-style-type: none;

        .appointment-slot {
            border: 2px solid var(--color-foreground);
            border-radius: 12px;
            padding: 0.5rem 1.75rem;
            background-color: #fff8f8;
            margin-block-end: 0.5rem;
        }

        .appointment-slot-center {
            font-style: italic;
        }
    }

    .self-assessment-form {
        margin-block: 2rem 1rem;

        div:last-of-type {
            margin-block-end: 1rem;
        }
    }

    .self-assessment-question {
        margin-block-end: 0.5rem;
    }
</style>