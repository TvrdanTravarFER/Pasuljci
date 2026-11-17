<script lang="ts">
    import Dialog from "#lib/components/Dialog.svelte"
    import { X } from "@lucide/svelte"
    import { numberToDow } from "#lib/utilities/dayOfWeek.ts"
    import { goto } from "$app/navigation"
    import { resolve } from "$app/paths"
    import { tlAppointmentStatus } from "#lib/utilities/translateEnums.ts"

    import type { PageProps } from "./$types";

    let { data }: PageProps = $props()

    const handleSubmit = () => {
        goto(resolve("/center"))
    }
</script>

<section class="center-overview">
    <h1 class="center-name">{data.staffData.centername}</h1>

    <h2>Radna vremena</h2>
    <ul class="center-worktimes">
        {#each data.centerWorktimes as worktime (worktime.dayofweek)}
            <li class="center-worktime-card">
                <p class="weekday">{numberToDow(worktime.dayofweek)}</p>
                <p class="worktime-interval">Od {worktime.workstart} do {worktime.workend}</p>
            </li>
        {/each}
    </ul>

    <h2>Otvoreni termini za darivanje</h2>
    <Dialog>
        {#snippet trigger()}
            Dodajte novi termin
        {/snippet}

        {#snippet title()}
            <h2>Novi termin darivanja</h2>
        {/snippet}

        {#snippet description()}
            <div></div>
        {/snippet}

        <form action="/api/staff/createAppointmentSlot" method="POST">
            <input type="hidden" name="centerid" id="centerid" value={data.staffData.centerid}>
            
            <label for="date">Datum</label>
            <input type="date" name="date" id="date">

            <br>

            <!-- Ova vremena moraju imat constraint na to da se samo moze napravit termin ako je unutar radnog vremena taj dan. -->
            <label for="centerappointmentstart">Početak</label>
            <input type="time" name="starttime" id="starttime" required>

            <br>

            <label for="centerappointmentend">Kraj</label>
            <input type="time" name="endtime" id="endtime" required>

            <br>

            <label for="capacity">Kapacitet</label>
            <input type="number" name="capacity" id="capacity" required>

            <br>

            <button type="submit" onsubmit={handleSubmit}>Dodaj termin</button>
        </form>

        {#snippet close()}
            <X size={24} />
        {/snippet}
    </Dialog>

    <ul class="available-appointments">
        {#each data.appointments as appointment (appointment)}
            <li class="appointment-slot">
                <p class="appointment-center-name">{data.staffData.centername}</p>
                <p class="appointment-interval">
                    {numberToDow(appointment.centerappointmentstart.getDay())},
                    {appointment.centerappointmentstart.toLocaleDateString("hr-HR")}, od
                    {appointment.centerappointmentstart.toLocaleTimeString("hr-HR")} do
                    {appointment.centerappointmentend.toLocaleTimeString("hr-HR")}
                </p>
                <p class="appointment-capacity">{appointment.capacity} mjesta</p>

                    <Dialog>
                        {#snippet trigger()}
                            Detalji
                        {/snippet}

                        {#snippet title()}
                            <h2>Detalji o terminu</h2>
                        {/snippet}

                        {#snippet description()}
                            <div></div>
                        {/snippet}

                        {#each data.appointmentReservations as appRes (appRes)}
                            {#if String(appointment.centerappointmentstart) === String(appRes.appointmentstart)}
                                <div class="appointment-reservation">
                                    <p>{appRes.firstname} {appRes.lastname}</p>
                                    <p>status: {tlAppointmentStatus(appRes.appointmentstatus)}</p>
                                    <div class="button-row">
                                        <button>Pristupio</button>
                                        <button>Pregled započeo</button>
                                        <button>Darivanje započelo</button>
                                        <button>Darivanje završeno</button>
                                    </div>
                                </div>
                            {/if}
                        {/each}

                        {#snippet close()}
                            <X size={24} />
                        {/snippet}
                    </Dialog>
            </li>
        {/each}
    </ul>
</section>



<style>
    .center-overview {
        margin-block-start: 4rem;
    }

    .center-name {
        margin-block-end: 2rem;
        font-size: 3rem;
    }

    .center-worktimes {
        padding-inline-start: 0;
        list-style-type: none;
        display: grid;
        grid-template-columns: 1fr;
        gap: 0.5rem;
        
        .center-worktime-card {
            border: 2px solid var(--color-accent);
            border-radius: 12px;
            padding: 0.5rem 1.75rem;
            background-color: #fff8f8;

            .weekday {
                font-weight: 700;
            }
        }
    }

    @media (min-width: 768px) {
        .center-worktimes {
            grid-template-columns: repeat(3, 1fr);
        }
    }

    .available-appointments {
        padding-inline-start: 0;
        list-style-type: none;

        .appointment-slot {
            border: 2px solid var(--color-foreground);
            border-radius: 12px;
            padding: 0.5rem 1.75rem;
            background-color: #fff8f8;
            margin-block-end: 0.5rem;
        }

        .appointment-center-name {
            font-weight: 700;
        }
    }

    .appointment-reservation {
        border-top: 1px solid #424242;
    }
</style>