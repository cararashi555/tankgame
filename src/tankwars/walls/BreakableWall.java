package tankwars.walls;

import tankwars.Bullet;
import tankwars.CollidableObject;
import tankwars.GameWorld;
import tankwars.Tank;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BreakableWall extends Wall {
    private int x, y;
    private Rectangle r;
    private static BufferedImage wallImg;
    private boolean collided = false;

    public BreakableWall(int x, int y){
        this.x = x;
        this.y = y;
        this.r = new Rectangle(x, y, wallImg.getWidth(), wallImg.getHeight());
    }

    public static void setImg(BufferedImage img){
        wallImg = img;
    }

    @Override
    public void checkCollision(CollidableObject c) {
        if(c instanceof Bullet){
            if(this.getRectangle().intersects(c.getRectangle())){
                collided = true;
            }
        }
    }

    public boolean hasCollided() {
        return collided;
    }

    @Override
    public Rectangle getRectangle() {
        return r;
    }

    public void drawImage(Graphics2D buffer){
        buffer.drawImage(wallImg, x, y, null);
    }
}
