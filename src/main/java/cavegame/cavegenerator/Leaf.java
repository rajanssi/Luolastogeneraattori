package cavegame.cavegenerator;

import static cavegame.utils.RandomNumberGenerator.*;

/**
 * BSP-puussa käytettävät lehtisolmut toteutettu tämän luokan avulla. Jokaisella lehdellä on potentiaalisesti kaksi lasta
 * (perustuen binäärihakupuun malliin).
 */
class Leaf {
    private final int x, y;
    final int width, height;
    Leaf child1, child2;
    Room room, room1, room2;

    /**
     * Konstruktori lehtisolmulle, joka ottaa solmun sijainnin xy-akselilla ja sen leveyden ja korkeuden.
     *
     * @param x      Lehden sijainti x-akselilla.
     * @param y      Lehden sijainti y-akselilla
     * @param width  Lehden leveys.
     * @param height Lehden korkeus.
     */
    public Leaf(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        child1 = null;
        child2 = null;
        room1 = null;
        room2 = null;
    }

    /**
     * Jakaa tämän lehden kahteen uuteen lehteen.
     *
     * @return Palauttaa true jos jakaminen onnistui, muuten false.
     */
    public boolean splitLeaf() {
        if (child1 != null || child2 != null) {
            return false;
        }

        int max;
        boolean splitHorizontally = getRandInt(0, 1) == 1;
        if ((double) width / height >= 1.25) {
            splitHorizontally = false;
        } else if ((double) height / width >= 1.25) {
            splitHorizontally = true;
        }

        int minLeafSize = 10;
        if (splitHorizontally) {
            max = height - minLeafSize;
        } else {
            max = width - minLeafSize;
        }

        if (max <= minLeafSize) {
            return false;
        }

        int split = getRandInt(minLeafSize, max);

        if (splitHorizontally) {
            child1 = new Leaf(x, y, width, split);
            child2 = new Leaf(x, y + split, width, height - split);
        } else {
            child1 = new Leaf(x, y, split, height);
            child2 = new Leaf(x + split, y, width - split, height);
        }

        return true;
    }

    /**
     * Luo huoneita tämän lehden sisälle.
     *
     * @param bsp Lehden BSP-puu, joka sisältää mm. kentät huoneiden maksimi- ja minimikoolle.
     */
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

    /**
     * Palauttaa lehden jonkin huoneen.
     *
     * @return Palauttaa huoneen 1, 2, tai null jos huoneita ei ole.
     */
    public Room getRoom() {
        if (room != null) {
            return room;
        } else {
            if (child1 != null) {
                room1 = child1.getRoom();
            }
            if (child2 != null) {
                room2 = child2.getRoom();
            }
            if (child1 == null && child2 == null) {
                return null;
            } else if (room2 == null) {
                return room1;
            } else if (room1 == null) {
                return room2;
            } else if (getRandInt(0, 1) == 1) {
                return room1;
            } else {
                return room2;
            }
        }
    }
}
