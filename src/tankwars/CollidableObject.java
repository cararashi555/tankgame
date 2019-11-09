package tankwars;

import java.awt.*;

public interface CollidableObject {
    public void checkCollision(Class c);
    public Rectangle getRectangle();

}
