<script lang="ts">
	import "../global.css"
	import favicon from '#lib/assets/favicon.svg';
	import type { LayoutProps } from './$types';

	import Menu from '@lucide/svelte/icons/menu';
	import X from "@lucide/svelte/icons/x"
	import { Collapsible } from 'bits-ui';
	import Dialog from "#lib/components/Dialog.svelte"

	let { children }: LayoutProps = $props();
</script>

<svelte:head>
	<link rel="icon" href={favicon} />
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin="anonymous">
	<link href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Figtree:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
</svelte:head>

<header>
	<nav class="main-navigation">
		<Collapsible.Root>
			<div class="navigation-bar">
				<div class="navigation-bar-left">
					<Collapsible.Trigger>
						<div class="navigation-bar-toggle">
							<Menu size={24} />
						</div>
					</Collapsible.Trigger>
					<h1>KrvnaVeza</h1>
				</div>
				<!-- TODO ovo bi trebalo otvorit modal view koji daje opciju za OAuth2 login. -->
				<Dialog buttonText="Prijava">
					{#snippet title()}
						<h2>Prijavite se</h2>
					{/snippet}

					{#snippet description()}
						Opis
					{/snippet}

					<form action="">
						<input type="text">
						<button type="submit">Prijava</button>
					</form>

					{#snippet close()}
						<X size={24} />
					{/snippet}
				</Dialog>
			</div>
			<Collapsible.Content>
			<!-- neka na mobitelu bude hamburger menu koji otvara opcije, ako ih bude trebalo. na pc je standardni navbar -->
				<ul class="navigation-links">
					<li>item1</li>
					<li>item2</li>
					<li>item3</li>
				</ul>
			</Collapsible.Content>
		</Collapsible.Root>
	</nav>
</header>
<main>
	{@render children()}
</main>

<style>
	header {
		position: sticky;
		top: 0;
		background-color: #ffffff00;
		backdrop-filter: blur(18px);
	}
	.main-navigation {
		margin-inline: 4dvw;
		padding-block-start: 0.375rem;

		.navigation-bar {
			display: flex;
			justify-content: space-between;

			.navigation-bar-left {
				display: flex;
				gap: 15%;

				h1 {
					margin-inline-start: 1rem;
					font-weight: 700;
				}
			}
		}
	}

	main {
		margin-inline: 2rem;
	}

	@media (min-width: 1024px) {
		.navigation-bar-toggle {
			display: none;
		}

		h1 {
			margin-inline-start: 0 !important;
		}

        main {
            margin-inline: 16vw;
            margin-block: 15vh 25vh;
        }
	}
</style>
