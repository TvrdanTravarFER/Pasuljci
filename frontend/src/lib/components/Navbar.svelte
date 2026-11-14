<script>
    import Menu from '@lucide/svelte/icons/menu';
	import X from "@lucide/svelte/icons/x"
	import { Collapsible } from 'bits-ui';
	import Dialog from "#lib/components/Dialog.svelte"
	import { authClient	} from "#lib/auth-client.ts"
	import { resolve } from "$app/paths"

	const session = authClient.useSession()

	const googleSignIn = async () => {
		await authClient.signIn.social({
			provider: "google",
			callbackURL: "/home",
			errorCallbackURL: "/",
		})
	}
</script>

<header>
	<nav class="main-navigation">
		<Collapsible.Root>
			<div class="navigation-bar">
				<div class="navigation-bar-left">
					<Collapsible.Trigger>
						<div class="navigation-bar-toggle">
							<Menu size={24} style="color: var(--color-foreground);" />
						</div>
					</Collapsible.Trigger>
					<h1>KrvnaVeza</h1>
				</div>
				{#if $session.data}
					<button class="user-logged-in">
						<a href={resolve("/home")}>{$session.data.user.name}</a>
						<span class="arrow-symbol">→</span>
					</button>
				{:else}
					<Dialog>
						{#snippet trigger()}
							Prijava
						{/snippet}

						{#snippet title()}
							<h2 class="dialog-title">Prijavite se</h2>
						{/snippet}

						{#snippet description()}
							Prijavom u našu platformu moći ćete detaljno pratiti povijest darivanja, prijavljivati se za nadolazeće akcije i još mnogo toga. Potpuno je besplatno.
						{/snippet}

						<form action="" class="platform-login-form" >
							<button onclick={googleSignIn}>Prijava uz Google</button>
						</form>

						{#snippet close()}
							<X size={24} />
						{/snippet}
					</Dialog>
				{/if}
			</div>
			<Collapsible.Content>
			<!-- todo: neka na mobitelu bude hamburger menu koji otvara opcije, ako ih bude trebalo. na pc je standardni navbar -->
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
		z-index: 1000;
	}
	.main-navigation {
		margin-inline: 4dvw;
		padding-block-start: 0.375rem;

		.navigation-bar {
			display: flex;
			justify-content: space-between;

			.navigation-bar-left {
				display: flex;
				gap: 3vw;

				h1 {
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
		margin-block-start: 1.5rem;
		text-align: center;

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

	.arrow-symbol {
		margin-inline-start: 1rem;
	}
</style>