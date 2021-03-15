import java.awt.*;

public class Bullet1 extends GameObject implements Player
{
    private Texture texture;
    private ProjectClass projectClass;

    public Bullet1(double x, double y, Texture texture, ProjectClass projectClass)
    { super(x,y); this.texture = texture; this.projectClass = projectClass; }
    public void tick()
    {
        y -= 10;
    }
    public void render(Graphics g) { g.drawImage(texture.bullet1, (int)x, (int)y, null); }
    public Rectangle getBounds() {return new Rectangle((int)x, (int)y, 32,32);}
    public double getX() {return x;}    public double getY() {return y;}
}
