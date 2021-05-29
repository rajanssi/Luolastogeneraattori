package userinterface;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;
import game.Character;
import game.CharacterFactory;
import game.World;
import game.WorldBuilder;

public class PlayScreen implements Screen {
    final private int screenWidth;
    final private int screenHeight;
    private CharacterFactory characterFactory;
    private World world;
    private Character player;

    public PlayScreen(){
        screenWidth = 80;
        screenHeight = 24;
        createWorld();
        characterFactory = new CharacterFactory(world);
        player = characterFactory.newPlayer();
    }

    private void createWorld(){
        world = new WorldBuilder(10000, 10000)
                .makeRooms()
                .build();
    }

    public void displayOutput(AsciiPanel terminal) {
        int left = getScrollX();
        int top = getScrollY();

        displayTiles(terminal, left, top);
        terminal.write(player.getSymbol(), player.getX() - left, player.getY() - top, player.getColor());
        terminal.write("Enter luo uuden luolaston!", 1, 22);
        terminal.write("Esc palaa alkuruutuun!", 1, 23);

    }

    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                return new StartScreen();
            case KeyEvent.VK_ENTER:
                createWorld();
                characterFactory = new CharacterFactory(world);
                player = characterFactory.newPlayer();
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_H: player.moveBy(-1, 0); break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_L: player.moveBy( 1, 0); break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_K: player.moveBy( 0,-1); break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_J: player.moveBy( 0, 1); break;
            case KeyEvent.VK_Y: player.moveBy(-1,-1); break;
            case KeyEvent.VK_U: player.moveBy( 1,-1); break;
            case KeyEvent.VK_B: player.moveBy(-1, 1); break;
            case KeyEvent.VK_N: player.moveBy( 1, 1); break;
        }
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
    }
}

