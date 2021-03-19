import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class BufferedImageLoader {
    public BufferedImage loadImage(String path) {
        InputStream input = getClass().getResourceAsStream(path);
        if (input == null) {
            input = getClass().getResourceAsStream("/" + path);
        }
        try {
            return ImageIO.read(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}