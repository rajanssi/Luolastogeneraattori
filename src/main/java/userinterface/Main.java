package userinterface;

import javax.swing.JFrame;

import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JFrame implements KeyListener {
    private final AsciiPanel terminal;
    private Screen screen;

    /**
     * Alustaa JFramepohjaisen käyttöliittymän.
     */
    public Main() {
        super();
        terminal = new AsciiPanel(150, 50, AsciiFont.CP437_12x12);
        add(terminal);
        pack();
        screen = new StartScreen();
        addKeyListener(this);
        repaint();
    }

    /**
     * Päivitä ruutu
     */
    public void repaint() {
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    /**
     * Päivittää ruudun jokaisen näppäinpainalluksen jälkeen
     *
     * @param e näppäinpainallus
     */
    public void keyPressed(KeyEvent e) {
        screen = screen.respondToUserInput(e);
        repaint();
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
