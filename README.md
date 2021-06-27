# Luolastogeneraattori

Lyhyt kuvaus projektista 

## Käyttöohje

Lataa ensin tiedosto [CaveGame-1.0.jar](https://github.com/rajanssi/Luolastogeneraattori/releases/tag/1). 

### Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar CaveGame-1.0.jar
```

Jos haluat suorittaa testejä, käynnistä ohjelma komennolla 

```
java -jar CaveGame-1.0.jar test
```

### Pelin aloittaminen

Ensimmäistä kertaa peliä pelatessasi voit aloittaa uuden pelin painamalla *Enter* aloitusvalikossa. Valikosta voit myös siirtyä poistua ohjelmasta painamalla *Esc*.

### Pelin pelaaminen

Pelinäkymässä oma pelihahmosi on merkitty '@' merkillä. Voit liikkua lattioita pitkin, jotka on merkitty '.' merkillä. Liikkuminen tapahtuu nuolinäppäimillä, ja voit liikkua joko ylös, alas, vasemmalle tai oikealle. Jokaisen liikahduksen jälkeen pelimaailman tila päivitetään. 

Jos liikut liian lähelle 'X' merkillä merkattua tekoälyhahmoa, tämä lähtee jahtaamaan pelihahmoasi. Voit hyökätä tekoälyhahmoa jos olet sen viereisessä ruudussa ja pyrit liikkumaan sen asuttamaan ruutuun. Tekoälyhahmo tuhoutuu kolmen hyökkäyksen jälkeen. 

Jos liikut luolaston oikeaan laitaan, peli generoi lisää huoneita luolastoon. 

### Pelistä poistuminen

Voit poistua ohjelmasta milloin tahansa painamalla ruksia yläoikealta. Voit myös poistua aloitusnäkymän kautta painamalla *Esc*. Aloitusnäkymään pääsee pelinäkymästä painamalla *Esc*.

### Testaus

Testausnäkymässä voit suorittaa joko oikeellisuus- tai suorituskykytestejä. Päävalikossa kysytään, mitä testejä haluat suorittaa. Kirjoita joko "1" tai "2" komentoriville ja paina *Enter*. Oikeellisuustesteissä kysytään vielä, kuinka monta testiä halutaan suorittaa. Anna tähän jokin järkevältä tuntuva numero (reilusti yli 1000 voi viedä tovin) ja paina *Enter*. Jos kirjoitat tässä jotain muuta kuin numeron komentoriville ja painat *Enter* ohjelma räjähtää. 

Kun testit on suoritettu voit poistua ohjelmasta kirjoittamalla "q" komentoriville ja painamalla *Enter*,


## Viikkoraportit

[Viikko 1](https://github.com/rajanssi/Luolastogeneraattori/blob/main/dokumentaatio/viikoraportit/viikkoraportti1.md)

[Viikko 2](https://github.com/rajanssi/Luolastogeneraattori/blob/main/dokumentaatio/viikoraportit/viikkoraportti2.md)

[Viikko 3](https://github.com/rajanssi/Luolastogeneraattori/blob/main/dokumentaatio/viikoraportit/viikkoraportti3.md)

[Viikko 4](https://github.com/rajanssi/Luolastogeneraattori/blob/main/dokumentaatio/viikoraportit/viikkoraportti4.md)

[Viikko 5](https://github.com/rajanssi/Luolastogeneraattori/blob/main/dokumentaatio/viikoraportit/viikkoraportti5.md)

[Viikko 6](https://github.com/rajanssi/Luolastogeneraattori/blob/main/dokumentaatio/viikoraportit/viikkoraportti6.md)

## Dokumentaatio

[Määrittelydokumentti](https://github.com/rajanssi/Luolastogeneraattori/blob/main/dokumentaatio/maarittelydokumentti.md)

[Toteutusdokumentti](https://github.com/rajanssi/Luolastogeneraattori/blob/main/dokumentaatio/toteutusdokumentti.md)

[Testausdokumentti](https://github.com/rajanssi/Luolastogeneraattori/blob/main/dokumentaatio/testausdokumentti.md)

## Komentorivitoiminnot

### Testaus

Yksikkötestit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon target suoritettavan jar-tiedoston CaveGame-1.0.jar

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/rajanssi/Luolastogeneraattori/blob/main/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_