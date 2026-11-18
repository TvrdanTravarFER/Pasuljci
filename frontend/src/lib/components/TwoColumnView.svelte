<script lang="ts">
    import type { Snippet } from "svelte"

    type Props = {
        leftColumn: Snippet;
        rightColumn: Snippet;
    };

    let { leftColumn, rightColumn, ...rest }: Props = $props()
</script>

<div class="two-column-view" {...rest}>
    <div class="left-column">
        {@render leftColumn()}
    </div>
    <div class="right-column">
        {@render rightColumn()}
    </div>
</div>

<style>
    .two-column-view {
        display: flex;
        flex-direction: column;
        gap: 1.5rem;
    }

    .left-column {
        flex: 1;
    }

    .right-column {
        flex: 1;
    }

    @media (min-width: 768px) {
        .two-column-view {
            flex-direction: row;
            gap: 3rem;
        }
    }
</style>