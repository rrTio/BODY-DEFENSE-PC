import java.awt.*;  import java.util.Random;

public class Enemies extends GameObject implements Enemy
{
    Random random = new Random();
    private Texture texture;
    private int speed = random.nextInt(4)+2;
    private Motion motion;
    private ProjectClass projectClass;

    public Enemies(double x, double y, Texture texture, Motion motion, ProjectClass projectClass)
    {
        super(x,y); this.texture = texture;
        this.motion = motion;
        this.projectClass = projectClass;
    }

    public void tick()
    {
        y += speed;
        if(y > 400-32) {y = -10; x = random.nextInt(600-32);}

        for(int i = 0; i < projectClass.playerA.size(); i++)
        {
            Player tempEnt = projectClass.playerA.get(i);
            if(Physics.Collision(this, tempEnt))
            {
                motion.removeEntity(tempEnt);
                motion.removeEntity(this);
                projectClass.setEnemyKilled(projectClass.getEnemyKilled() + 1);
            }
        }
    }

    public void render(Graphics g) { g.drawImage(texture.enemy, (int)x, (int)y, null); }
    public Rectangle getBounds() {return new Rectangle((int)x, (int)y, 32,32);}
    public double getX() {return x;}
    public double getY() {return y;}
    public void setY(double y) {this.y = y;}
}
