import javax.swing.*;
import java.awt.*;

public class Player1 extends GameObject implements Player
{
    private double xVelocity = 0, yVelocity = 0;
    private Texture texture;
    ProjectClass projectClass;
    Motion motion;

    public Player1(double x, double y, Texture texture, ProjectClass projectClass, Motion motion)
    {super(x, y); this.texture = texture;
    this.projectClass = projectClass; this.motion = motion;}
    public void tick()
    {
        x+=xVelocity; y+=yVelocity;
        if(x <= 0) x = 0;
        if(x >= 600-32) x = 600-32;
        for(int i = 0; i < projectClass.enemy.size(); i++)
        {
            Enemy enemy = projectClass.enemy.get(i);
            if(Physics.Collision(this, enemy))
            {
                motion.removeEntity(enemy);
                ProjectClass.health -= 10;
                ProjectClass.kills -=1;
                ProjectClass.hits += 1;
                projectClass.setEnemyKilled(projectClass.getEnemyKilled() + 1);
                if(ProjectClass.health == 0)
                {
                    UIManager UI=new UIManager();
                    UI.put("OptionPane.background", Color.BLACK);
                    UI.put("OptionPane.messageForeground", Color.GREEN);
                    UI.put("Panel.background", Color.BLACK);
                    try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                    JOptionPane.showMessageDialog(projectClass, "NO LIVES LEFT. GAME OVER. GAME WILL EXIT. BYE!");
                    System.exit(0);
                }
            }
        }
    }
    public void render(Graphics g) { g.drawImage(texture.player1, (int)x, (int)y, null); }
    public Rectangle getBounds() {return new Rectangle((int)x, (int)y, 32,32);}
    public double getX() { return x; }
    public double getY() { return y; }
    public void setxVelocity(double xVelocity){this.xVelocity = xVelocity;}
}
