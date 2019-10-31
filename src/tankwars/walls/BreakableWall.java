package tankwars.walls;

import tankwars.CollidableObject;

import java.awt.*;

public class BreakableWall extends Wall {
    @Override
    public boolean checkCollision() {
        return false;
    }

    @Override
    public Rectangle getRectangle() {
        return null;
    }
}
