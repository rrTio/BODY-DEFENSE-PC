import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BufferedImageLoad
{
    private BufferedImage image;
    public BufferedImage loadImage(String path) throws IOException {image = ImageIO.read(getClass().getResource(path)); return image;}
}
