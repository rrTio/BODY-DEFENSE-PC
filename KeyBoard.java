import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyBoard extends KeyAdapter
{
    ProjectClass projectClass;

    public KeyBoard(ProjectClass projectClass) { this.projectClass = projectClass; }
    public void keyPressed(KeyEvent e) { projectClass.keyPressed(e); }
    public void keyReleased(KeyEvent e) { projectClass.keyReleased(e); }
}
