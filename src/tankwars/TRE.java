/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankwars;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;


public class TRE extends JPanel  {


    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 960;
    private BufferedImage world;
    private Background backgroundImg;
    private Graphics2D buffer;
    private JFrame jf;
    private Tank t1;
    private Tank t2;


    public static void main(String[] args) {
        Thread x;
        TRE trex = new TRE();
        trex.init();
        try {

            while (true) {
                trex.t1.update();
                trex.repaint();
                System.out.println(trex.t1);
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {

        }

    }

    private void init() {
        this.jf = new JFrame("Tank Wars");

        this.world = new BufferedImage(TRE.SCREEN_WIDTH, TRE.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage t1img = null, t2img = null, background;

        try {
            BufferedImage tmp;
            System.out.println(System.getProperty("user.dir"));

            t1img = read(new File("resources/tank1.png"));
            background = read(new File("resources/background.png"));
            backgroundImg = new Background(background);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        t1 = new Tank(200, 200, 0, 0, 0, t1img);


        TankControl tc1 = new TankControl(t1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);

        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);


        this.jf.addKeyListener(tc1);


        this.jf.setSize(TRE.SCREEN_WIDTH, TRE.SCREEN_HEIGHT + 30);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);


    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();
        super.paintComponent(g2);

        this.backgroundImg.drawImage(buffer);
        this.t1.drawImage(buffer);
        g2.drawImage(world,0,0,null);
    }


}
