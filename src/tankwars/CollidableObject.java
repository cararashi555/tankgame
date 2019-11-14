package tankwars;

import java.awt.*;

public interface CollidableObject {
    public void checkCollision(CollidableObject c);
    public Rectangle getRectangle();
}
