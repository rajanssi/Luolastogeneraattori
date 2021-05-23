package cavegenerator;

import static cavegenerator.Utils.getRandInt;

class Leaf {
    int x, y;
    int width, height;
    int MIN_LEAF_SIZE;
    Leaf child1, child2;
    Rect room, room1, room2;

    Leaf(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        MIN_LEAF_SIZE = 10;
        child1 = null;
        child2 = null;
        room1 = null;
        room2 = null;
    }

    boolean splitLeaf() {
        if (child1 != null || child2 != null)
            return false;

        int max;
        boolean splitHorizontally = getRandInt(0, 1) == 1;
        if ((double) width / height >= 1.25)
            splitHorizontally = false;
        else if ((double) height / width >= 1.25)
            splitHorizontally = true;

        if (splitHorizontally)
            max = height - MIN_LEAF_SIZE;
        else
            max = width - MIN_LEAF_SIZE;

        if (max <= MIN_LEAF_SIZE)
            return false;

        int split = getRandInt(MIN_LEAF_SIZE, max);

        if (splitHorizontally) {
            child1 = new Leaf(x, y, width, split);
            child2 = new Leaf(x, y + split, width, height - split);
        } else {
            child1 = new Leaf(x, y, split, height);
            child2 = new Leaf(x + split, y, width - split, height);
        }

        return true;
    }

    void createRooms(BSPTree bsp) {
        if (child1 != null || child2 != null) {
            if (child1 != null)
                child1.createRooms(bsp);

            if (child2 != null)
                child2.createRooms(bsp);

            if (child1 != null && child2 != null) {
                bsp.createHall(child1.getRoom(), child2.getRoom());
            }
        } else {
            int w = getRandInt(bsp.ROOM_MIN_SIZE, Math.min(bsp.ROOM_MAX_SIZE, width - 1));
            int h = getRandInt(bsp.ROOM_MIN_SIZE, Math.min(bsp.ROOM_MAX_SIZE, height - 1));
            int x = getRandInt(this.x, this.x + (width - 1) - w);
            int y = getRandInt(this.y, this.y + (height - 1) - h);

            room = new Rect(x, y, w, h);
            bsp.createRoom(room);
        }
    }

    Rect getRoom() {
        if (room != null) return room;
        else {
            if (child1 != null) room1 = child1.getRoom();
            if (child2 != null) room2 = child2.getRoom();
            if (child1 == null && child2 == null) return null;
            else if (room2 == null) return room1;
            else if (room1 == null) return room2;
            else if (getRandInt(0, 1) == 1) return room1;
            else return room2;
        }
    }
}