public class Physics
{
    public static boolean Collision(Player enta, Enemy entb)
    { if(enta.getBounds().intersects(entb.getBounds())){return true;} return false; }

    public static boolean Collision(Enemy entb, Player enta)
    { if(entb.getBounds().intersects(enta.getBounds())) {return true;} return false; }
}
