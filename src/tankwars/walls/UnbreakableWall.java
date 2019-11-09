package tankwars.walls;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UnbreakableWall extends Wall {
    private int x, y;
    private Rectangle r;
    private static BufferedImage wallImg;

    public UnbreakableWall() {}

    public UnbreakableWall(int x, int y){
        this.x = x;
        this.y = y;
        this.r = new Rectangle(x, y, wallImg.getWidth(), wallImg.getHeight());
    }

    public static void setImg(BufferedImage img){
        UnbreakableWall.wallImg = img;
    }

    @Override
    public void checkCollision(Class c) {
    }

    @Override
    public Rectangle getRectangle() {
        return r;
    }

    public void drawImage(Graphics2D buffer){
        buffer.drawImage(wallImg, x, y, null);
    }
}
