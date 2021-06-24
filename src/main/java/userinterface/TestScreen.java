package userinterface;

import asciiPanel.AsciiPanel;
import cavegenerator.BSPTree;
import game.World;

import java.awt.event.KeyEvent;

public class TestScreen implements Screen{
    private final int screenWidth;
    private final int screenHeight;
    private int state;
    private World world;
    private BSPTree bsp;

    public TestScreen(int w, int h) {
        this.screenWidth = w;
        this.screenHeight = h;
        this.state = 0;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        if (state == 0) {
            terminal.writeCenter("Mita testataan?", 21);
            terminal.writeCenter("1: Luolastojen luomisen tehokkuutta", 23);
            terminal.writeCenter("2: Luotujen luolastojen oikeellisuutta", 24);
        } else if (state == 1) {
            terminal.writeCenter("Luodaan uutta pelimaailmaa...", 23);
            long start = System.currentTimeMillis();
            bsp = new BSPTree(30000, 30000, 200, 200, 100);
            long end = System.currentTimeMillis();

            String message = "1000x1000 maailmaan aikaa kului " + (end - start) + " millisekuntia";
            terminal.writeCenter(message, 24);
        }

        terminal.write("Esc palaa alkuruutuun!", 1, screenHeight - 2);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                break;
            case KeyEvent.VK_NUMPAD1:
            case KeyEvent.VK_1:
                if (state == 0) {
                    System.out.println("1");
                    state = 1;
                }
                break;
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_2:
                if (state == 0) {
                    System.out.println("2");
                }
                break;
            case KeyEvent.VK_ESCAPE:
                return new StartScreen(screenWidth, screenHeight);
            default:
                break;
        }
        return this;
    }

    private void createWorld() {

    }
}
