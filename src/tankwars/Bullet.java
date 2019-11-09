package tankwars;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet implements CollidableObject {
    private BufferedImage bulletImg;
    private BufferedImage explosion;
    private Tank owner;
    private int x, y;

    Bullet(int x, int y, Tank owner){
        this.x = x;
        this.y = y;
        this.owner = owner;
    }

    @Override
    public void checkCollision() {

    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(x, y, bulletImg.getWidth(), bulletImg.getHeight());
    }
}
