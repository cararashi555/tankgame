package tankwars;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet implements CollidableObject {
    private static BufferedImage bulletImg;
    private BufferedImage explosion;
    private int x, y, angle;

    public Bullet(int x, int y, int angle){
        this.x = x;
        this.y = y;
        this.angle =  angle;
    }

    public void drawImage(Graphics2D buffer){
        buffer.drawImage(bulletImg, x, y, null);
    }

    public static void setImg(BufferedImage img){
        bulletImg = img;
    }

    @Override
    public void checkCollision(Class c) {

    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(x, y, bulletImg.getWidth(), bulletImg.getHeight());
    }
}
