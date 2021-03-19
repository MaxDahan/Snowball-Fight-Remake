import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class TitleScreen extends JFrame implements KeyListener {
    private static final long serialVersionUID = 1L;
    private MyButton play;
    private JLabel title;
    private JLayeredPane pane;
    
    public TitleScreen(final int GWIDTH, final int GHEIGHT) {
        requestFocus();
        setSize(GWIDTH, GHEIGHT);
        setUndecorated(true);
        setAlwaysOnTop(true);
        addKeyListener(this);
        setIconImage(new BufferedImageLoader().loadImage("/icon.png"));
        
        pane = new JLayeredPane();
        pane.setLayout(null);
        add(pane);
        
        play = new MyButton("Play");
        play.setForeground(new Color(50, 230, 0));
        play.setHoverBackgroundColor(new Color(0, 0, 0, 0));
        play.setHoverTextColor(new Color(50, 210, 0));
        play.setPressedBackgroundColor(new Color(0, 0, 0, 0));
        play.setPressedTextColor(new Color(50, 190, 0));
        play.setFont(new Font("Arial", 0, 300));
        play.setBounds(GWIDTH / 2 - 400 - 45, GHEIGHT / 2 - 200 + 45, 800, 400);
        play.setFocusable(false);
        play.setBorder(null);
        play.addActionListener((ActionListener)new TitleScreen.PlayAction());
        pane.setLayer(play, 1);
        pane.add(play);
        
        title = new JLabel("Welcome to snowball!");
        title.setFont(new Font("Arial", 0, 175));
        title.setBounds(100, 75, title.getFontMetrics(title.getFont()).stringWidth(title.getText()), title.getFontMetrics(title.getFont()).getHeight());
        
        pane.setLayer(title, 1);
        pane.add(title);
        
        setVisible(true);
    }
    
    private class PlayAction implements ActionListener {
        public void actionPerformed(final ActionEvent e) {
            new Game();
            setVisible(false);
        }
    }
    public void keyPressed(final KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }
    
    public static void main(final String[] args) {
        new TitleScreen(1920, 1080);
    }
    public void keyReleased(final KeyEvent e) {}
    public void keyTyped(final KeyEvent e) {}
}