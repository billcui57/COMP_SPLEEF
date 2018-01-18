
import java.awt.Graphics;
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
public class CRandomWeaponBubble {

    Random ran = new Random();
    int bubblex;
    int bubbley;
    int gun;
    int size = 20;

    public void generate(boolean[][] ground) {
        do{
        bubblex = ran.nextInt(ground[0].length - size -50);
        bubbley = ran.nextInt(ground.length-size-300);
        gun=ran.nextInt(2);
        }while((ground[bubbley + 1 +size][bubblex] == false)||(ground[bubbley][bubblex+size]==true));
        
        while (ground[bubbley - 1 +size][bubblex] == true) {
            bubbley--;
        }
    }
    
    public void draw(Graphics g){
        g.fillOval(bubblex, bubbley, size, size);
        switch(gun){
            case 0:
                g.drawString("Shotgun", bubblex, bubbley);
                break;
            case 1:
                g.drawString("Rocket", bubblex, bubbley);
                break;
        }
    }

}

