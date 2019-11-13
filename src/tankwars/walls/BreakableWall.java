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
    private GameWorld g;

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
        if(this.getRectangle().intersects(c.getRectangle())){

        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(x, y, wallImg.getWidth(), wallImg.getHeight());
    }

    public void drawImage(Graphics2D buffer){
        buffer.drawImage(wallImg, x, y, null);
    }
}
