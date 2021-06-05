package userinterface;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;
import game.Character;
import game.World;

public class PlayScreen implements Screen {
    private final int screenWidth;
    private final int screenHeight;
    private final World world;
    private final Character player;

    public PlayScreen(){
        screenWidth = 150;
        screenHeight = 50;
        world = new World(50, 50).build();
        player = world.addPlayer();
    }

    private void growWorld() {
        world.growWorld(40);
        for (Character c : world.getCharacters()) {
            c.updateWorld(world);
        }
    }

    public void displayOutput(AsciiPanel terminal) {
        int left = getScrollX();
        int top = getScrollY();

        displayTiles(terminal, left, top);
        terminal.write("Enter luo uuden luolaston!", 1, 22);
        terminal.write("Esc palaa alkuruutuun!", 1, 23);
    }

    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                return new StartScreen();
            case KeyEvent.VK_ENTER:
                return new PlayScreen();
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_H:
                player.moveBy(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_L:
                player.moveBy( 1, 0);
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_K:
                player.moveBy( 0,-1);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_J:
                player.moveBy( 0, 1);
                break;
            case KeyEvent.VK_Y:
                player.moveBy(-1,-1);
                break;
            case KeyEvent.VK_U:
                player.moveBy( 1,-1);
                break;
            case KeyEvent.VK_B:
                player.moveBy(-1, 1);
                break;
            case KeyEvent.VK_N:
                player.moveBy( 1, 1);
                break;
            case KeyEvent.VK_SPACE: growWorld(); break;
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
        for (int x = 0; x < screenWidth; x++){
            for (int y = 0; y < screenHeight; y++){
                int wx = x + left;
                int wy = y + top;

                terminal.write(world.getSymbol(wx, wy), x, y, world.getColor(wx, wy));
            }
        }

        for (Character c : world.getCharacters()) {
            if (c.getX() > left && c.getX() < screenWidth + left) {
                terminal.write(c.getSymbol(), c.getX() - left, c.getY() - top, c.getColor());
            }
        }

    }
}

