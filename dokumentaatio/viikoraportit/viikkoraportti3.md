# Viikkoraportti 24-29.5

Käytetty tuntimäärä: 15

### Mitä olen tehnyt tällä viikolla?

Käyttöliittymäkirjasto tuli vaihdettua Zirconista [AsciiPaneliin](https://github.com/trystan/AsciiPanel), koska jälkimmäisen käyttö on yksinkertaisempaa. Ulkoasu on vähän 
tylsempi, mutta tähän projektiin ihan riittävän hyvä. Ohjelman luokkarakenne on nyt selkeämpi ja testaukset ovat edistyneet. Myös koodin kommentointia on aloiteltu. Checkstyle ja testauskattavuuden seuranta on nyt konfiguroitu.

### Miten ohjelma on edistynyt?

Luolia voi nyt generoida käyttöliittymästä ja luolastossa voi liikkua lattioita pitkin seinien sisällä.

![cavegif](https://user-images.githubusercontent.com/70325495/120069014-504b5200-c08c-11eb-96fa-545ae2b9ef88.gif)

Käyttöliittymä tuli vaihdettua ja sitä myötä siis uusi käyttöliittymäkoodi. Pelilogiikkaa varten tuli kirjoiteltua koodia ja sen pitäisi olla helposti laajennettavissa tarpeen
mukaan. Ohjelman luokkarakenne on nyt myös ihan hyvällä mallilla.

### Mitä opin tällä viikolla / tänään?

AsciiPanelin käyttö, joka on on suhteellisen yksinkertainen käyttöliittymäkirjasto. [Binary space algoritmista](https://en.wikipedia.org/wiki/Binary_space_partitioning) tuli luettua lisää
ja pohdittua miten siitä saisi dynaamisen. Yleisesti asiaa luolastojen generoinnista ja pelihahmon liikuttamisesta luolastossa. 

### Mikä jäi epäselväksi tai tuottanut vaikeuksia? 

Ei nyt tule mitään erityistä mieleen.

### Mitä teen seuraavaksi?

* Muiden tekoälyhahmojen sijoittaminen luolastoon. Niiden liikkuminen voitaneen toteuttaa esim. Dijkstran algoritmin avulla
* Luolaston dynaaminen kasvaminen pelaajan liikkumisen perusteella
* Oma satunnaisnumerogeneraattori ja ArrayList toteutuksen aloittaminen
* Muita algorimteja luolaston generointiin?
