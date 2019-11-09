package tankwars.walls;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UnbreakableWall extends Wall {
    private int x, y;
    private Rectangle r;
    private BufferedImage wallImg;

    public UnbreakableWall() {}

    public UnbreakableWall(BufferedImage img) {
        loadImage(img);
    }

    public void setImg(BufferedImage wallImg){
        this.wallImg = wallImg;
    }

    private void loadImage(BufferedImage img) {
        this.wallImg = img;
    }

    @Override
    public void checkCollision() {
    }

    @Override
    public Rectangle getRectangle() {
        return r;
    }

    public void drawImage(Graphics2D buffer){
        buffer.drawImage(wallImg, x, y, null);
    }
}
