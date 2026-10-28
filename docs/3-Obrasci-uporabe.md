# 3. Obrasci uporabe

**Cilj:** Prikazati glavne interakcije između aktera aplikacije i sustava. Opišite kako akter ostvaruje cilj i što se događa kada važan korak ne uspije. Interakcije temeljite na odobrenim zahtjevima i ažurirajte ih kako se aplikacija razvija.

## 3.1 Dijagram obrazaca uporabe

![Dijagram obrazaca](./assets/diagrams/3-1-KrvnaVeza-system-UC.svg)

**Slika 3.1. Dijagram obrazaca sustava KrvnaVeza**

**PlantUML izvor:** [izvor](./puml/3-1-KrvnaVeza-system-UC.puml)

**Provjera dijagrama:** Prikažite prepoznate uloge koje **komuniciraju s aplikacijom**, a ne dionike koji s njom nemaju interakciju. Svaki glavni korisnički cilj mora imati pridruženog aktera; uključeni obrazac uporabe može se dosegnuti preko drugog obrasca uporabe bez vlastite izravne veze s akterom. `<<include>>` i `<<extend>>` koristite samo kada odnos odgovara stvarnom ponašanju.

<details>
<summary><strong>Primjer aktera i granica sustava</strong></summary>

**Opis primjera — CrisisMap:**

Dijagram prikazuje **ogledni scenarij** s građaninom, posjetiteljem i koordinatorom pomoći te vanjskim pružateljem identiteta. Pružatelj identiteta sudjeluje u obrascu uporabe „Prijava u sustav” kao sekundarni akter: korisnik pokreće prijavu u aplikaciji, a vanjski sustav provodi autentikaciju. Budući da projekt koristi vanjskog pružatelja identiteta, ta se ovisnost prikazuje i u dijagramu obrazaca uporabe; tehnički detalji integracije pripadaju arhitekturnoj dokumentaciji.

**Odabir aktera i ciljeva:** Granica pokazuje što aplikacija nudi, akteri imaju različite ciljeve, a nijedan akter ni obrazac uporabe ne tvrdi da ova studentska aplikacija angažira hitne službe. Dijagram usporedite s odobrenim funkcijskim zahtjevima i akterima.


**Pet pitanja za odabir sadržaja dijagrama** (ne pet dijagrama za predaju):

| Fokus | Pitanje |
| --- | --- |
| Cijeli sustav | Koji korisnički ciljevi najbolje objašnjavaju čemu ova aplikacija služi? |
| Ključne funkcionalnosti | Koje ciljeve treba detaljnije opisati ili, ako je potrebno, prikazati zasebnim usmjerenim dijagramom? |
| Korisničke uloge | Koji akteri mogu sudjelovati u pojedinom cilju i kako se njihove ovlasti razlikuju? |
| Ključni procesi | Koji cilj ima važne alternative koje treba opisati u obrascu uporabe? |
| Vanjske integracije | Sudjeluje li drugi sustav u obrascu uporabe kao **sekundarni akter**, primjerice odobreni pružatelj identiteta pri prijavi? |

Vanjski sustav može biti sekundarni akter obrasca uporabe kada sudjeluje u ostvarenju korisničkog cilja; tehničke veze opisuju se u arhitekturi. Nemojte stvarati dodatni obavezni dijagram obrazaca uporabe samo radi popisa integracija. Prenatrpan dijagram podijelite prema povezanim akterima ili ciljevima samo kada time postaje čitljiviji.

</details>

## 3.2 Specifikacije obrazaca uporabe

**Cilj:** Za svaki obrazac uporabe iz dijagrama objasniti cilj aktera, uobičajenu interakciju s aplikacijom i važne alternative. Jednostavne obrasce uporabe opišite kratko; proširite one kod kojih alternative, vanjske ovisnosti ili posljedice zahtijevaju dodatno objašnjenje.

**Za svaki obrazac uporabe koristite jednu okomitu tablicu.** Navedite samo onoliko pojedinosti koliko interakcija zahtijeva; neprimjenjivu alternativu izostavite umjesto da je popunjavate samo radi forme.

### UC-01: Pregled karte i stanja zaliha

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Posjetitelj i svi prijavljeni akteri |
| **Povezani zahtjevi** | F-001, F-023 |
| **Preduvjeti** | Nema. |
| **Glavni uspješni tok** | 1. Posjetitelj otvara početnu stranicu.<br>2. Sustav prikazuje kartu s transfuzijskim centrima i najavljenim akcijama.<br>3. Sustav prikazuje razinu zalihe (kritično, nisko, dovoljno) za svaku krvnu grupu.<br>4. Posjetitelj odabire centar ili akciju, sustav prikazuje njihove pojedinosti. |
| **Alternativni tokovi / Iznimke** | **2a — Karta nije dostupna:** Vanjska usluga za kartu ne odgovara. Sustav prikazuje popis centara i akcija bez karte.<br>**4a — Nema najavljenih akcija:** Sustav prikazuje poruku da trenutno nema najavljenih akcija. |
| **Posljedice (ishod)** | Podaci se samo prikazuju, stanje sustava se ne mijenja. |

### UC-02: Prijava u sustav

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Posjetitelj |
| **Sekundarni akter** | Pružatelj identiteta (OAuth 2.0) |
| **Povezani zahtjevi** | F-002, NF-04 |
| **Preduvjeti** | Posjetitelj nije prijavljen. |
| **Glavni uspješni tok** | 1. Posjetitelj odabire prijavu.<br>2. Sustav ga preusmjerava pružatelju identiteta.<br>3. Posjetitelj se autentificira kod pružatelja identiteta i odobrava pristup.<br>4. Pružatelj vraća posjetitelja u sustav uz potvrđenu adresu e-pošte i token.<br>5. Sustav pri prvoj prijavi stvara račun darivatelja. Inače prepoznaje postojeći račun i njegovu ulogu.<br>6. Sustav prikazuje funkcije koje odgovaraju ulozi. |
| **Alternativni tokovi / Iznimke** | **3a — Odustajanje ili odbijanje:** Posjetitelj odustane ili odbije pristup, sustav se vraća na javni prikaz bez prijave.<br>**4a — Adresa e-pošte nije valjana ili nije potvrđena:** Sustav odbija prijavu i objašnjava razlog.<br>**5a — Račun je deaktiviran:** Sustav odbija prijavu uz poruku da je račun deaktiviran. |
| **Posljedice (ishod)** | Posjetitelj je prijavljen s ovlastima svoje uloge, te je pri prvoj prijavi stvoren račun darivatelja. |

### UC-03: Upravljanje profilom i postavkama obavijesti

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Darivatelj |
| **Povezani zahtjevi** | F-003, F-004, F-005 |
| **Preduvjeti** | Darivatelj je prijavljen. |
| **Glavni uspješni tok** | 1. Darivatelj otvara profil.<br>2. Unosi ili mijenja ime i prezime, datum rođenja, spol, mjesto stanovanja, broj mobilnog telefona i krvnu grupu (ako mu je poznata).<br>3. Odabire način primanja obavijesti i najveću udaljenost za obavijesti o akcijama.<br>4. Sprema promjene i sustav ih potvrđuje. |
| **Alternativni tokovi / Iznimke** | **4a — Nevaljani podaci:** Obavezno polje nedostaje ili je nevaljano. Sustav označava pogrešku i ne sprema profil.<br>**4b — Krvna grupa već potvrđena:** Ako je krvnu grupu potvrdio djelatnik, izmjena krvne grupe je nemoguća. |
| **Posljedice (ishod)** | Profil i postavke su spremljeni, unesena krvna grupa označena je kao neprovjerena. |

### UC-04: Provjera podobnosti i samoprocjena

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Darivatelj (poziva ga UC-05 ili UC-07) |
| **Povezani zahtjevi** | F-006, F-009, F-012, F-019 |
| **Preduvjeti** | Darivatelj je prijavljen i odabrao je termin ili akciju. |
| **Glavni uspješni tok** | 1. Sustav izračunava datum od kojeg darivatelj smije ponovno darivati iz povijesti darivanja, odgode i pravila.<br>2. Sustav provjerava je li darivatelj na odabrani datum podoban.<br>3. Sustav prikazuje kratki upitnik za samoprocjenu.<br>4. Darivatelj ispunjava upitnik.<br>5. Sustav pohranjuje odgovore i vraća poziv obrascu uporabe koji ga je pokrenuo. |
| **Alternativni tokovi / Iznimke** | **2a — Darivatelj nije podoban na taj datum:** Sustav objašnjava da nije podoban i prikazuje datum od kojeg će biti, tok se prekida.<br>**2b — Trajna nepodobnost ili blokada zbog nedolazaka:** Sustav odbija nastavak i objašnjava razlog (trajna nepodobnost ili privremena blokada rezervacija), tok se prekida.<br>**5a — Odgovori upućuju da vjerojatno neće moći darivati:** Sustav prikazuje upozorenje da upitnik nije medicinska odluka, darivatelj može nastaviti ili odustati. |
| **Posljedice (ishod)** | Odgovori upitnika spremljeni su uz rezervaciju ili prijavu. Dostupni su samo djelatnicima centra. |

### UC-05: Rezervacija termina u centru

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Darivatelj |
| **Povezani zahtjevi** | F-011, F-013, F-016, F-014 |
| **Preduvjeti** | Darivatelj je prijavljen i centar ima definirane termine. |
| **Glavni uspješni tok** | 1. Darivatelj odabire centar i datum.<br>2. Sustav prikazuje slobodne termine.<br>3. Darivatelj odabire termin.<br>4. Sustav izvodi provjeru podobnosti i samoprocjenu (UC-04).<br>5. Darivatelj potvrđuje rezervaciju.<br>6. Sustav stvara rezervaciju u stanju „rezerviran”, smanjuje broj slobodnih mjesta i generira QR kod.<br>7. Sustav nudi dodavanje termina u Google Calendar, darivatelj to prihvaća ili odbija. |
| **Alternativni tokovi / Iznimke** | **2a — Nema slobodnih termina:** Sustav obavještava darivatelja i predlaže drugi datum ili centar.<br>**4a — Provjera podobnosti nije uspjela:** Rezervacija se ne stvara, ishod kao u UC-04.<br>**5a — Termin je u međuvremenu popunjen:** Sustav odbija rezervaciju, osvježava popis i traži odabir drugog termina.<br>**7a — Dodavanje u kalendar nije uspjelo:** Rezervacija ostaje važeća. Sustav javlja da događaj nije dodan. |
| **Posljedice (ishod)** | Rezervacija postoji s QR kodom, dan prije termina sustav šalje podsjetnik s uputama za pripremu. |

### UC-06: Otkazivanje rezerviranog termina

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Darivatelj |
| **Povezani zahtjevi** | F-015 |
| **Preduvjeti** | Darivatelj ima rezervaciju u stanju „rezerviran”. |
| **Glavni uspješni tok** | 1. Darivatelj otvara svoju rezervaciju.<br>2. Odabire otkazivanje i potvrđuje.<br>3. Sustav provjerava da do početka termina ima više od dva sata.<br>4. Sustav postavlja stanje „otkazan” i oslobađa mjesto. |
| **Alternativni tokovi / Iznimke** | **2a — Darivatelj odustaje od otkazivanja:** Rezervacija ostaje nepromijenjena.<br>**3a — Manje od dva sata do početka:** Sustav odbija otkazivanje i objašnjava pravilo. |
| **Posljedice (ishod)** | Rezervacija je otkazana, mjesto je ponovno slobodno i QR kod više ne vrijedi. |

### UC-07: Prijava na terensku akciju

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Darivatelj |
| **Povezani zahtjevi** | F-032, F-033 |
| **Preduvjeti** | Darivatelj je prijavljen. Akcija je u stanju „otvorene prijave”. |
| **Glavni uspješni tok** | 1. Darivatelj odabire akciju na karti ili u popisu.<br>2. Sustav prikazuje pojedinosti akcije.<br>3. Darivatelj se prijavljuje.<br>4. Sustav izvodi provjeru podobnosti i samoprocjenu (UC-04) za datum akcije.<br>5. Sustav bilježi prijavu i prikazuje potvrdu. |
| **Alternativni tokovi / Iznimke** | **1a — Prijave nisu otvorene:** Sustav prikazuje akciju bez mogućnosti prijave.<br>**4a — Darivatelj nije podoban na dan akcije:** Sustav odbija prijavu uz objašnjenje (UC-04).<br>**3b — Darivatelj je već prijavljen:** Sustav prikazuje postojeću prijavu. |
| **Posljedice (ishod)** | Prijava je zabilježena i brojana u pregledu organizatora (UC-17). |

### UC-08: Pregled povijesti darivanja i preuzimanje potvrde

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Darivatelj |
| **Povezani zahtjevi** | F-024, F-035, F-036 |
| **Preduvjeti** | Darivatelj je prijavljen. |
| **Glavni uspješni tok** | 1. Darivatelj otvara pregled „Moja darivanja”.<br>2. Sustav prikazuje povijest darivanja, obavijesti o izdanim dozama i stečene značke.<br>3. Darivatelj uz uspješno darivanje odabire preuzimanje potvrde.<br>4. Sustav generira PDF potvrdu i nudi ga za preuzimanje. |
| **Alternativni tokovi / Iznimke** | **2a — Testiranje doze nije zadovoljavajuće:** Sustav ne prikazuje rezultat testiranja nego samo poruku da se darivatelj javi transfuzijskom centru.<br>**2b — Nema darivanja:** Sustav prikazuje praznu povijest.<br>**3a — Darivanje nije uspješno:** Potvrda nije dostupna za to darivanje.|
| **Posljedice (ishod)** | Podaci se samo prikazuju, ne otkrivaju se podaci o primatelju. |

### UC-09: Izvoz podataka i brisanje računa

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Darivatelj |
| **Povezani zahtjevi** | F-040, F-041, NF-09 |
| **Preduvjeti** | Darivatelj je prijavljen. |
| **Glavni uspješni tok** | 1. Darivatelj odabire preuzimanje svojih podataka te sustav daje datoteku s njegovim podacima.<br>2. Darivatelj zatim zatraži brisanje računa.<br>3. Sustav upozorava da je radnja trajna i traži potvrdu.<br>4. Darivatelj potvrđuje.<br>5. Sustav briše osobne podatke i račun te anonimizira evidenciju darivanja. |
| **Alternativni tokovi / Iznimke** | **4a — Darivatelj odustaje:** Račun ostaje nepromijenjen.<br>**5a — Brisanje nije uspjelo:** Sustav javlja pogrešku i ne ostavlja račun u djelomično obrisanom stanju. |
| **Posljedice (ishod)** | Račun više ne postoji, darivanja i doze ostaju bez veze s osobom radi sljedivosti. |

### UC-10: Definiranje radnog vremena i kapaciteta termina

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Djelatnik transfuzijskog centra |
| **Povezani zahtjevi** | F-010 |
| **Preduvjeti** | Djelatnik je prijavljen i dodijeljen centru. |
| **Glavni uspješni tok** | 1. Djelatnik otvara postavke termina svog centra.<br>2. Unosi radno vrijeme, trajanje termina i broj mjesta.<br>3. Sprema postavke.<br>4. Sustav generira termine koje darivatelji mogu rezervirati. |
| **Alternativni tokovi / Iznimke** | **3a — Nevaljane postavke:** Radno vrijeme ili kapacitet nisu valjani, sustav objašnjava pogrešku i ne sprema ih. |
| **Posljedice (ishod)** | Slobodni termini centra odgovaraju postavkama, postojeće rezervacije ostaju važeće. |

### UC-11: Obrada dolaska darivatelja

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Djelatnik transfuzijskog centra |
| **Povezani zahtjevi** | F-004, F-009, F-016, F-017, F-018, F-019, NF-02, NF-03 |
| **Preduvjeti** | Djelatnik je prijavljen i darivatelj ima rezervaciju u stanju „rezerviran”. |
| **Glavni uspješni tok** | 1. Darivatelj dolazi, djelatnik skenira QR kod rezervacije.<br>2. Sustav prikazuje rezervaciju i postavlja stanje „pristupio”.<br>3. Djelatnik otvara pregled i sustav mu prikazuje odgovore upitnika, pristup se bilježi u zapisniku.<br>4. Djelatnik prihvaća darivatelja. Stanje je „na pregledu”, a zatim „darivanje u tijeku”.<br>5. Nakon darivanja djelatnik postavlja stanje „uspješno darovano”. Pri prvom darivanju potvrđuje krvnu grupu darivatelja.<br>6. Sustav nastavlja evidencijom doze (UC-12). |
| **Alternativni tokovi / Iznimke** | **1a — QR kod nije valjan ili je rezervacija otkazana:** Sustav odbija kod i javlja razlog.<br>**1b — Darivatelj ne dođe:** Djelatnik postavlja stanje „nije pristupio”, ako je to treći put sustav na mjesec dana onemogućuje nove rezervacije darivatelja.<br>**4a — Odbijen na pregledu:** Djelatnik navodi razlog i određuje privremenu odgodu ili trajnu nepodobnost. Stanje je „odbijen na pregledu”, a sustav ažurira podobnost darivatelja. |
| **Posljedice (ishod)** | Termin je u završnom stanju. Podobnost darivatelja ažurirana je prema ishodu i svaki je pristup zdravstvenim podacima zabilježen u zapisniku. |

### UC-12: Evidencija doze i upravljanje zalihom

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Djelatnik transfuzijskog centra |
| **Povezani zahtjevi** | F-020, F-021, F-022, F-023 |
| **Preduvjeti** | Djelatnik je prijavljen i darivanje je u stanju „uspješno darovano”. |
| **Glavni uspješni tok** | 1. Djelatnik unosi oznaku doze.<br>2. Unosi krvnu grupu, količinu i rok trajanja.<br>3. Sustav sprema dozu u stanju „prikupljena” i povezuje je s darivanjem.<br>4. Djelatnik šalje dozu u testiranje, a nakon zadovoljavajućeg rezultata na zalihu.<br>5. Sustav preračunava zalihu i razinu zalihe za krvnu grupu.<br>6. Pri izdavanju bolnici djelatnik postavlja stanje „izdana bolnici”, sustav obavještava darivatelja. |
| **Alternativni tokovi / Iznimke** | **1a — Oznaka doze već postoji:** Sustav odbija unos i javlja da je oznaka zauzeta.<br>**4a — Testiranje nije zadovoljavajuće:** Djelatnik postavlja stanje „odbačena”, a darivatelj dobiva samo poruku da se javi centru.<br>**4b — Rok trajanja je istekao:** Doza prelazi u „odbačena” automatski i ne ulazi u zalihu.<br>**5a — Zaliha pada ispod kritične razine:** Sustav predlaže slanje hitnog poziva (UC-13). |
| **Posljedice (ishod)** | Doza je u novom stanju, zaliha i razine su ažurirani i vidljivi na svim pregledima. |

### UC-13: Slanje hitnog poziva

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Djelatnik transfuzijskog centra |
| **Sekundarni akter** | Servis za SMS poruke |
| **Povezani zahtjevi** | F-025, F-026, F-027, F-028 |
| **Preduvjeti** | Djelatnik je prijavljen, zaliha neke krvne grupe je ispod kritične razine i sustav je predložio hitni poziv. |
| **Glavni uspješni tok** | 1. Djelatnik otvara prijedlog hitnog poziva.<br>2. Odobrava poziv i određuje područje.<br>3. Sustav odabire podobne darivatelje tražene krvne grupe na tom području koji u tekućem tjednu nisu primili hitni poziv.<br>4. Sustav šalje SMS poruke preko servisa. pPoruka sadrži poveznicu na slobodne termine.<br>5. Sustav počinje pratiti odaziv: broj rezervacija i uspješnih darivanja nakon poziva. |
| **Alternativni tokovi / Iznimke** | **2a — Djelatnik odbija prijedlog:** Poziv se ne šalje i prijedlog se zatvara.<br>**3a — Nema podobnih darivatelja:** Sustav javlja da nema primatelja i ne šalje poziv.<br>**4a — Servis za SMS nije dostupan:** Slanje je zabilježeno kao neuspjelo. Djelatnik je obaviješten i može ponoviti slanje. |
| **Posljedice (ishod)** | Hitni poziv je zabilježen s popisom primatelja, odaziv se ažurira i vidljiv je u statistici. |

### UC-14: Pregled statistike i izvoz podataka

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Djelatnik transfuzijskog centra i administrator |
| **Povezani zahtjevi** | F-037, F-028 |
| **Preduvjeti** | Akter je prijavljen s odgovarajućom ulogom. |
| **Glavni uspješni tok** | 1. Akter otvara pregled „Statistika”.<br>2. Sustav prikazuje kretanje zaliha po krvnim grupama kroz vrijeme, prosječnu dnevnu potrošnju, procjenu dana do nestašice te odaziv na hitne pozive i akcije.<br>3. Akter odabire izvoz.<br>4. Sustav generira CSV datoteku s prikazanim podacima. |
| **Alternativni tokovi / Iznimke** | **2a — Nema dovoljno podataka:** Sustav označava da procjena nije moguća za krvne grupe bez potrošnje.<br>**4a — Izvoz nije uspio:** Sustav javlja pogrešku i ne generira datoteku. |
| **Posljedice (ishod)** | Podaci se samo prikazuju i izvoze, stanje sustava se ne mijenja. |

### UC-15: Predlaganje terenske akcije

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Organizator akcija |
| **Povezani zahtjevi** | F-029, F-031 |
| **Preduvjeti** | Organizator je prijavljen i ima dodijeljenu ulogu. |
| **Glavni uspješni tok** | 1. Organizator odabire predlaganje akcije.<br>2. Unosi lokaciju, datum, vrijeme i očekivani broj darivatelja.<br>3. Šalje prijedlog.<br>4. Sustav sprema akciju u stanju „predložena” i obavještava centar. |
| **Alternativni tokovi / Iznimke** | **3a — Nedostaju obavezni podaci ili je datum u prošlosti:** Sustav objašnjava problem i ne sprema prijedlog.<br>**4a — Lokacija se ne može prikazati na karti:** Sustav traži ispravak lokacije. |
| **Posljedice (ishod)** | Prijedlog je u stanju „predložena” i čeka odluku centra (UC-16). |

### UC-16: Odluka o prijedlogu terenske akcije

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Djelatnik transfuzijskog centra |
| **Povezani zahtjevi** | F-030, F-031 |
| **Preduvjeti** | Djelatnik je prijavljen i postoji akcija u stanju „predložena”. |
| **Glavni uspješni tok** | 1. Djelatnik otvara prijedlog akcije.<br>2. Prihvaća akciju i dodjeljuje joj mobilni tim djelatnika.<br>3. Sustav postavlja stanje „potvrđena”, a zatim otvara prijave darivatelja (stanje „otvorene prijave”).<br>4. Sustav obavještava organizatora i darivatelje unutar njihove odabrane udaljenosti. |
| **Alternativni tokovi / Iznimke** | **2a — Akcija se odbija:** Djelatnik mora unijeti obrazloženje, sustav ga prikazuje organizatoru, a akcija ne napreduje.<br>**2b — Tim nije dodijeljen:** Sustav ne dopušta prihvaćanje bez tima.<br>**Iznimka — Akcija se otkazuje:** Sustav postavlja stanje „otkazana” i obavještava prijavljene darivatelje i organizatora. |
| **Posljedice (ishod)** | Akcija je potvrđena s timom ili odbijena uz obrazloženje. |

### UC-17: Praćenje terenske akcije

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Organizator akcija |
| **Povezani zahtjevi** | F-034, F-035 |
| **Preduvjeti** | Organizator je prijavljen a akcija je potvrđena. |
| **Glavni uspješni tok** | 1. Organizator otvara svoju akciju.<br>2. Sustav prikazuje broj prijavljenih darivatelja.<br>3. Organizator šalje obavijest prijavljenim darivateljima.<br>4. Nakon održane akcije sustav prikazuje statistiku: broj darivatelja koji su pristupili, uspješno darivali ili su bili odbijeni.<br>5. Pri jubilarnom darivanju sustav obavještava organizatora kako bi mogao pripremiti priznanje. |
| **Alternativni tokovi / Iznimke** | **3a — Nema prijavljenih darivatelja:** Slanje obavijesti nije moguće, sustav to objašnjava.<br>**4a — Akcija još nije održana:** Statistika nije dostupna. |
| **Posljedice (ishod)** | Obavijesti su poslane. Statistika odgovara evidentiranim ishodima. |

### UC-18: Upravljanje korisnicima i ulogama

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Administrator |
| **Povezani zahtjevi** | F-038, F-039 |
| **Preduvjeti** | Administrator je prijavljen. |
| **Glavni uspješni tok** | 1. Administrator otvara popis korisnika i transfuzijskih centara.<br>2. Pregledava zahtjev za ulogu djelatnika centra ili organizatora akcija.<br>3. Nakon provjere dodjeljuje ulogu (djelatniku i centar).<br>4. Po potrebi deaktivira račun korisnika zbog zlouporabe.<br>5. Sustav sprema promjenu. |
| **Alternativni tokovi / Iznimke** | **3a — Provjera nije uspjela:** Administrator odbija zahtjev i korisnik ostaje darivatelj.<br>**4a — Deaktivacija vlastitog računa:** Sustav to ne dopušta. |
| **Posljedice (ishod)** | Uloga je dodijeljena ili račun deaktiviran, deaktivirani korisnik se ne može prijaviti. |

### UC-19: Podešavanje pravila podobnosti i pragova zaliha

| Element specifikacije | Opis |
| :--- | :--- |
| **Primarni akter** | Administrator |
| **Povezani zahtjevi** | F-007, F-023, F-038 |
| **Preduvjeti** | Administrator je prijavljen. |
| **Glavni uspješni tok** | 1. Administrator otvara postavke pravila.<br>2. Mijenja najmanji razmak između darivanja po spolu, najveći broj darivanja u godini i pragove zaliha (nisko, kritično) po krvnim grupama.<br>3. Sprema promjene.<br>4. Sustav koristi nove vrijednosti pri izračunu podobnosti i razina zaliha. |
| **Alternativni tokovi / Iznimke** | **3a — Nevaljane vrijednosti:** Sustav odbija spremanje (npr. prag za kritično veći od praga za nisko, nenumerički ili nepozitivni brojevi) i objašnjava pogrešku. |
| **Posljedice (ishod)** | Pravila su spremljena i razine zaliha se preračunavaju. |

<details>
<summary><strong>Primjeri specifikacija: Registracija i prijava kriznog događaja</strong></summary>

**Primjer — CrisisMap:**

**UC-001: Registracija računa.** Ovaj kratki primjer odnosi se na projekt čiji odobreni zadatak uključuje račune s adresom e-pošte i lozinkom.

| Polje | Opis |
| --- | --- |
| Primarni akter | Građanin (A-02 u primjeru) |
| ID zahtjeva | F-001 |
| Preduvjeti, ako postoje | Nema. |
| Glavni tijek | Građanin unosi adresu e-pošte; aplikacija šalje poveznicu za potvrdu; građanin potvrđuje račun. |
| Alternativni ili neuspješni tijekovi, ako postoje | Adresa e-pošte već postoji → aplikacija objašnjava zašto se registracija ne može nastaviti. |
| Rezultat | Račun je aktivan nakon potvrde. |

Ako odobreni zadatak umjesto toga koristi isključivo OAuth prijavu, zamijenite ovaj primjer odgovarajućim ponašanjem prijave; nemojte dodavati registraciju lokalnom lozinkom samo radi podudaranja s primjerom.

**UC-002: Podnošenje prijave kriznog događaja.** Ista polja mogu opisati dulji tijek u kojem odabir lokacije, provjera valjanosti i pohrana mogu ne uspjeti.

| Polje | Opis |
| --- | --- |
| Primarni akter | Građanin |
| ID zahtjeva | F-004 |
| Preduvjeti, ako postoje | Obrazac za prijavu dostupan je. Ako odobreni zadatak zahtijeva prijavu u sustav, građanin je prijavljen. |
| Glavni tijek | 1. Građanin otvara obrazac za prijavu; aplikacija prikazuje polja za vrstu kriznog događaja, razinu ozbiljnosti, opis i lokaciju.<br>2. Građanin unosi podatke i odabire lokaciju na karti ili drugim podržanim načinom.<br>3. Građanin podnosi obrazac; aplikacija provjerava obavezna polja i lokaciju.<br>4. Aplikacija pohranjuje prijavu i prikazuje potvrdu s referencom na nju. |
| Alternativni ili neuspješni tijekovi, ako postoje | **1a — Prijava u sustav nije uspjela:** Ako je prijava obavezna, a pružatelj identiteta je ne dovrši, prijava kriznog događaja ostaje nepodnesena i aplikacija objašnjava kako pokušati ponovno.<br>**2a — Odbijen pristup lokaciji:** Ako aplikacija nudi geolokaciju, ali je korisnik odbije, građanin ručno odabire lokaciju ako ta mogućnost postoji; tijek se nastavlja u koraku 3.<br>**3a — Nevaljana ili nedostajuća lokacija:** Aplikacija objašnjava problem, po mogućnosti zadržava unesene podatke i ne pohranjuje prijavu; građanin ispravlja lokaciju i ponavlja korak 3.<br>**3b — Pohrana nije uspjela:** Aplikacija prijavljuje neuspjeh i ne prikazuje lažnu potvrdu o podnošenju. |
| Rezultat | Aplikacija pohranjuje prijavu kriznog događaja i potvrđuje njezino podnošenje. |

**Odabir razine detalja:** Oba primjera koriste ista polja. UC-001 je kratak jer je glavna iznimka jasna; UC-002 zahtijeva numerirane korake i alternative jer oni mijenjaju ishod i mogu usmjeravati ispitivanje.Pri prilagodbi primjera koristite ID-ove zahtjeva i ponašanje vlastite aplikacije.

**UC-005: Moderiranje prijave kriznog događaja.** Ovaj primjer vrijedi samo za projekt u kojem odobreni opseg uključuje moderatorski pregled prijava.

| Polje | Opis |
| --- | --- |
| Primarni akter | Moderator (A-05 u primjeru) |
| ID zahtjeva | F-008 |
| Preduvjeti, ako postoje | Moderator je prijavljen i ima moderatorske ovlasti; postoji prijava koja čeka pregled. |
| Glavni tijek | 1. Moderator otvara prijavu koja čeka pregled.<br>2. Aplikacija prikazuje podatke prijave i dostupne moderatorske radnje.<br>3. Moderator odabire odobravanje ili odbijanje.<br>4. Aplikacija provjerava ovlasti, mijenja status prijave i prikazuje novi status. |
| Alternativni ili neuspješni tijekovi, ako postoje | **3a — Radnja nije potvrđena:** Moderator odustaje od promjene i prijava ostaje nepromijenjena.<br>**4a — Ovlasti nisu valjane:** Aplikacija odbija promjenu i status prijave ostaje nepromijenjen. |
| Rezultat | Prijava ima novi status ako je moderatorska radnja uspješno provedena; inače ostaje nepromijenjena. |
</details>

---

## 3.3 Provjera dosljednosti obrazaca uporabe

**Cilj:** Provjeriti međusobnu usklađenost obrazaca uporabe, aktera, zahtjeva i ispitnih slučajeva. Cjelovito mapiranje održava se isključivo u **[matrici sljedivosti zahtjeva](2-Analiza-zahtjeva.md#26-sljedivost-zahtjeva)**.

| Provjera | Pitanje za tim |
| --- | --- |
| Zahtjev | Je li svaki važan obrazac uporabe povezan s postojećim zahtjevom? |
| Akter | Odgovara li akter naveden ovdje prethodno utvrđenoj ulozi i dopuštenoj interakciji? |
| Aplikacija | Može li tim demonstrirati opisani glavni rezultat i važnu alternativu ili je opis jasno označen kao trenutačno oblikovanje? |

---

Povezivanje zahtjev → obrazac uporabe → ispitivanje održavajte **samo jednom, u [tablici sljedivosti zahtjeva](./2-Analiza-zahtjeva.md)**. Ovdje nemojte održavati drugu projektnu tablicu mapiranja ni stupac sa statusom implementacije. Ako važan zahtjev nema interakciju s ljudskim akterom, objasnite gdje se obrađuje umjesto izmišljanja aktera.

<details>
<summary><strong>Provjera pokrivenosti zahtjeva</strong></summary>

**Primjer — CrisisMap:** Tablica pokrivenosti obrazaca uporabe koju treba analizirati:

| Broj obrasca uporabe | Naziv obrasca uporabe | Obuhvaćeni funkcijski zahtjevi |
| --- | --- | --- |
| UC-001 | Registracija računa | F-001: Registracija računa putem e-pošte |
| UC-002 | Prijava kriznog događaja | F-022: Prijava i provjera kriznog događaja |
| UC-003 | Sustav upozorenja | F-045: Slanje upozorenja korisnicima i timovima |
| UC-004 | Koordinacija spašavanja | F-067: Koordinacija s hitnim službama |

---

**Problemi pokrivenosti koje treba riješiti:**

- **Pogrešan ID zahtjeva:** ova tablica povezuje UC-002 s F-022, dok se opis UC-002 u odjeljku 3.2 poziva na F-004, zahtjev za podnošenje prijave iz odjeljka 2.1.
- **Zahtjevi koji ne postoje:** F-022, F-045 i F-067 nisu definirani u oglednim zahtjevima u 2.1.
- **Obrasci uporabe bez opisa:** UC-003 i UC-004 nemaju opis u 3.2. UC-004 (koordinacija s hitnim službama) ujedno je mogućnost s vanjskim posljedicama koju opseg dopušta samo ako je uključena u odobreni zadatak.
- **Nesklad aktera:** primjer aktera u odjeljku 2.3 navodi da koordinator pomoći podnosi prijave (F-004), dok UC-002 kao primarnog aktera navodi građanina.

</details>

**Završna provjera:** Dijagram, specifikacije obrazaca uporabe i matrica sljedivosti međusobno su usklađeni. Svaki važan obrazac uporabe povezan je s postojećim zahtjevom, akteri odgovaraju stvarnim ulogama u aplikaciji, a opisani glavni i alternativni tijekovi odgovaraju ponašanju koje sustav stvarno podržava.