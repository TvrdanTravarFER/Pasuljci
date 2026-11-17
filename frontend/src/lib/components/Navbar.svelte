
<script>
	import { resolve } from "$app/paths"
	import { authClient	} from "#lib/auth-client.ts"
	// import { Collapsible } from 'bits-ui';
    import Menu from '@lucide/svelte/icons/menu';
	import X from "@lucide/svelte/icons/x"
	import Dialog from "#lib/components/Dialog.svelte"
	import "../../styles/navbar.css"

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
		<!-- <Collapsible.Root> -->
			<div class="navigation-bar">
				<div class="navigation-bar-left">
					<!-- <Collapsible.Trigger> -->
						<div class="navigation-bar-toggle">
							<Menu size={24} style="color: var(--color-foreground);" />
						</div>
					<!-- </Collapsible.Trigger> -->
					<h1 class="page-title">KrvnaVeza</h1>
				</div>
				{#if $session.data}
					<button class="user-logged-in">
						<a class="button-primary" href={resolve("/home")}>
							{$session.data.user.name}
							<span class="arrow-symbol">→</span>
						</a>
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
						<div class="google-sign-in-button">
							<button class="button-primary" onclick={googleSignIn}>Prijava uz Google</button>
						</div>

						{#snippet close()}
							<X size={24} />
						{/snippet}
					</Dialog>
				{/if}
			</div>
			<!-- todo: neka na mobitelu bude hamburger menu koji otvara opcije, ako ih bude trebalo. na pc je standardni navbar -->
			<!-- <Collapsible.Content>
				<ul class="navigation-links">
					<li>item1</li>
					<li>item2</li>
					<li>item3</li>
				</ul>
			</Collapsible.Content>
		</Collapsible.Root> -->
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

				.page-title {
					font-weight: 700;
				}
			}
		}
	}

    @media (min-width: 1024px) {
        .navigation-bar-toggle {
			display: none;
		}

		.page-title {
			margin-inline-start: 0 !important;
		}
    }

	.google-sign-in-button {
		margin-block-start: 1.5rem;
		text-align: center;
	}

	.arrow-symbol {
		margin-inline-start: 1rem;
	}
</style>