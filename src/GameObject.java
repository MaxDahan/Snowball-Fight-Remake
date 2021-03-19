import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    protected BufferedImage image;
    protected ID id;
    protected int x, y, width, height, speed, startX, startY;
    protected float velX, velY;
    protected Rectangle topBound, bottomBound, rightBound, leftBound;
    
    public GameObject(int x, int y, BufferedImage image, ID id) {
        topBound = new Rectangle();
        bottomBound = new Rectangle();
        rightBound = new Rectangle();
        leftBound = new Rectangle();
        
        this.x = x;
        this.y = y;
        this.image = image;
        this.id = id;
        
        width = image.getWidth();
        height = image.getHeight();
        startX = x;
        startY = y;
        
        setUpBounds(speed);
    }
    public GameObject(int x, int y, int width, int height, ID id) {
        topBound = new Rectangle();
        bottomBound = new Rectangle();
        rightBound = new Rectangle();
        leftBound = new Rectangle();
        
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        
        startX = x;
        startY = y;
        
        setUpBounds(speed);
    }
    
    public void reset() {
        x = startX;
        y = startY;
        velX = 0.0f;
        velY = 0.0f;
    }
    
    public void setUpBounds(int maxSpeed) {
        topBound.setBounds(x + maxSpeed, y, width - maxSpeed * 2, height / 2);
        bottomBound.setBounds(x + maxSpeed, y + height / 2, width - maxSpeed * 2, height / 2);
        rightBound.setBounds(x + width - 1, y, 1, height);
        leftBound.setBounds(x, y, 1, height);
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    public abstract void update();
    public abstract void draw(Graphics2D p0);
    public void drawHitBox(Graphics2D g) {
        g.setColor(Color.PINK);
        g.drawRect((int)topBound.getX(), (int)topBound.getY(), (int)topBound.getWidth(), (int)topBound.getHeight());
        g.drawRect((int)bottomBound.getX(), (int)bottomBound.getY(), (int)bottomBound.getWidth(), (int)bottomBound.getHeight());
        g.drawRect((int)rightBound.getX(), (int)rightBound.getY(), (int)rightBound.getWidth(), (int)rightBound.getHeight());
        g.drawRect((int)leftBound.getX(), (int)leftBound.getY(), (int)leftBound.getWidth(), (int)leftBound.getHeight());
    }
}
