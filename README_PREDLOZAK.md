<!--
PREDLOŽAK — README završnog projekta na kolegiju Programsko inženjerstvo FER-a.
Kopirajte ovu datoteku u README.md u repozitoriju svojeg projekta. Zamijenite
svaku uputu u uglatim zagradama činjenicama o svojem projektu, uklonite sve
komentare s uputama i ogledne <details> blokove te prije predaje izbrišite ovu
napomenu. Nemojte kopirati nastavni primjer kao rezultat svojeg projekta.
-->

# KrvnaVeza

Web platforma koja umrežava i povezuje darivatelje krvi s transfuzijskim centrima i organizatorima akcija darivanja krvi.

**Projekt na kolegiju:** Ovu je aplikaciju razvio studentski tim u sklopu kolegija [Programsko inženjerstvo](https://www.fer.unizg.hr/predmet/proinz) na Fakultetu elektrotehnike i računarstva Sveučilišta u Zagrebu (FER).

## Opis aplikacije

Bolnicama je krv svakodnevno potrebna za različite operacije i liječenja pacijenata. Transfuzijski centri, kao i organizatori akcija darivanja krvi, svaki na svoje načine obavještavaju darivatelje, dok darivatelji često samostalno moraju pratiti gdje i kada se akcije darivanja održavaju. Aplikacija KrvnaVeza namjerava povezati sve ove sudionike i omogućiti im neometanu suradnju preko jedinstvene platforme.

**Ključne funkcionalnosti**

- Pregled karte transfuzijskih centara na odabranom području i stanja zaliha krvi u njima po krvnim grupama.
- Registriranim darivateljima je dostupan prikaz dostupnih mjesta i termina, rezerviranje termina te praćenje povijesti darivanja.
- Transfuzijskim centrima je omogućeno praćenje zaliha krvi i slanje poziva konkretnim darivateljima u slučaju nedostatka određene krvne grupe.

Za potpuni opseg, zahtjeve, oblikovanje i dokaze ispitivanja pogledajte [docs](docs/Home.md).

<a id="deployment"></a>
## Postavljanje (engl. deployment)

<!-- UPUTA: Ovaj odjeljak opisuje već postavljenu aplikaciju koju se  može otvoriti i isprobati bez lokalnog pokretanja izvornog koda.
Za Iteraciju 1 i Završni pregled navedite konkretne podatke. Provjerite vodi li URL na javno dostupnu pokrenutu aplikaciju i radi li navedeni tijek uporabe. Nikada ne objavljujte lozinke ni pristupne tokene. -->

**Javno dostupna aplikacija:** https://krvnaveza.onrender.com/

**Isprobajte:** Provjerite na glavnoj stranici stanje zaliha krvi po krvnim grupama, kao i lokacije transfuzijskih centara diljem Hrvatske. Ako se prijavite, moći ćete i pretraživati te zakazivati se za termine darivanja koje su otvorili centri.
**Demonstracija:** Bez korisničkog računa moguće je vidjeti kartu s transfuzijskim centrima diljem Hrvatske kao i globalno stanje zaliha krvi po grupama. Za ostale značajke potrebna je prijava u aplikaciju, koju je moguće ostvariti koristeći već postojeći Google račun.

**Ograničenja:** [Kratko navedite ograničenja postavljene inačice ili postavite poveznicu na jedinstveno mjerodavno mjesto u dokumentaciji s poznatim problemima.]

[Vodič za postavljanje i pokretanje](docs/6-Postavljanje-instalacija-i-konfiguracija.md) opisuje smještaj aplikacije (engl. hosting) koji tim stvarno koristi. Tim može koristiti Render ili drugu platformu koju ste dogovorili s asistentom/demonstratorom.

<a id="quick-start"></a>
## Brzi početak / instalacija

<!-- Obavezno od Demo 1 nadalje. Zamijenite mjesta za unos naredbama koje je drugi član tima uspješno provjerio nakon čistog kloniranja repozitorija. Detaljne upute za administraciju držite u projektnoj dokumentaciji u direktoriju docs/. -->

**Preduvjeti:**
- Izvršno okruženje: Node.js verzija 26
- Upravitelj paketa: npm verzija 11.21
- Baza podataka: PostgreSQL 18

Lokalno pokretanje:

```bash
git clone https://github.com/TvrdanTravarFER/Pasuljci

# postavljanje lokalne baze podataka:
cd Pasuljci/scripts
./seed-database.sh

# instaliranje dependency paketa i priprema baze za OAuth2:
cd ../frontend
npm install
npx @better-auth/cli migrate

# izgradnja stranice
npm run build
node build/index.js
```

**Adresa:** http://localhost:5173

**Konfiguracija:** [varijable okruženja]

Uspješno postavljanje trebalo bi otvoriti početnu stranicu bez grešaka. Za detaljno postavljanje pogledajte [projektnu dokumentaciju](docs/Home.md).

## Tehnologije

Navedite nekoliko tehnologija i vanjskih usluga koje bitno određuju kako vaša aplikacija radi. Za svaku navedite njezinu konkretnu ulogu i jedan razlog ili ograničenje važno za projekt. Alat za razvoj ili ispitivanje uključite samo ako je njegova uloga važna za razumijevanje načina na koji je projekt izrađen ili provjeren. Nemojte prepisivati popis ovisnosti (engl. dependencies), statistiku programskih jezika ni popis razvojnih okruženja i komunikacijskih alata. Tablicu provjerite prema stvarnom repozitoriju i pokrenutoj aplikaciji; potrebne verzije i konfiguraciju dokumentirajte u vodiču za instalaciju.

| Tehnologija ili alat | Uloga u ovom projektu                                       | Razlog uporabe ili važno ograničenje |
| -------------------- | ----------------------------------------------------------- | --- |
| Java & Spring Boot   | Glavni programski jezik i okvir za razvoj backend dijela stranice | Prethodno iskustvo tima s Javom|
| Svelte               | Oblikovanje korisničkog sučelja i frontend dijela stranice                           | Prethodno iskustvo s alatom |
| SvelteKit            | Prototipiranje backend dijela stranice i inicijalno testiranje pristupa prema bazi                      | Prethodno iskustvo i lagana integracija |
| PostgreSQL           | Pohrana podataka prijave i podataka koji se koriste u samim funkcionalnostima stranice | Prethodno iskustvo a alatom |
| OpenStreetMap        | Prikaz karte transfuzijskih centara i akcija darivanja krvi na početnoj stranici | Brza i lagana integracija u aplikaciju |

<a id="team-members"></a>
## Članovi tima

| Član | Glavna uloga ili doprinos |
| --- | --- |
| Kristijan Kralj | Voditelj tima, razvoj poslužiteljskog dijela (backend) |
| Lucija Cvetković | Projektna dokumentacija, razvoj poslužiteljskog dijela (backend) |
| Karlo Mijić | Razvoj poslužiteljskog dijela (backend) |
| Marko Mišković | Razvoj korisničkog sučelja (frontend) |
| Sven Rešetar | Razvoj poslužiteljskog dijela (backend) |
| Noah Sitar | Razvoj poslužiteljskog dijela (backend) |
| Marko Vrančić | Projektna dokumentacija, razvoj baze podataka |

## Doprinos projektu

[Ako je primjenjivo, ukratko opišite kako se evidentiraju zadaci i nedostaci te kako se pregledavaju promjene koda i gdje se nalazi aktualna evidencija zadataka. Ako tim održava `CONTRIBUTING.md`, ovdje postavite poveznicu umjesto ponavljanja pravila. Nemojte stvarati tu datoteku samo zato da biste popunili ovaj odjeljak.]

<a id="license"></a>

**Status licence:** [Navedite licencu i postavite poveznicu na projektnu datoteku `LICENSE` ili izričito navedite da nije dodijeljena licenca koja dopušta javnu ponovnu uporabu. Namjeravani opseg provjerite s timom; nemojte prepisivati licencu predloška ove dokumentacije kao licencu svoje aplikacije.]

Dokumentacijski predložak kolegija sadrži otvorene obrazovne sadržaje (engl. Open Educational Resources, OER) i licenciran je licencom Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International. Ta licenca **ne određuje automatski licencu studentske aplikacije**; tim treba jasno navesti licencu svojeg projekta u prethodnom retku i datoteci `LICENSE`, ako je primjenjivo.

Kod trećih strana, skupovi podataka, slike, ikone i drugi sadržaji zadržavaju vlastite licence i uvjete uporabe. [Po potrebi postavite poveznicu na zahvale i izvore.]

<a id="ai-usage"></a>
## Uporaba umjetne inteligencije [![AI Usage: Disclosed](https://img.shields.io/badge/AI%20Usage-Disclosed-blue.svg)](https://www.fer.unizg.hr/_download/repository/Policy%20on%20the%20appropriate%20use%20of%20artificial%20intelligence%20at%20the%20faculty%20of%20electrical%20engineering%20and%20computing%5B1%5D.pdf)

Ovaj projekt slijedi FER-ova [pravila o primjerenoj uporabi umjetne inteligencije](https://www.fer.unizg.hr/_download/repository/Policy%20on%20the%20appropriate%20use%20of%20artificial%20intelligence%20at%20the%20faculty%20of%20electrical%20engineering%20and%20computing%5B1%5D.pdf).

**Izjava:** [Navedite jesu li korišteni alati umjetne inteligencije. Ako jesu, navedite alate i njihove glavne namjene te objasnite kako je tim pregledao nastali kod ili tekst i kako može objasniti vlastiti doprinos. Ako nisu korišteni, to jasno navedite. U alate umjetne inteligencije nemojte unositi privatne podatke, pristupne podatke ni tuđe zaštićene materijale.]

[Ako je za ovaj projekt potreban detaljniji zapis uporabe umjetne inteligencije, postavite poveznicu na njegovo jedinstveno mjerodavno mjesto u dokumentaciji.]

<a id="code-of-conduct-and-support"></a>
## Kodeks ponašanja i podrška [![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](CODE_OF_CONDUCT.md)

Od članova tima očekuje se pridržavanje **Kodeksa ponašanja studenata** Fakulteta elektrotehnike i računarstva Sveučilišta u Zagrebu, pravila timskog rada na kolegiju i [Etičkog kodeksa IEEE-a](https://www.ieee.org/about/corporate/governance/p7-8.html).

`CODE_OF_CONDUCT.md` projekta se može pronaći [ovdje](https://github.com/TvrdanTravarFER/Pasuljci/blob/main/CODE_OF_CONDUCT.md).

---

Struktura dokumentacije prilagođena je predlošku kolegija Programsko inženjerstvo FER-a autora [Vlade Sruka](https://www.fer.unizg.hr/vlado.sruk), licenciranom pod [CC BY-NC-SA 4.0](https://creativecommons.org/licenses/by-nc-sa/4.0/). Aplikaciju i njezin sadržaj izradio je gore navedeni studentski tim.
