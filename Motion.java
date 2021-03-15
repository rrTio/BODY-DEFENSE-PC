import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Motion
{
    private LinkedList<Player> playerA = new LinkedList<Player>();
    private LinkedList<Enemy> enemy = new LinkedList<Enemy>();
    Player enta; Enemy entb;
    ProjectClass projectClass;
    Texture texture;
    Motion motion;
    Random random = new Random();

    public Motion(Texture texture, ProjectClass projectClass)
    { this.projectClass = projectClass; this.texture = texture; }

    public void tick()
    {
        for(int i = 0; i < playerA.size(); i++) {enta = playerA.get(i); enta.tick();}
        for(int i = 0; i < enemy.size(); i++) {entb = enemy.get(i); entb.tick();}
    }
    public void render(Graphics g)
    {
        for(int i = 0; i < playerA.size(); i++) {enta = playerA.get(i); enta.render(g);}
        for(int i = 0; i < enemy.size(); i++) {entb = enemy.get(i); entb.render(g);}
    }

    public void createEnemy(int enemyCount)
    {
        for(int i = 0; i<enemyCount; i++)
        {addEntity(new Enemies(random.nextInt(600), -10, texture, this, projectClass));}
    }

    public void addEntity(Player block) { playerA.add(block);}
    public void removeEntity(Player block) { playerA.remove(block);}
    public void addEntity(Enemy block) { enemy.add(block);}
    public void removeEntity(Enemy block) { enemy.remove(block);}

    public LinkedList<Player> getEntitiy() {return playerA;}
    public LinkedList<Enemy> getEntitiyB() {return enemy;}
}
