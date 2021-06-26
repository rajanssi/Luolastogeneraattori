# Testausdokumentti

Ohjelmaa on yksikkötestattu JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein. Suorituskykyä ja oikeellisuutta on testattu myös ohjelman laajennuksella.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Yksikkötesteillä testataan kokonaisvaltaisesti sekä pelilogiikkaa, että pelimaailmaa pelimaailman rakentaamiseen käytettävää BSP algoritmia ja BSP-puu tietorakennetta. Lisäksi testataan mm. itse tehtyä satunnaislukugeneraattoria ja omaa taulukkolistatoteutusta. 

### Testauskattavuus

JUnitilla suoritetetun yksikkötestauksen rivikattavuus on *96%* ja haarautumiskattavuus *87%*.

<img src="https://user-images.githubusercontent.com/70325495/123524157-1d1ed180-d6d1-11eb-8e69-61f98bf8fbd5.png" width="900">

Yksikkötestauksesta on jätetty pois käyttöliittymätoiminnallisuuden ja suorituskykytestien yksikkötestaukset.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/rajanssi/luolastogeneraattori/blob/master/dokumentaatio/maarittelydokumentti.md) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. 

## Suorituskyky- ja oikeellisuustestit

### Oikeellisuus


