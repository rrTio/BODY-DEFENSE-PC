import java.awt.*;

public interface Player
{
    void tick(); void render(Graphics g);
    Rectangle getBounds();
    double getX(); double getY();
}
