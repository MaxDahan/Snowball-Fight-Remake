import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;

public class EndScreen extends JFrame implements KeyListener {
    protected int player1Score, player2Score, GWIDTH, GHEIGHT, GBORDER;
    private Game game;
    protected JTextArea firstPodium, secondPodium, winPlayer, losePlayer;
    private MyButton reset;
    private JLayeredPane pane;
    private Color nothingColor;
    
    public EndScreen(int GWIDTH, int GHEIGHT, Game game) {
        GBORDER = 5;
        nothingColor = new Color(0, 0, 0, 0);
        
        requestFocus();
        setUndecorated(true);
        setAlwaysOnTop(true);
        setSize(GWIDTH, GHEIGHT);
        addKeyListener(this);
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, GBORDER));
        setIconImage(new BufferedImageLoader().loadImage("/icon.png"));
        
        this.GWIDTH = GWIDTH;
        this.GHEIGHT = GHEIGHT;
        this.game = game;
        
        setUpScreen();
        setVisible(false);
    }

    public void setUpScreen() {
        pane = new JLayeredPane();
        pane.setLayout(null);
        add(pane);
        
        podiumsWithPlayers();
        
        reset = new MyButton("reset");
        reset.setFont(new Font("Arial", 1, 125));
        reset.setBackground(nothingColor);
        reset.setPressedBackgroundColor(nothingColor);
        reset.setHoverBackgroundColor(nothingColor);
        reset.setPressedTextColor(Color.BLUE);
        reset.setHoverTextColor(Color.CYAN);
        reset.setBorder(null);
        reset.setBounds(755, 500, 300, 200);
        reset.addActionListener((ActionListener)new ResetEvent());
        pane.setLayer(reset, 1);
        pane.add(reset);
    }
    public void podiumsWithPlayers() {
        firstPodium = new JTextArea("  2");
        firstPodium.setFont(new Font("Arial", 1, 225));
        firstPodium.setForeground(Color.WHITE);
        firstPodium.setEditable(false);
        firstPodium.setBackground(Color.BLACK);
        firstPodium.setBounds(300, GHEIGHT - 300, 375, 300);
        firstPodium.addKeyListener(this);
        pane.setLayer(firstPodium, 1);
        pane.add(firstPodium);
        
        secondPodium = new JTextArea("  1");
        secondPodium.setFont(new Font("Arial", 1, 275));
        secondPodium.setForeground(Color.WHITE);
        secondPodium.setEditable(false);
        secondPodium.setBackground(Color.BLACK);
        secondPodium.setBounds(GWIDTH - 300 - 400, GHEIGHT - 500, 450, 500);
        secondPodium.addKeyListener(this);
        pane.setLayer(secondPodium, 1);
        pane.add(secondPodium);
        
        winPlayer = new JTextArea();
        winPlayer.setEditable(false);
        winPlayer.setBackground(Color.BLUE);
        winPlayer.setBounds(secondPodium.getX() + secondPodium.getWidth() / 2 - 200, secondPodium.getY() - 400, 400, 400);
        winPlayer.addKeyListener(this);
        pane.setLayer(winPlayer, 2);
        pane.add(winPlayer);
        
        losePlayer = new JTextArea();
        losePlayer.setEditable(false);
        losePlayer.setBackground(Color.RED);
        losePlayer.setBounds(firstPodium.getX() + firstPodium.getWidth() / 2 - 162, firstPodium.getY() - 325, 325, 325);
        losePlayer.addKeyListener(this);
        pane.setLayer(losePlayer, 2);
        pane.add(losePlayer);
    }
    
    public void reset() {
        game.gameFrame.setVisible(true);
        
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        setVisible(false);
        game.handler.reset();
        game.pause = false;
        game.gameFrame.requestFocus();
    }
    public class ResetEvent implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            EndScreen.this.reset();
        }
    }
    
    public void keyPressed(final KeyEvent e) {
        final int key = e.getKeyCode();
        if (key == 27) {
            System.exit(0);
        } else if (key == 82) {
            reset();
        }
    }
    public void keyReleased(final KeyEvent e) {}
    public void keyTyped(final KeyEvent e) {}
    public static void main(final String[] e) {
        new EndScreen(1920, 1080, new Game());
    }
}