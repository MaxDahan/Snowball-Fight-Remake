import java.awt.image.BufferedImage;
import java.util.Random;

public class LoadedTextures {
    private BufferedImageLoader BIL;
    private Random rand;
    private String playerPath, snowballPath;
    private BufferedImage pauseImg,
    player1Up, player1Down, player1Right, player1Left,
    player1UpRight, player1UpLeft, player1DownRight, player1DownLeft,
    player2Up, player2Down, player2Right, player2Left,
    player2UpRight, player2UpLeft, player2DownRight, player2DownLeft,
    snowball1, snowball2, snowball3, snowball4;
    
    public LoadedTextures() {
        BIL = new BufferedImageLoader();
        rand = new Random();
        playerPath = "player/";
        snowballPath = "snowballs/";
        pauseImg = BIL.loadImage("pause.png");
        player1Up = BIL.loadImage(String.valueOf(playerPath) + "player1Up.png");
        player1Down = BIL.loadImage(String.valueOf(playerPath) + "player1Down.png");
        player1Right = BIL.loadImage(String.valueOf(playerPath) + "player1Right.png");
        player1Left = BIL.loadImage(String.valueOf(playerPath) + "player1Left.png");
        player1UpRight = BIL.loadImage(String.valueOf(playerPath) + "player1UpRight.png");
        player1UpLeft = BIL.loadImage(String.valueOf(playerPath) + "player1UpLeft.png");
        player1DownRight = BIL.loadImage(String.valueOf(playerPath) + "player1DownRight.png");
        player1DownLeft = BIL.loadImage(String.valueOf(playerPath) + "player1DownLeft.png");
        player2Up = BIL.loadImage(String.valueOf(playerPath) + "player2Up.png");
        player2Down = BIL.loadImage(String.valueOf(playerPath) + "player2Down.png");
        player2Right = BIL.loadImage(String.valueOf(playerPath) + "player2Right.png");
        player2Left = BIL.loadImage(String.valueOf(playerPath) + "player2Left.png");
        player2UpRight = BIL.loadImage(String.valueOf(playerPath) + "player2UpRight.png");
        player2UpLeft = BIL.loadImage(String.valueOf(playerPath) + "player2UpLeft.png");
        player2DownRight = BIL.loadImage(String.valueOf(playerPath) + "player2DownRight.png");
        player2DownLeft = BIL.loadImage(String.valueOf(playerPath) + "player2DownLeft.png");
        snowball1 = BIL.loadImage(String.valueOf(snowballPath) + "snowball1.png");
        snowball2 = BIL.loadImage(String.valueOf(snowballPath) + "snowball2.png");
        snowball3 = BIL.loadImage(String.valueOf(snowballPath) + "snowball3.png");
        snowball4 = BIL.loadImage(String.valueOf(snowballPath) + "snowball4.png");
    }
    
    public BufferedImage getPauseImg() {
        return pauseImg;
    }
    public BufferedImage getPlayer1Up() {
        return player1Up;
    }
    public BufferedImage getPlayer1Down() {
        return player1Down;
    }
    public BufferedImage getPlayer1Right() {
        return player1Right;
    }
    public BufferedImage getPlayer1Left() {
        return player1Left;
    }
    public BufferedImage getPlayer1UpRight() {
        return player1UpRight;
    }
    public BufferedImage getPlayer1UpLeft() {
        return player1UpLeft;
    }
    public BufferedImage getPlayer1DownRight() {
        return player1DownRight;
    }
    public BufferedImage getPlayer1DownLeft() {
        return player1DownLeft;
    }
    public BufferedImage getPlayer2Up() {
        return player2Up;
    }
    public BufferedImage getPlayer2Down() {
        return player2Down;
    }
    public BufferedImage getPlayer2Right() {
        return player2Right;
    }
    public BufferedImage getPlayer2Left() {
        return player2Left;
    }
    public BufferedImage getPlayer2UpRight() {
        return player2UpRight;
    }
    public BufferedImage getPlayer2UpLeft() {
        return player2UpLeft;
    }
    public BufferedImage getPlayer2DownRight() {
        return player2DownRight;
    }
    public BufferedImage getPlayer2DownLeft() {
        return player2DownLeft;
    }
    public BufferedImage getSnowball() {
        int num = rand.nextInt(4) + 1;
        if (num == 1) {
            return snowball1;
        } else if (num == 2) {
            return snowball2;
        } else if (num == 3) {
            return snowball3;
        } else return snowball4;
    }
}