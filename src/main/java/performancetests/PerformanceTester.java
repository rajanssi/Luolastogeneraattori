package performancetests;

import cavegame.cavegenerator.Room;
import cavegame.game.World;

import java.util.Scanner;

public class PerformanceTester {
    private final Scanner reader;
    private int state;
    private World world;

    public PerformanceTester() {
        this.reader = new Scanner(System.in);
        state = 0;
    }

    public void launch()  {
        boolean run = true;
        while (run) {
            run = true;
            System.out.println("Mitä testataan?");
            System.out.println("1: Luolastojen luomisen tehokkuutta");
            System.out.println("2: Luotujen luolastojen oikeellisuutta");
            System.out.println("q poistuu");
            String input = reader.nextLine();
            switch (input) {
                case "1":
                    performanceTest();
                    break;
                case "2":
                    validityTest();
                    break;
                case "q":
                    run = false;
                    break;
            }
        }
    }

    private void performanceTest() {
        System.out.println("Suoritetaan testejä...");
        for (int i = 10; i <= 100000; i *= 10) {
            long start = System.currentTimeMillis();
            world = new World(i, i, 20).build();
            long end = System.currentTimeMillis();
            String message = String.format("%d x %d maailmaan aikaa kului " + (end - start) + " millisekuntia", i, i);
            System.out.println(message);
        }
    }
    boolean[][] seen;
    private void validityTest() {
        int size = 300;
        System.out.println("Tarkistetaan, että 300x300 kokoisen luolaston jokainen huone on yhdistetty");
        int index = 1;
        while (index <= 10) {
            boolean error = false;
            world = new World(size, size, 10).build();
            System.out.printf("Maailmassa %d huoneita on yhteensä %d kappaletta ", index,  world.rooms.size());
            for (int i = 0; i < world.rooms.size(); i++) {
                Room r = world.rooms.get(i);
                for (int j = i; j < world.rooms.size(); j++) {
                    seen = new boolean[size][size];
                    Room r2 = world.rooms.get(i);
                    dfs(r.centerX(), r.centerY(), r2);
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

    }

    private void dfs(int x, int y, Room target) {
        if (seen[x][y] || seen[target.centerX()][target.centerY()]) {
            return;
        }
        seen[x][y] = true;
        if (world.getTile(x + 1, y).isWalkable()) {
            dfs(x + 1, y, target);
        }
        if (world.getTile(x - 1, y).isWalkable()) {
            dfs(x - 1, y, target);

        }
        if (world.getTile(x, y + 1).isWalkable()) {
            dfs(x, y + 1, target);

        }
        if (world.getTile(x, y - 1).isWalkable()) {
            dfs(x, y - 1, target);
        }
    }
}
