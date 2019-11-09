package tankwars.walls;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BreakableWall extends Wall {
    private int x, y;
    private Rectangle r;
    private BufferedImage wallImg;

    public BreakableWall(int x, int y, BufferedImage img){
        this.x = x;
        this.y = y;
        this.r = new Rectangle(x, y, img.getWidth(), img.getHeight());
    }

    public void setImg(BufferedImage wallImg){
        this.wallImg = wallImg;
    }

    @Override
    public void checkCollision() {
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(x, y, wallImg.getWidth(), wallImg.getHeight());
    }

    public void drawImage(Graphics2D buffer){
        buffer.drawImage(wallImg, x, y, null);
    }
}
