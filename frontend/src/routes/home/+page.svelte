<script>
    import DarivateljView from "#lib/views/home/DarivateljView.svelte";
    import DjelatnikView from "#lib/views/home/DjelatnikView.svelte";
    import AdministratorView from "#lib/views/home/AdministratorView.svelte";
    import VodjaAkcijaView from "#lib/views/home/VodjaAkcijaView.svelte";

    let userRole = "djelatnik"
</script>

{#if userRole == "darivatelj"}
<DarivateljView />
{:else if userRole == "djelatnik"}
<DjelatnikView />
{:else if userRole == "administrator"}
<AdministratorView />
{:else if userRole == "vodjaAkcija"}
<VodjaAkcijaView />
{:else}
<div></div>
{/if}
