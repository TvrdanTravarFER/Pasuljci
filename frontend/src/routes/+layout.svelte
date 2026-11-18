<script lang="ts">
	import "../styles/global.css"
	import favicon from '#lib/assets/favicon.svg';

	import { browser } from "$app/env"
    import { setWorkerUrl } from "maplibre-gl"
    import workerUrl from "maplibre-gl/dist/maplibre-gl-worker.mjs?url"

	import type { LayoutProps } from './$types';

	if (browser) setWorkerUrl(workerUrl)

	let { children }: LayoutProps = $props();
</script>

<svelte:head>
	<link rel="icon" href={favicon} />
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Figtree:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
</svelte:head>

{@render children()}

<style></style>
