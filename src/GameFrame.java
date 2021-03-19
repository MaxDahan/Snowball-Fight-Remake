import javax.swing.JFrame;

public class GameFrame extends JFrame {
    
    public GameFrame(int GWIDTH, int GHEIGHT, Game game) {
        setUndecorated(true);
        setAlwaysOnTop(true);
        setSize(GWIDTH, GHEIGHT);
        setIconImage(new BufferedImageLoader().loadImage("/icon.png"));
        add(game);
        setVisible(true);
    }
}