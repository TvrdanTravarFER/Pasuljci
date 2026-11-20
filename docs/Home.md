# KrvnaVeza — Projektna dokumentacija

**Kolegij:** [Programsko inženjerstvo](https://www.fer.unizg.hr/predmet/proinz), Fakultet elektrotehnike i računarstva Sveučilišta u Zagrebu  
**Akademska godina:** [2026./2027.]  
**Tim:** _Pasuljci_  
**Nastavnik:** Vlado Sruk  
**Asistent / demonstrator:** Saša Kendjel (asistent), Mirko Bušić (demonstrator)

## Pregled projekta

KrvnaVeza je web aplikacija namijenjena povezivanju darivatelja krvi i transfuzijskih centara. Ciljani korisnici su darivatelji krvi, djelatnici transfuzijskih centara i voditelji terenskih akcija. Glavna svrha aplikacije je olakšati organizaciju darivanja krvi kroz pregled zaliha po krvnim grupama, informiranje o akcijama darivanja, upravljanje terminima i evidenciju darivanja krvi.

**Aplikacija i osnovno postavljanje:** [README repozitorija](../README_PREDLOZAK.md#quick-start)  
**Javno dostupna aplikacija:** pogledajte [README – Postavljanje](../README_PREDLOZAK.md#deployment)

## Članovi tima i uloge

Ažurirani popis članova, njihovih GitHub profila i glavnih uloga ili doprinosa nalazi se u [README-u repozitoriju](../README_PREDLOZAK.md#team-members).

## Kazalo dokumentacije

| Stranica | Svrha |
| --- | --- |
| [1. Opseg projekta](1-Projekt.md) | Problem, cilj i granice projekta |
| [2. Analiza zahtjeva](2-Analiza-zahtjeva.md) | Zahtjevi, ograničenja i sljedivost |
| [3. Obrasci uporabe](3-Obrasci-uporabe.md) | Akteri i reprezentativne interakcije korisnika |
| [4. Arhitektura i oblikovanje](4-Arhitektura-i-oblikovanje.md) | Glavne odluke oblikovanja, struktura, podaci i ponašanje |
| [5. Ispitivanje](5-Ispitivanje.md) | Pristup ispitivanju, stvarni rezultati i poznati nedostaci |
| [6. Postavljanje, instalacija i konfiguracija](6-Postavljanje-instalacija-i-konfiguracija.md) | Lokalna instalacija, konfiguracija, javno postavljanje i administracija |
| [7. Zaključak i budući rad](7-Zakljucak-i-buduci-rad.md) | Zaključak i moguća daljnja poboljšanja |
| [A. Pregled aktivnosti grupe](A-Pregled-aktivnosti-grupe.md) | Aktivnosti tima, doprinosi i izazovi |

## Ključne točke projekta

| Kontrolna točka | Demonstracija | Video |
| --- | --- | --- |
| Iteracija 1 | [Poveznica na demonstraciju ili inačicu projekta] | [Poveznica na video Demo 1, ako je snimljen] |
| Završni pregled | [Poveznica na završnu inačicu projekta] | [Poveznica na video završne demonstracije, ako je snimljen] |

Dokumentacija se kontinuirano ažurira kako se aplikacija razvija.

## Organizacija projekta i razvojni proces

**Način rada:** Github issues za određene dulje zadatke, Whatsapp za organizaciju tima i manjih zadataka te rad u paru.

**Praćenje trenutačnih zadataka i nedostataka:** [Issues](https://github.com/TvrdanTravarFER/Pasuljci/issues)

## Pravila dokumentacije

Dokumentacija se sastoji od međusobno povezanih Markdown stranica. Obavezni UML dijagrami izrađuju se u PlantUML-u, uz PlantUML izvor u dokumentaciji sačuvajte i čitljivu sliku. Primjeri u ovom predlošku kolegija pokazuju kako nešto dokumentirati; nisu dio rezultata studentskog projekta.

## Akademski integritet, ponašanje i licenciranje

README repozitorija sadrži projektnu [izjavu o uporabi umjetne inteligencije](../README_PREDLOZAK.md#ai-usage), [informacije o kodeksu ponašanja i podršci](../README_PREDLOZAK.md#code-of-conduct-and-support) te [status licence ili ponovne uporabe](../README_PREDLOZAK.md#license). Pristupni podaci i druge tajne nikada se ne objavljuju u dokumentaciji niti dijele u običnim porukama.

## Izjava o uporabi umjetne inteligencije

<!-- UPUTA: Ovo je detaljan zapis uporabe umjetne inteligencije; README sadrži kratak sažetak i poveznicu na ovaj odjeljak. Unesite samo ono što se stvarno dogodilo i uklonite neupotrijebljene retke. Ako nije korišten nijedan alat generativne umjetne inteligencije, zamijenite cijeli odjeljak jednom rečenicom koja to jasno navodi. Svaki redak mora upućivati na stranicu, datoteku, zahtjev za uključivanje promjena (PR) ili zadatak. Nikada ne unosite pristupne podatke, osobne podatke ni tuđi zaštićeni sadržaj u alate umjetne inteligencije. -->

Kratak sažetak: [README – Uporaba umjetne inteligencije](../README_PREDLOZAK.md#ai-usage). Ovaj odjeljak bilježi koji su alati korišteni, za koju dokumentaciju ili kod te kako je tim provjerio rezultat. Slijedi FER-ova [pravila o primjerenoj uporabi umjetne inteligencije](https://www.fer.unizg.hr/_download/repository/Policy%20on%20the%20appropriate%20use%20of%20artificial%20intelligence%20at%20the%20faculty%20of%20electrical%20engineering%20and%20computing%5B1%5D.pdf).

### Korišteni alati

| Alat | Inačica ili model | Koristio/la | Glavna svrha |
| --- | --- | --- | --- |
| OpenAI Codex | - | SR | Revizija ER modela, izrada i provjera UML dijagrama ponašanja |

### Opseg doprinosa umjetne inteligencije

| Stranica, datoteka ili artefakt | Vrsta pomoći | Što je alat proizveo | Što je tim zadao i promijenio | Lokacija |
| --- | --- | --- | --- | --- |
| ER-mode.md | Revizija postojećeg sadržaja | Alat je pomogau u reviziji ER modela | Postojeći ER model je bio zadan kao osnova za reviziju. Promjenjene su manje greške alata i greške u inicijalnom ER modelu | ER-model.md |
| UML-ponasanje.md | Izrada i provjera dijagrama | UML dijagram ponašanja i provjeru | Za osnovu su korišteni projektni zahtjevi i dokumentacija. Promjenjene su greške i krive informacije koje je generirao alat te su promjene provjerene uz pomoć alata | UML-ponasanje.md |
| Pull request | Generiranje teksta | Opis pull requesta | Zadano je što je mijenjano i po tome je generiran opis PR-a | [PR #38](https://github.com/TvrdanTravarFER/Pasuljci/pull/38) |

### Provjera generiranog sadržaja

| Provjera | Kako je provedena | Dokaz |
| --- | --- | --- |
| Činjenična točnost | Pregled samog programera na tom commit-u te pregled reviewera u PR-u | SR, KM, [PR #38](https://github.com/TvrdanTravarFER/Pasuljci/pull/38) |
| Nema tvrdnji o neostvarenim funkcionalnostima | [Provjereno je da opisane funkcionalnosti, ispitivanja i rezultati stvarno postoje; planirani rad označen je kao planiran] | [Usporedba s odjeljkom 5.4 (Ispitivanje) i odjeljkom 7.1 (Zaključak)] |
| Dosljednost s drugim stranicama | [Nazivi, ID-ovi i dijagrami provjereni su prema Analizi zahtjeva, Obrascima uporabe, Arhitekturi i oblikovanju te Ispitivanju] | [Kontrolni popis ili bilješka pregleda] |
| Izvori dijagrama | [Generirani PlantUML je iscrtan, ispravljen i odgovara implementiranom sustavu] | [`.puml` datoteka i izrađena slika] |
| Povjerljivi sadržaj | Commit i PR su pregledali minimalno 2 osobe | - |

### Pogreške pronađene u izlazu umjetne inteligencije

| Problem | Kako je pronađen | Rješenje |
| --- | --- | --- |
| Krive veze u ER modelu  | Problem pronađen pregledom modificiranog ER modela | - |
| Nepotpunost sekvencijskog dijagrama | Ručnom provjerom te ponovnom provjerom uz pomoć Codex-a | - |

<!-- UPUTA: Zabilježite stvarne ispravke ili napišite „Nisu utvrđene”. Općenita tvrdnja poput „sav tekst je pregledan” nije dokaz; dokaz su prethodne tablice. Tim mora moći objasniti svaku rečenicu koju objavi. -->
