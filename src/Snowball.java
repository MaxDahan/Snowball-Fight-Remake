import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Snowball extends GameObject implements Runnable {
    protected boolean heldByPlayer1, heldByPlayer2, thrownByP1, thrownByP2;
    private Player playerHolding;
    private Handler handler;
    protected int snowballSpeed, distance, maxDistance;
    
    public Snowball(int x, int y, int width, int height, ID id, Handler handler) {
        super(x, y, width, height, id);
        
        heldByPlayer1 = false;
        heldByPlayer2 = false;
        thrownByP1 = false;
        thrownByP2 = false;
        snowballSpeed = 24;
        distance = 0;
        maxDistance = 550;
        
        setUp(handler, x, y);
    }
    public Snowball(int x, int y, BufferedImage image, ID id, Handler handler) {
        super(x, y, image, id);
        
        heldByPlayer1 = false;
        heldByPlayer2 = false;
        thrownByP1 = false;
        thrownByP2 = false;
        snowballSpeed = 24;
        distance = 0;
        maxDistance = 550;
        
        setUp(handler, x, y);
    }
    public void setUp(Handler handler, int x, int y) {
        this.handler = handler;
        speed = snowballSpeed;
        startX = x;
        startY = y;
    }
    
    public void update() {
        x += (int)velX;
        y += (int)velY;
        if (image != null) {
            width = image.getWidth();
            height = image.getHeight();
        }
        if (heldByPlayer1 || heldByPlayer2) {
            if (playerHolding.up) {
                x = playerHolding.x + playerHolding.width / 2 - width / 2;
                y = playerHolding.y - height / 2;
            } else if (playerHolding.down) {
                x = playerHolding.x + playerHolding.width / 2 - width / 2;
                y = playerHolding.y + playerHolding.height - height / 2;
            } else if (playerHolding.right) {
                x = playerHolding.x + playerHolding.width - width / 2;
                y = playerHolding.y + playerHolding.height / 2 - height / 2;
            } else if (playerHolding.left) {
                x = playerHolding.x - width / 2;
                y = playerHolding.y + playerHolding.height / 2 - height / 2;
            }
            
            if (playerHolding.up && playerHolding.right) {
                x = playerHolding.x + playerHolding.width - width / 2 - 10;
                y = playerHolding.y - height / 2 + 10;
            } else if (playerHolding.up && playerHolding.left) {
                x = playerHolding.x - width / 2 + 10;
                y = playerHolding.y - height / 2 + 10;
            } else if (playerHolding.down && playerHolding.right) {
                x = playerHolding.x + playerHolding.width - width / 2 - 10;
                y = playerHolding.y + playerHolding.height - height / 2 - 10;
            } else if (playerHolding.down && playerHolding.left) {
                x = playerHolding.x - width / 2 + 10;
                y = playerHolding.y + playerHolding.height - height / 2 - 10;
            }
        }
        collision();
        setUpBounds(snowballSpeed);
    }
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        if (image == null) {
            g.fillRect(x, y, width, height);
        } else {
            g.drawImage(image, x, y, null);
        }
    }
    
    public void collision() {
        for (GameObject tempObject : handler.objects) {
            if (getBounds().intersects(tempObject.getBounds())) {
                if (tempObject.id == ID.topWall) {
                    y = tempObject.y + tempObject.height;
                    velX = 0.0f;
                    velY = 0.0f;
                } else if (tempObject.id == ID.bottomWall) {
                    y = tempObject.y - height;
                    velX = 0.0f;
                    velY = 0.0f;
                } else if (tempObject.id == ID.rightWall) {
                    x = tempObject.x - width;
                    velX = 0.0f;
                    velY = 0.0f;
                } else if (tempObject.id == ID.leftWall) {
                    x = tempObject.x + tempObject.width;
                    velX = 0.0f;
                    velY = 0.0f;
                } else if (tempObject.id == ID.wall) {
                    if (getBounds().intersects(tempObject.topBound)) {
                        y = tempObject.y - height;
                        velX = 0.0f;
                        velY = 0.0f;
                    } else if (getBounds().intersects(tempObject.bottomBound)) {
                        y = tempObject.y + tempObject.height;
                        velX = 0.0f;
                        velY = 0.0f;
                    } else if (getBounds().intersects(tempObject.rightBound)) {
                        x = tempObject.x + tempObject.width;
                        velX = 0.0f;
                        velX = 0.0f;
                    } else {
                        if (!getBounds().intersects(tempObject.leftBound)) {
                            continue;
                        }
                        x = tempObject.x - width;
                        velX = 0.0f;
                        velX = 0.0f;
                    }
                } else if (tempObject.id == ID.snowball && tempObject.x != x) {
                    Snowball tempSnowball = (Snowball)tempObject;
                    if ((!tempSnowball.thrownByP1 && !tempSnowball.thrownByP2) || (!thrownByP1 && !thrownByP2)) {
                        continue;
                    }
                    collideWithSnowball(tempObject);
                } else {
                    if (tempObject.id != ID.player) {
                        continue;
                    }
                    Player tempPlayer = (Player)tempObject;
                    if (tempPlayer.playerChoice && thrownByP2 && !heldByPlayer2) {
                        handler.game.resetGameNextFrame = true;
                        Player player1 = handler.game.player1;
                        ++player1.score;
                        handler.game.endScreen.winPlayer.setBackground(Color.RED);
                        handler.game.endScreen.losePlayer.setBackground(Color.BLUE);
                    } else {
                        if (tempPlayer.playerChoice || !thrownByP1 || heldByPlayer1) {
                            continue;
                        }
                        handler.game.resetGameNextFrame = true;
                        Player player2 = handler.game.player2;
                        ++player2.score;
                        handler.game.endScreen.winPlayer.setBackground(Color.BLUE);
                        handler.game.endScreen.losePlayer.setBackground(Color.RED);
                    }
                }
            }
        }
    }
    public void collideWithSnowball(GameObject tempObject) {
        if (getBounds().intersects(tempObject.topBound)) {
            y = tempObject.y - height;
            velX = 0.0f;
            velY = 0.0f;
        } else if (getBounds().intersects(tempObject.bottomBound)) {
            y = tempObject.y + tempObject.height;
            velX = 0.0f;
            velY = 0.0f;
        } else if (getBounds().intersects(tempObject.rightBound)) {
            x = tempObject.x + tempObject.width;
            velX = 0.0f;
            velX = 0.0f;
        } else if (getBounds().intersects(tempObject.leftBound)) {
            x = tempObject.x - width;
            velX = 0.0f;
            velX = 0.0f;
        }
    }
    
    public void run() {
        while (true) {
            if (thrownByP1 || thrownByP2) {
                ++distance;
            } else {
                distance = 0;
            }
            
            if (distance >= maxDistance) {
                distance = 0;
                velX = 0.0f;
                velY = 0.0f;
                thrownByP1 = false;
                thrownByP2 = false;
            }
            
            try {
                Thread.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void reset() {
        x = startX;
        y = startY;
        velX = 0.0f;
        velY = 0.0f;
        heldByPlayer1 = false;
        heldByPlayer2 = false;
        thrownByP1 = false;
        thrownByP2 = false;
    }
    
    public void setPlayerHolding(Player playerHolding) {
        this.playerHolding = playerHolding;
    }
}