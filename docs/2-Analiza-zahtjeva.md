# 2. Analiza zahtjeva

Zahtjevi u ovom poglavlju izvedeni su iz opsega projekta i projektnog zadatka. Svaki zahtjev ima identifikator, prioritet, izvor i način provjere. Zahtjevi se tijekom razvoja mogu mijenjati, a značajne promjene bilježe se u odjeljku 2.5.

**Ljestvica prioriteta:** 
- *Visok* – bez toga aplikacija ne ostvaruje glavni cilj 
- *Srednji* – važno za potpunu uporabu, ali glavni dio aplikacije radi bez njih
- *Nizak* – korisno proširenje koje se prvo izostavlja ako ponestane vremena

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
| F-008 | Kada darivatelj ponovno postane podoban, sustav mu šalje obavijest s popisom slobodnih termina i akcija u njegovoj blizini. | Srednji | Odobreni zadatak | Na dan podobnosti darivatelj prima obavijest odabranim kanalom (F-005) s popisom termina i akcija unutar odabrane udaljenosti. |
| F-009 | Darivatelj za kojeg je djelatnik unio trajnu nepodobnost više ne može rezervirati termine. | Visok | Odobreni zadatak | Pokušaj rezervacije takvog darivatelja je odbijen uz objašnjenje. |
| F-010 | Djelatnik centra definira radno vrijeme centra i kapacitet pojedinih termina. | Visok | Odobreni zadatak | Darivatelji vide samo slobodne termine unutar radnog vremena. |
| F-011 | Darivatelj rezervira termin u centru. Rezervacija je dopuštena samo ako je darivatelj na taj datum podoban. | Visok | Odobreni zadatak | Podobni darivatelj dobiva potvrđenu rezervaciju, a za datum na kojem nije podoban rezervacija je odbijena uz objašnjenje. Popunjen termin se ne može rezervirati. |
| F-012 | Prije potvrde rezervacije darivatelj ispunjava kratki upitnik za samoprocjenu. Sustav upozorava darivatelja ako prema odgovorima vjerojatno neće moći darivati. | Srednji | Odobreni zadatak | Odgovori se spremaju uz rezervaciju. Kod odgovora koji upućuju na nemogućnost darivanja prikazuje se upozorenje. |
| F-013 | Rezervirani termin dodaje se u Google Calendar darivatelja. | Srednji | Odobreni zadatak | Nakon rezervacije darivatelj jednim odabirom dodaje događaj s datumom, vremenom i lokacijom u svoj Google Calendar. |
| F-014 | Dan prije termina darivatelj dobiva podsjetnik s uputama za pripremu. | Srednji | Odobreni zadatak | Za rezervaciju koja nije otkazana podsjetnik se šalje dan prije termina odabranim kanalom (F-005). |
| F-015 | Darivatelj može otkazati termin najkasnije dva sata prije početka. | Visok | Odobreni zadatak | Otkazivanje do dva sata prije početka oslobađa mjesto i postavlja stanje „otkazan”, a kasnija otkazivanja su odbijena uz objašnjenje. |
| F-016 | Uz svaku rezervaciju sustav generira QR kod koji djelatnik skenira pri dolasku darivatelja. | Visok | Odobreni zadatak | Darivatelj vidi QR kod uz rezervaciju, a skeniranjem otvara pripadajuću rezervaciju i postavlja stanje „pristupio”. |
| F-017 | Termin darivanja prolazi stanja: rezerviran, pristupio, na pregledu, darivanje u tijeku, uspješno darovano ili odbijen na pregledu. Rezerviran termin može prijeći u otkazan ili nije pristupio. | Visok | Odobreni zadatak | Sustav dopušta samo propisane prijelaze među stanjima. Nedopušten prijelaz je odbijen. |
| F-018 | Pri odbijanju na pregledu djelatnik navodi razlog i određuje je li riječ o privremenoj odgodi (uz datum do kojeg vrijedi) ili o trajnoj nepodobnosti. | Visok | Odobreni zadatak | Bez razloga odbijanje se ne može spremiti, privremena odgoda utječe na datum podobnosti (F-006), trajna nepodobnost onemogućuje rezervacije (F-009). |
| F-019 | Darivatelju koji tri puta ne pristupi rezerviranom terminu sustav na mjesec dana onemogućuje nove rezervacije. | Srednji | Odobreni zadatak | Nakon trećeg stanja „nije pristupio” nove rezervacije su odbijene mjesec dana uz objašnjenje. |
| F-020 | Nakon uspješnog darivanja djelatnik evidentira dozu krvi unosom oznake (skeniranjem barkoda), krvne grupe, količine i roka trajanja. | Visok | Odobreni zadatak | Doza se sprema s navedenim podacima i povezana je s darivanjem. Oznaka doze je jedinstvena. |
| F-021 | Doza prolazi stanja: prikupljena, u testiranju, na zalihi te izdana bolnici ili odbačena. Doza se odbacuje ako laboratorijsko testiranje nije zadovoljavajuće. | Visok | Odobreni zadatak | Sustav dopušta samo propisane prijelaze, odbačene doze ne ulazu u zalihu. |
| F-022 | Doze kojima je istekao rok trajanja sustav automatski prebacuje u stanje „odbačena”. | Visok | Odobreni zadatak | Nakon isteka roka doza je u stanju „odbačena” bez ručne radnje i ne računa se u zalihu. |
| F-023 | Stanje zaliha po krvnim grupama izračunava se iz doza koje su na zalihi. Razine (kritično, nisko, dovoljno) određuju pragovi koje podešava administrator. | Visok | Odobreni zadatak | Promjena stanja doze mijenja zalihu i razinu odgovarajuće krvne grupe. Nakon promjene praga razina se ponovno računa. |
| F-024 | Darivatelj na pregledu „Moja darivanja” vidi povijest svojih darivanja i obavijest kada je njegova doza izdana bolnici, bez podataka o primatelju. Ako testiranje doze nije zadovoljavajuće, vidi samo poruku da se javi transfuzijskom centru. | Srednji | Odobreni zadatak | Povijest sadrži darivanja darivatelja, pregled ne sadrži podatke o primatelju ni rezultat testiranja već samo propisanu poruku. |
| F-025 | Kada zaliha neke krvne grupe padne ispod kritične razine, sustav djelatniku centra predlaže slanje hitnog poziva. | Visok | Odobreni zadatak | Pad zalihe ispod kritičnog praga stvara prijedlog hitnog poziva vidljiv djelatniku. |
| F-026 | Djelatnik odobrava hitni poziv i određuje područje. Sustav putem vanjskog servisa šalje SMS podobnim darivateljima te krvne grupe na tom području, uz poveznicu na slobodne termine. | Visok | Odobreni zadatak | Poruku primaju samo podobni darivatelji tražene krvne grupe s odabranog područja. Poruka sadrži poveznicu na slobodne termine. |
| F-027 | Isti darivatelj može primiti najviše jedan hitni poziv tjedno. | Srednji | Odobreni zadatak | Darivatelj koji je u tekućem tjednu već primio hitni poziv ne prima novi. |
| F-028 | Za svaki hitni poziv sustav prati odaziv: broj darivatelja koji su nakon poziva rezervirali termin i broj onih koji su uspješno darivali. | Srednji | Odobreni zadatak | Uz poziv su prikazani broj rezervacija i broj uspješnih darivanja nastalih nakon poziva. |
| F-029 | Organizator predlaže terensku akciju navodeći lokaciju, datum, vrijeme i očekivani broj darivatelja. | Visok | Odobreni zadatak | Prijedlog s obaveznim podacima sprema se u stanju „predložena” i vidljiv je centru. |
| F-030 | Transfuzijski centar prihvaća akciju i dodjeljuje joj mobilni tim djelatnika ili je odbija uz obrazloženje. | Visok | Odobreni zadatak | Prihvaćena akcija postaje „potvrđena” s dodijeljenim timom, odbijanje traži obrazloženje koje vidi organizator. |
| F-031 | Akcija prolazi stanja: predložena, potvrđena, otvorene prijave te održana ili otkazana. | Srednji | Odobreni zadatak | Sustav dopušta samo propisane prijelaze. Prijave darivatelja moguće su samo u stanju „otvorene prijave”. |
| F-032 | Darivatelj se prijavljuje na terensku akciju, prijava je dopuštena samo ako je darivatelj na dan akcije podoban. | Visok | Odobreni zadatak | Podobni darivatelj uspješno se prijavljuje, nepodobni je odbijen uz objašnjenje. |
| F-033 | Darivatelj prima obavijest o akcijama unutar najveće udaljenosti koju je odabrao. | Srednji | Odobreni zadatak | Otvaranje prijava za akciju unutar odabrane udaljenosti šalje obavijest, akcija izvan udaljenosti ne šalje obavijest. |
| F-034 | Organizator vidi broj prijavljenih darivatelja, može im slati obavijesti, a nakon održane akcije vidi statistiku (pristupili, uspješno darivali, odbijeni). | Srednji | Odobreni zadatak | Prikazani brojevi odgovaraju evidentiranim prijavama i ishodima darivanja, obavijest primaju samo prijavljeni darivatelji te akcije. |
| F-035 | Pri jubilarnom darivanju (10., 25., 50. ili 100.) darivatelj dobiva digitalnu značku u profilu, a organizator obavijest kako bi mogao pripremiti dodjelu priznanja. | Nizak | Odobreni zadatak | Nakon uspješnog jubilarnog darivanja značka je vidljiva u profilu, a organizator je obaviješten. |
| F-036 | Nakon svakog uspješnog darivanja darivatelj može preuzeti potvrdu o darivanju u PDF formatu. | Nizak | Odobreni zadatak | Za uspješno darivanje dostupna je PDF potvrda s podacima darivatelja i darivanja, a za neuspjelo darivanje nije. |
| F-037 | Djelatnik centra na pregledu „Statistika” vidi kretanje zaliha po krvnim grupama kroz vrijeme, prosječnu dnevnu potrošnju, procjenu broja dana do nestašice po krvnoj grupi te odaziv na hitne pozive i akcije, uz izvoz u CSV. Administrator pregledava statistiku korištenja sustava. | Srednji | Odobreni zadatak | Prikazani podaci odgovaraju evidentiranim dozama, pozivima i akcijama. Izvoz daje CSV datoteku s prikazanim podacima. |
| F-038 | Administrator upravlja korisnicima i transfuzijskim centrima, nakon provjere dodjeljuje uloge djelatnika centra i organizatora akcija te postavlja pragove zaliha. | Visok | Odobreni zadatak | Korisnik dobiva ulogu tek nakon radnje administratora, korisnik bez uloge administratora ne može izvesti te radnje. |
| F-039 | U slučaju zlouporabe administrator može deaktivirati korisnički račun. | Srednji | Odobreni zadatak | Deaktivirani korisnik ne može se prijaviti niti koristiti zaštićene funkcije. |
| F-040 | Darivatelj može preuzeti sve svoje podatke. | Srednji | Odobreni zadatak | Darivatelj dobiva datoteku sa svojim podacima u profilu, rezervacijama i povijesti darivanja. |
| F-041 | Darivatelj može zatražiti brisanje korisničkog računa. Evidencija njegovih darivanja se anonimizira, ali ne briše. | Srednji | Odobreni zadatak | Nakon brisanja račun ne postoji, osobni podaci nisu povezani s evidencijom darivanja, a doze i darivanja ostaju u sustavu radi sljedivosti. |

<details>
<summary><strong>Izvori zahtjeva i kriteriji prihvaćanja</strong></summary>

Svaki zahtjev treba imati stabilan ID. **Izvor označava odakle zahtjev potječe**, primjerice iz odobrenog projektnog zadatka, zahtjeva dionika ili povratne informacije koja je promijenila zahtjev. Opišite opažljivo ponašanje umjesto naziva zaslona ili programskog okvira. U cijeloj dokumentaciji koristite istu ljestvicu prioriteta. Složenu korisničku interakciju objasnite u odgovarajućem obrascu uporabe umjesto kopiranja cijelog tijeka u ovu tablicu.

**Primjer — funkcijski zahtjevi za aplikaciju CrisisMap:**

Kategorije izvora pokazuju različite načine na koje zahtjev može nastati.

| ID | Opis | Prioritet | Izvor | Kriteriji prihvaćanja |
| --- | --- | --- | --- | --- |
| F-001 | Sustav omogućuje korisniku registraciju računa pomoću adrese e-pošte. | Visok | Zahtjev dionika | Korisnik se može registrirati adresom e-pošte, primiti poruku za potvrdu i aktivirati račun slijedeći poveznicu. |
| F-002 | Sustav omogućuje oporavak lozinke putem e-pošte. | Srednji | Zahtjev dionika | Korisnik može zatražiti ponovno postavljanje lozinke, primiti poveznicu i uspješno postaviti novu lozinku. |
| F-003 | Sustav prikazuje popis dostupnih resursa povezanih s kriznim događajima. | Visok | Postojeći sustav | Korisnik može pregledati ažurirani popis resursa relevantnih za vrstu kriznog događaja, lokaciju i raspoloživost. |
| F-004 | Sustav omogućuje korisnicima podnošenje prijave kriznog događaja s lokacijom. | Visok | Dokument sa zahtjevima | Korisnik može podnijeti prijavu s vrstom kriznog događaja, razinom ozbiljnosti i lokacijom te dobiti potvrdu o zaprimanju. Ako odbije pristup geolokaciji preglednika, može lokaciju odabrati ručno.|
| F-005 | Sustav šalje korisnicima obavijesti o relevantnim promjenama u njihovoj blizini. | Visok | Povratna informacija korisnika | Korisnici dobivaju obavijest unutar jedne minute od relevantne promjene za njihovu lokaciju. |
| F-006 | Sustav prikazuje dostupne resurse prema lokaciji. | Visok | Analiza dokumentacije | Prikazuje se ažurirani popis resursa prilagođen vrsti kriznog događaja, lokaciji i raspoloživosti. |
| F-007 | Sustav prikazuje prijave kriznih događaja s lokacijom kao oznake na karti. | Visok | Odobreni zadatak | Prijava s valjanom lokacijom prikazuje se kao oznaka na karti; odabirom oznake korisnik može otvoriti pripadajuću prijavu. |
| F-008 | Sustav omogućuje moderatoru odobravanje ili odbijanje podnesenih prijava. | Visok | Zahtjev dionika | Moderator može odobriti ili odbiti prijavu koja čeka pregled; korisnik bez moderatorskih ovlasti ne može izvršiti te radnje. |

**Kriteriji prihvaćanja i podrijetlo zahtjeva:** Primjer prikazuje registraciju, oporavak lozinke, resurse, prijavu kriznog događaja i obavijesti te nekoliko vrsta izvora i opažljivih kriterija. Istodobno otvara korisna pitanja: opisuju li F-003 i F-006 istu funkcionalnost resursa? Je li obećanje od jedne minute u F-005 dogovoreno i moguće ispitati? Treba li aplikaciji koja koristi isključivo OAuth uopće F-001 i F-002? Retke koristite kao materijal za analizu; u svojem projektu navedite vlastite zahtjeve. U stvarnom projektu stupac Izvor bilježi odakle su doista potekli njegovi zahtjevi.

**Primjer prilagodbe kada odobreni zadatak koristi OAuth i kartu:** Umjesto kopiranja registracije e-poštom i oporavka lozinke formulirajte relevantni zahtjev za prijavu, primjerice: „F-001: Korisnici se prijavljuju putem odobrenog OAuth pružatelja identiteta; uspješna prijava omogućuje radnje rezervirane za prijavljene korisnike, a odustajanje od prijave ostavlja te radnje nedostupnima.” Zaseban zahtjev za kartu može glasiti: „F-007 : Prijave s lokacijom prikazuju se kao oznake na karti; odabirom oznake otvara se pripadajuća prijava.” Izvor treba biti stvarni odobreni zadatak ili dogovoreni zahtjev dionika. Ova je prilagodba zamjena za neodgovarajuće retke, a ne dodatna tablica koju svaki tim mora ispuniti.

Za složenu interakciju kratak scenarij *Given / When / Then* može pojasniti kriterij. Primjerice: *Given* prijava bez lokacije, *when* građanin je podnese, *then* sustav objašnjava da lokacija nedostaje i ne pohranjuje prijavu. Nemojte jednostavan kriterij ponavljati i u prozi i u Gherkinu niti pisati Gherkin za svaki zahtjev.

</details>


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
| NF-09 | Sustav mora uz svaku odbijenu radnju korisniku prikazati razumljiv razlog. | Srednji | Procjena tima | Testni scenarij: izazvati odbijanje (nepodoban datum, popunjen termin, prekasno otkazivanje) i provjeriti da se prikazuje razlog. |

<details>
<summary><strong>Nefunkcijski zahtjevi za analizu</strong></summary>

Relevantna svojstva mogu se odnositi na uporabljivost, pristupačnost, sigurnost, performanse ili pouzdanost. Odaberite svojstva specifična za projekt i opišite kako ih je moguće provjeriti. Zahtjev navodi što se očekuje; stvarna opažanja i dokazi pripadaju rezultatima ispitivanja, a ne drugom stupcu s rezultatima u ovoj tablici.

**Primjer — nefunkcijski zahtjevi za aplikaciju CrisisMap:**

| ID | Opis | Prioritet | Izvor |
| --- | --- | --- | --- |
| NF-1.1 | Sustav mora podržati 1.000 istodobnih korisnika uz vrijeme odziva kraće od 1 s. | Visok | SLA | Opteretiti sustav zadanim brojem istodobnih korisnika i izmjeriti vrijeme odziva. |
| NF-1.2 | Svi osjetljivi podaci moraju biti šifrirani algoritmom AES-256. | Visok | Sigurnosna politika | Pregledati način pohrane i konfiguraciju šifriranja za podatke na koje se zahtjev odnosi. |
| NF-1.3 | Korisničko sučelje mora podržavati engleski i španjolski jezik. | Srednji | Povratna informacija dionika | Pregledati reprezentativne korisničke tijekove u oba jezika. |
| NF-1.4 | Dostupnost sustava mora iznositi najmanje 99,5 % mjesečno. | Visok | SLA | Izračunati mjesečnu dostupnost iz zapisa sustava za nadzor, ako takvi podaci postoje.|
| NF-3.1.7 | Sustav treba imati odgovarajuću dokumentaciju. | Visok | Pravila dokumentiranja | Pregledati zahtijevane dokumentaciju prema obavezama.|
| NF-3.1.7.1 | Kod sustava treba biti dokumentiran prema „Code Conventions for the Java Programming Language”. | Visok | Pravila dokumentiranja | Pregledati kod. |
| NF-3.1.7.2 | Sustav treba biti opisan projektnim dokumentom/SRS-om. | Visok | Pravila dokumentiranja | Pregledati strukturu dokumenta  |
| NF-3.1.7.3 | Sustav treba imati „Operativni priručnik” koji opisuje pravilnu uporabu. | Visok | Pravila dokumentiranja | Pregledati postoji li priručnik i mogu li se prema njemu izvršiti opisane radnje. |
| NF-3.1.7.4 | Sustav treba imati „Plan implementacije” za pravilno postavljanje. | Visok | Smjernice za postavljanje | Pregledati odgovara li opisani postupak stvarnom postupku postavljanja.|

**Provjerljivost i opseg:** Primjer uvodi performanse, sigurnost, lokalizaciju, dostupnost i dokumentaciju, uključujući hijerarhijski ID zahtjeva. Navedeni izvori primjeri su podrijetla zahtjeva, a ne dokaz da za studentski projekt stvarno postoji SLA ili sigurnosna politika. Vrijednosti poput 1.000 korisnika, 99,5 % mjesečne dostupnosti i AES-256 moraju imati odobren izvor i izvediv način provjere prije nego što postanu zahtjevi tima. Skup zahtjeva o dokumentaciji ilustrira razlaganje zahtjeva, ali zahtjevi za izradu *ove dokumentacije kolegija* u pravilu pripadaju uputama zadatka, a ne zahtjevima kvalitete web-aplikacije.

**Primjer prilagodbe:** Ako je pristupačnost zahtjevana za  projekt, projektno specifičan zahtjev može glasiti: „NF-01: Sve obavezne radnje u obrascu za podnošenje prijave moguće je izvršiti samo tipkovnicom, uz vidljiv fokus na aktivnoj kontroli.” Provjera: navigirati kroz obrazac bez pokazivačkog uređaja, odabrati ili unijeti lokaciju, poslati prijavu i provjeriti može li se pročitati rezultat. To je provjerljivo svojstvo kvalitete, a ne dodatna funkcionalnost koju svaki projekt mora implementirati.

</details>

## 2.3 Dionici i akteri

**Cilj:** Utvrditi uloge koje utječu na zahtjeve i razlikovati dionike od aktera koji komuniciraju s aplikacijom.

Tablica razlikuje korisničke uloge (A), vanjske sustave koji sudjeluju u obrascima uporabe (S) i dionike koji aplikaciju ne koriste, ali određuju njezina ograničenja (D).

| ID uloge ili aktera | Odnos prema sustavu | Relevantni zahtjevi |
| --- | --- | --- |
| A-01 Posjetitelj | Akter. Neregistrirani korisnik, ostali akteri naslijeđuju njegove mogućnosti. | F-001, F-002 |
| A-02 Darivatelj | Akter. Upravlja profilom, rezervira i otkazuje termine, prijavljuje se na akcije, pregledava povijest. | F-002 – F-006, F-008, F-011 – F-016, F-024, F-032, F-033, F-035, F-036, F-040, F-041 |
| A-03 Djelatnik transfuzijskog centra | Akter. Upravlja terminima, zaprima darivatelje, evidentira doze, šalje hitne pozive, odlučuje o akcijama, pregledava statistiku. | F-004, F-009, F-010, F-016 – F-023, F-025 – F-028, F-030, F-031, F-037 |
| A-04 Organizator terenskih akcija | Akter. Predlaže akcije i prati prijave i statistiku akcija. | F-029, F-031, F-034, F-035 |
| A-05 Administrator | Akter. Upravlja korisnicima, ulogama, pravilima podobnosti i pragovima zaliha. | F-007, F-023, F-037, F-038, F-039 |
| S-01 Pružatelj identiteta (OAuth 2.0) | Sekundarni akter (vanjski sustav) u prijavi. | F-002, NF-04 |
| S-02 Servis za SMS poruke | Sekundarni akter (vanjski sustav) pri slanju hitnih poziva. | F-026 |
| D-01 Bolnica | Dionik koji ne koristi aplikaciju, prima doze krvi. | F-021, F-024 |
| D-02 Primatelj krvi | Dionik koji ne komunicira s aplikacijom, njegovi podaci se ne pohranjuju. | NF-06 |
| D-03 Nadzorno tijelo za zaštitu osobnih podataka | Dionik koji ne koristi aplikaciju, određuje zahtjeve zaštite podataka. | NF-02, NF-03, NF-05, F-040, F-041 |

Zahtjevi koje izvodi sustav bez ljudskog aktera (F-008, F-014, F-022, F-033) nemaju zaseban akter u dijagramu obrazaca uporabe.

<details>
<summary><strong>Akteri i povezani zahtjevi</strong></summary>

**Primjer — akteri aplikacije CrisisMap:**

| ID aktera | Uloga | Obuhvaćeni funkcijski zahtjevi |
| --- | --- | --- |
| A-01 | Koordinator pomoći | F-003: Pregled resursa; F-004: Podnošenje prijave kriznog događaja. |
| A-02 | Građanin | F-001: Izrada računa; F-005: Primanje obavijesti. |
| A-03 | Administrator sustava | NF-1.2: Provođenje sigurnosnih pravila; upravljanje korisnicima. |
| A-04 | Posjetitelj | F-007: Pregled prijava na karti. |
| A-05 | Moderator | F-008: Odobravanje ili odbijanje prijava. |

**Odgovornosti aktera:** Uloga aktera treba biti povezana s interakcijom, a ne samo s nazivom. Provjerite podnosi li koordinator pomoći u odobrenom zadatku doista prijave kriznih događaja. Poveznica administratora s NF-1.2 navodi sigurnosni aspekt, ali sama po sebi ne opisuje obrazac uporabe; navedite stvarnu radnju administratora ili izostavite ulogu ako je nema. Nastavnici zainteresirani za rezultat mogu biti dionici, a da nisu akteri aplikacije. Tablica je polazište za razmišljanje, a ne potvrđen opis ovlasti u CrisisMapu.

</details>

---

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
| AS-02 | Pretpostavka | Upitnik za samoprocjenu samo upozorava i ne sprječava potvrdu rezervacije. | Odobreni zadatak (tumačenje tima) | Provjeriti s asistentom i ako treba blokirati, mijenja se F-012. |
| AS-03 | Pretpostavka | Zalihe i pragovi su sustavski (zbroj doza na zalihi u svim centrima), a hitni poziv bilježi centar koji ga je odobrio. | Procjena tima | Provjeriti s asistentom i ako treba zaliha po centru, pragovi i prijedlozi hitnih poziva postaju vezani uz centar (F-023, F-025). |

<details>
<summary><strong>Ograničenja nasuprot pretpostavkama</strong></summary>

Ograničenje je uvjet koji tim mora poštovati; pretpostavka je uvjet na koji se tim oslanja i koji treba ponovno provjeravati.

**Primjer — projektni kontekst CrisisMapa:**

| ID | Ograničenje ili pretpostavka | Izvor | Posljedica ili način provjere |
| --- | --- | --- | --- |
| C-01 | Odobreni zadatak zahtijeva OAuth prijavu u sustav za uređivanje vlastitih prijava korisnika. | Odobreni zadatak  | Zaštićeno uređivanje projektirati oko odabranog pružatelja identiteta; provjeriti autorizirane i neautorizirane zahtjeve. |
| AS-01 | Prijave koje se prikazuju na karti imaju uporabive koordinate. | Pretpostavka o podacima  | Pregledati ogledne i uvezene prijave; planirati prikaz prijava bez valjanih koordinata. |

**Ograničenje nasuprot pretpostavci:** C-01 navodi obvezujuću odluku i njezin arhitekturni učinak; AS-01 je moguće provjeriti i može se pokazati netočnim. Nemojte kopirati C-01 ako OAuth nije dio stvarnog odobrenog zadatka. Projektni odgovor objasnite u arhitekturi bez prepisivanja ove tablice.

</details>

---

## 2.5 Značajne promjene zahtjeva

**Cilj:** Sačuvati razlog promjena koje mijenjaju dogovoreni opseg, prioritet ili kriterije prihvaćanja, uz istodobno održavanje prethodnih zahtjeva ažurnima.

| Datum | ID zahtjeva | Promjena i razlog | Dogovoreno s / dokaz |
| --- | --- | --- | --- |
|  |  |  |  |

<details>
<summary><strong>Primjer značajne promjene</strong></summary>

**Primjer scenarija — odluka tijekom pregleda CrisisMapa:**

| Datum | ID zahtjeva | Promjena i razlog | Dogovoreno s / dokaz |
| --- | --- | --- | --- |
| [Datum pregleda] | F-004, NF-01 | Dodati ručni odabir lokacije kada korisnik odbije geolokaciju preglednika; bez toga građanin ne može podnijeti prijavu kriznog događaja s lokacijom. | [Poveznica na stvarnu bilješku pregleda ili zadatak tima] |

**Razlog promjene:** Zapis navodi zahvaćene stavke i objašnjava razlog iz perspektive korisnika, a zatim upućuje na dokaz odluke.  Nakon stvarne odluke ažurirajte trenutačne zahtjeve; stilska izmjena teksta ne zahtijeva zapis u dnevniku promjena. Trenutačni red zadataka držite u alatu za praćenje zadataka.

</details>

---

## 2.6 Sljedivost zahtjeva

**Cilj:** Osigurati dvosmjernu sljedivost — povezati zahtjeve s opisanim ponašanjem, dijelovima sustava koji ih ostvaruju te ispitivanjem ili drugom provjerom. 

| ID zahtjeva | Obrazac uporabe / modul | Arhitekturna realizacija | Ispitivanje ili provjera |
| --- | --- | --- | --- |
| F-001 | UC-01 Pregled karte i stanje zaliha | Web-klijent, domenska pravila | - |
| F-002 | UC-02 Prijava u sustav | Web-klijent, autentifikacija i uloge, PostgreSQL | - |
| F-003 | UC-03 Upravljanje profilom i postavkama obavijesti | Web-klijent, PostgreSQL, rute darivatelja | - |
| F-004 | UC-03 Upravljanje profilom i postavkama obavijesti, UC-11 Obrada dolaska darivatelja | PostgreSQL, rute darivatelja, rute osoblja | - |
| F-005 | UC-03 Upravljanje profilom i postavkama obavijesti | Web-klijent, PostgreSQL, rute darivatelja | - |
| F-006 | UC-04 Provjera podobnosti i samoprocjena | Domenska pravila, rute darivatelja | - |
| F-007 | UC-19 Podešavanje pravila podobnosti i pragova zaliha | Domenska pravila, PostgreSQL, rute administracije | - |
| F-008 | Automatska obavijest kad darivatelj postane podoban | Planirani poslovi, domenska pravila, SMS adapter | - |
| F-009 | UC-04 Provjera podobnosti i samoprocjena, UC-11 Obrada dolazaka darivatelja | Domenska pravila, rute darivatelja | - |
| F-010 | UC-10 Definiranje radnog vremena i kapacitet termina | PostgreSQL, rute osoblja | - |
| F-011 | UC-05 Rezervacija termina u centru | Domenska pravila, PostgreSQL, rute darivatelja | - |
| F-012 | UC-04 Provjera podobnosti i samoprocjena | Web-klijent, rute darivatelja | - |
| F-013 | UC-05 Rezervacija termina u centru | Web-klijent, Google kalendar | - |
| F-014 | Automatski podsjetnik prije termina (UC-05) | Planirani poslovi, SMS adapter | - |
| F-015 | UC-06 Otkazivanje rezerviranog termina | Domenska pravila, PostgreSQL, rute darivatelja | - |
| F-016 | UC-05 Rezervacija termina, UC-11 Obrada dolaska darivatelja | Web-klijent, rute darivatelja, rute osoblja | - |
| F-017 | UC-11 Obrada dolaska darivatelja | Domenska pravila, rute osoblja | - |
| F-018 | UC-11 Obrada dolaska darivatelja | Domenska pravila, rute osoblja | - |
| F-019 | UC-04 Provjera podobnosti i samoprocjena, UC-11 Obrada dolaska darivatelja | PostgreSQL, rute osoblja | - |
| F-020 | UC-12 Evidencija doze i upravljanje zalihama | PostgreSQL, rute osoblja | - |
| F-021 | UC-12 Evidencija doze i upravljanje zalihama | Domenska pravila, rute osoblja | - |
| F-022 | Automatsko odbacivanje doza kojima je istekao rok (UC-12) | Planirani poslovi, Domenska pravila | - |
| F-023 | UC-01 Pregled karte i stanja zaliha, UC-12 Evidencija doze i upravljanje zalihom, UC-19 Podešavanje pravila podobnosti i pragova zaliha krvi | Domenska pravila, rute osoblja | - |
| F-024 | UC-08 Pregled povijesti darivanja i preuzimanje potvrde | PostgreSQL, rute darivatelja | - |
| F-025 | UC-13 Slanje hitnog poziva | Planirani poslovi, domenska pravila, rute osoblja | - |
| F-026 | UC-13 Slanje hitnog poziva | Domenska pravila, SMS adapter, rute osoblja | - |
| F-027 | UC-13 Slanje hitnog poziva | PostgreSQL, rute osoblja | - |
| F-028 | UC-13 Slanje hitnog poziva, UC-14 Pregled statistike i izvoz podatak | PostgreSQL, rute osoblja | - |
| F-029 | UC-15 Predlaganje terenske akcije | PostgreSQL, rute organizatora | - |
| F-030 | UC-16 Odluka o prijedlogu terenske akcije | Domenska pravila, rute osoblja | - |
| F-031 | UC-15 Predlaganje terenske akcije, UC-16 Odluka o prijedlogu terenske akcije, UC-17 Praćenje terenske akcije | Domenska pravila | - |
| F-032 | UC-07 Prijava na terensku akciju | Domenska pravila, rute darivatelja | - |
| F-033 | Automatsko obavijest o akcijama u blizini (UC-07) | Planirani poslovi, SMS adapter | - |
| F-034 | UC-17 Praćenje terenske akcije | Rute organizatora, rute organizatora | - |
| F-035 | UC-08 Pregled povijesti darivanja i preuzimanje potvrde, UC-17 Praćenje terenske akcije | Rute darivatelja, Rute organizatora | - |
| F-036 | UC-08 Pregled povijesti darivanja i preuzimanje potvrde | Rute darivatelja | - |
| F-037 | UC-14 Pregled statistike i izvoz podataka | Rute osoblja, rute administracije | - |
| F-038 | UC-18 Upravljanje korisnicima i ulogama, UC-19 Podešavanje pravila podobnosti i pragova zaliha | Rute administracije, PostgreSQL | - |
| F-039 | UC-18 Upravljanje korisnicima i ulogama | Rute administracije, autentifikacija i uloge | - |
| F-040 | UC-09 Izvoz podataka i brisanje računa | Rute darivatelja | - |
| F-041 | UC-09 Izvoz podataka i brisanje računa | Rute darivatelja, PostgreSQL | - |
| NF-01 | Ključno kod darivatelja na uskim zaslonima | Web-klijent | - |
| NF-02 | UC-11 Obrada dolaska darivatelja | Rute osoblja centra, autentifikacija i uloge | - |
| NF-03 | UC-11 Obrada dolaska darivatelja | Rute osoblja centra, PostgreSQL | - |
| NF-04 | UC-02 Prijava u sustav | Autentifikacija i uloge | - |
| NF-05 | anonimizacija podataka (UC-09) | Rute darivatelja, PostgreSQL | - |
| NF-06 | UC-08 Pregled povijesti darivanja i preuzimanje potvrde | Rute darivatelja, PostgreSQL | - |
| NF-07 | UC-05 Rezervacija termina | Rute darivatelja, PostgreSQL | - |
| NF-08 | Sve komunikacije klijenta i poslužitelja | Poslužiteljska aplikacija | - |
| NF-09 | Sve radnje na aplikaciji | Web-klijent, Rute darivatelja | - |
|  |  |  |  |

<details>
<summary><strong>Sljedivost zahtjeva do obrazaca uporabe i ispitivanja</strong></summary>

| ID zahtjeva | Obrazac uporabe / modul | Arhitekturna realizacija | Ispitivanje ili provjera |
| --- | --- | --- | --- |
| F-001 | [UC-001] | [Komponenta, modul ili drugo mjesto realizacije] | [ST-01, CT-01 ili druga provjera] |
|  |  |  |  |

**Primjer — poveznice zahtjeva CrisisMapa:**

| ID zahtjeva | Obrazac uporabe / ponašanje | Arhitekturna realizacija | Ispitivanje / provjera |
| --- | --- | --- | --- |
| F-004 | UC-002 Podnošenje prijave kriznog događaja | Web-klijent; API za prijave; usluga za prijave | ST-01 Valjano podnošenje prijave; ST-02 Nedostaje obavezna lokacija; ST-03 Dopuštenje geolokacije odbijeno |
| F-005 | Odabir primatelja obavijesti | Proces za obavijesti | CT-06 Primatelji obavijesti; potpuna dostava obavijesti nije pokrivena ovom provjerom |

**Pokrivenost i dokazi:** CT-06 provjerava odabir primatelja za F-005, ali sam po sebi ne dokazuje da je obavijest stvarno dostavljena; ako projekt zahtijeva potpunu dostavu od početka do kraja, za taj dio treba postojati zasebna provjera. Svaki redak pokazuje gdje je zahtjev opisan kao ponašanje, gdje je ostvaren u sustavu i gdje se nalazi dokaz njegove provjere. Zahtjev ne mora imati zaseban obrazac uporabe ako ne opisuje korisničku interakciju; tada navedite relevantno ponašanje, arhitekturni element ili drugi prikladan način realizacije. U svojem projektu koristite stvarne nazive komponenata, modula i provjera iz dokumentacije i implementacije. Nedostajuću poveznicu ostavite praznom ili označite kao *na čekanju* uz kratak razlog. Stvarni rezultati ispitivanja bilježe se uz ispitne slučajeve; ova tablica pokazuje gdje ih pronaći.

</details>

---

## 2.7 Pregled zahtjeva

**Cilj:** Zabilježiti važne povratne informacije o zahtjevima i odluke koje iz njih proizlaze na dvije formalne kontrolne točke projekta, u 7. i 14. tjednu.

| Kontrolna točka | Povratna informacija ili nalaz | Odluka o zahtjevu ili opsegu |
| --- | --- | --- |
| Iteracija 1 | [Povratna informacija] | [Odluka; vidi odjeljak 2.5] |
| Demo 2  | [Povratna informacija] | [Odluka; vidi odjeljak 2.5] |
| Završni pregled | [Povratna informacija] | [Odluka; vidi odjeljak 2.5] |

<details>
<summary><strong>Odluke o zahtjevima nakon pregleda</strong></summary>

**Primjer scenarija — pregled zahtjeva CrisisMapa:**

| Kontrolna točka | Povratna informacija ili nalaz | Odluka o zahtjevu ili opsegu |
| --- | --- | --- |
| Iteracija 1 | Demonstracija pokazuje da odbijanje pristupa lokaciji preglednika onemogućuje podnošenje prijave. | Dogovoriti ručni odabir lokacije; ažurirati F-004 i primjer NF-01 te razlog zabilježiti u odjeljku 2.5. |

**Odluka povezana s povratnom informacijom:** Zapis povezuje opaženo ponašanje s konkretnom promjenom zahtjeva i izbjegava zapisnik cijelog sastanka. Zapišite samo stvarne povratne informacije i odluke za kontrolne točke. Konzultacije u nekom drugom tjednu ne stvaraju dodatnu obaveznu predaju.

</details>

---

>**Završna provjera:** Zahtjevi su aktualni, provjerljivi i povezani. Svaki važan zahtjev moguće je pratiti od njegova opisa i ponašanja preko mjesta realizacije u sustavu do odgovarajućeg ispitivanja ili druge provjere. Poveznice koriste stvarne identifikatore i nazive iz projektne dokumentacije.

