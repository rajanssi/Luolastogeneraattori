# Testausdokumentti

Ohjelmaa on yksikkötestattu JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

JUnit testien päähuomiona on sudoku.domain pakkauksen SudokuService luokka. Sen kautta pääsee käsiksi lähes kaikkiin sovelluslogiikan muihin luokkiin, joten sitä testaamalla kaikkien muiden luokkien toiminnallisuus testautuu samalla. Lisäksi käyttöliittymä ja sovelluslogiikka kommunikoivat pääasiassa SudokuService luokan kautta, joten on mielekästä testata suurin osa sovelluslogiikan toiminnallisuuksista sen kautta. GameServiceTest on siis pääasiassa integraatiotestausta. Esim. SettingsTest on kuitenkin puhtaasti yksikkötestausta.

Yksikkötesteillä testataan kokonaisvaltaisesti sekä pelilogiikkaa, että pelimaailmaa pelimaailman rakentaamiseen käytett

### Oikeellisuus



### Testauskattavuus

JUnitilla suoritetetun yksikkötestauksen rivikattavuus on *91%* ja haarautumiskattavuus *87%*.

<img src="https://raw.githubusercontent.com/rajanssi/ot-harjoitustyo/master/dokumentaatio/kuvat/b-1.png" width="800">


### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/rajanssi/luolastogeneraattori/blob/master/dokumentaatio/maarittelydokumentti.md) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien yhteydessä on sudokuruudut yritetty täyttää myös virheellisillä arvoilla kuten kirjaimilla.

## Suorituskyky- ja oikeellisuustestit

Suorituskykytesteistä on erillinen dokumentti [täällä](#)