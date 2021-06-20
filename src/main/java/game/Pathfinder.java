package game;

import utils.ArrayList;

/**
 * Etsii reitin lähtöruudusta lähimpää vihollista kohden
 */
public class Pathfinder {
    private final int x1, x2;
    private final int y1, y2;
    private final World w;
    private final Character c, o;
    private final int direction;
    private int n;
    ArrayList<Edge> edges = new ArrayList<>();

    /**
     * Ruutujen yhteyksiä kuvaava kaari.
     */
    class Edge {
        int a, b;

        Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    void addEdge(int a, int b, int c) {
        if (c == '#' || c == '*') {
            return;
        }
        edges.add(new Edge(a, b));
    }

    /**
     * Konstruktori ottaa pelimaailman, hakevan hahmon ja kohdehahmon parametreiksi
     *
     * @param w Nykyinen pelimaailma
     * @param c Hakua suorittava hahmo
     * @param o Haun kohteena oleva hahmo
     */
    public Pathfinder(World w, Character c, Character o) {
        x1 = Math.max(c.getX() - 5, 0);
        x2 = Math.min(c.getX() + 5, w.getWidth());
        y1 = Math.max(c.getY() - 5, 0);
        y2 = Math.min(c.getY() + 5, w.getHeight());

        this.w = w;
        this.c = c;
        this.o = o;

        int[] ab = addEdges(createArea());
        int[] dist = findRoutes(ab[1]);
        this.direction = direction(ab, dist);
    }

    /**
     * Palauttaa suunnan jonne kannattaa siirtyä pelaajan tavoittamiseksi
     *
     * @return Integer-luku väliltä -1 - 3 (-1 = haku epäonnistui, 0 = ylös, 1 = oikealle, 2 = alas, 3 = vasemmalle).
     */
    public int getDirection() {
        return this.direction;
    }

    private int direction(int[] ab, int[] dist) {
        int[] moves = {-12, 1, 12, -1};
        int min = 1000;
        int direction = -1;
        for (int i = 0; i < 4; i++) {
            int distance = dist[ab[0] + moves[i]];
            if (distance < 0) {
                continue;
            }
            if (distance < min) {
                min = distance;
                direction = i;
            }
        }
        return direction;
    }

    /**
     * Luo kaaret leveyshaussa käytettävää kaarilistaa varten ja palauttaa alku- ja lähtöruudut.
     *
     * @param r String-taulukko, joka kuvaa hahmon ympäriltä haettavia ruutuja.
     * @return Palauttaa kaksi indeksiä sisältävän int-taulukon, joista ensimmäisessä indeksissä on hakevan hahmon sijainti
     * ja toisessa indeksissä on haettavan hahmon sijainti.
     */
    private int[] addEdges(String[] r) {
        int n = r.length;
        int m = r[0].length();
        this.n = n * m;
        int[] ab = new int[2];
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                int a = i * m + j;
                if (r[i].toCharArray()[j] == 'A') {
                    ab[0] = a;
                } else if (r[i].toCharArray()[j] == 'B') {
                    ab[1] = a;
                }
                addEdge(a, (i - 1) * m + j, r[i - 1].toCharArray()[j]);
                addEdge(a, (i + 1) * m + j, r[i + 1].toCharArray()[j]);
                addEdge(a, a - 1, r[i].toCharArray()[j - 1]);
                addEdge(a, a + 1, r[i].toCharArray()[j + 1]);
            }
        }
        return ab;
    }

    /**
     * Rakentaa pelihahmon ympäriltä haettavan alueen
     *
     * @return Palauttaa String-taulukon, joka vastaa 2-ulotteista kuvausta hahmon ympäriltä haettavista ruuduista
     */
    private String[] createArea() {
        String[] r = new String[12];
        for (int i = y1 + 1; i < y2; i++) {
            String s = "#";
            for (int j = x1 + 1; j < x2; j++) {
                if (c.getX() == j && c.getY() == i) {
                    s += 'A';
                    continue;
                } else if (o.getX() == j && o.getY() == i) {
                    s += 'B';
                    continue;
                }
                s += (w.getTile(j, i).isWalkable() ? '.' : '#');
            }
            s += '#';
            r[i - y1] = s;
        }

        for (int i = 0; i < 12; i++) {
            if (r[i] == null) {
                r[i] = "############";
            }
            while (r[i].length() < 12) {
                r[i] += '#';
            }
        }
        return r;
    }

    /**
     * Etsii lyhimmät polut lähtöruudusta kaikkiin ruutuihin Bellmanin ja Fordin algoritmin avulla
     *
     * @param a Lähtöruudun indeksi yksiulotteisessa muodossa
     * @return Palauttaa kaikki etäisyydet lähtöruudusta taulukkona
     */
    private int[] findRoutes(int a) {
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = -1;
        }
        dist[a] = 0;
        while (true) {
            boolean change = false;
            for (int i = 0; i < edges.size(); i++) {
                Edge e = edges.get(i);
                if (dist[e.a] == -1) {
                    continue;
                }
                int newDist = dist[e.a] + 1;
                if (newDist < dist[e.b] || dist[e.b] == -1) {
                    dist[e.b] = newDist;
                    change = true;
                }
            }
            if (!change) {
                break;
            }
        }
        return dist;
    }
}
