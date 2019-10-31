package tankwars;

import java.awt.*;

public interface CollidableObject {
    public boolean checkCollision();
    public Rectangle getRectangle();
}
