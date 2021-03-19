import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Wall extends GameObject {
    private Handler handler;
    
    public Wall(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
    }
    public Wall(int x, int y, BufferedImage image, ID id) {
        super(x, y, image, id);
    }
    public Wall(int x, int y, int width, int height, ID id, int speed, Handler handler) {
        super(x, y, width, height, id);
        this.speed = speed;
        this.handler = handler;
        setUpBounds(handler.maxSpeed + 30);
    }
    public Wall(int x, int y, BufferedImage image, ID id, int speed, Handler handler) {
        super(x, y, image, id);
        this.speed = speed;
        this.handler = handler;
        setUpBounds(handler.maxSpeed + 30);
    }
    
    public void generateAndSetRandomLocation() {
        boolean openSpot = false;
        int spawnX = 0;
        int spawnY = 0;
        while (!openSpot) {
            openSpot = true;
            Random rand = handler.rand;
            Game game = handler.game;
            spawnX = rand.nextInt(1920);
            Random rand2 = handler.rand;
            Game game2 = handler.game;
            spawnY = rand2.nextInt(1080);
            for (GameObject tempObject : handler.objects) {
                if (new Rectangle(spawnX, spawnY, width, height).intersects(tempObject.getBounds())) {
                    openSpot = false;
                }
            }
        }
        x = spawnX;
        y = spawnY;
    }
    
    public void update() {
        if (image != null) {
            width = image.getWidth();
            height = image.getHeight();
        }
        if (id == ID.wall || id == ID.speedPowerUp) {
            setUpBounds(handler.maxSpeed + 30);
        }
    }
    public void draw(Graphics2D g) {
        if (id == ID.wall) {
            g.setColor(Color.CYAN);
        } else if (id == ID.speedPowerUp) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.BLACK);
        }
        
        if (image == null) {
            g.fillRect(x, y, width, height);
        } else {
            g.drawImage(image, x, y, null);
        }
    }
    
    public void reset() {
        if (id == ID.speedPowerUp) {
            generateAndSetRandomLocation();
        }
        velX = 0.0f;
        velY = 0.0f;
    }
}