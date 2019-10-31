package tankwars.walls;

import tankwars.CollidableObject;

import java.awt.*;

public abstract class Wall implements CollidableObject {
    @Override
    public boolean checkCollision() {
        return false;
    }

    @Override
    public Rectangle getRectangle() {
        return null;
    }
}
