import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.awt.image.BufferedImage;

public class Player extends GameObject {
    private Handler handler;
    private static int runSpeed = 10;
    protected boolean playerChoice, holdingSnowball, up, down, right, left, speedUp, speedUpCheck;
    protected int speedUpTime, speedUpSpeed, score;
    protected SpeedUpTimer speedUpTimer;
    
    public Player(int x, int y, int width, int height, boolean playerChoice, ID id, Handler handler) {
        super(x, y, width, height, id);
        speedUpCheck = true;
        speedUpTime = 3;
        speedUpSpeed = 13;
        speedUpTimer = new SpeedUpTimer();
        setUp(playerChoice, handler, x, y);
    }
    public Player(int x, int y, BufferedImage image, boolean playerChoice, ID id, Handler handler) {
        super(x, y, image, id);
        speedUpCheck = true;
        speedUpTime = 3;
        speedUpSpeed = 13;
        speedUpTimer = new SpeedUpTimer();
        setUp(playerChoice, handler, x, y);
    }
    public void setUp(boolean playerChoice, Handler handler, int startX, int startY) {
    	this.playerChoice = playerChoice;
    	this.handler = handler;
        this.startX = startX;
        this.startY = startY;
        
        speed = 10;
        speedUpTimer.start();
    }
    
    public void update() {
        if (image != null) {
            width = image.getWidth();
            height = image.getHeight();
        }
        
        if (playerChoice) {
            player1Update();
        } else {
            player2Update();
        }
        
        setUpBounds(handler.maxSpeed);
        collision();
        setUpBounds(handler.maxSpeed);
    }
    public void player1Update() {
        x += (int)velX;
        y += (int)velY;
        if (handler.up1) {
            velY = (float)(-speed);
            up();
        } else if (handler.down1) {
            velY = (float)speed;
            down();
        } else {
            velY = 0.0f;
        }
        
        if (handler.down1) {
            velY = (float)speed;
            down();
        } else if (handler.up1) {
            velY = (float)(-speed);
            up();
        } else {
            velY = 0.0f;
        }
        
        if (handler.right1) {
            velX = (float)speed;
            right();
        } else if (handler.left1) {
            velX = (float)(-speed);
            left();
        } else {
            velX = 0.0f;
        }
        
        if (handler.left1) {
            velX = (float)(-speed);
            left();
        } else if (handler.right1) {
            velX = (float)speed;
            right();
        } else {
            velX = 0.0f;
        }
        
        if (handler.up1 && handler.right1) {
            up = true;
            down = false;
            right = true;
            left = false;
            velX /= (float)1.15;
            velY /= (float)1.15;
        }
        
        if (handler.up1 && handler.left1) {
            up = true;
            down = false;
            right = false;
            left = true;
            velX /= (float)1.15;
            velY /= (float)1.15;
        }
        
        if (handler.down1 && handler.right1) {
            up = false;
            down = true;
            right = true;
            left = false;
            velX /= (float)1.15;
            velY /= (float)1.15;
        }
        
        if (handler.down1 && handler.left1) {
            up = false;
            down = true;
            right = false;
            left = true;
            velX /= (float)1.15;
            velY /= (float)1.15;
        }
        
        imageDirection();
    }
    public void player2Update() {
        x += (int)velX;
        y += (int)velY;
        if (handler.up2) {
            velY = (float)(-speed);
            up();
        } else if (handler.down2) {
            velY = (float)speed;
            down();
        } else {
            velY = 0.0f;
        }

        if (handler.down2) {
            velY = (float)speed;
            down();
        } else if (handler.up2) {
            velY = (float)(-speed);
            up();
        } else {
            velY = 0.0f;
        }
        
        if (handler.right2) {
            velX = (float)speed;
            right();
        } else if (handler.left2) {
            velX = (float)(-speed);
            left();
        } else {
            velX = 0.0f;
        }
        
        if (handler.left2) {
            velX = (float)(-speed);
            left();
        } else if (handler.right2) {
            velX = (float)speed;
            right();
        } else {
            velX = 0.0f;
        }
        
        if (handler.up2 && handler.right2) {
            up = true;
            down = false;
            right = true;
            left = false;
            velX /= (float)1.15;
            velY /= (float)1.15;
        }
        
        if (handler.up2 && handler.left2) {
            up = true;
            down = false;
            right = false;
            left = true;
            velX /= (float)1.15;
            velY /= (float)1.15;
        }
        
        if (handler.down2 && handler.right2) {
            up = false;
            down = true;
            right = true;
            left = false;
            velX /= (float)1.15;
            velY /= (float)1.15;
        }
        
        if (handler.down2 && handler.left2) {
            up = false;
            down = true;
            right = false;
            left = true;
            velX /= (float)1.15;
            velY /= (float)1.15;
        }
        
        imageDirection();
    }
    
    public void imageDirection() {
        if (playerChoice) {
            if (up) {
                image = handler.LT.getPlayer1Up();
            } else if (down) {
                image = handler.LT.getPlayer1Down();
            } else if (right) {
                image = handler.LT.getPlayer1Right();
            } else if (left) {
                image = handler.LT.getPlayer1Left();
            }
            
            if (up && right) {
                image = handler.LT.getPlayer1UpRight();
            } else if (up && left) {
                image = handler.LT.getPlayer1UpLeft();
            } else if (down && right) {
                image = handler.LT.getPlayer1DownRight();
            } else if (down && left) {
                image = handler.LT.getPlayer1DownLeft();
            }
        } else {
            if (up) {
                image = handler.LT.getPlayer2Up();
            } else if (down) {
                image = handler.LT.getPlayer2Down();
            } else if (right) {
                image = handler.LT.getPlayer2Right();
            } else if (left) {
                image = handler.LT.getPlayer2Left();
            }
            
            if (up && right) {
                image = handler.LT.getPlayer2UpRight();
            } else if (up && left) {
                image = handler.LT.getPlayer2UpLeft();
            } else if (down && right) {
                image = handler.LT.getPlayer2DownRight();
            } else if (down && left) {
                image = handler.LT.getPlayer2DownLeft();
            }
        }
    }
    public void collision() {
        for (int i = 0; i < handler.objects.size(); ++i) {
            GameObject tempObject = handler.objects.get(i);
            if (getBounds().intersects(tempObject.getBounds())) {
                if (tempObject.id == ID.topWall) {
                    y = tempObject.y + tempObject.height;
                    velY = 0.0f;
                } else if (tempObject.id == ID.bottomWall) {
                    y = tempObject.y - height;
                    velY = 0.0f;
                } else if (tempObject.id == ID.rightWall) {
                    x = tempObject.x - width;
                    velX = 0.0f;
                } else if (tempObject.id == ID.leftWall) {
                    x = tempObject.x + tempObject.width;
                    velX = 0.0f;
                } else if (tempObject.id == ID.wall) {
                    if (getBounds().intersects(tempObject.topBound)) {
                        y = tempObject.y - height;
                        velY = 0.0f;
                    } else if (getBounds().intersects(tempObject.bottomBound)) {
                        y = tempObject.y + tempObject.height;
                        velY = 0.0f;
                    } else if (getBounds().intersects(tempObject.rightBound)) {
                        x = tempObject.x + tempObject.width;
                        velX = 0.0f;
                    } else if (getBounds().intersects(tempObject.leftBound)) {
                        x = tempObject.x - width;
                        velX = 0.0f;
                    }
                } else if (tempObject.id == ID.speedPowerUp) {
                    speed = speedUpSpeed;
                    if (speed > handler.maxSpeed) {
                        handler.maxSpeed = speed;
                    }
                    
                    speedUp = true;
                    
                    GameObject gameObject = tempObject;
                    Game game = handler.game;
                    gameObject.x = 1920 + 200;
                }
            }
        }
    }
    public void pickUpAndThrow() {
        for (Snowball snowball : handler.game.snowballs) {
            if (getBounds().intersects(snowball.getBounds())) {
                if (!holdingSnowball) {
                    holdingSnowball = true;
                    
                    if (playerChoice) {
                        snowball.heldByPlayer1 = true;
                        snowball.heldByPlayer2 = false;
                    } else {
                        snowball.heldByPlayer1 = false;
                        snowball.heldByPlayer2 = true;
                    }
                    snowball.setPlayerHolding(this);
                }
                else if (snowball.heldByPlayer1 && holdingSnowball && playerChoice) {
                    holdingSnowball = false;
                    snowball.heldByPlayer1 = false;
                    snowball.setPlayerHolding((Player)null);
                    snowball.thrownByP1 = true;
                    snowball.thrownByP2 = false;
                    if (up) {
                        snowball.velX = 0.0f;
                        snowball.velY = (float)(-snowball.speed);
                    } else if (down) {
                        snowball.velX = 0.0f;
                        snowball.velY = (float)snowball.speed;
                    } else if (right) {
                        snowball.velX = (float)snowball.speed;
                        snowball.velY = 0.0f;
                    } else if (left) {
                        snowball.velX = (float)(-snowball.speed);
                        snowball.velY = 0.0f;
                    }
                    
                    if (up && right) {
                        snowball.velX = snowball.speed / 1.15f;
                        snowball.velY = -snowball.speed / 1.15f;
                    } else if (up && left) {
                        snowball.velX = -snowball.speed / 1.15f;
                        snowball.velY = -snowball.speed / 1.15f;
                    } else if (down && right) {
                        snowball.velX = snowball.speed / 1.15f;
                        snowball.velY = snowball.speed / 1.15f;
                    } else {
                        if (!down || !left) {
                            continue;
                        }
                        snowball.velX = -snowball.speed / 1.15f;
                        snowball.velY = snowball.speed / 1.15f;
                    }
                } else {
                    if (!snowball.heldByPlayer2 || !holdingSnowball || playerChoice) {
                        continue;
                    }
                    
                    holdingSnowball = false;
                    snowball.heldByPlayer2 = false;
                    snowball.setPlayerHolding((Player)null);
                    snowball.thrownByP1 = false;
                    snowball.thrownByP2 = true;
                    
                    if (up) {
                        snowball.velX = 0.0f;
                        snowball.velY = (float)(-snowball.speed);
                    } else if (down) {
                        snowball.velX = 0.0f;
                        snowball.velY = (float)snowball.speed;
                    } else if (right) {
                        snowball.velX = (float)snowball.speed;
                        snowball.velY = 0.0f;
                    } else if (left) {
                        snowball.velX = (float)(-snowball.speed);
                        snowball.velY = 0.0f;
                    }
                    
                    if (up && right) {
                        snowball.velX = snowball.speed / 1.15f;
                        snowball.velY = -snowball.speed / 1.15f;
                    } else if (up && left) {
                        snowball.velX = -snowball.speed / 1.15f;
                        snowball.velY = -snowball.speed / 1.15f;
                    } else if (down && right) {
                        snowball.velX = snowball.speed / 1.15f;
                        snowball.velY = snowball.speed / 1.15f;
                    } else {
                        if (!down || !left) {
                            continue;
                        }
                        snowball.velX = -snowball.speed / 1.15f;
                        snowball.velY = snowball.speed / 1.15f;
                    }
                }
            }
        }
    }
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        if (image == null) {
            if (playerChoice) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.RED);
            }
            g.fillRect(x, y, width, height);
        } else {
            g.drawImage(image, x, y, null);
        }
    }
    public void reset() {
        x = startX;
        y = startY;
        velX = 0.0f;
        velY = 0.0f;
        holdingSnowball = false;
        speedUp = false;
        speed = 10;
        handler.up1 = false;
        handler.up2 = false;
        handler.down1 = false;
        handler.down2 = false;
        handler.right1 = false;
        handler.right2 = false;
        handler.left1 = false;
        handler.left2 = false;
        if (playerChoice) {
            down();
        } else {
            up();
        }
    }
    
    public void up() {
        up = true;
        down = false;
        right = false;
        left = false;
    }
    public void down() {
        up = false;
        down = true;
        right = false;
        left = false;
    }
    public void right() {
        up = false;
        down = false;
        right = true;
        left = false;
    }
    public void left() {
        up = false;
        down = false;
        right = false;
        left = true;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    private class SpeedUpTimer extends Thread {
        public void run() {
            while (Player.this.speedUpCheck) {
                if (Player.this.speedUp) {
                    try {
                        Thread.sleep(Player.this.speedUpTime * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Player.this.speedUp = false;
                    Player.this.speed = 10;
                }
                
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}