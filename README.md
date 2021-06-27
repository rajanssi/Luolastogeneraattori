# Luolastogeneraattori

Lyhyt kuvaus projektista 

## Käyttöohje

Tähän käyttöohje

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

Testit suoritetaan komennolla

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

generoi hakemistoon target suoritettavan jar-tiedoston Sudoku-1.0-SNAPSHOT.jar

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/rajanssi/ot-harjoitustyo/blob/master/Sudoku/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
