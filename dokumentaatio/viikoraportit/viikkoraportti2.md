# Viikkoraportti 16-23.5

Käytetty tuntimäärä: 15

### Mitä olen tehnyt tällä viikolla?

Käyttöliittymäkirjaston valinta ja toteutuksen pohtimista viikon alkupuoliskolla. Ohjelman kirjoittaminen saatiin alkuun. Luolia voi nyt generoida [binary space partitioning](https://en.wikipedia.org/wiki/Binary_space_partitioning) algoritmilla. Testaus ei ole vielä kattavaa, mutta sille on nyt runko valmiina. 

### Miten ohjelma on edistynyt?

Ohjelmassa on nyt alustava käyttöliittymä ja luolia generoiva algoritmi. Luolia generoiva BSP algoritmi on toteutettu [tämän](https://github.com/AtTheMatinee/dungeon-generation/blob/master/dungeonGenerationAlgorithms.py) mallin mukaan, toki eri kielellä ja tähän tarkoitukseen muutettuna. Ohjelman graafisen käyttöliittymän piirtämiseen käytetään [Zircon](https://hexworks.org/projects/zircon/) kirjastoa. Ohjelman rakenne ei ole vielä mallillaan.

### Mitä opin tällä viikolla / tänään?

Erilaisia luolaston generointiin tarkoitettuja algoritmeja tuli tutkailtua. Erityisesti edellä mainittu BSP algoritmi ja BSP-puu. Käyttöliittymän rakentaminen Zirconilla. 

### Mikä jäi epäselväksi tai tuottanut vaikeuksia? Vastaa tähän kohtaan rehellisesti, koska saat tarvittaessa apua tämän kohdan perusteella.

Ei nyt tule mitään erityistä mieleen.

### Mitä teen seuraavaksi?

JUnit yksikkötestaus kunnolla kattavaksi. Koodin kommentointi, JavaDoc, checkstyle ja jacocon konffaukset kuntoon. Ohjelman luokkarakenteen järkeellistäminen. 
