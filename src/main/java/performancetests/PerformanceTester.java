package performancetests;

import cavegame.cavegenerator.Room;
import cavegame.game.World;

import java.util.Scanner;

public class PerformanceTester {
    private final Scanner reader;
    private World world;

    public PerformanceTester() {
        this.reader = new Scanner(System.in);
    }

    public void launch() {
        boolean run = true;
        while (run) {
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
        for (int i = 64; i <= 16384; i *= 2) {
            long start = System.currentTimeMillis();
            world = new World(i, i, 20).build();
            long end = System.currentTimeMillis();
            String message = String.format("%d x %d luolaston luomiseen aikaa kului " + (end - start) + " millisekuntia", i, i);
            System.out.println(message);
        }
    }

    private void validityTest() {
        int size = 1000;
        System.out.printf("Tarkistetaan, että %d x %d kokoisen luolaston jokainen huone on yhdistetty\n", size, size);
        System.out.println("Kuinka monta testiä ajetaan?");
        int tests = reader.nextInt();
        int index = 1;
        long begin = System.currentTimeMillis();
        while (index <= tests) {
            boolean error = false;
            world = new World(size, size, 10).build();
            world.growWorld(size);
            System.out.printf("Luolastossa %d huoneita on yhteensä %d kappaletta ", index, world.rooms.size());
            boolean[][] seen = new boolean[size * 2][size];
            for (int i = 0; i < world.rooms.size(); i++) {
                Room r = world.rooms.get(i);
                for (int j = i; j < world.rooms.size(); j++) {
                    Room r2 = world.rooms.get(i);
                    dfs(seen, r.centerX(), r.centerY(), r2);
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
        long end = System.currentTimeMillis();
        System.out.println("Testeihin kului aikaa yhteensä " + (end - begin) + " millisekuntia.");
    }

    private void dfs(boolean[][] seen, int x, int y, Room target) {
        if (seen[x][y] || seen[target.centerX()][target.centerY()]) {
            return;
        }
        seen[x][y] = true;
        if (world.getTile(x + 1, y).isWalkable()) {
            dfs(seen, x + 1, y, target);
        }
        if (world.getTile(x - 1, y).isWalkable()) {
            dfs(seen, x - 1, y, target);
        }
        if (world.getTile(x, y + 1).isWalkable()) {
            dfs(seen, x, y + 1, target);
        }
        if (world.getTile(x, y - 1).isWalkable()) {
            dfs(seen, x, y - 1, target);
        }
    }
}
