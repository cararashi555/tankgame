package tankwars.walls;

import java.awt.*;

public class UnbreakableWall extends Wall{
    @Override
    public boolean checkCollision() {
        return false;
    }

    @Override
    public Rectangle getRectangle() {
        return null;
    }
}
