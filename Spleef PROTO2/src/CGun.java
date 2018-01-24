import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.FileInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

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

    int[] bulletx = new int[100];
    int[] bullety = new int[100];
    int maxbullet;
    int[] velocity = new int[100];
    int[] direction = new int[100];
    boolean canShoot;
    int weapontype = 1;

    //which weapon shoot decision
    public void shoot(int playerx, int playery, int faceWhere) {
        switch (weapontype) {
            case 1:
                machineGunCount = 0;
                gunShoot(playerx, playery, faceWhere);

                break;
            case 2:
                machineGunCount = 0;
                shotgunShoot(playerx, playery, faceWhere);
                weapontype = 1;

                break;
            case 3:
                machineGunCount = 0;
                rocketShoot(playerx, playery, faceWhere);
                weapontype = 1;
            case 4:
                machineGunShoot(playerx, playery, faceWhere);
                break;
        }
    }
    int machineGunCount = 0;

    public void machineGunShoot(int playerx, int playery, int faceWhere) {
        maxbullet = 40;
        //machine gun rapid fire
        if (canShoot == true) {
            bulletx[machineGunCount] = playerx;
            bullety[machineGunCount] = playery;
            direction[machineGunCount] = faceWhere;

            bulletspread = 0;
            blastradius = 15;
            velocity[machineGunCount] = 20;
            machineGunCount++;
            if (machineGunCount == maxbullet) {
                canShoot = false;
                weapontype = 1;

            }
        }
    }

    public void gunShoot(int playerx, int playery, int faceWhere) {
        maxbullet = 1;
        //normal gun shoot once
        if (canShoot == true) {
            bulletx[0] = playerx;
            bullety[0] = playery;
            direction[0] = faceWhere;
            canShoot = false;
            bulletspread = 0;
            blastradius = 15;
            velocity[0] = 5;
        }

    }

    int bulletspread;

    public void shotgunShoot(int playerx, int playery, int faceWhere) {
        maxbullet = 20;
        //shotgun shoot once with bulletspread
        if (canShoot == true) {
            for (int i = 0; i < maxbullet; i++) {
                bulletx[i] = playerx;
                bullety[i] = playery;
                velocity[i] = 10;
                direction[i] = faceWhere;
            }

            bulletspread = 1;
            canShoot = false;
            blastradius = 15;
        }
    }

    int blastradius;

    public void rocketShoot(int playerx, int playery, int faceWhere) {
        //rocket shoot once with blast radius
        maxbullet = 1;
        if (canShoot == true) {
            bulletx[0] = playerx;
            bullety[0] = playery;
            direction[0] = faceWhere;
            canShoot = false;
            bulletspread = 0;
            blastradius = 50;
            velocity[0] = 15;
        }
    }

    //updates bullets
    public void update(int bulletStartx, int bulletStarty, boolean[][] ground, BufferedImage map) {
        int bulletOut = 0;
        for (int i = 0; i < maxbullet; i++) {

            //moves bullets
            bulletx[i] += (velocity[i] + (i * bulletspread)) * direction[i];
            bullety[i] += velocity[i];

            //tests if hit ground
            if (ground[bullety[i]][bulletx[i]] == true) {
                for (int y = -blastradius; y < blastradius; y++) {
                    for (int x = -blastradius; x < blastradius; x++) {
                        //ground is destroyed
                        ground[bullety[i] + y][bulletx[i] + x] = false;
                        //stops bullet
                        bulletspread = 0;
                        velocity[i] = 0;
                        if ((ground[bullety[i] + y][bulletx[i] + x] == false) && (map.getRGB(bulletx[i] + x, bullety[i] + y) != new Color(242, 101, 34).getRGB())) {
                            int rgb = new Color(214, 217, 223).getRGB();
                            map.setRGB(bulletx[i] + x, bullety[i] + y, rgb);
                        }
                    }
                }
            }

            //do so all bullets leave before can shoot
            if ((bulletx[i] + velocity[i] * direction[i] >= ground[0].length - 30) || (bulletx[i] + velocity[i] * direction[i] <= 30) || bullety[i] + 50 >= ground.length) {
                bulletspread = 0;
                velocity[i] = 0;
            }
            //do so all bullets leave before can shoot
            if (velocity[i] == 0) {
                bulletOut++;
            }

        }
        //do so all bullets leave before can shoot
        if (bulletOut == maxbullet) {
            canShoot = true;
            machineGunCount = 0;
        }

    }

    //show bullets
    public void show(Graphics g) {
        for (int i = 0; i < maxbullet; i++) {
            g.drawLine(bulletx[i], bullety[i], bulletx[i] + velocity[i] * direction[i], bullety[i] + velocity[i]);
        }
    }

    //WIP audio
    public void pew() throws Exception {
        
        InputStream music;
        try {
            music = new FileInputStream(new File("pew.wav"));
            AudioStream audios = new AudioStream(music);
            AudioPlayer.player.start(audios);
        } catch (Exception e) {
            System.out.println("hi");
        }

    }

}
