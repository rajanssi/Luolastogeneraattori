package userinterface;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;

public class StartScreen implements Screen {

    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter("Luolastopeli", 11);
        terminal.writeCenter("-- paina [enter] aloittaaksesi --", 21);
        terminal.writeCenter("-- paina [esc] poistuaksesi --", 22);
    }

    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
            case KeyEvent.VK_ENTER:
                return new PlayScreen();
        }
        return this;
    }
}