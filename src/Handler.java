import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Random;

public class Handler {
    protected LinkedList<GameObject> objects;
    protected boolean up1, down1, right1, left1, up2, down2, right2, left2;
    protected LoadedTextures LT;
    protected BufferedImageLoader BIL;
    protected Game game;
    protected int maxSpeed;
    protected Random rand;
    
    public Handler() {
        objects = new LinkedList<GameObject>();
        up1 = false;
        down1 = false;
        right1 = false;
        left1 = false;
        up2 = false;
        down2 = false;
        right2 = false;
        left2 = false;
        BIL = new BufferedImageLoader();
        maxSpeed = 0;
        rand = new Random();
    }
    
    public void update() {
        for (int i = 0; i < objects.size(); ++i) {
            objects.get(i).update();
        }
    }
    public void draw(final Graphics2D g) {
        for (int i = 0; i < objects.size(); ++i) {
            objects.get(i).draw(g);
        }
    }
    
    public void reset() {
        for (final GameObject tempObject : objects) {
            tempObject.reset();
        }
    }
    
    public void addObject(final GameObject tempObject) {
        objects.add(tempObject);
    }
    public void removeObject(final GameObject tempObject) {
        objects.remove(tempObject);
    }
}