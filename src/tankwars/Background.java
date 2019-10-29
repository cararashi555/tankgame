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

        int width = 1280;
        int height = 960;
        setPreferredSize(new Dimension(width, height));
    }

    private void loadImage(BufferedImage img) {
        this.background = img;
    }

    void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.background, 0,0, null);
    }
}
