/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatastarter;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utils.CardSwitcher;
import javax.swing.JPanel;
import utils.ImageUtil;

/**
 *
 * @author michael.roy-diclemen
 */
public class EndPanel extends javax.swing.JPanel {
        public static final String CARD_NAME = "end";
 CardSwitcher switcher;
 BufferedImage image;
    /**
     * Creates new form EndPanel
     */
    public EndPanel(CardSwitcher p) {
        initComponents();
        switcher = p;
        image = ImageUtil.loadAndResizeImage("sheep.png",200,200);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draw image
        g.drawImage(image, 200, 200, this);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        againButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        againButton.setText("Menu");
        againButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                againButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(390, 390, 390)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(againButton))
                .addContainerGap(397, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(againButton)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(163, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void againButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_againButtonActionPerformed
        switcher.switchToCard(IntroPanel.CARD_NAME);
    }//GEN-LAST:event_againButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton againButton;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
