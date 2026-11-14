<script lang="ts">
    import type { Snippet } from "svelte";
    import { Dialog, type WithoutChild } from "bits-ui";

    type Props = Dialog.RootProps & {
        trigger: Snippet;
        title: Snippet;
        description: Snippet;
        close: Snippet;
        contentProps?: WithoutChild<Dialog.ContentProps>;
    };

    let {
        open = $bindable(false),
        children,
        trigger,
        contentProps,
        title,
        description,
        close,
        ...restProps
    }: Props = $props();
</script>

<Dialog.Root bind:open {...restProps}>
    <Dialog.Trigger class="dialog-trigger">
        {@render trigger()}
    </Dialog.Trigger>
    <Dialog.Portal>
        <Dialog.Overlay class="dialog-overlay" />
        <Dialog.Content class="dialog-content" {...contentProps}>
            <Dialog.Title>
                {@render title()}
            </Dialog.Title>
            <Dialog.Description class="dialog-description">
                {@render description()}
            </Dialog.Description>
                {@render children?.()}
            <Dialog.Close class="dialog-close">{@render close()}</Dialog.Close>
        </Dialog.Content>
    </Dialog.Portal>
</Dialog.Root>