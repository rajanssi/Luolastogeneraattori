package cavegame.userinterface;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import cavegame.game.Character;
import cavegame.game.Tile;
import cavegame.game.World;

public class PlayScreen implements Screen {
    private final int screenWidth;
    private final int screenHeight;
    private final World world;
    private final Character player;

    public PlayScreen(int w, int h) {
        screenWidth = w;
        screenHeight = h;
        world = new World(screenWidth, screenHeight, 5).build();
        player = world.addPlayer();
        world.addEnemies();
    }

    private void growWorld() {
        world.growWorld(screenWidth);
        for (int i = 0; i < world.getCharacters().size(); i++) {
            Character c = world.getCharacters().get(i);
            c.updateWorld(world);
        }

    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        int left = getScrollX();
        int top = getScrollY();
        displayTiles(terminal, left, top);
        String stats = String.format(" %3d/%3d hp", player.getHp(), 5);
        String location = String.format(" %3d x %3d y", player.getX(), player.getY());
        terminal.write("Enter luo uuden luolaston!", 1, screenHeight - 1);
        terminal.write("Esc palaa alkuruutuun!", 1, screenHeight - 2);
        terminal.write(stats, 1, screenHeight - 3);
        terminal.write(location, 1, screenHeight - 4);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                return new StartScreen(screenWidth, screenHeight);
            case KeyEvent.VK_ENTER:
                return new PlayScreen(screenWidth, screenHeight);
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_H:
                player.moveBy(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_L:
                player.moveBy(1, 0);
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_K:
                player.moveBy(0, -1);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_J:
                player.moveBy(0, 1);
                break;
            case KeyEvent.VK_Y:
                player.moveBy(-1, -1);
                break;
            case KeyEvent.VK_U:
                player.moveBy(1, -1);
                break;
            case KeyEvent.VK_B:
                player.moveBy(-1, 1);
                break;
            case KeyEvent.VK_N:
                player.moveBy(1, 1);
                break;
            case KeyEvent.VK_SPACE:
                player.moveBy(0, 0);
                break;
            case KeyEvent.VK_X:
                System.out.println(world.rooms.size());
                break;
            case KeyEvent.VK_C:
                System.out.println(world.getCharacters().size());
                break;
        }

        if (world.getWidth() - player.getX() < 10) {
            growWorld();
        }

        world.updateWorld();

        return this;
    }

    private int getScrollX() {
        return Math.max(0, Math.min(player.getX() - screenWidth / 2, world.getWidth() - screenWidth));
    }

    private int getScrollY() {
        return Math.max(0, Math.min(player.getY() - screenHeight / 2, world.getHeight() - screenHeight));
    }

    private void displayTiles(AsciiPanel terminal, int left, int top) {
        for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                int wx = x + left;
                int wy = y + top;
                Tile t = world.getTile(wx, wy);
                terminal.write(t.getSymbol(), x, y, t.getColor());
            }
        }
        for (int i = 0; i < world.getCharacters().size(); i++) {
            Character c = world.getCharacters().get(i);
            if (c.getX() > left && c.getX() < screenWidth + left) {
                terminal.write(c.getSymbol(), c.getX() - left, c.getY() - top, c.getColor());
            }
        }

    }
}

