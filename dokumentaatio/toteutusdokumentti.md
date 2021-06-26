# Toteutusdokumentti

Tämä dokumentti antaa lyhyen yleiskuvan ohjelman rakenteesta, käyttöliittymästä, sovelluslogiikasta, ja ohjelmassa käytettävän luolastoja luovan algoritmin toiminnallisuudsta. 

## Rakenne

Ohjelman rakenne noudattaa pääpiirteiltään kolmitasoista kerrosarkkitehtuuria ja koodin pakkausrakenne on seuraava:

<img src = # width="400">

Pakkaus *userinterface* sisältää AsciiPanel kirjaston avulla toteutetun JFrame-pohjaisen käyttöliittymän. Käyttöliittymässä on luokat PlayScreen ja StartScreen, jotka toteuttavat rajapinnan Screen metodit ruudun päivittämiseen ja käyttäjän syötteiden rekisteröimiseen. 

Pakkaus *game* sisältää pelilogiikan kannalta oleelliset luokat, kuten World ja Character. Näiden luokkien avulla rakennetaan pelimaailma ja pelihahmot ja päivitetään niiden tila käyttöliittymäsyötteiden mukaisesti. Pakkauksessa *game* on myös luokat Pathfinder ja CharacterAi, jotka vastaavat tekoälyhahmojen toiminnallisuudesta mm. reitinhaulla Bellman-Fordin algoritmia käyttäen. 

Pakkaukseen *cavegenerator* on sisällytetty luolastojen luomista varten oleelliset tietorakenteet, tietotyypit ja algoritmit. Luolastot luodaan [binary space partitioning](#) algoritmilla ja jaettu tilan osiointi talletetaan BSP-puu tietorakenteeseen. Pakkauksessa on myös luokat Leaf, Room ja FloorGenerator BSP algoritmin toiminnallisuuden toteuttamiseen ja algoritmin tuottaman datan muntamiseen kaksiulotteiseksi pelimaailmatasoksi.  

Pakkauksessa *utils* on itse toteutettu taulukkolistatoteutus ArrayList, joka korvaa Javan standardikirjaston toteutuksen. Pakkauksessa on myös yksinkertainen pseudosatunnaislukugeneraattori, jota käytetään eri paikoin ohjelmassa. 

## Käyttöliittymä

Ohjelmalla on kaksi eri käyttöliittymää. Normaalikäyttöä varten tarkoitettu käyttöliittymä ja suorituskyky- ja oikeellisuustestaukseen liittyvä komentorivikäyttöliittymä. 

### Pelikäyttöliittymä

Graafinen käyttöliittymä sisältää kaksi eri näkymää:

* Alkunäkymä
* Pelinäkymä

Graafisen käyttöliittymä on toteutettu [AsciiPanel](#) kirjaston avulla. Graafinen käyttöliittymä käynnistyy Main-luokasta, mikäli ohjelmalle ei ole annettu erillisiä argumentteja. Kahta eri näkymää varten on omat luokat: PlayScreen ja StartScreen. Molemmat luokat toteuttavat Screen-rajapinnan, jossa määritellään ruudun piirtämiseen ja käyttäjän interaktioon vaikuttavat metodit *displayOutput(AsciiPanel terminal)* ja *respondToUserInput(KeyEvent key)*. Suurin osa ohjelman käytöstä tapahtuu PlayScreen luokan välityksellä, johon piirretään pelimaailma ja pelihahmot.

Käyttöliittymä on pyritty eristämään mahdollisimman paljon sovelluslogiikasta. Metodeja kutsutaan PlayScreen luokassa pelimaailmaa hallitsevan *world* olion ja pelaajan hahmoa hallitsevan *player* olion kautta. Pelimaailman ja pelaajahahmon tilaa päivitetään käyttäjän antaessa hyväksyttyjä syötteitä näppäimistöltä. Myös graafisessa käyttöliittymässä kaikki syötteet annetaan näppäimistöltä.

Toiminnallisuus on pyritty pitämään suhteellisen yksinkertaisena ja helppokäyttöisenä. Sovellus käynnistyy alkunäkymään, josta Enteriä painamalle pelinäkymään. Pelinäkymästä voi siirtyä takaisin alkunäkymään painamalla ESC. Pelistä voi poistua alkunäkymän kautta painamalla ESC.

Pelinkäymässä käyttäjä voi siirtää pelihahmoa joko ylös, alas, vasemmalle tai oikealle nuolinäppäiten avulla. Lisäksi välilyönnillä pelaaja pysyy paikallaan, mutta pelimaailma päivittyy yhden vuoron verran (esim. muut pelihahmot saattavat liikkua jonnekkin). Pelaajan liikkuessa tarpeeksi lähelle X:llä merkittyä tekoälyhahmoa, tämä pyrkii hyökkäämään pelaajan hahmoa vastaan. Pelaaja voi myös hyökätä vihollishahmoa vastaan yrittämällä liikkua siihen ruutuun, missä vihollishahmo sijaitsee. Jokainen liikkumisyritys toisen hahmon täyttämään ruutuun tulkitaan hyökkäykseksi, joka poistaa yhden pisteen hahmon elinvoimasta. Tekoälyhahmoilla on alussa kolme pistettä elinvoimaa. Kun elinvoima laskee nollaan, tekoälyhahmo tuhoutuu ja se poistetaan pelistä. 

Siirtyessään tarpeeksi lähelle luolaston oikeaa reunaa luolasto kasvaa automaattisesti. Jos luolasto on tarpeeksi suuri ja pelihahmo liikkuu liian lähelle jotain reunaa, käyttöliittymä seuraa pelihahmon liikkeitä pitäen pelihahmon aina ruudun sisällä.

### Testauskäyttöliittymä

Antamalla ohjelmalle jonkin argumentin komentoriviltä sitä käynnistettäessä, ohjelma käynnistyy komentoriviltä suoritettavaan testauskäyttöliittymään. Käyttäjä voi sitten valita, minkä testin hän haluaa suorittaa syöttämällä 1 tai 2 komentoriville ja painamalla Enter. Ohjelmasta voi poistua syöttämällä q ja painamalla Enter.

## Sovelluslogiikka

Sovelluksen kommunikointi käyttöliittymän kanssa toteutetaan pakkauksen *game* World ja Character luokkien olioiden avulla. Pelinäkymään siirryttäessä luodaan instanssi World ja Character luokkien olioista kuvaamaan pelimaailmaa ja pelaajaa. World luokan kannalta oleellisia käyttöliittymälle tarjoavia toimintoja ovat mm.

<img src = # width="800">

* void updateWorld()
* void growWorld(int horizontalGrowth)
* void addEnemies(), void addPlayer()

Vastaavasti Character luokan käyttöliittälle tarjoamia metodeja ovat mm.

* void moveBy(int mx, int my)
* void updateWorld(World w)

World luokasta tarjotaan myös käyttöliittymälle metodia *ArrayList<Character> getCharacters()*, joka palauttaa taulukkolistan pelimaailman kaikista tekoälyhahmoista. Tämän metodin avulla voidaan piirtää kaikki hahmot käyttöliittymään.

Uutta maailmaa luodessa käytetään World luokan metodia *build()*. Tämä rakentaa BSP-algoritmilla uuden BSP puun, jonka pohjalta luodaan kaksiulotteinen pelimaailma. Metodi kerää myös kaikki BSP algoritmin rakentamat huoneet omaan taulukkolistaan. Metodi *addPlayer()* lisää pelaajahahmon jonkin huoneen tyhjään kohtaan metodin *addAtEmptyLocation(Character character, Room r)* metodin avulla. Metodi *addEnemies()* puolestaan käy läpi kaikki BSP-puun luomat huoneet ja lisää niihin 20% todennäköisyydellä tekoälyhahmon. 

Metodia *growWorld(int horizontalGrowth)* käytetään kasvattamaan pelimaailmaa horisontaaliseen suuntaan. Metodi luo uuden BSP-puun, luo uuden kaksiulotteisen Tile taulukon johon pelimaailman ruudut on talletettu ja kopio vanhan taulukon alkiot uuteen taulukkoon. Sitten uuden BSP-puun luomat huoneet kopioidaan taulukon loppupäähän. Samalla luodaan uusia tekoälyhahmoja uuden alueen huoneisiin edellä mainitun *addEnemies()* metodin avulla. Uusi luotu pelimaailma asetetaan sitten vanhan päälle ja kaikille maailman hahmoille päivitetään uusi pelimaailma. Metodin parametreilla voidaan hallita pelimaailman kasvunopeutta. 

Character luokka sisältää metodeita mm. pelihahmon liikuttamista varten. Tekoälyhahmoja liikutetaan CharacterAi ja Pathfinder luokkien avulla Bellman-Fordin algoritmilla, mikäli pelaajan hahmo on tarpeeksi lähellä tekoälyhahmoa. Tätä tarkistetaan jokaisen vuoron yhteydessä pelimaailmaa päivitettäessä World luokan metodissa *updateWorld()*. Mikäli pelaaja on vihollishahmon viereisessä ruudussa, vihollishahmo hyökkää pelaajaa vastaan Character luokan *attack(Character o)* avulla. Pelaaja voi myös samalla metodilla hyökätä vihollishahmoa vastaan. Yleistapauksessa tekoälyhahmot kuitenkin vain liikkuvat johonkin satunnaiseen suuntaan.

## Luolastoalgoritmi

Ohjelman algoritmillisesti mielenkiintoisin toiminnallisuus tapahtuu *cavegenerator* pakkauksen sisällä. Luolastojen luominen perustuu [binary space partitioning](#) algoritmiin. Algoritmille annetaan parametreina haluttu maailman leveys ja korkeus, suurin sallittu lehden koko, ja huoneiden maksimi- ja minimikoot. 

<img src = # width = "800">

Kuten edellä näkyvästä kuvasta käy ilmi, algoritmi luo ensin BSP-puulle juurisolmun, joka jaetaan sitten kahdeksi kahdeksi pienemmäksi lehdeksi. Sitten jaetaan puun solmuja jaetaan yhä pienemmäksi, kunnes kaikki lehtisolmut ovat tarpeeksi riittävän pieniä. Tällöin lehden koordinaateista voidaan muodostaa huone.

***
    private void splitLeaves() {
        boolean splitSuccessfully = true;
        while (splitSuccessfully) {
            splitSuccessfully = false;
            int j = leaves.size();
            for (int i = 0; i < j; i++) {
                Leaf l = leaves.get(i);
                if (l.child1 == null && l.child2 == null) {
                    if (l.width > maxLeafSize || l.height > maxLeafSize || getRandInt(0, 5) > 4) {
                        if (l.splitLeaf()) {
                            leaves.add(l.child1);
                            leaves.add(l.child2);
                            splitSuccessfully = true;
                            j = leaves.size();
                        }
                    }
                }
            }
        }
    }
***

Kahden lehtisolmun huoneen välille luodaan käytävä, joka yhdistää huoneet. Algoritmi takaa, että kaikki huoneet ovat yhdistetty toisiinsa (kuten oikeellisuustesteistä käy ilmi). 

***
    public void createRooms(BSPTree bsp) {
            if (child1 != null || child2 != null) {
                if (child1 != null) {
                    child1.createRooms(bsp);
                }
                if (child2 != null) {
                    child2.createRooms(bsp);
                }
                if (child1 != null && child2 != null) {
                    FloorGenerator.createHall(child1.getRoom(), child2.getRoom(), bsp.getLevel());
                }
            } else {
                int w = getRandInt(bsp.roomMinSize, Math.min(bsp.roomMaxSize, width - 1));
                int h = getRandInt(bsp.roomMinSize, Math.min(bsp.roomMaxSize, height - 1));
                int x = getRandInt(this.x, this.x + (width - 1) - w);
                int y = getRandInt(this.y, this.y + (height - 1) - h);
                room = new Room(x, y, w, h);
                bsp.getRooms().add(room);
                FloorGenerator.createRoom(room, bsp.getLevel());
            }
        }
***
Lehtien jakaminen itsessään on varsin nopea operaatio, jonka aikavaativuus on luokkaa O(log n). Algoritmin kokonaisaika- ja tilavaativuus on kuitenkin *O(n\*m)*, missä *n* ja *m* ovat luotavan pelimaailman leveys ja korkeus. 

***
    for (int i = 0; i < mapWidth; i++) {
        for (int j = 0; j < mapHeight; j++) {
            level[i][j] = Tile.BOUNDS;
        }
    }
***

## Lähteet