# Testausdokumentti

Ohjelmaa on yksikkötestattu JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein. Suorituskykyä ja oikeellisuutta on testattu myös ohjelman laajennuksella.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Yksikkötesteillä testataan kokonaisvaltaisesti sekä pelilogiikkaa, että pelimaailmaa pelimaailman rakentaamiseen käytettävää BSP algoritmia ja BSP-puu tietorakennetta. Lisäksi testataan mm. itse tehtyä satunnaislukugeneraattoria ja omaa taulukkolistatoteutusta. 

### Testauskattavuus

JUnitilla suoritetetun yksikkötestauksen rivikattavuus on *96%* ja haarautumiskattavuus *87%*.

<img src="https://user-images.githubusercontent.com/70325495/123524157-1d1ed180-d6d1-11eb-8e69-61f98bf8fbd5.png" width="900">

Testauskattavuudesta on jätetty pois käyttöliittymätoiminnallisuuden ja suorituskykytestien yksikkötestaukset.

### Järjestelmätestaus

Järjestelmätason testausta on tehty manuaalisesti projetkin alusta alkaen, joka oli oleellista etenkin käyttöliittymän testauksen kannalta. Myös mm. tekoälyhahmojen reitinhaun ja luolastojen visuaalisen ilmeen selvityksessä manuaalinen järjestelmätason testaus on ollut oleellista.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/rajanssi/luolastogeneraattori/blob/master/dokumentaatio/maarittelydokumentti.md) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. 

## Suorituskyky- ja oikeellisuustestit

### Oikeellisuus

Luotujen luolastojen oikeellisuutta on testattu siten, että jokaisesta huoneesta on oltava jokin reitti johonkin toiseen luolaston huoneeseen. Reittejä etsitään tässä syvyyshaun avulla. 

***
    private void validityTest() {
        int size = 1000;
        System.out.printf("Tarkistetaan, että %d x %d kokoisen luolaston jokainen huone on yhdistetty\n", size, size);
        System.out.println("Kuinka monta testiä ajetaan?");
        int tests = reader.nextInt();
        int index = 1;
        long begin = System.currentTimeMillis();
        while (index <= tests) {
            boolean error = false;
            world = new World(size, size, 10).build();
            world.growWorld(size);
            System.out.printf("Luolastossa %d huoneita on yhteensä %d kappaletta ", index, world.rooms.size());
            boolean[][] seen = new boolean[size * 2][size];
            for (int i = 0; i < world.rooms.size(); i++) {
                Room r = world.rooms.get(i);
                for (int j = i; j < world.rooms.size(); j++) {
                    Room r2 = world.rooms.get(i);
                    dfs(seen, r.centerX(), r.centerY(), r2);
                    if (!seen[r2.centerX()][r2.centerY()]) {
                        error = true;
                        break;
                    }
                }
            }
            if (!error) {
                System.out.println("... ja kaikki huoneet on yhdistetty");
            } else {
                System.out.println("... ja kaikki huoneet eivät ole yhdistetty");
            }
            index++;
        }
        long end = System.currentTimeMillis();
        System.out.println("Testeihin kului aikaa yhteensä " + (end - begin) + " millisekuntia.");
    }
***

Testejä tuli tehtyä allekirjoittaneen koneella 1000 kappaletta, johon aikaa kului noin 2 minuuttia. 2000x1000 kokoisessa luolastossa oli tyypillisesti hieman yli 10000 huonetta. Jokaisessa testissä kaikista huoneista oli olemassa reitti jokaiseen muuhun huoneeseen. Huomionarvoista näissä testeissä myös oli se, että luolasto pysyi yhtenäisenä pelimaailman kasvattamisenkin jälkeen. Tämä oli oletuksena myös manuaalisten järjestelmätason testausten jälkeen.

Oikeellisuuden lisäksi testattiin algoritmin suorituskykyä. Testeissä luotiin 9 luolastoa joista pienin oli 64x64 ja suurin 16384x16384.

***
    private void performanceTest() {
        System.out.println("Suoritetaan testejä...");
        for (int i = 64; i <= 16384; i *= 2) {
            long start = System.currentTimeMillis();
            world = new World(i, i, 20).build();
            long end = System.currentTimeMillis();
            String message = String.format("%d x %d luolaston luomiseen aikaa kului " + (end - start) + " millisekuntia", i, i);
            System.out.println(message);
        }
    }
***

Suorituskykytestien tulokset:

| Koko        | Luomisaika      |
| ------------- |:-------------:| 
| 64 x 64          | 4ms |
| 128 x 128        | 2ms | 
| 256 x 256        | 4ms | 
| 512 x 512        | 8ms |
| 1024 x 1024      | 24ms | 
| 2048 x 2048      | 118ms | 
| 4096 x 4096      | 555ms | 
| 8192 x 8192      | 3166ms | 
| 16384 x 16384    | 16078ms | 

Alla vielä samat tulokset kaaviossa.

![graph](https://user-images.githubusercontent.com/70325495/123549301-147dd800-d771-11eb-9620-7bd8dfeea56e.png)

Tulokset vastaavat hyvin odotuksia algoritmin aikavaativuuden ollessa O(n^2). Käytännössä luolaston koon kasvattamiseen kuluu eksponentiaalien määrä aikaa. Tämä tarkoittaa, että kerralla ei valtavia luolastoja pysty tekemään. Käytännössä kuitenkin jo 1000x1000 luolasto on melko suuri ja luolasto kasvaa dynaamisesti pelaajan liikkeiden mukaan. Todellisessa maailmassa tästä ei siis muodostu ongelmaa.
