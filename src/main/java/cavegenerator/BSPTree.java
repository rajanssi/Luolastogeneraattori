package cavegenerator;

import java.util.ArrayList;

import static cavegenerator.Utils.getRandInt;

public class BSPTree {
    int MAX_LEAF_SIZE;
    int ROOM_MAX_SIZE;
    int ROOM_MIN_SIZE;
    int MAP_WIDTH;
    int MAP_HEIGHT;
    int[][] level;

    public BSPTree(int MAP_WIDTH, int MAP_HEIGHT, int MAX_LEAF_SIZE, int ROOM_MAX_SIZE, int ROOM_MIN_SIZE) {
        this.MAX_LEAF_SIZE = MAX_LEAF_SIZE;
        this.ROOM_MAX_SIZE = ROOM_MAX_SIZE;
        this.ROOM_MIN_SIZE = ROOM_MIN_SIZE;
        this.MAP_WIDTH = MAP_WIDTH;
        this.MAP_HEIGHT = MAP_HEIGHT;
        level = new int[MAP_WIDTH][MAP_HEIGHT];
    }

    public int[][] generateLevel() {
        ArrayList<Leaf> leaves = new ArrayList<>();
        Leaf rootLeaf = new Leaf(0, 0, MAP_WIDTH, MAP_HEIGHT);
        leaves.add(rootLeaf);
        boolean splitSuccessfully = true;
        while (splitSuccessfully) {
            splitSuccessfully = false;

            int j = leaves.size();
            for (int i = 0; i < j; i++) {
                Leaf l = leaves.get(i);
                if (l.child1 == null && l.child2 == null) {
                    if (l.width > MAX_LEAF_SIZE || l.height > MAX_LEAF_SIZE || getRandInt(0, 5) > 4) {
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

        rootLeaf.createRooms(this);
        return level;
    }

    void createRoom(Rect room) {
        for (int x = room.x1 + 1; x <= room.x2; x++) {
            for (int y = room.y1 + 1; y <= room.y2; y++) {
                level[x][y] = 1;
            }
        }
    }

    void createHall(Rect room1, Rect room2) {
        int x1 = room1.centerX();
        int y1 = room1.centerY();
        int x2 = room2.centerX();
        int y2 = room2.centerY();

        if (getRandInt(0, 1) == 1) {
            createVerTunnel(y1, y2, x2);
            createHorTunnel(x1, x2, y1);
        } else {
            createHorTunnel(x1, x2, y2);
            createVerTunnel(y1, y2, x1);
        }
    }

    void createHorTunnel(int x1, int x2, int y) {
        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2) + 1; x++) {
            level[x][y] = 1;
        }
    }

    void createVerTunnel(int y1, int y2, int x) {
        for (int y = Math.min(y1, y2); y <= Math.max(y1, y2) + 1; y++) {
            level[x][y] = 1;
        }
    }

}

