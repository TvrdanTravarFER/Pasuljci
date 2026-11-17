<script lang="ts">
    import type { LayoutProps } from './$types';

    let { children }: LayoutProps = $props()
</script>


<main>
    {@render children()}
</main>

<style>
    main {
        max-width: 65%;
        margin: 0 auto;
    }
</style>