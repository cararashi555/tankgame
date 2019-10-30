package tankwars;

import java.awt.image.BufferedImage;

public class Bullet implements CollidableObject {
    private Tank tankPlayer;
    private BufferedImage bulletImg;
    private BufferedImage explosion;

    Tank getTankPlayer(){
        return tankPlayer;
    }

    public void setTankPlayer(Tank t){
        this.tankPlayer = t;
    }

}
