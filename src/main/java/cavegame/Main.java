package cavegame;

import javax.swing.JFrame;

import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;
import cavegame.userinterface.Screen;
import cavegame.userinterface.StartScreen;
import performancetests.PerformanceTester;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JFrame implements KeyListener {
    private final AsciiPanel terminal;
    private final int screenWidth = 100;
    private final int screenHeight = 50;
    private Screen screen;

    /**
     * Alustaa JFramepohjaisen käyttöliittymän.
     */
    public Main() {
        super();
        terminal = new AsciiPanel(screenWidth, screenHeight, AsciiFont.CP437_12x12);
        add(terminal);
        pack();
        screen = new StartScreen(screenWidth, screenHeight);
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
        if (args.length == 0) {
            Main app = new Main();
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.setVisible(true);
        } else if (args[0].equals("test")){
            PerformanceTester p = new PerformanceTester();
            p.launch();
        } else {
            System.out.println("Virheellinen argumentti");
            System.out.println("Jos haluat suorittaa testejä, anna argumentti 'test'");
        }
    }
}
