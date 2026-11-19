# 2. Analiza zahtjeva

Zahtjevi u ovom poglavlju izvedeni su iz opsega projekta i projektnog zadatka. Svaki zahtjev ima identifikator, prioritet, izvor i način provjere. Zahtjevi se tijekom razvoja mogu mijenjati, a značajne promjene bilježe se u odjeljku 2.5.

**Ljestvica prioriteta:** 
- *Visok* - bez toga aplikacija ne ostvaruje glavni cilj 
- *Srednji* - važno za potpunu uporabu, ali glavni dio aplikacije radi bez njih
- *Nizak* - korisno proširenje koje se prvo izostavlja ako ponestane vremena

## 2.1 Funkcijski zahtjevi

**Cilj:** Navesti što aplikacija mora omogućiti korisnicima, odakle pojedina mogućnost proizlazi i koji se opažljivi rezultat smatra prihvaćanjem.

| ID | Opis | Prioritet | Izvor | Kriteriji prihvaćanja |
| --- | --- | --- | --- | --- |
| F-001 | Sustav neregistriranim korisnicima omogućuje pregled karte transfuzijskih centara i najavljenih akcija te javni prikaz stanja zaliha po krvnim grupama (kritično, nisko, dovoljno). | Visok | Odobreni zadatak | Posjetitelj bez prijave vidi centre, akcije na karti i razinu zalihe za svaku krvnu grupu. Ostale funkcije traže prijavu. |
| F-002 | Sustav omogućuje registraciju i prijavu putem OAuth 2.0 protokola uz valjanu adresu e-pošte, sve funkcije osim F-001 dostupne su samo prijavljenim korisnicima. | Visok | Odobreni zadatak | Uspješna prijava otvara funkcije prema ulozi korisnika, a pokušaj pristupa zaštićenoj funkciji bez prijave preusmjerava na prijavu. Odustajanje od prijave ostavlja zaštićene radnje nedostupnima. |
| F-003 | Darivatelj u profilu unosi ime i prezime, datum rođenja, spol, mjesto stanovanja, broj mobilnog telefona i, ako mu je poznata, krvnu grupu. | Visok | Odobreni zadatak | Darivatelj može spremiti i izmijeniti navedene podatke, krvna grupa može ostati prazna. |
| F-004 | Krvna grupa koju unese darivatelj označena je kao neprovjerena dok je ne potvrdi djelatnik centra. | Srednji | Odobreni zadatak | U profilu je uz unesenu krvnu grupu vidljiva oznaka „neprovjerena”. Nakon potvrde djelatnika oznaka nestaje. |
| F-005 | Darivatelj odabire način primanja obavijesti (SMS, e-pošta) i najveću udaljenost od svoje lokacije za koju želi primati obavijesti o akcijama. | Srednji | Odobreni zadatak | Odabrane postavke se spremaju i primjenjuju pri slanju sljedećih obavijesti. |
| F-006 | Sustav za svakog darivatelja izračunava datum od kojeg ponovno smije darivati, uzimajući u obzir najmanji razmak između darivanja, najveći dopušteni broj darivanja u godini i privremenu odgodu koju unosi djelatnik. | Visok | Odobreni zadatak | Za darivatelja s poznatom poviješću sustav prikazuje točan datum podobnosti u skladu s postavljenim pravilima. Privremena odgoda pomiče datum, a trajna nepodobnost ga uklanja. |
| F-007 | Administrator podešava parametre pravila podobnosti. | Srednji | Odobreni zadatak | Nakon izmjene parametra izračun podobnosti (F-006) koristi nove vrijednosti. |
| F-008 | Darivatelj za kojeg je djelatnik unio trajnu nepodobnost više ne može rezervirati termine. | Visok | Odobreni zadatak | Pokušaj rezervacije takvog darivatelja je odbijen uz objašnjenje. |
| F-009 | Djelatnik centra definira radno vrijeme centra i kapacitet pojedinih termina. | Visok | Odobreni zadatak | Darivatelji vide samo slobodne termine unutar radnog vremena. |
| F-010 | Darivatelj rezervira termin u centru. Rezervacija je dopuštena samo ako je darivatelj na taj datum podoban. | Visok | Odobreni zadatak | Podobni darivatelj dobiva potvrđenu rezervaciju, a za datum na kojem nije podoban rezervacija je odbijena uz objašnjenje. Popunjen termin se ne može rezervirati. |
| F-011 | Prije potvrde rezervacije darivatelj ispunjava kratki upitnik za samoprocjenu. Sustav upozorava darivatelja ako prema odgovorima vjerojatno neće moći darivati. | Srednji | Odobreni zadatak | Odgovori se spremaju uz rezervaciju. Kod odgovora koji upućuju na nemogućnost darivanja prikazuje se upozorenje. |
| F-012 | Rezervirani termin dodaje se u Google Calendar darivatelja. | Srednji | Odobreni zadatak | Nakon rezervacije darivatelj jednim odabirom dodaje događaj s datumom, vremenom i lokacijom u svoj Google Calendar. |
| F-013 | Darivatelj može otkazati termin najkasnije dva sata prije početka. | Visok | Odobreni zadatak | Otkazivanje do dva sata prije početka oslobađa mjesto i postavlja stanje „otkazan”, a kasnija otkazivanja su odbijena uz objašnjenje. |
| F-014 | Uz svaku rezervaciju sustav generira QR kod koji djelatnik skenira pri dolasku darivatelja. | Visok | Odobreni zadatak | Darivatelj vidi QR kod uz rezervaciju, a skeniranjem otvara pripadajuću rezervaciju i postavlja stanje „pristupio”. |
| F-015 | Termin darivanja prolazi stanja: rezerviran, pristupio, na pregledu, darivanje u tijeku, uspješno darovano ili odbijen na pregledu. Rezerviran termin može prijeći u otkazan ili nije pristupio. | Visok | Odobreni zadatak | Sustav dopušta samo propisane prijelaze među stanjima. Nedopušten prijelaz je odbijen. |
| F-016 | Pri odbijanju na pregledu djelatnik navodi razlog i određuje je li riječ o privremenoj odgodi (uz datum do kojeg vrijedi) ili o trajnoj nepodobnosti. | Visok | Odobreni zadatak | Bez razloga odbijanje se ne može spremiti, privremena odgoda utječe na datum podobnosti (F-006), trajna nepodobnost onemogućuje rezervacije (F-008). |
| F-017 | Darivatelju koji tri puta ne pristupi rezerviranom terminu sustav na mjesec dana onemogućuje nove rezervacije. | Srednji | Odobreni zadatak | Nakon trećeg stanja „nije pristupio” nove rezervacije su odbijene mjesec dana uz objašnjenje. |
| F-018 | Nakon uspješnog darivanja djelatnik evidentira dozu krvi unosom oznake (skeniranjem barkoda), krvne grupe, količine i roka trajanja. | Visok | Odobreni zadatak | Doza se sprema s navedenim podacima i povezana je s darivanjem. Oznaka doze je jedinstvena. |
| F-019 | Doza prolazi stanja: prikupljena, u testiranju, na zalihi te izdana bolnici ili odbačena. Doza se odbacuje ako laboratorijsko testiranje nije zadovoljavajuće. | Visok | Odobreni zadatak | Sustav dopušta samo propisane prijelaze, odbačene doze ne ulazu u zalihu. |
| F-020 | Stanje zaliha po krvnim grupama izračunava se iz doza koje su na zalihi. Razine (kritično, nisko, dovoljno) određuju pragovi koje podešava administrator. | Visok | Odobreni zadatak | Promjena stanja doze mijenja zalihu i razinu odgovarajuće krvne grupe. Nakon promjene praga razina se ponovno računa. |
| F-021 | Darivatelj na pregledu „Moja darivanja” vidi povijest svojih darivanja i obavijest kada je njegova doza izdana bolnici, bez podataka o primatelju. Ako testiranje doze nije zadovoljavajuće, vidi samo poruku da se javi transfuzijskom centru. | Srednji | Odobreni zadatak | Povijest sadrži darivanja darivatelja, pregled ne sadrži podatke o primatelju ni rezultat testiranja već samo propisanu poruku. |
| F-022 | Kada zaliha neke krvne grupe padne ispod kritične razine, sustav djelatniku centra predlaže slanje hitnog poziva. | Visok | Odobreni zadatak | Pad zalihe ispod kritičnog praga stvara prijedlog hitnog poziva vidljiv djelatniku. |
| F-023 | Djelatnik odobrava hitni poziv i određuje područje. Sustav putem vanjskog servisa šalje SMS podobnim darivateljima te krvne grupe na tom području, uz poveznicu na slobodne termine. | Visok | Odobreni zadatak | Poruku primaju samo podobni darivatelji tražene krvne grupe s odabranog područja. Poruka sadrži poveznicu na slobodne termine. |
| F-024 | Isti darivatelj može primiti najviše jedan hitni poziv tjedno. | Srednji | Odobreni zadatak | Darivatelj koji je u tekućem tjednu već primio hitni poziv ne prima novi. |
| F-025 | Za svaki hitni poziv sustav prati odaziv: broj darivatelja koji su nakon poziva rezervirali termin i broj onih koji su uspješno darivali. | Srednji | Odobreni zadatak | Uz poziv su prikazani broj rezervacija i broj uspješnih darivanja nastalih nakon poziva. |
| F-026 | Organizator predlaže terensku akciju navodeći lokaciju, datum, vrijeme i očekivani broj darivatelja. | Visok | Odobreni zadatak | Prijedlog s obaveznim podacima sprema se u stanju „predložena” i vidljiv je centru. |
| F-027 | Transfuzijski centar prihvaća akciju i dodjeljuje joj mobilni tim djelatnika ili je odbija uz obrazloženje. | Visok | Odobreni zadatak | Prihvaćena akcija postaje „potvrđena” s dodijeljenim timom, odbijanje traži obrazloženje koje vidi organizator. |
| F-028 | Akcija prolazi stanja: predložena, potvrđena, otvorene prijave te održana ili otkazana. | Srednji | Odobreni zadatak | Sustav dopušta samo propisane prijelaze. Prijave darivatelja moguće su samo u stanju „otvorene prijave”. |
| F-029 | Darivatelj se prijavljuje na terensku akciju, prijava je dopuštena samo ako je darivatelj na dan akcije podoban. | Visok | Odobreni zadatak | Podobni darivatelj uspješno se prijavljuje, nepodobni je odbijen uz objašnjenje. |
| F-030 | Organizator vidi broj prijavljenih darivatelja, može im slati obavijesti, a nakon održane akcije vidi statistiku (pristupili, uspješno darivali, odbijeni). | Srednji | Odobreni zadatak | Prikazani brojevi odgovaraju evidentiranim prijavama i ishodima darivanja, obavijest primaju samo prijavljeni darivatelji te akcije. |
| F-031 | Pri jubilarnom darivanju (10., 25., 50. ili 100.) darivatelj dobiva digitalnu značku u profilu, a organizator obavijest kako bi mogao pripremiti dodjelu priznanja. | Nizak | Odobreni zadatak | Nakon uspješnog jubilarnog darivanja značka je vidljiva u profilu, a organizator je obaviješten. |
| F-032 | Nakon svakog uspješnog darivanja darivatelj može preuzeti potvrdu o darivanju u PDF formatu. | Nizak | Odobreni zadatak | Za uspješno darivanje dostupna je PDF potvrda s podacima darivatelja i darivanja, a za neuspjelo darivanje nije. |
| F-033 | Djelatnik centra na pregledu „Statistika” vidi kretanje zaliha po krvnim grupama kroz vrijeme, prosječnu dnevnu potrošnju, procjenu broja dana do nestašice po krvnoj grupi te odaziv na hitne pozive i akcije, uz izvoz u CSV. Administrator pregledava statistiku korištenja sustava. | Srednji | Odobreni zadatak | Prikazani podaci odgovaraju evidentiranim dozama, pozivima i akcijama. Izvoz daje CSV datoteku s prikazanim podacima. |
| F-034 | Administrator upravlja korisnicima i transfuzijskim centrima, nakon provjere dodjeljuje uloge djelatnika centra i organizatora akcija te postavlja pragove zaliha. | Visok | Odobreni zadatak | Korisnik dobiva ulogu tek nakon radnje administratora, korisnik bez uloge administratora ne može izvesti te radnje. |
| F-035 | U slučaju zlouporabe administrator može deaktivirati korisnički račun. | Srednji | Odobreni zadatak | Deaktivirani korisnik ne može se prijaviti niti koristiti zaštićene funkcije. |
| F-036 | Darivatelj može preuzeti sve svoje podatke. | Srednji | Odobreni zadatak | Darivatelj dobiva datoteku sa svojim podacima u profilu, rezervacijama i povijesti darivanja. |
| F-037 | Darivatelj može zatražiti brisanje korisničkog računa. Evidencija njegovih darivanja se anonimizira, ali ne briše. | Srednji | Odobreni zadatak | Nakon brisanja račun ne postoji, osobni podaci nisu povezani s evidencijom darivanja, a doze i darivanja ostaju u sustavu radi sljedivosti. |

## 2.2 Nefunkcijski zahtjevi

**Cilj:** Odrediti važna svojstva kvalitete i uvjete rada odobrene aplikacije na način koji tim može razumno provjeriti.

| ID | Opis | Prioritet | Izvor | Način provjere |
| --- | --- | --- | --- | --- |
| NF-01 | Aplikacija je web-aplikacija prilagođena (responzivna) mobilnom uređaju: ključni tijekovi darivatelja (pregled karte, rezervacija, otkazivanje, prikaz QR koda) mogu se izvesti na uskom zaslonu. | Visok | Uputa | Proći ključne tijekove na zaslonu širine mobitela i na računalu. Svi koraci moraju biti izvedivi bez vodoravnog pomicanja stranice. |
| NF-02 | Podaci o zdravstvenom stanju darivatelja (odgovori iz upitnika, razlozi odbijanja i odgode) dostupni su samo djelatnicima transfuzijskog centra. | Visok | Sigurnost | Testni scenarij: korisnici u ulogama darivatelja, organizatora i administratora pokušavaju dohvatiti te podatke i moraju biti odbijeni. |
| NF-03 | Svaki pristup zdravstvenim podacima iz NF-02 bilježi se u zapisnik (tko, kada, čiji podaci). | Visok | Sigurnost | Nakon pristupa u zapisniku postoji odgovarajući zapis i nije ga moguće izmijeniti kroz aplikaciju. |
| NF-04 | Autorizacija unutar aplikacije provodi se OAuth 2.0 protokolom uz tokene. | Visok | Sigurnost | Zahtjevi zaštićenom resursu bez valjanog tokena i zahtjevi s valjanim tokenom uloge koja nema ovlast su odbijeni. |
| NF-05 | Evidencija darivanja i doza čuva se i nakon brisanja korisničkog računa, ali bez mogućnosti povezivanja s osobom. | Visok | Sigurnost | Nakon brisanja računa u bazi ne postoji osobni podatak povezan s darivanjima ali doze ostaju sljedive do darivanja. |
| NF-06 | Sustav ne pohranjuje i ne prikazuje podatke o primatelju krvi. | Visok | Sigurnost | Pregled modela podataka i ekrana „Moja darivanja”: ne postoji polje ni prikaz s podacima o primatelju. |
| NF-07 | Istodobne rezervacije ne smiju prekoračiti kapacitet termina. | Visok | Upute | Automatizirani test: više istodobnih zahtjeva za zadnje slobodno mjesto: točno jedan uspijeva. |
| NF-08 | Komunikacija klijenta i poslužitelja odvija se šifriranom vezom (HTTPS). | Srednji | Sigurnost | Pregled konfiguracije posluženog okruženja. Pristup nešifriranom adresom je odbijen. |
| NF-09 | Sustav mora uz svaku odbijenu radnju korisniku prikazati razumljiv razlog. | Srednji | Procjena tima | Izazvati odbijanje (nepodoban datum, popunjen termin, prekasno otkazivanje) i provjeriti da se prikazuje razlog. |
| NF-10 | Kada darivatelj ponovno postane podoban sustav ga automatski obavještava odabranim kanalom (SMS ili e-pošta) s popisom slobodnih termina i akcija u njegovoj blizini | Srednji | Upute | Darivatelju postaviti datum ponovne podobnosti na današnji dan i provjeriti da obavijest stiže odabranim kanalom s terminima i akcijama unutar odabrane udaljenosti |
| NF-11 | Sustav automatski šalje podsjetnik s uputama za pripremu dan prije termina (samo za neotkazane rezervacije) | Srednji | Uputa | Rezervirati termin za sutra, pokrenuti planirani posao i provjeriti da podjsetnik siže odabranim kanalom i da se za otkazanu rezervaciju ne šalje |
| NF-12 | Doze kojima je istekao rok trajanje sustav automatski prebacuje stanje u "odbačeno" i one se ne računaju u zalihu | Visok | Uputa | Dozi postaviti rok trajanja u prošlost , pokrenuti plairani posao i provjeriti da je doza odbačena i nije više uračunata u zalihu |
| NF-13 | Kada se otvoru prijave za akcije sustav automatski obavještava darivatelje koji su unutar njihove najveće udaljenosti koju su odabrali | Srednji | Uputa | Otvoriti prijave za akcije i provjeriti da obavijesti primaju darivatelji unutar odabrane udaljenosti, a ostali ne primaju |

## 2.3 Dionici i akteri

Tablica razlikuje korisničke uloge (A), vanjske sustave koji sudjeluju u obrascima uporabe (S) i dionike koji aplikaciju ne koriste, ali određuju njezina ograničenja (D).

| ID uloge ili aktera | Odnos prema sustavu | Relevantni zahtjevi |
| --- | --- | --- |
| A-01 Posjetitelj | Neregistrirani korisnik, ostali akteri naslijeđuju njegove mogućnosti. | F-001, F-002 |
| A-02 Darivatelj | Upravlja profilom, rezervira i otkazuje termine, prijavljuje se na akcije, pregledava povijest. | F-002 do F-006, NF-10, NF-11, F-010 do F-014, F-021, F-029, NF-13, F-031, F-032, F-036, F-037 |
| A-03 Djelatnik transfuzijskog centra | Upravlja terminima, zaprima darivatelje, evidentira doze, šalje hitne pozive, odlučuje o akcijama, pregledava statistiku. | F-004, F-008, F-009, F-014 do F-020, NF-12, F-022 do F-025, F-027, F-028, F-033 |
| A-04 Organizator terenskih akcija | Predlaže akcije i prati prijave i statistiku akcija. | F-026, F-028, F-030, F-031 |
| A-05 Administrator | Upravlja korisnicima, ulogama, pravilima podobnosti i pragovima zaliha. | F-007, F-020, F-033, F-034, F-035 |
| S-01 Pružatelj identiteta (OAuth 2.0) | Sekundarni akter (vanjski sustav) u prijavi. | F-002, NF-04 |
| S-02 Servis za SMS poruke | Sekundarni akter (vanjski sustav) pri slanju hitnih poziva. | F-023 |
| D-01 Primatelj krvi | Dionik koji ne komunicira s aplikacijom, njegovi podaci se ne pohranjuju. | NF-06 |
| D-02 Nadzorno tijelo za zaštitu osobnih podataka | Dionik koji ne koristi aplikaciju, određuje zahtjeve zaštite podataka. | NF-02, NF-03, NF-05, F-036, F-037 |

Zahtjevi koje izvodi sustav bez ljudskog aktera (NF-010, NF-11, NF-12, NF-13) nemaju zaseban akter u dijagramu obrazaca uporabe.

## 2.4 Ograničenja i pretpostavke

**Cilj:** Zabilježiti projektno specifične granice i pretpostavke koje utječu na zahtjeve ili oblikovanje, zajedno s njihovim izvorom i praktičnom posljedicom. Ovo je njihovo mjerodavno mjesto u dokumentaciji.

| ID | Tip | Opis ograničenja ili pretpostavke | Izvor | Inženjerska posljedica / Provjera |
| --- | --- | --- | --- | --- |
| C-01 | Ograničenje | Aplikacija je web-aplikacija prilagođena mobilnom uređaju. | Odobreni zadatak | Jedna web-aplikacija s responzivnim sučeljem, ne izrađuju se izvorne mobilne aplikacije (NF-01). |
| C-02 | Ograničenje | Prijava i autorizacija provode se OAuth 2.0 protokolom uz tokene i valjanu adresu e-pošte. | Odobreni zadatak | Aplikacija se oslanja na vanjskog pružatelja identiteta. Zaštićene krajnje točke projektirati oko tokena. Provjeriti autorizirane i neautorizirane zahtjeve (NF-04). |
| C-03 | Ograničenje | Karta se prikazuje korištenjem vanjske usluge. | Odobreni zadatak | Podaci o centrima i akcijama pohranjuju se u aplikaciji, vanjska usluga samo prikazuje kartu. |
| C-04 | Ograničenje | Hitni pozivi šalju se SMS porukama putem vanjskog servisa (npr. Infobip). | Odobreni zadatak | Slanje poruka izdvojiti iza sučelja za obavijesti kako bi se pružatelj mogao zamijeniti. |
| C-05 | Ograničenje | Pri brisanju računa evidencija darivanja se anonimizira, a ne briše, zbog sljedivosti doza. | Odobreni zadatak | Model podataka mora razdvojiti osobne podatke od evidencije darivanja i doza (NF-06). |
| AS-01 | Pretpostavka | Sustav podržava više transfuzijskih centara (zadatak govori o centrima u množini). Svaki ima svoje radno vrijeme, termine i djelatnike. | Procjena tima | Provjeriti s asistentom do prve kontrolne točke. Ako je dovoljan jedan centar, ostaje mogućnost proširenja. |
| AS-02 | Pretpostavka | Upitnik za samoprocjenu samo upozorava i ne sprječava potvrdu rezervacije. | Odobreni zadatak (tumačenje tima) | Provjeriti s asistentom i ako treba blokirati, mijenja se F-011. |
| AS-03 | Pretpostavka | Zalihe i pragovi su sustavski (zbroj doza na zalihi u svim centrima), a hitni poziv bilježi centar koji ga je odobrio. | Procjena tima | Provjeriti s asistentom i ako treba zaliha po centru, pragovi i prijedlozi hitnih poziva postaju vezani uz centar (F-020, F-022). |

---

## 2.5 Značajne promjene zahtjeva

**Cilj:** Sačuvati razlog promjena koje mijenjaju dogovoreni opseg, prioritet ili kriterije prihvaćanja, uz istodobno održavanje prethodnih zahtjeva ažurnima.

| Datum | ID zahtjeva | Promjena i razlog | Dogovoreno s / dokaz |
| --- | --- | --- | --- |
|  |  |  |  |

## 2.6 Sljedivost zahtjeva

Tablica povezuje svaki zahtjev s ponašanjem, dijelom sustava koji  ga ostvaruje i ispitivanjem. Funkcijski zahtjevi su povezani s obrascima uporabe a nefunkcijski zahtjevi nisu vezani uz obrasce uporabe pa je za njih opisano ponašanje koje se provjerava. Svaki funkcijski zahtjev ima barem jedan obrazac uporabe te dio sustava koji ga ostvaruje.

| ID zahtjeva | Obrazac uporabe / modul | Arhitekturna realizacija | Ispitivanje ili provjera |
| --- | --- | --- | --- |
| F-001 | UC-01 Pregled karte i stanje zaliha | Web-klijent, domenska pravila | - |
| F-002 | UC-02 Prijava u sustav | Web-klijent, autentifikacija i uloge, PostgreSQL | - |
| F-003 | UC-03 Upravljanje profilom i postavkama obavijesti | Web-klijent, PostgreSQL, rute darivatelja | - |
| F-004 | UC-03 Upravljanje profilom i postavkama obavijesti, UC-11 Obrada dolaska darivatelja | PostgreSQL, rute darivatelja, rute osoblja | - |
| F-005 | UC-03 Upravljanje profilom i postavkama obavijesti | Web-klijent, PostgreSQL, rute darivatelja | - |
| F-006 | UC-04 Provjera podobnosti i samoprocjena | Domenska pravila, rute darivatelja | - |
| F-007 | UC-19 Podešavanje pravila podobnosti i pragova zaliha | Domenska pravila, PostgreSQL, rute administracije | - |
| F-008 | UC-04 Provjera podobnosti i samoprocjena, UC-11 Obrada dolazaka darivatelja | Domenska pravila, rute darivatelja | - |
| F-009 | UC-10 Definiranje radnog vremena i kapacitet termina | PostgreSQL, rute osoblja | - |
| F-010 | UC-05 Rezervacija termina u centru | Domenska pravila, PostgreSQL, rute darivatelja | - |
| F-011 | UC-04 Provjera podobnosti i samoprocjena | Web-klijent, rute darivatelja | - |
| F-012 | UC-05 Rezervacija termina u centru | Web-klijent, Google kalendar | - |
| F-013 | UC-06 Otkazivanje rezerviranog termina | Domenska pravila, PostgreSQL, rute darivatelja | - |
| F-014 | UC-05 Rezervacija termina, UC-11 Obrada dolaska darivatelja | Web-klijent, rute darivatelja, rute osoblja | - |
| F-015 | UC-11 Obrada dolaska darivatelja | Domenska pravila, rute osoblja | - |
| F-016 | UC-11 Obrada dolaska darivatelja | Domenska pravila, rute osoblja | - |
| F-017 | UC-04 Provjera podobnosti i samoprocjena, UC-11 Obrada dolaska darivatelja | PostgreSQL, rute osoblja | - |
| F-018 | UC-12 Evidencija doze i upravljanje zalihama | PostgreSQL, rute osoblja | - |
| F-019 | UC-12 Evidencija doze i upravljanje zalihama | Domenska pravila, rute osoblja | - |
| F-020 | UC-01 Pregled karte i stanja zaliha, UC-12 Evidencija doze i upravljanje zalihom, UC-19 Podešavanje pravila podobnosti i pragova zaliha krvi | Domenska pravila, rute osoblja | - |
| F-021 | UC-08 Pregled povijesti darivanja i preuzimanje potvrde | PostgreSQL, rute darivatelja | - |
| F-022 | UC-13 Slanje hitnog poziva | Planirani poslovi, domenska pravila, rute osoblja | - |
| F-023 | UC-13 Slanje hitnog poziva | Domenska pravila, SMS adapter, rute osoblja | - |
| F-024 | UC-13 Slanje hitnog poziva | PostgreSQL, rute osoblja | - |
| F-025 | UC-13 Slanje hitnog poziva, UC-14 Pregled statistike i izvoz podatak | PostgreSQL, rute osoblja | - |
| F-026 | UC-15 Predlaganje terenske akcije | PostgreSQL, rute organizatora | - |
| F-027 | UC-16 Odluka o prijedlogu terenske akcije | Domenska pravila, rute osoblja | - |
| F-028 | UC-15 Predlaganje terenske akcije, UC-16 Odluka o prijedlogu terenske akcije, UC-17 Praćenje terenske akcije | Domenska pravila | - |
| F-029 | UC-07 Prijava na terensku akciju | Domenska pravila, rute darivatelja | - |
| F-030 | UC-17 Praćenje terenske akcije | Rute organizatora, rute organizatora | - |
| F-031 | UC-08 Pregled povijesti darivanja i preuzimanje potvrde, UC-17 Praćenje terenske akcije | Rute darivatelja, Rute organizatora | - |
| F-032 | UC-08 Pregled povijesti darivanja i preuzimanje potvrde | Rute darivatelja | - |
| F-033 | UC-14 Pregled statistike i izvoz podataka | Rute osoblja, rute administracije | - |
| F-034 | UC-18 Upravljanje korisnicima i ulogama, UC-19 Podešavanje pravila podobnosti i pragova zaliha | Rute administracije, PostgreSQL | - |
| F-035 | UC-18 Upravljanje korisnicima i ulogama | Rute administracije, autentifikacija i uloge | - |
| F-036 | UC-09 Izvoz podataka i brisanje računa | Rute darivatelja | - |
| F-037 | UC-09 Izvoz podataka i brisanje računa | Rute darivatelja, PostgreSQL | - |
| NF-01 | Ključni tijekovi korisnika na uskim zaslonima | Web-klijent | - |
| NF-02 | Pristup zdravstvenim podatcima darivatelja (upitnik, razlozi odbijanja, odgode) | Rute osoblja centra, autentifikacija i uloge | - |
| NF-03 | Bilježenje pristupa zdravstvenim podatcima u zapisnik | Rute osoblja centra, PostgreSQL | - |
| NF-04 | Autorizacija zahtjeva tokenom i ulogom | Autentifikacija i uloge | - |
| NF-05 | Anonimizacija podataka pri brisanju računa | Rute darivatelja, PostgreSQL | - |
| NF-06 | Pregled povijesti darivanja i preuzimanje potvrde | Rute darivatelja, PostgreSQL | - |
| NF-07 | Rezervacija termina uz istodobne zahtjeve (kapacitet termina) | Rute darivatelja, PostgreSQL | - |
| NF-08 | Sve komunikacije klijenta i poslužitelja | Poslužiteljska aplikacija | - |
| NF-09 | Prikaz razloga uz odbijanje radnje | Web-klijent, Rute darivatelja | - |
| NF-10 | Automatska obavijest kad darivatelj ponovno postane podoban | Planirani poslovi, domenska pravila, SMS adapter | - |
| NF-11 | Automatski podsjetnik dan prije termina | Planirani poslovi, SMS adapter | - |
| NF-12 | Automatsko odbacivanje doza kojima je istekao rok | Planirani poslovi, domenska pravila | - |
| NF-13 | Automatska obavijest o akcijama u blizini | Planirani poslovi, SMS adapter | - |
|  |  |  |  |

## 2.7 Pregled zahtjeva

**Cilj:** Zabilježiti važne povratne informacije o zahtjevima i odluke koje iz njih proizlaze na dvije formalne kontrolne točke projekta, u 7. i 14. tjednu.

| Kontrolna točka | Povratna informacija ili nalaz | Odluka o zahtjevu ili opsegu |
| --- | --- | --- |
| Iteracija 1 | [Povratna informacija] | [Odluka; vidi odjeljak 2.5] |
| Demo 2  | [Povratna informacija] | [Odluka; vidi odjeljak 2.5] |
| Završni pregled | [Povratna informacija] | [Odluka; vidi odjeljak 2.5] |

---

>**Završna provjera:** Zahtjevi su aktualni, provjerljivi i povezani. Svaki važan zahtjev moguće je pratiti od njegova opisa i ponašanja preko mjesta realizacije u sustavu do odgovarajućeg ispitivanja ili druge provjere. Poveznice koriste stvarne identifikatore i nazive iz projektne dokumentacije.

