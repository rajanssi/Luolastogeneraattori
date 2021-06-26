package cavegame.userinterface;

import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;

public interface Screen {
    void displayOutput(AsciiPanel terminal);

    Screen respondToUserInput(KeyEvent key);
}