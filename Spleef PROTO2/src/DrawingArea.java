
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author billc
 */
public class DrawingArea extends javax.swing.JPanel {

    /**
     * Creates new form DrawingArea
     */
    public DrawingArea() {
        initComponents();

        //player animation
        avatar = new Image[10];
        for (int i = 0; i < avatar.length; i++) {
            avatar[i] = Toolkit.getDefaultToolkit().getImage("sprite_0" + i + ".png");
        }
        
        

    }

    boolean[][] ground;

    int players = 2;
    CPlayer player1;
    CPlayer player2;
    CGun gun1;
    CGun gun2;
    CRandomWeaponBubble bubble1;
    CRandomWeaponBubble bubble2;
    Image[] avatar = new Image[10];
    int avatarIndex = 0;
    BufferedImage map;
    String mapName = null;
    Image mainMenu = Toolkit.getDefaultToolkit().getImage("Main_Menu.png");

    int scene = 1;
    boolean runGameStartUp = false;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //scenes
        switch (scene) {
            case 1:
                menu(g);
                break;
            case 2:
                game(g);
                break;
            case 3:
                Image pastgame = Toolkit.getDefaultToolkit().getImage("saved.png");
                g.drawImage(pastgame, 0, 0, this);
                break;
            case 4:
                Image pauseMenu = Toolkit.getDefaultToolkit().getImage("paused_menu.png");
                g.drawImage(pauseMenu, 0, 0, this);
                break;
            case 5:
                tutorial(g,1);
                break;
            case 6:
                tutorial(g,2);
                break;
        }

    }

    public void tutorial(Graphics g, int page) {
        Image tutorial = Toolkit.getDefaultToolkit().getImage("tutorial" + page + ".png");
        g.drawImage(tutorial, 0, 0, this);
    }

    public void menu(Graphics g) {
        //main menu
        g.drawImage(mainMenu, 0, 0, this);
        if (runGameStartUp == true) {
            startUp();
        }
    }

    public void restart() {
        startUp();
    }

    public void startUp() {
        //game start up all players and bubbles reset
        try {
            map = ImageIO.read(new File(mapName));
        } catch (IOException e) {
            System.out.println("HEEEELLLPPPPP!!!!!!");
        }
        ground = new boolean[map.getHeight()][map.getWidth()];
        ground = groundDetect();
        player1 = new CPlayer();
        player2 = new CPlayer();
        player1.placeRandom(ground);
        player2.placeRandom(ground);
        bubble1 = new CRandomWeaponBubble();
        bubble1.generate(ground);
        bubble2 = new CRandomWeaponBubble();
        bubble2.generate(ground);

        gun1 = new CGun();
        gun2 = new CGun();
        scene = 2;
        runGameStartUp = false;
    }

    public void game(Graphics g) {

        //draws map
        g.drawImage(map, 0, 0, this);

        //updates and shows players
        //updates and shows bullets
        player1.show(g, avatar, avatarIndex, this);
        player1.update(ground);
        g.drawString("1", player1.playerx, player1.playery);
        gun1.update(player1.playerx, player1.playery, ground, map);
        gun1.show(g);

        player2.show(g, avatar, avatarIndex, this);
        player2.update(ground);
        g.drawString("2", player2.playerx, player2.playery);
        gun2.update(player2.playerx, player2.playery, ground, map);
        gun2.show(g);

        //detects bubble and player collision
        if ((player1.right > bubble1.bubblex) && (player1.playerx < bubble1.right)
                && (player1.bottom > bubble1.bubbley) && (player1.playery < bubble1.bottom)) {
            gun1.weapontype = bubble1.gun + 2;
            bubble1.generate(ground);

        }

        if ((player2.right > bubble1.bubblex) && (player2.playerx < bubble1.right)
                && (player2.bottom > bubble1.bubbley) && (player2.playery < bubble1.bottom)) {
            gun2.weapontype = bubble1.gun + 2;
            bubble1.generate(ground);

        }

        if ((player1.right > bubble2.bubblex) && (player1.playerx < bubble2.right)
                && (player1.bottom > bubble2.bubbley) && (player1.playery < bubble2.bottom)) {
            gun1.weapontype = bubble2.gun + 2;
            bubble2.generate(ground);

        }

        if ((player2.right > bubble2.bubblex) && (player2.playerx < bubble2.right)
                && (player2.bottom > bubble2.bubbley) && (player2.playery < bubble2.bottom)) {
            gun2.weapontype = bubble2.gun + 2;
            bubble2.generate(ground);

        }

        //draws bubbles
        bubble1.draw(g);
        bubble2.draw(g);

        //animation
        avatarIndex++;
        if (avatarIndex == avatar.length) {
            avatarIndex = 0;
        }

        //test if anyone won
        if (player1.lost == true) {
            System.out.println("player 2 wins");
            Image p2win = Toolkit.getDefaultToolkit().getImage("player2wins.png");
            g.drawImage(p2win, WIDTH, WIDTH, this);

            //writes map to .png
            try {
                // retrieve image

                File outputfile = new File("saved.png");
                ImageIO.write(map, "png", outputfile);
            } catch (IOException e) {

            }

            t1.stop();
        } else if (player2.lost == true) {

            System.out.println("player 1 wins");
            Image p1win = Toolkit.getDefaultToolkit().getImage("player1wins.png");
            g.drawImage(p1win, WIDTH, WIDTH, this);
            try {
                // retrieve image

                File outputfile = new File("saved.png");
                ImageIO.write(map, "png", outputfile);
            } catch (IOException e) {

            }
            t1.stop();
        }

    }

    public boolean[][] groundDetect() {
        //game start up converts .png to array
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                if ((map.getRGB(x, y) != new Color(242, 101, 34).getRGB()) && (map.getRGB(x, y) != new Color(214, 217, 223).getRGB())) {
                    ground[y][x] = true;
                } else {
                    ground[y][x] = false;
                }
            }
        }
        this.setSize(map.getWidth(), map.getHeight());

        return ground;
    }

    Timer t1;

    public void timer() {
        t1 = new Timer(30, new TimerListener());
    }

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            DrawingArea.super.repaint();

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
