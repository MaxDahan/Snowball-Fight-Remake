import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

public class Game extends Canvas implements Runnable, KeyListener {
    public static final int GWIDTH = 1920, GHEIGHT = 1080;
    private Thread thread;
    protected Handler handler;
    protected LoadedTextures LT;
    protected Player player1, player2;
    private Wall topWall, bottomWall, rightWall, leftWall, snowFortTop, snowFortBottom, speedPowerUp;
    protected ArrayList<Snowball> snowballs;
    protected boolean running, pause, updateRun, drawRun, stopGameNextFrame, resetGameNextFrame;
    private int speedPowerUpTime;
    private Random rand;
    private SpeedPowerUpRespawn SPUR;
    protected EndScreen endScreen;
    protected GameFrame gameFrame;
    
    public Game() {
        snowballs = new ArrayList<Snowball>();
        speedPowerUpTime = 7;
        SPUR = new SpeedPowerUpRespawn();
        
        endScreen = new EndScreen(1920, 1080, this);
        gameFrame = new GameFrame(1920, 1080, this);
        
        addKeyListener(this);
        
        createFields();
        addObjects();
        start();
    }
    public void createFields() {
        LT = new LoadedTextures();
        rand = new Random();
        handler = new Handler();
        handler.LT = LT;
        handler.game = this;
        
        int startingY = 100;
        player1 = new Player(960 - LT.getPlayer1Down().getWidth() / 2, startingY, LT.getPlayer1Down(), true, ID.player, handler);
        player2 = new Player(960 - LT.getPlayer2Up().getWidth() / 2, 1080 - startingY - LT.getPlayer2Up().getHeight(), LT.getPlayer2Up(), false, ID.player, handler);
        handler.maxSpeed = player1.speed;
        
        int wallSize = 50;
        topWall = new Wall(0, 0, 1920, wallSize, ID.topWall);
        bottomWall = new Wall(0, 1080 - wallSize, 1920, wallSize, ID.bottomWall);
        rightWall = new Wall(1920 - wallSize, 0, wallSize, 1080, ID.rightWall);
        leftWall = new Wall(0, 0, wallSize, 1080, ID.leftWall);
        
        int snowballSize = 40;
        snowballs.add(new Snowball(200, 200, LT.getSnowball(), ID.snowball, handler));
        snowballs.add(new Snowball(1720, 200, LT.getSnowball(), ID.snowball, handler));
        snowballs.add(new Snowball(200, 880, LT.getSnowball(), ID.snowball, handler));
        snowballs.add(new Snowball(1720, 880, LT.getSnowball(), ID.snowball, handler));
        snowFortTop = new Wall(300, 225, 1320, 100, ID.wall, player1.speed, handler);
        snowFortBottom = new Wall(300, 755, 1320, 100, ID.wall, player1.speed, handler);
        speedPowerUp = new Wall(60, 200, 50, 50, ID.speedPowerUp, player1.speed, handler);
    }
    public void addObjects() {
        handler.addObject((GameObject)topWall);
        handler.addObject((GameObject)bottomWall);
        handler.addObject((GameObject)rightWall);
        handler.addObject((GameObject)leftWall);
        
        for (final Snowball snowball : snowballs) {
            handler.addObject((GameObject)snowball);
            new Thread((Runnable)snowball).start();
        }
        
        handler.addObject((GameObject)snowFortTop);
        handler.addObject((GameObject)snowFortBottom);
        
        handler.addObject((GameObject)speedPowerUp);
        
        handler.addObject((GameObject)player1);
        handler.addObject((GameObject)player2);
    }
    public void start() {
        SPUR.start();
        
        running = true;
        updateRun = true;
        drawRun = true;
        
        thread = new Thread(this);
        thread.start();
    }
    
    public void keyPressed(final KeyEvent e) {
        final int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_ESCAPE) {
            stop();
        } else if (key == 80) {
            if (updateRun) {
                updateRun = false;
            } else {
                updateRun = true;
            }
        } else if (key == KeyEvent.VK_E) {
            handler.up1 = true;
        } else if (key == KeyEvent.VK_S) {
            handler.left1 = true;
        } else if (key == KeyEvent.VK_D) {
            handler.down1 = true;
        } else if (key == KeyEvent.VK_F) {
            handler.right1 = true;
        } else if (key == KeyEvent.VK_Q) {
            player1.pickUpAndThrow();
        } else if (key == KeyEvent.VK_UP) {
            handler.up2 = true;
        } else if (key == KeyEvent.VK_LEFT) {
            handler.left2 = true;
        } else if (key == KeyEvent.VK_DOWN) {
            handler.down2 = true;
        } else if (key == KeyEvent.VK_RIGHT) {
            handler.right2 = true;
        } else if (key == KeyEvent.VK_M) {
            player2.pickUpAndThrow();
        }
    }
    public void keyReleased(final KeyEvent e) {
        final int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_E) {
            handler.up1 = false;
        } else if (key == KeyEvent.VK_S) {
            handler.left1 = false;
        } else if (key == KeyEvent.VK_D) {
            handler.down1 = false;
        } else if (key == KeyEvent.VK_F) {
            handler.right1 = false;
        } else if (key == KeyEvent.VK_UP) {
            handler.up2 = false;
        } else if (key == KeyEvent.VK_LEFT) {
            handler.left2 = false;
        } else if (key == KeyEvent.VK_DOWN) {
            handler.down2 = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            handler.right2 = false;
        }
    }
    
    public void update() {
        if (resetGameNextFrame) {
            handler.reset();
            resetGameNextFrame = false;
            pause = true;
            endScreen.setVisible(true);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameFrame.setVisible(false);
        }
        
        if (updateRun) handler.update();
    }
    public void paint() {
        if (drawRun) {
            BufferStrategy bs = getBufferStrategy();
            if (bs == null) {
                createBufferStrategy(3);
                return;
            }
            Graphics2D g = (Graphics2D)bs.getDrawGraphics();
            
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 1920, 1080);
            handler.draw(g);
            
            if (!updateRun) {
                g.drawImage(LT.getPauseImg(), 960 - LT.getPauseImg().getWidth() / 2, 540 - LT.getPauseImg().getHeight() / 2, null);
            }
            
            g.dispose();
            bs.show();
        }
    }
    
    public void run() {
        requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1.0E9 / amountOfTicks;
        double delta = 0.0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            if (stopGameNextFrame) {
                stopGameNextFrame = false;
                running = false;
            }
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1.0) {
                if (!pause) {
                    update();
                }
                --delta;
            }
            if (!pause) {
                paint();
            }
            ++frames;
            if (System.currentTimeMillis() - timer > 1000L) {
                timer += 1000L;
                frames = 0;
            }
        }
        stop();
    }
    public void stop() {
        running = false;
        try {
            thread.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
    
    private class SpeedPowerUpRespawn extends Thread {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(speedPowerUpTime * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                speedPowerUp.generateAndSetRandomLocation();
            }
        }
    }
    
    public void keyTyped(final KeyEvent arg0) {}
    public static void main(final String[] args) {
        new Game();
    }
}
