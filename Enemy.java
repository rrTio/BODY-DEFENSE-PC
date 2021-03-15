import java.awt.*;

public interface Enemy
{
    void tick(); void render(Graphics g);
    Rectangle getBounds();
    double getX(); double getY();
}
