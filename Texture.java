import java.awt.image.BufferedImage;

public class Texture
{
    private SpriteSheet spriteSheet;
    public BufferedImage player1, bullet1, enemy;

    public Texture(ProjectClass projectClass) { spriteSheet = new SpriteSheet(projectClass.getSpriteSheet()); getTexture(); }

    private void getTexture()
    {
        player1 = spriteSheet.grabImage(1,1,32,32);
        bullet1 = spriteSheet.grabImage(2,1,32,32);
        enemy = spriteSheet.grabImage(3,1,32,32);
    }
}
