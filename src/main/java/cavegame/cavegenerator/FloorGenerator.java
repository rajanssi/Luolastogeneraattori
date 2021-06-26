package cavegame.cavegenerator;

import cavegame.game.Tile;

import static cavegame.utils.RandomNumberGenerator.getRandInt;

/**
 * Metodeja pelitason ruutujen rakentamiselle annetun tietorakenteen perusteella.
 */
public class FloorGenerator {

    /**
     * Yhdistää kaksi BSP-puun luomaa huonetta toisiinsa käytävällä.
     * @param room1 Lähtöhuone, josta käytävä alkaa.
     * @param room2 Maalihuone, jonne käytävä päättyy.
     * @param level Taso, jossa yhdistäminen tehdään.
     */
    public static void connectTrees(Room room1, Room room2, Tile[][] level) {
        createHall(room1, room2, level);
    }

    /**
     * Rakentaa huoneen seinät ja lattiat annettuun tasoon BSP algoritmin luoman huoneen spesifikaatioiden mukaisesti.
     * @param room Huone, jonka pohjalta tasoon luodaan seinät ja lattiat.
     * @param level Taso, jonne seinät ja lattiat luodaan.
     */
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

    /**
     * Rakentaa käytävän kahden huoneen välille.
     * @param room1 Lähtöhuone, josta käytävä alkaa.
     * @param room2 Maalihuone, jonne käytävä päättyy.
     * @param level Taso, jossa käytävä luodaan.
     */
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

    /**
     * Rakentaa käytävää horisontaalisesti.
     * @param x1 Käytävän pää x-akselilla.
     * @param x2 Käytävän häntä x-akselilla.
     * @param y Käyätävän sijainti y-akselilla
     * @param level Taso, jossa käytävä tehdään.
     */
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

    /**
     * Rakentaa käytävää vertikaalisesti.
     * @param y1 Käytävän pää y-akselilla.
     * @param y2 Käytävän häntä y-akselilla.
     * @param x Käyätävän sijainti x-akselilla
     * @param level Taso, jossa käytävä tehdään.
     */
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
