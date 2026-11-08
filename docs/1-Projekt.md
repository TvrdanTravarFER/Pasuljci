# 1. Opseg projekta

_KrvnaVeza_ je web-aplikacija koja povezuje darivatelje krvi, transfuzijske centre i organizatore terenskih akcija darivanja. Darivatelji u njoj vide kada i gdje mogu darivati krv i rezervirati termin, centri prate zalihe po krvnim grupama i u slučaju potrebe ciljano obavještavaju odgovarajuće darivatelje, a organizatori akcija planiraju terenske akcije i prate odaziv. Aplikacija ne zamjenjuje liječnički pregled ni odluku djelatnika centra: ona organizira termine, evidenciju i obavijesti oko darivanja, dok medicinsku odluku i laboratorijsko testiranje i dalje provode zdravstveni djelatnici.

## 1.1 Problem i cilj projekta

Bolnice svakodnevno trebaju krv za operacije, liječenje onkoloških bolesnika i zbrinjavanje hitnih stanja. Krv se ne može proizvesti, a njezini sastojci imaju ograničen rok trajanja, pa zalihe moraju stalno obnavljati dobrovoljni darivatelji. Zalihe pritom nisu stabilne: ljeti i u vrijeme blagdana darivatelja je manje, a zaliha pojedinih krvnih grupa, osobito negativnih, zna pasti ispod kritične razine.

Mnogi bi darivatelji češće darivali krv kad bi znali kada ponovno smiju darivati krv, gdje i kada se održava najbliža akcija darivanja i je li upravo njihova krvna grupa trenutno potrebna. Transfuzijski centri i organizatori akcija darivatelje danas najčešće obavještavaju pozivima, plakatima i porukama koje ne stignu do svih, dok se evidencija darivanja vodi odvojeno od informacija dostupnih darivateljima.

Cilj projekta je izraditi funkcionalnu web-platformu koja na jednom mjestu povezuje darivatelje, transfuzijske centre i organizatore akcija. Aplikacija treba omogućiti:

- *Darivatelj* može vidjeti centre i akcije na karti, saznati datum od kojeg ponovno smije darivati, rezervirati termin i pregledati povijest svojih darivanja.
- *Djelatnik* centra vodi termine, evidentira darivanja i doze krvi, prati zalihe po krvnim grupama i šalje hitne pozive samo podobnim darivateljima tražene krvne grupe.
- *Organizator akcija* predlaže terensku akciju, prati prijave i nakon akcije vidi statistiku.
- *Administrator* upravlja korisnicima, ulogama, pravilima podobnosti i pragovima zaliha.

Očekivana korist projekta:

- *Darivatelj:* manje nagađanja o tome kada smije darivati i gdje je najbliži termin. 
- *Transfuzijski centar:* zajednička evidencija termina, doza i zaliha na jednom mjestu te mogućnost da hitni poziv dobiju samo darivatelji koji su podobni, odgovarajuće krvne grupe i na odgovarajućem području.
- *Organizator akcija:* jasan postupak prijedloga akcije, pregled broja prijavljenih darivatelja i statistika nakon održane akcije.

## 1.2 Ciljani korisnici i dionici

Aplikacija ima pet korisničkih uloga, a dio dionika s aplikacijom nema izravan doticaj, ali svojim zahtjevima određuje ograničenja sustava (npr. zaštita osobnih podataka i anonimnost).

| Skupina korisnika ili dionika | Potreba ili interes relevantan za ovaj projekt |
| --- | --- |
| Posjetitelj (neregistrirani korisnik) | Pregledati kartu transfuzijskih centara i najavljenih akcija te javno stanje zaliha po krvnim grupama (kritično, nisko, dovoljno) bez prijave. |
| Darivatelj | Znati kada ponovno smije darivati, pronaći najbliži centar ili akciju, rezervirati i otkazati termin, primati obavijesti, vidjeti povijest svojih darivanja i preuzeti potvrdu o darivanju. |
| Djelatnik transfuzijskog centra | Definirati radno vrijeme i kapacitet termina, zaprimiti darivatelja, evidentirati ishod pregleda i darivanja te dozu krvi, pratiti zalihe, slati hitne pozive i odlučivati o prijedlozima terenskih akcija. |
| Organizator terenskih akcija (npr. gradsko društvo Crvenog križa) | Predložiti akciju, vidjeti broj prijavljenih darivatelja, slati im obavijesti i pregledati statistiku održane akcije. |
| Administrator | Upravljati korisnicima i transfuzijskim centrima, dodjeljivati uloge nakon provjere, podešavati pravila podobnosti i pragove zaliha te deaktivirati račun u slučaju zlouporabe. |
| Bolnica (dionik) | Prima doze krvi iz centra. Ne koristi aplikaciju, a u sustavu se bilježi samo da je doza izdana bolnici. |
| Primatelj krvi (dionik) | Nema veze s aplikacijom, sustav ne pohranjuje i ne prikazuje nikakve podatke o primatelju. |
| Nadzorno tijelo za zaštitu osobnih podataka (dionik) | Zdravstveni podaci darivatelja osjetljivi su osobni podaci te dionik ne koristi aplikaciju, ali određuje ograničenja: pristup samo djelatnicima centra, zapisnik pristupa, izvoz podataka i anonimizacija pri brisanju računa. |

## 1.3 Granice projekta

**Uključeno:**

- Javni pregled karte centara i akcija te razina zaliha po krvnim grupama.
- Prijava putem _OAuth 2.0_ i profil darivatelja (uključujući postavke obavijesti).
- Izračun podobnosti darivatelja prema povijesti darivanja i pravilima koja podešava administrator.
- Definiranje termina u centru, rezervacija s upitnikom za samoprocjenu, otkazivanje, QR kod i podsjetnici.
- Tijek darivanja: zaprimanje, pregled, darivanje, odbijanje (privremeno ili trajno), evidencija doze i njezinih stanja te izračun zaliha iz doza na zalihi.
- Hitni pozivi s praćenjem odaziva.
- Terenske akcije: prijedlog organizatora, odluka centra, prijave darivatelja, obavijesti i statistika.
- Jubilarne digitalne značke, potvrda o darivanju u PDF formatu, statistika za djelatnike s izvozom u CSV.
- Administracija korisnika, uloga, pravila podobnosti i pragova zaliha.
- Zaštita osjetljivih podataka: ograničen pristup, zapisnik pristupa, izvoz podataka i brisanje računa uz anonimizaciju.

**Izvan opsega ili odgođeno:**

- *Medicinska odluka o podobnosti:* Upitnik za samoprocjenu samo upozorava darivatelja, odluku donosi djelatnik centra pri pregledu.
- *Laboratorijsko testiranje doza:* Sustav bilježi samo ishod testiranja koji unese djelatnik.
- *Evidencija primatelja i transfuzije u bolnici:* Bolnica nije korisnik sustava, posljednje stanje doze u sustavu je stanje „izdana bolnici”.
- *Izvorne mobilne aplikacije:* Aplikacija je responzivna web-aplikacija. Razvoj zasebne aplikacije treba previše vremena.
- *Prediktivni modeli potražnje:* Procjena broja dana do nestašice računa se iz prosječne dnevne potrošnje. napredna predviđanja nisu dio aplikacije zbog njihove tehnološke složenosti.

**Vanjske ovisnosti:**

- *Pružatelj identiteta (OAuth 2.0)*, npr. Google – prijava i potvrda valjane adrese e-pošte. Bez njega nema pristupa funkcijama za prijavljene korisnike.
- *Usluga prikaza karte* (_Google Maps_ ili _OpenStreetMap_) – prikaz centara i akcija. Podatke o centrima i akcijama aplikacija čuva sama.
- *Servis za slanje SMS poruka* (npr. _Infobip_) – hitni pozivi i obavijesti darivateljima koji su odabrali SMS.
- *Usluga slanja e-pošte* – obavijesti darivateljima koji su odabrali e-poštu.
- *Google Calendar* – dodavanje rezerviranog termina u kalendar darivatelja.
- *Poslužiteljska okolina i baza podataka* za rad aplikacije.

## 1.4 Postojeći pristupi i opravdanost projekta

Transfuzijski centri i organizatori akcija darivatelje danas najčešće obavještavaju pozivima, plakatima i porukama, dok se evidencija darivanja vodi odvojeno od informacija koje vide darivatelji. Takav pristup omogućuje da se akcija najavi, ali ne daje darivatelju osobni pregled: kada ponovno smije darivati, gdje je najbliži slobodan termin i je li njegova krvna grupa trenutno potrebna.

KrvnaVeza objedinjuje evidenciju darivanja, termine, zalihe i obavijesti na jednom mjestu te ciljano poziva samo podobne darivatelje tražene krvne grupe. Aplikacija ne zamjenjuje medicinski pregled ni odluku djelatnika centra.

---

>**Završna provjera:** Opis problema, cilj projekta, korisnici i dionici, granice opsega te postojeći pristupi međusobno su usklađeni. Navedene koristi ne predstavljaju neprovjerene tvrdnje, a uključene i izostavljene funkcionalnosti odgovaraju odobrenom projektnom zadatku.
