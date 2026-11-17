<script lang="ts">
    import AdminView from "#lib/views/home/AdminView.svelte";
    import DonorView from "#lib/views/home/DonorView.svelte";
    import OrganizerView from "#lib/views/home/OrganizerView.svelte";
    import StaffView from "#lib/views/home/StaffView.svelte";

    import type { PageProps } from "./$types"

    let { data }: PageProps = $props()

    const userRole = (() => data)().userRole
</script>

{#if userRole == "ADMIN"}
    <AdminView />
{:else if userRole == "DONOR"}
    <DonorView activeAppointment={data.activeAppointment} />
{:else if userRole == "ORGANIZER"}
    <OrganizerView />
{:else if userRole == "STAFF"}
    <StaffView />
{:else}
    <div>404</div>
{/if}
