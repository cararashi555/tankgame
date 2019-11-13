package tankwars.powerup;

import tankwars.CollidableObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class PowerUp implements CollidableObject{
    @Override
    public void checkCollision(CollidableObject c) {
        this.getRectangle().intersects(c.getRectangle());
    }

    @Override
    public Rectangle getRectangle() { return null; }

    public static void setImg(BufferedImage powerUp){ }
    public void drawImage(Graphics2D buffer){ }
}
