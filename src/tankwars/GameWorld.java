/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankwars;


import tankwars.powerup.ExtraLife;
import tankwars.powerup.PowerUp;
import tankwars.walls.BreakableWall;
import tankwars.walls.UnbreakableWall;
import tankwars.walls.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;


public class GameWorld extends JPanel  {

    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 950;
    public static final int WORLD_WIDTH = 1600;
    public static final int WORLD_HEIGHT = 1200;

    private BufferedImage world;
    private Background backgroundImg;
    private Graphics2D buffer;
    private JFrame jf;
    private Tank t1;
    private Tank t2;
    private GameMap map;


    public static void main(String[] args) {
        Thread x;
        GameWorld trex = new GameWorld();
        trex.init();
        try {

            while (true) {
                trex.repaint();
//                System.out.println(trex.t1);
//                System.out.println(trex.t2);
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {

        }
    }

    private void init() {
        this.jf = new JFrame("Tank Wars");

        this.world = new BufferedImage(GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage t1img = null, background, unbreakableWall = null, bullet, extraLife, breakableWall = null;

        try {
            BufferedImage tmp;

            t1img = read(new File("resources/tank1.png"));

            background = read(new File("resources/Background.bmp"));
            backgroundImg = new Background(background);

            unbreakableWall = read(new File("resources/Wall1.gif"));
            UnbreakableWall.setImg(unbreakableWall);
            breakableWall = read(new File("resources/Wall2.gif"));
            BreakableWall.setImg(breakableWall);

            bullet = read(new File("resources/Weapon.gif"));
            Bullet.setImg(bullet);

            //extraLife = read(new File("resources/power-up-1.png"));
           // ExtraLife.setImg(extraLife);



        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        t1 = new Tank(430, 600, 0, 0, 0, t1img);
        t2 = new Tank(1200, 600, 0, 0, 180, t1img);

        map = new GameMap();
//        /**
//         * Add coordinates to breakable and unbreakable walls.
//         * Then add all to ArrayList<Wall>
//         *
//         **/
//
//        for(int i = 0; i < WORLD_WIDTH; i += unbreakableWall.getHeight()){
//            walls.add(new UnbreakableWall(i, 0));
//            walls.add(new UnbreakableWall(i, WORLD_HEIGHT - unbreakableWall.getHeight()));
//        }
//        for(int i = unbreakableWall.getHeight(); i < WORLD_HEIGHT; i += unbreakableWall.getHeight()){
//            walls.add(new UnbreakableWall(WORLD_WIDTH - unbreakableWall.getHeight(), i));
//            walls.add(new UnbreakableWall(0, i));
//        }
//
//        for(int i = WORLD_WIDTH / 10; i < WORLD_WIDTH; i += 192){
//            for(int j = WORLD_HEIGHT / 4; j < WORLD_HEIGHT - 192; j += 192){
//                walls.add(new BreakableWall(i, j));
//            }
//        }
//
//        for(int i = 64; i < WORLD_HEIGHT - 64; i += breakableWall.getHeight()){
//            walls.add(new BreakableWall(WORLD_WIDTH / 2 + 32, i));
//        }

        //powerUps.add(new ExtraLife(250, 550));

        TankControl tc1 = new TankControl(t1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);
        TankControl tc2 = new TankControl(t2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_ENTER);

        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);


        this.jf.addKeyListener(tc1);
        this.jf.addKeyListener(tc2);

        this.jf.setSize(SCREEN_WIDTH, SCREEN_HEIGHT + 30);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);

    }

    private void update(){
        t1.update();
        t2.update();

        t1.checkCollision(t2);
        t2.checkCollision(t1);
        map.handleCollision(t1);
        map.handleCollision(t2);
    }

    /**
        X coordinate for split screen mode. Screen size becomes SCREEN_WIDTH / 2.
        Tanks need to be located with an X coordinate that equals to half of the split screen.
        Hence, x coordinate would be equal to 1/4 of the total SCREEN_WIDTH.
        Function below checks x coordinate for both tanks.
     */
    private int getXCoordinate(Tank t){
        int x = t.getX();
        if(x < SCREEN_WIDTH / 4)
            x = SCREEN_WIDTH / 4;
        if(x > WORLD_WIDTH - SCREEN_WIDTH / 4)
            x = WORLD_WIDTH - SCREEN_WIDTH / 4;
        return x;
    }

    /**
    Y coordinate for split screen mode.
    Tanks need to be visible with a y coordinate that equals to half of the split screen.
    Hence, y coordinate would be equal to 1/2 of the total SCREEN_HEIGHT.
    Function below checks y coordinate for both tanks.
    */
    public int getYCoordinate(Tank t){
        int y = t.getY();
        if(y < SCREEN_HEIGHT / 2)
            y = SCREEN_HEIGHT / 2;
        if(y > WORLD_HEIGHT - SCREEN_HEIGHT / 2)
            y = WORLD_HEIGHT - SCREEN_HEIGHT / 2;
        return y;
    }

    @Override
    public void paintComponent(Graphics g) {
        update();
        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();
        super.paintComponent(g2);

        this.setBackground(Color.black);
        this.backgroundImg.drawImage(buffer);

        map.drawImage(buffer);
        /*for(PowerUp p : powerUps)
            p.drawImage(buffer);*/

        this.t1.drawImage(buffer);
        this.t2.drawImage(buffer);

        BufferedImage leftScreen = world.getSubimage(getXCoordinate(t1) - SCREEN_WIDTH / 4, getYCoordinate(t1) - SCREEN_HEIGHT / 2, SCREEN_WIDTH / 2, SCREEN_HEIGHT);
        BufferedImage rightScreen = world.getSubimage(getXCoordinate(t2) - SCREEN_WIDTH / 4, getYCoordinate(t2) - SCREEN_HEIGHT / 2, SCREEN_WIDTH / 2, SCREEN_HEIGHT);

        g2.drawImage(leftScreen, 0, 0, null);
        g2.drawImage(rightScreen, SCREEN_WIDTH / 2 + 10, 0, null);

        g2.setColor(Color.GREEN);
        g2.fillRect(SCREEN_WIDTH / 4 - 60, 30, 2* t1.getCurrentHealth(), 30);
        g2.fillRect(SCREEN_WIDTH - SCREEN_WIDTH / 4 - 140, 30, 2* t2.getCurrentHealth(), 30);

        /**
         * Add minimap. Set its width and length to 1/5 of WORLD_WIDTH and WORLD_HEIGHT respectively.
         */
        g2.drawImage(world, SCREEN_WIDTH / 2 - WORLD_WIDTH / 10, SCREEN_HEIGHT - WORLD_HEIGHT / 5, WORLD_WIDTH / 5, WORLD_HEIGHT / 5, null);
    }


}
