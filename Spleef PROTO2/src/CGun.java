
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author billc
 */
public class CGun {

    int[] bulletx = new int[20];
    int[] bullety = new int[20];
    int maxbullet;
    int[] velocity = new int [20];
    int direction;
    boolean canShoot = true;
    int weapontype = 1;

    public void shoot(int playerx, int playery, int faceWhere) {
        switch (weapontype) {
            case 1:
                gunShoot(playerx, playery, faceWhere);
                break;
            case 2:
                shotgunShoot(playerx, playery, faceWhere);
                weapontype = 1;
                break;
            case 3:
                rocketShoot(playerx, playery, faceWhere);
                weapontype = 1;
        }
    }

    public void gunShoot(int playerx, int playery, int faceWhere) {
        maxbullet = 1;
        if (canShoot == true) {
            bulletx[0] = playerx;
            bullety[0] = playery;
            direction = faceWhere;
            canShoot = false;
            bulletspread = 0;
            blastradius = 15;
            velocity[0] = 5;
        }

    }

    int bulletspread;

    public void shotgunShoot(int playerx, int playery, int faceWhere) {
        maxbullet = 20;
        if (canShoot == true) {
            for (int i = 0; i < maxbullet; i++) {
                bulletx[i] = playerx;
                bullety[i] = playery;
                velocity[i] = 5;
            }
            direction = faceWhere;
            bulletspread = 1;
            canShoot = false;
            blastradius = 15;
        }
    }

    int blastradius;

    public void rocketShoot(int playerx, int playery, int faceWhere) {
        maxbullet = 1;
        if (canShoot == true) {
            bulletx[0] = playerx;
            bullety[0] = playery;
            direction = faceWhere;
            canShoot = false;
            bulletspread = 0;
            blastradius = 50;
            velocity[0] = 5;
        }
    }

    public void update(int bulletStartx, int bulletStarty, boolean[][] ground, BufferedImage map) {
       int bulletOut=0;
        for (int i = 0; i < maxbullet; i++) {

            bulletx[i] += (velocity[i]+(i*bulletspread)) * direction;
            bullety[i] += velocity[i];

            if (ground[bullety[i]][bulletx[i]] == true) {
                for (int y= -blastradius; y < blastradius; y++) {
                    for (int x = -blastradius; x < blastradius; x++) {
                        ground[bullety[i] + y][bulletx[i] + x] = false;
                        bulletspread=0;
                        velocity[i] = 0;
                        if ((ground[bullety[i]+y][bulletx[i]+x] == false) && (map.getRGB(bulletx[i]+x, bullety[i]+y) != new Color(242, 101, 34).getRGB())) {
                            int rgb = new Color(214, 217, 223).getRGB();
                            map.setRGB(bulletx[i]+x, bullety[i]+y, rgb);
                        }
                    }
                }
            }

            //do so all bullets leave before can shoot
            if ((bulletx[i] + velocity[i] * direction >= ground[0].length - 30) || (bulletx[i] + velocity[i] * direction <= 30) || bullety[i] + 50 >= ground.length) {
                bulletspread=0;
                velocity[i] = 0;
            }
            
            
            if(velocity[i]==0){
                bulletOut++;
            }

        }
        
        if(bulletOut==maxbullet){
            
                canShoot=true;
            }else{
                canShoot=false;
            }
        
        
        
        
    }

    public void show(Graphics g) {
        for (int i = 0; i < maxbullet; i++) {
            g.drawLine(bulletx[i], bullety[i], bulletx[i] + velocity[i] * direction, bullety[i] + velocity[i]);
        }
    }

}
