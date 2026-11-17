# sv

Sve što Vam treba za izgradnju Svelte projekta, powered by [`sv`](https://github.com/sveltejs/cli).

## Stvaranje projekta

Ako vidite ovo, vjerovatno ste već obavili ovaj korak. Čestitamo!

```sh
# stvori novi projekt
npx sv create my-app
```

Kako biste reproducirali ovaj projekt s istim konfiguracijama, pokrenite:

```sh
# rekreiraj ovaj projekt
npx sv@1.3.0 create --template minimal --types ts --add prettier eslint vitest="usages:unit,component" --install npm .
```

## Dodavanje značajki

Dodajte značajke svojem projektu koristeći `sv add`:

```sh
npx sv add
```

Na primjer, ako biste htjeli dodati Tailwind CSS:

```sh
npx sv add tailwindcss
```

## Razvoj

Nakon što ste stvorili projekt i instalirali ovisnosne pakete (_dependencies_) koristeći `npm install` (ili `pnpm install` ili `yarn`), možete pokrenuti razvojni poslužitelj (_development server_):

```sh
npm run dev

# ili, pokreni poslužitelj i otvori aplikaciju u novoj kartici preglednika
npm run dev -- --open
```

## Izgradnja

Kako biste izgradili produkcijsku verziju svoje aplikacije, koristite:

```sh
npm run build
```

Produkcijski build možete pretpregledati koristeći `npm run preview`

> Kako biste poslužili svoju aplikaciju, možda ćete trebati instalirati [adapter](https://svelte.dev/docs/kit/adapters) za okruženje koje ste odabrali.

## Korištene tehnologije

Ova web-aplikacija koristi sljedeće tehnologije, pakete i komponente:

- **Svelte:** glavno okruženje za izgradnju korisničkog sučelja, zasnovano na komponentama
- **SvelteKit:** temelj koji pruža usmjeravanje (_routing_), iscrtavanje (_rendering_). Zajedno s _Node.js_ adapterom, omogućava postavljanje na Render.
- **bits.ui:** okvir za komponente korisničkog sučelja

Produkcijski alati koji se koriste pri programiranju:

- **Vitest:** jedinični testovi i testovi komponenata
- **Prettier:** formatiranje koda
- **ESLint:** statična analiza koda i linting

```
npx @better-auth/cli migrate
```
