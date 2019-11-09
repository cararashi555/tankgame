package tankwars;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MapMaker {

        private BufferedImage wall;

        public MapMaker(BufferedImage img) {
            loadImage(img);
        }

        private void loadImage(BufferedImage img) {
            this.wall = img;
        }

        void drawImage(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            for(int i = 0; i < GameWorld.SCREEN_WIDTH; i += wall.getWidth()){
                    g2d.drawImage(this.wall, i,0, null);
            }
            for(int i = wall.getHeight(); i < GameWorld.SCREEN_HEIGHT; i += wall.getHeight()){
                g2d.drawImage(this.wall, 0,i, null);
            }
            for(int i = 0; i < GameWorld.SCREEN_WIDTH; i += wall.getWidth()){
                g2d.drawImage(this.wall, i,GameWorld.SCREEN_HEIGHT-wall.getHeight(), null);
            }
            for(int i = wall.getHeight(); i < GameWorld.SCREEN_HEIGHT; i += wall.getHeight()){
                g2d.drawImage(this.wall, GameWorld.SCREEN_WIDTH - wall.getWidth(),i, null);
            }
        }
}
