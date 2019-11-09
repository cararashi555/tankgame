package tankwars;

import java.awt.*;

public interface CollidableObject {
    public void checkCollision();
    public Rectangle getRectangle();
}
