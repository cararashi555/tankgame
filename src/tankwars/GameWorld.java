/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankwars;


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
    public static final int SCREEN_HEIGHT = 960;
    public static final int WORLD_WIDTH = 2240;
    public static final int WORLD_HEIGHT = 2240;

    private BufferedImage world;
    private Background backgroundImg;
    private Graphics2D buffer;
    private JFrame jf;
    private Tank t1;
    private Tank t2;
    private ArrayList<Bullet> bullets;
    private ArrayList<Wall> walls = new ArrayList<>();


    public static void main(String[] args) {
        Thread x;
        GameWorld trex = new GameWorld();
        trex.init();
        try {

            while (true) {
                trex.repaint();
                System.out.println(trex.t1);
                System.out.println(trex.t2);
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {

        }
    }

    private void init() {
        this.jf = new JFrame("Tank Wars");

        this.world = new BufferedImage(GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage t1img = null, background, unbreakableWall = null, bullet, breakableWall = null;

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


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        t1 = new Tank(210, 420, 0, 0, 0, t1img);
        t2 = new Tank(995, 420, 0, 0, 180, t1img);

        for(int i = 0; i < SCREEN_WIDTH; i += unbreakableWall.getHeight()){
            walls.add(new UnbreakableWall(i, 0));
            walls.add(new UnbreakableWall(i, SCREEN_HEIGHT - unbreakableWall.getHeight()));
        }
        for(int i = unbreakableWall.getHeight(); i < SCREEN_HEIGHT; i += unbreakableWall.getHeight()){
            walls.add(new UnbreakableWall(SCREEN_WIDTH - unbreakableWall.getHeight(), i));
            walls.add(new UnbreakableWall(0, i));
        }

        for(int i = SCREEN_WIDTH / 10; i < SCREEN_WIDTH; i += 192){
            for(int j = SCREEN_HEIGHT / 4; j < SCREEN_HEIGHT - 192; j += 192){
                walls.add(new BreakableWall(i, j));
            }
        }

        for(int i = 64; i < SCREEN_HEIGHT - 64; i += breakableWall.getHeight()){
            walls.add(new BreakableWall(SCREEN_WIDTH / 2 - 32, i));
        }

        
        TankControl tc1 = new TankControl(t1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);
        TankControl tc2 = new TankControl(t2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SHIFT);

        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);


        this.jf.addKeyListener(tc1);
        this.jf.addKeyListener(tc2);

        this.jf.setSize(GameWorld.SCREEN_WIDTH, GameWorld.SCREEN_HEIGHT + 30);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);

    }

    private void update(){
        t1.update();
        t2.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        update();
        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();
        super.paintComponent(g2);

        this.backgroundImg.drawImage(buffer);
        for(Wall w: walls){
            w.drawImage(buffer);
        }

        this.t1.drawImage(buffer);
        this.t2.drawImage(buffer);
        g2.drawImage(world, 0 , 0 , null);
    }


}
