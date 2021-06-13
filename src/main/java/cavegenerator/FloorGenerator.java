package cavegenerator;

import game.Tile;

import static utils.RandomNumberGenerator.getRandInt;

/**
 * Metodeja pelitason ruutujen rakentamiselle annetun tietorakenteen perusteella.
 */
public class FloorGenerator {

    public static void connectTrees(Room room1, Room room2, Tile[][] level) {
        createHall(room1, room2, level);
    }

    static void createRoom(Room room, Tile[][] level) {
        for (int x = room.x1 + 1; x < room.x2; x++) {
            for (int y = room.y1 + 1; y < room.y2; y++) {
                level[x][y] = Tile.FLOOR;
                if (x == room.x1 + 1 || x == room.x2 - 1) {
                    level[x][y] = Tile.ROOMWALL;
                }
                if (y == room.y1 + 1 || y == room.y2 - 1) {
                    level[x][y] = Tile.ROOMWALL;
                }
            }
        }
    }

    static void createHall(Room room1, Room room2, Tile[][] level) {
        int x1 = room1.centerX();
        int y1 = room1.centerY();
        int x2 = room2.centerX();
        int y2 = room2.centerY();

        if (getRandInt(0, 1) == 1) {
            createVerTunnel(y1, y2, x2, level);
            createHorTunnel(x1, x2, y1, level);
        } else {
            createHorTunnel(x1, x2, y2, level);
            createVerTunnel(y1, y2, x1, level);
        }
    }

    static void createHorTunnel(int x1, int x2, int y, Tile[][] level) {
        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            if (level[x][y - 1] != Tile.FLOOR && level[x][y - 1] != Tile.DOOR) {
                level[x][y - 1] = Tile.WALL;
            }
            if (level[x][y + 1] != Tile.FLOOR && level[x][y + 1] != Tile.DOOR) {
                level[x][y + 1] = Tile.WALL;
            }
            if (level[x][y] == Tile.ROOMWALL && level[x - 1][y] != Tile.ROOMWALL && level[x + 1][y] != Tile.ROOMWALL) {
                level[x][y] = Tile.DOOR;
            } else if (level[x][y] != Tile.DOOR && level[x][y - 1] != Tile.DOOR && level[x][y + 1] != Tile.DOOR) {
                level[x][y] = Tile.FLOOR;
            }
        }

    }

    static void createVerTunnel(int y1, int y2, int x, Tile[][] level) {
        for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
            if (level[x - 1][y] != Tile.FLOOR && level[x - 1][y] != Tile.DOOR) {
                level[x - 1][y] = Tile.WALL;
            }
            if (level[x + 1][y] != Tile.FLOOR && level[x + 1][y] != Tile.DOOR) {
                level[x + 1][y] = Tile.WALL;
            }
            if (level[x][y] == Tile.ROOMWALL && level[x][y - 1] != Tile.ROOMWALL && level[x][y + 1] != Tile.ROOMWALL) {
                level[x][y] = Tile.DOOR;
            } else if (level[x][y] != Tile.DOOR && level[x - 1][y] != Tile.DOOR && level[x + 1][y] != Tile.DOOR) {
                level[x][y] = Tile.FLOOR;
            }
        }
    }
}
