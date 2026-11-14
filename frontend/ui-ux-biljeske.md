**glavni view**

profil, neki osnovni pregled stanja

**POV: DARIVATELJ**

- gumb za postavke
  - omogucava upravljanje profilom (uc-03)
  - izvoz podataka i brisanje racuna (uc-09)
- kartica "darivanja"
  - omogucava flow za rezervaciju termina (uc-05)
  - otkazivanje rezerviranog termina (uc-06)
  - prijavu na terensku akciju (uc-07)
  - pregled povijesti darivanja (uc-08)

**POV: DJELATNIK**

- kartica "centar"
  - omogucava pogled na statistiku centra, pregled termina i zakazivanje novog s pripadajucim parametrima (uc-10)

tijekom provodjenja termina pali se gumb "termin" koji otvara pogled gdje se moze vidjeti evidencija svih djelatnika. moguce je skenirati direktno qr (djelatnik tad valjda pali kameru mobitela) koja ga onda, pod uvjetom da je prijavljen na njemu u platformu, automatski postavlja na "pristupio" (to mora bit trigger za taj uvjet). zatim djelatnika to salje na ekran za bas taj termin tog darivatelja gdje on vidi rezultate upitnika i ima dva gumba: prihvati i odbij. ako djelatnik procijeni po odgovorima upitnika da darivatelj moze pristupiti darivanju, prihvaca ga (cime se stanje te rezervacije postavlja na "na pregledu") a u protivnom "odbijen na pregledu" sto djelatniku otvara view gdje treba napisati razlog zasto je odbijen kao i mogucu odgodu (privremeno ili trajno). svima koji su na pregledu djelatnik moze jednim klikom na listi (svakom od njih) pribaciti na stanje "darivanje u tijeku" a po zavrsetku i "uspjesno darovano".

kad prodje termin, tad automatski sustav postavlja sve darivatelje koji nisu dosli u stanje "nije pristupio". (iako uc-11 1b kaze da to manualno treba djelatnik al meni je ovako logicnije). ako bilo kad sam darivatelj manualno otkaze, tad se stanje mijenja u "otkazan".

znaci:
```
[rezervacija ne postoji] -> (UC-05 6.) -> [REZERVIRAN]

             -> (UC-11 2. darivatelj dodje u vrijeme i djelatnik skenira njegov QR) -> [PRISTUPIO]
[REZERVIRAN] -> (UC-11 1a. darivatelj otkaze gumbom) -> [TERMIN OTKAZAN]
             -> (UC-11 1b. darivatelj ne dodje na vrijeme) -> [NIJE PRISTUPIO]


[PRISTUPIO] -> (UC-11 4. djelatnik prihvaca darivatelja) -> [NA PREGLEDU]
            -> (UC-11 4a. djelatnik odbija darivatelja) -> [ODBIJEN NA PREGLEDU]

[NA PREGLEDU] -> (nakon sto darivatelj zapocne darivanje, a djelatnik stisne gumb na listi clanova) -> [DARIVANJE U TIJEKU]

[DARIVANJE U TIJEKU] -> (darivatelj gotov, sve u redu) -> [USPJESNO DAROVANO]
```

```mermaid
---
config:
  flowchart:
    curve: stepBefore
---

flowchart TB
    ne_postoji["Rezervacija 
    ne postoji"]
    rezerviran[REZERVIRAN]
    pristupio[PRISTUPIO]
    nije_pristupio[NIJE PRISTUPIO]
    otkazan[TERMIN OTKAZAN]
    na_pregledu[NA PREGLEDU]
    odbijen[ODBIJEN NA PREGLEDU]
    u_tijeku[DARIVANJE U TIJEKU]
    uspjesno[USPJESNO DAROVANO]

    ne_postoji  --> |UC-05 6.| rezerviran
    rezerviran  --> |UC-11 2.| pristupio
    rezerviran  --> |UC-11 1a.| otkazan
    rezerviran  --> |UC-11 1b.| nije_pristupio
    pristupio   --> |UC-11 4.| na_pregledu
    pristupio   --> |UC-11 4a.| odbijen
    na_pregledu --> |Djelatnik gumb| u_tijeku --> |Djelatnik gumb| uspjesno

    style nije_pristupio stroke:#ff0000
    style otkazan stroke:#ff0000
    style odbijen stroke:#ff0000
    style uspjesno stroke:#00ff00
```

## Tokovi sucelja

```mermaid
---
title: Prijava u sustav
config:
  flowchart:
    curve: stepAfter
---
flowchart TB
    root["/"]
    dialog{{Dialog za prijavu}}
    pruzatelj{{VANJSKI PRUZATELJ PRIJAVE}}
    home["/home"]
    dar_home["/home (DarivateljView)"]
    djel_home["/home (DjelatnikView)"]
    org_home["/home (VodjaAkcijaView)"]
    admin_home["/home (AdministratorView)"]

    root --> |Gumb 'prijava'| dialog --> |Gumb za vanjskog pruzatelja| pruzatelj
    pruzatelj --> |Uspjesna prijava, prva. Korisnik je po defaultu darivatelj| dar_home
    pruzatelj --> |Uspjesna prijava, nije prva| home
    pruzatelj --> |Odustano ili odbijena prijava| root
    home --> |Korisnik je darivatelj| dar_home
    home --> |Korisnik je djelatnik| djel_home
    home --> |Korisnik je vodja akcije| org_home
    home --> |Korisnik je administrator| admin_home
```

```mermaid
---
title: Rezervacija termina
config:
  flowchart:
    curve: stepAfter
---

flowchart TB
    home["/home"]
    darivanja["/home/appoint"]
    pogled_rezervacije{{Pogled rezervacija}}
    widget_centar_datum([Korisnik upisuje u polja centar i datum])
    slobodni_termini{{Prikazuju se slobodni termini ispod}}
    nema_slobodnih_termina{{Obavijest da nema slobodnih mjesta i prijedlog za druge}}
    gumb_rezerviraj([Gumb 'Rezerviraj' na terminu])
    provjera[Dialog forma za provjeru podobnosti]
    potvrda_rezervacije([Gumb potvrdi rezervaciju])
    zapis_rezervacije{{Sustav sprema rezervaciju, rezultate provjere, smanjuje broj slobodnih mjesta}}
    qr_prikaz{{Prikaz QR koda darivatelju}}
    spremi_google_calendar([Gumbi za spremanje ili odbijanje spremanja rezervacije u kalendar])
    obavijest_kalendar_nije_uspio{{"Stranica javlja da sustav nije uspio (toast ili nesto)"}}

    home --> |Gumb 'darivanja'| darivanja --> pogled_rezervacije --> widget_centar_datum
    widget_centar_datum --> |Ima slobodnih mjesta za te parametre| slobodni_termini
    widget_centar_datum --> |Nema slobodnih mjesta za te parametre| nema_slobodnih_termina
    slobodni_termini --> gumb_rezerviraj
    nema_slobodnih_termina --> gumb_rezerviraj
    gumb_rezerviraj --> provjera
    provjera --> |Provjera je odbijena| home
    provjera --> |Provjera je prihvacena| potvrda_rezervacije
    potvrda_rezervacije --> zapis_rezervacije --> qr_prikaz --> |U redu| spremi_google_calendar
    potvrda_rezervacije --> |U medjuvremenu se popunilo| darivanja
    spremi_google_calendar --> |Uspjelo| home
    spremi_google_calendar --> |Neuspjelo| obavijest_kalendar_nije_uspio --> home
```

```mermaid
---
title: Otkazivanje rezerviranog termina
config:
  flowchart:
    curve: stepAfter
---

flowchart TB
    home["/home"]
    rezervacija{{Dialog 'rezervacija'}}
    otkazivanje_potvrdi{{Novi dialog kojim se potvrdjuje ili odbija rezervacija}}
    ima_2_sata((Je li ima 2 sata?))
    otkaz{{Sustav brise rezervaciju i stavlja status 'otkazano'}}
    ne_otkaz{{Sustav ne brise rezervaciju i daje popup da se to ne smije}}

    home --> rezervacija --> |Gumb 'otkazi'| otkazivanje_potvrdi
    otkazivanje_potvrdi --> |Da| ima_2_sata --> |Ima| otkaz
    ima_2_sata --> |Nema| ne_otkaz
    otkaz --> home
    ne_otkaz --> home
    otkazivanje_potvrdi --> |Ne| home
```