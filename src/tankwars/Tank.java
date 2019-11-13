package tankwars;



import tankwars.walls.Wall;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Tank implements CollidableObject {

    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;
    private int waitTime = 0;
    private Rectangle r;

    private final int R = 5;
    private final int ROTATIONSPEED = 5;
    private int currentHealth;
    private int lives;

    private BufferedImage img;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private boolean stop = false;


    Tank(int x, int y, int vx, int vy, int angle, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.angle = angle;
        this.r = new Rectangle(x, y, img.getWidth(), img.getHeight());

    }

    public int getX() { return x; }

    public int getY() { return y; }

    public void collided(int value){
        if(currentHealth - value <= 0)
            currentHealth = 0;
        else
            currentHealth -= value;
    }

    public void powerHealth(int value){
        if(currentHealth + value >= 100)
            currentHealth = 100;
        else
            currentHealth += value;
    }

    public int getCurrentHealth() { return currentHealth; }

    public int getLives() { return lives; }

    public void setLives(int lives) { this.lives = lives; }

    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleShootPressed() {
        this.ShootPressed = true;
    }

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void unToggleShootPressed() {
        this.ShootPressed = false;
    }



    public void update() {
        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        if (this.ShootPressed && System.currentTimeMillis() - waitTime  > 1000) {
            this.shootBullet();
            waitTime = (int) System.currentTimeMillis();
        }
    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        if(!stop){
            x -= vx;
            y -= vy;
        }
        checkBorder();
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
    }

    private void shootBullet() {
        Bullet b = new Bullet(this.x, this.y, this.angle);
        bullets.add(b);
    }

    @Override
    public void checkCollision(CollidableObject c) {
        if(this.getRectangle().intersects(c.getRectangle())){
            if(c instanceof Bullet){
                collided(10);
            }
            this.stop = true;
        }
        this.stop = false;
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(this.x, this.y, img.getWidth(), img.getHeight());
    }


    private void checkBorder() {
        if (x < 30) {
            x = 30;
        }
        if (x >= GameWorld.WORLD_WIDTH - 88) {
            x = GameWorld.WORLD_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= GameWorld.WORLD_HEIGHT - 80) {
            y = GameWorld.WORLD_HEIGHT - 80;
        }
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    public ArrayList<Bullet> getBullets(){
        return bullets;
    }


    void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);
    }

}
