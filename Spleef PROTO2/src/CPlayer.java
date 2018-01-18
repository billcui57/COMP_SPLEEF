
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author billc
 */
public class CPlayer {

    int playerx = 10;
    int playery = 50;
    int GRAVITY = 1;
    int velocity = 0;
    int size = 20;
    boolean canJump = true;
     boolean lost= false;

    public void update(boolean[][] ground) {

        boolean fall = true;

        //test if player can fit through hole in ground or wall
        for (int i = 0; i < size + 1; i++) {
            if (ground[playery + size][playerx + i] == true) {
                fall = false;
                playery--;
                velocity = 0;
                canJump = true;
            }

            if (ground[playery + i][playerx + size] == true) {
                horizontalVelocity = 0;
                playerx--;
            }

            if (ground[playery + i][playerx] == true) {
                horizontalVelocity = 0;
                playerx++;
            }
            
             if (ground[playery -1][playerx + i] == true) {
                
                playery++;
                velocity = 0;
                
            }
        }

        //fall
        if (fall == true) {
            velocity += GRAVITY;
            playery += velocity;
        }

        //move left and right
        playerx += horizontalVelocity;

        //prevent player from leaving array
        if (playerx - horizontalVelocity - size <= 0) {
            playerx = horizontalVelocity + size;
        } else if (playerx + size + horizontalVelocity >= ground[0].length-40) {
            playerx = ground[0].length -40 - horizontalVelocity - size;
        }

        //player loses
         
        if (playery > ground.length - 30) {
            velocity = 0;
            playery--;
            lost = true;
            System.out.println("Loser");
        }
        
      

    }

    int horizontalVelocity = 0;
    int maxHorizontalVelocity = 5;

    public void show(Graphics g,Image[] avatar,int avatarIndex,ImageObserver io) {
        if(faceDirection==1){
            g.drawImage(avatar[avatarIndex], playerx, playery, size,size,io);
        }else if(faceDirection==-1){
            g.drawImage(avatar[avatarIndex], playerx+size, playery, size*faceDirection,size,io);
        }
        
    }

    int faceDirection = 1;
    int directionSize = 0;

    public void moveLeft() {

        horizontalVelocity = -maxHorizontalVelocity;
        faceDirection = -1;
        directionSize = -size;
    }

    public void moveRight() {

        horizontalVelocity = maxHorizontalVelocity;

        faceDirection = 1;
        directionSize = 0;
    }

    public void jump() {
        System.out.println("hi");
        if (canJump == true) {
            playery -= 5;
            velocity = -17;
            canJump = false;
        }
    }

    public void stop() {
        horizontalVelocity = 0;
    }
    
    Random ran  = new Random();
    public void placeRandom(boolean[][] ground){
        
        //spawn always on ground???
         playerx =ran.nextInt(ground[0].length - 50);
        
        
        
    }

   
}
