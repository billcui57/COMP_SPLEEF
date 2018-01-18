import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author billc
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        drawingArea1.timer();
        drawingArea1.t1.start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        drawingArea1 = new DrawingArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1150, 920));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jButton1.setFocusable(false);
        jButton1.setText("Country");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFocusable(false);
        jButton2.setText("Heavens");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFocusable(false);
        jButton3.setText("Hell");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFocusable(false);
        jButton4.setText("See Past Games");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout drawingArea1Layout = new javax.swing.GroupLayout(drawingArea1);
        drawingArea1.setLayout(drawingArea1Layout);
        drawingArea1Layout.setHorizontalGroup(
            drawingArea1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(drawingArea1Layout.createSequentialGroup()
                .addContainerGap(557, Short.MAX_VALUE)
                .addGroup(drawingArea1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, drawingArea1Layout.createSequentialGroup()
                        .addGroup(drawingArea1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(519, 519, 519))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, drawingArea1Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(499, 499, 499))))
        );
        drawingArea1Layout.setVerticalGroup(
            drawingArea1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, drawingArea1Layout.createSequentialGroup()
                .addContainerGap(390, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(85, 85, 85)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(223, 223, 223))
        );

        getContentPane().add(drawingArea1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        switch (evt.getKeyCode()) {
            case 65:
            case 68:
                drawingArea1.player1.stop();
                break;

            case 37:
            case 39:
                drawingArea1.player2.stop();
                break;

        }


    }//GEN-LAST:event_formKeyReleased

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:

        switch (evt.getKeyCode()) {
            case 87:
                drawingArea1.player1.jump();
                break;
            case 65:
                drawingArea1.player1.moveLeft();
                break;
            case 68:
                drawingArea1.player1.moveRight();
                break;
            case 32:
                if (drawingArea1.gun1.canShoot == true) {
                    drawingArea1.gun1.shoot(drawingArea1.player1.playerx, drawingArea1.player1.playery, drawingArea1.player1.faceDirection);
                }
                break;
            case 38:
                drawingArea1.player2.jump();
                break;
            case 37:
                drawingArea1.player2.moveLeft();
                break;
            case 39:
                drawingArea1.player2.moveRight();
                break;
            case 76:
                if (drawingArea1.gun1.canShoot == true) {
                    drawingArea1.gun2.shoot(drawingArea1.player2.playerx, drawingArea1.player2.playery, drawingArea1.player2.faceDirection);
                }
                break;
        }

        System.out.println("Hello");


    }//GEN-LAST:event_formKeyPressed

    private void drawingArea1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawingArea1MousePressed
        // TODO add your handling code here:
        for (int y = -10; y < 11; y++) {
            for (int x = -10; x < 11; x++) {
                drawingArea1.ground[evt.getY() + y][evt.getX() + x] = false;

                int rgb = new Color(214, 217, 223).getRGB();
                drawingArea1.map.setRGB(evt.getX() + x, evt.getY() + y, rgb);

            }
        }
    }//GEN-LAST:event_drawingArea1MousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        drawingArea1.runGameStartUp = true;
        drawingArea1.mapName = "Map.png";
        drawingArea1.remove(jButton1);
        drawingArea1.remove(jButton2);
        drawingArea1.remove(jButton3);
        drawingArea1.remove(jButton4);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        drawingArea1.runGameStartUp = true;
        drawingArea1.mapName = "map_sky.png";
        drawingArea1.remove(jButton1);
        drawingArea1.remove(jButton2);
        drawingArea1.remove(jButton3);
        drawingArea1.remove(jButton4);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         drawingArea1.runGameStartUp = true;
        drawingArea1.mapName = "map hell.png";
        drawingArea1.remove(jButton1);
        drawingArea1.remove(jButton2);
        drawingArea1.remove(jButton3);
        drawingArea1.remove(jButton4);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         drawingArea1.scene = 3;

        drawingArea1.remove(jButton1);
        drawingArea1.remove(jButton2);
        drawingArea1.remove(jButton3);
        drawingArea1.remove(jButton4);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private DrawingArea drawingArea1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    // End of variables declaration//GEN-END:variables
}
