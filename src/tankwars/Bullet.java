package tankwars;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet implements CollidableObject {
    private static BufferedImage bulletImg;
    private BufferedImage explosion;
    private final int R = 5;
    private int x, y, angle, vx, vy;
    private Rectangle r;
    private boolean collided;
    int waitTime = 0;


    public Bullet(int x, int y, int angle){
        this.x = x;
        this.y = y;
        this.vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        this.vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        this.angle =  angle;
        this.r = new Rectangle(x, y, bulletImg.getWidth(), bulletImg.getHeight());
    }

    public void drawImage(Graphics2D buffer){
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.bulletImg.getWidth() / 2.0, this.bulletImg.getHeight() / 2.0);
        buffer.drawImage(bulletImg, x, y, null);

        spawnBullet();

    }

    public static void setImg(BufferedImage img){
        bulletImg = img;
    }

    @Override
    public void checkCollision(CollidableObject c) {
        if(collided){
            setImg(explosion);
        }
        setImg(bulletImg);
    }

    public void spawnBullet(){
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;

    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(x, y, bulletImg.getWidth(), bulletImg.getHeight());
    }
}
