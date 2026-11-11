<script>
    import Menu from '@lucide/svelte/icons/menu';
	import X from "@lucide/svelte/icons/x"
	import { Collapsible } from 'bits-ui';
	import Dialog from "#lib/components/Dialog.svelte"
</script>

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
						<h2 class="dialog-title">Prijavite se</h2>
					{/snippet}

					{#snippet description()}
						Prijavom u našu platformu moći ćete detaljno pratiti povijest darivanja, prijavljivati se za nadolazeće akcije i još mnogo toga. Potpuno je besplatno.
					{/snippet}

					<form action="" class="platform-login-form" >
						<input type="email" placeholder="Adresa e-pošte">
						<br>
						<input type="password" placeholder="Lozinka">
						<br>
						<button type="submit">Prijava</button>
					</form>

					<p class="signup-hint">Nemate račun? Prijavite se odmah</p>

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

    @media (min-width: 1024px) {
        .navigation-bar-toggle {
			display: none;
		}

		h1 {
			margin-inline-start: 0 !important;
		}
    }

	.platform-login-form {
		margin-block-start: 2rem;
		text-align: center;

		input {
			border: 2px solid color-mix(in srgb, var(--color-background) 90%, #000 10%);
			border-radius: 4px;
			padding: 0.25rem 0.5rem;
			outline-color: var(--color-accent);
			-webkit-outline-color: var(--color-accent);
		}

		input[type="email"] {
			margin-block-end: 0.25rem;
		}

		input[type="password"] {
			margin-block-end: 1rem;
		}

		button {
			background-color: var(--color-accent);
			color: var(--color-background);
			padding: 0.6rem 1.5rem;
			margin-block-start: 0.15rem;
			border: 3px solid var(--color-accent);
			border-radius: 36px;
			font-weight: 700;
			transition: all 150ms ease-in-out;
		}

		button:hover {
			color: var(--color-accent);
			background-color: var(--color-background);
		}

	}
	.signup-hint {
		text-align: center;
		font-size: 14px;
		margin-block-start: 1.5rem;
	}
</style>