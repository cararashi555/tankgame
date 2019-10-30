package tankwars;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Background extends JPanel {
    private BufferedImage background;

    public Background(BufferedImage img) {
        initBackground(img);
    }

    private void initBackground(BufferedImage img) {
        loadImage(img);

        int width = img.getWidth();
        int height = img.getHeight();
        setPreferredSize(new Dimension(width, height));
    }

    private void loadImage(BufferedImage img) {
        this.background = img;
    }

    void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int world_width = 1280;
        for(int i = 0; i < world_width; i+=320){
            int world_height = 960;
            for(int j = 0; j < world_height; j+=240){
                g2d.drawImage(this.background, i,j, null);
            }
        }
    }
}
