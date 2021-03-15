import javax.sound.sampled.AudioSystem; import javax.sound.sampled.Clip;
import javax.swing.*;                   import java.awt.*;                      import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;   import java.awt.image.BufferedImage;
import java.io.File;                    import java.io.IOException;
import java.util.LinkedList;

public class ProjectClass extends Canvas implements Runnable
{
    private BufferedImage spriteSheet = null, background = null;
    private final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
    private Thread thread;
    private Motion motion;
    private Player1 player1;
    private Texture texture;
    public static int kills = 0, hits = 0, health = 200;
    public LinkedList<Player> playerA;
    public LinkedList<Enemy> enemy;
    private int enemyCount = 4, enemyKilled = 0;
    public boolean running = false, shoot = false;
    public static final int width = 600, height = 400;

    public void run()
    {
        initialize();
        long timeLast = System.nanoTime();
        final double ticksCount = 60.0;
        double ns = 1000000000 / ticksCount, d = 0;
        int update = 0, frames = 0;
        long timer = System.currentTimeMillis();
        while (running)
        {
            long timeNow = System.nanoTime();
            d += (timeNow - timeLast) / ns; timeLast = timeNow;
            if(d >= 1) {
                tick(); update++; d--;}
            render(); frames++;
            if(System.currentTimeMillis() - timer > 1000)
            { timer += 1000; System.out.println(update + " " + frames); update = 0; frames = 0; }
        }
        stop();
    }

    public void play()
    {
        String song  = "C:\\Users\\Vader\\Desktop\\PROJECT RT\\bgMusic.wav";
        try {
            File file = new File(song); Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file)); clip.start();
        } catch (Exception ex) {System.err.println(ex.getMessage());}
    }

    public void initialize()
    {
        requestFocus(); BufferedImageLoad load = new BufferedImageLoad();
        try{spriteSheet = load.loadImage("/SPRITEsheet.png"); background = load.loadImage("background.jpg");}
        catch(IOException e){e.printStackTrace();}
        addKeyListener(new KeyBoard(this));
        texture = new Texture(this);
        motion = new Motion(texture, this);
        player1 = new Player1(300, 350, texture, this, motion);
        motion.createEnemy(enemyCount);
        playerA = motion.getEntitiy(); enemy = motion.getEntitiyB();
    }

    private void tick()
    {
        player1.tick(); motion.tick();
        if(enemyKilled >= enemyCount) {enemyCount += 2; enemyKilled = 0; motion.createEnemy(enemyCount);}
    }
    public BufferedImage getSpriteSheet() { return spriteSheet; }
    public int getEnemyKilled(){return enemyKilled;}
    public void setEnemyKilled(int enemyKilled)
    {
        this.enemyKilled = enemyKilled; kills +=1;
        if(kills == 400)
        {
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            JOptionPane.showMessageDialog(this, "CONGRATULATIONS. YOU REACHED 400 KILLS. BYE");
            System.exit(0);
        }
    }

    private void render()
    {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if(bufferStrategy == null) { createBufferStrategy(3); return; }
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(image, 0,0, getWidth(), getHeight(), this); g.drawImage(background,0,0, this);
        background.getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        player1.render(g); motion.render(g);
        g.setColor(Color.GRAY); g.fillRect(5,5,200, 25);
        g.setColor(Color.GREEN); g.fillRect(5,5, health, 25);
        g.setColor(Color.RED); g.drawRect(5,5, health, 25);
        g.setColor(Color.BLACK); g.setFont(new Font("Arial", Font.BOLD, 15)); g.drawString("HEALTH: " + health, 20,23);
        g.setColor(Color.BLACK); g.setFont(new Font("Arial", Font.BOLD, 20)); g.drawString("KILLS: " + kills, 10,55);
        g.setColor(Color.BLACK); g.setFont(new Font("Arial", Font.BOLD, 20)); g.drawString("HITS: " + hits, 120,55);
        g.dispose(); bufferStrategy.show();
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_D) { player1.setxVelocity(15); }
        else if(key == KeyEvent.VK_A) { player1.setxVelocity(-15); }
        else if(key == KeyEvent.VK_ESCAPE)
        {
            UIManager UI=new UIManager();
            UI.put("OptionPane.background", Color.BLACK);
            UI.put("OptionPane.messageForeground", Color.GREEN);
            UI.put("Panel.background", Color.BLACK);
            int result = 0;
            JOptionPane.showConfirmDialog(this,"Sure? You want to exit?", "BODY DEFENSE", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){System.exit(0);}
        }
        else if(key == KeyEvent.VK_SPACE && !shoot)
        {shoot = true; motion.addEntity(new Bullet1(player1.getX(), player1.getY(), texture, this));}
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_D) {player1.setxVelocity(0);}
        else if(key == KeyEvent.VK_A) {player1.setxVelocity(0);}
        else if(key == KeyEvent.VK_SPACE) {shoot = false;}
    }

    synchronized void start()
    {
        if(running) {return;}
        running = true; thread = new Thread(this); thread.start();
    }

    synchronized void stop()
    {
        if(!running){return;} running = false;
        try {thread.join();} catch (InterruptedException e){e.printStackTrace();}
        System.exit(1);
    }
}
