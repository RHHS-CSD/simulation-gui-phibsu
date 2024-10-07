/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatastarter;

import java.awt.Color;
import utils.CardSwitcher;
import utils.ImageUtil;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

/**
 *
 * @author michael.roy-diclemen
 */
public class GamePanel extends javax.swing.JPanel implements MouseListener {

    public static final String CARD_NAME = "game";

    CardSwitcher switcher; // This is the parent panel
    Timer animTimer;
    BufferedImage sheepImg;
    BufferedImage foxImg;
    final int OFFSET = 5;
    ArraySimulation game;
    final int HEIGHT = 640;
    int size = HEIGHT / game.ROWS;
    boolean started = false;
    int speed;

    /**
     * Creates new form GamePanel
     */
    public GamePanel(CardSwitcher p) {
        initComponents();
        sheepImg = ImageUtil.loadAndResizeImage("sheep.png", size, size);//, WIDTH, HEIGHT)//ImageIO.read(new File("yourFile.jpg"));
        foxImg = ImageUtil.loadAndResizeImage("fox.png", size, size);

        this.setFocusable(true);

        // tell the program we want to listen to the mouse
        addMouseListener(this);
        //tells us the panel that controls this one
        switcher = p;
        //create and start a Timer for animation
        speed = 500;
        animTimer = new Timer(speed, new AnimTimerTick());
        //create game grid
        game = new ArraySimulation();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draw the animals
        for (int r = 0; r < game.ROWS; r++) {
            for (int c = 0; c < game.COLUMNS; c++) {
                if (game.grid[r][c] == 1) {
                    g.drawImage(sheepImg, c * size + OFFSET, r * size + OFFSET, this);
                } else if (game.grid[r][c] == 2) {
                    g.drawImage(foxImg, c * size + OFFSET, r * size + OFFSET, this);
                }
            }
        }

        //draw the grid outlines
        g.setColor(Color.BLACK);
        for (int r = 0; r <= game.ROWS; r++) {
            for (int c = 0; c <= game.COLUMNS; c++) {
                g.drawLine(OFFSET + c * size, OFFSET, OFFSET + c * size, game.COLUMNS * size + OFFSET);
            }
            g.drawLine(OFFSET, r * size + OFFSET, OFFSET + game.ROWS * size, r * size + OFFSET);
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

        stepButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();
        endButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        speedSlider = new javax.swing.JSlider();
        speedLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 204));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        stepButton.setText("Step");
        stepButton.setEnabled(false);
        stepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepButtonActionPerformed(evt);
            }
        });

        stopButton.setText("Stop");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        confirmButton.setText("Confirm ");
        confirmButton.setToolTipText("");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        endButton.setText("End");
        endButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endButtonActionPerformed(evt);
            }
        });

        startButton.setText("Start");
        startButton.setEnabled(false);
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        speedSlider.setMaximum(1500);
        speedSlider.setMinimum(100);
        speedSlider.setOrientation(javax.swing.JSlider.VERTICAL);
        speedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                speedSliderStateChanged(evt);
            }
        });

        speedLabel.setText("Speed: 0.5 ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(675, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(speedLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(endButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(confirmButton, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                        .addComponent(stopButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stepButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(47, 47, 47))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(stepButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(speedLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(endButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
//        lineX = 0;
    }//GEN-LAST:event_formComponentShown

    private void stepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepButtonActionPerformed
        game.step();
        repaint();
    }//GEN-LAST:event_stepButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        started = true;
        game.fillGrid();
        confirmButton.setEnabled(false);
        startButton.setEnabled(true);
        stopButton.setEnabled(true);
        stepButton.setEnabled(true);
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void endButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endButtonActionPerformed
        animTimer.stop();
        started = false;
        game.reset();
        switcher.switchToCard(EndPanel.CARD_NAME);
        confirmButton.setEnabled(true);
        startButton.setEnabled(false);
        stopButton.setEnabled(false);
        stepButton.setEnabled(false);
    }//GEN-LAST:event_endButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        animTimer.stop();
    }//GEN-LAST:event_stopButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        animTimer.start();
    }//GEN-LAST:event_startButtonActionPerformed

    private void speedSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_speedSliderStateChanged
        speed = speedSlider.getValue();
        animTimer.setDelay(speed);
        speedLabel.setText("Speed: " + Math.round(speed/100.0*100)/100.0);
    }//GEN-LAST:event_speedSliderStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmButton;
    private javax.swing.JButton endButton;
    private javax.swing.JLabel speedLabel;
    private javax.swing.JSlider speedSlider;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stepButton;
    private javax.swing.JButton stopButton;
    // End of variables declaration//GEN-END:variables
    /**
     * This event captures a click which is defined as pressing and releasing in
     * the same area
     *
     * @param me
     */
    public void mouseClicked(MouseEvent me) {
        if (!started) {
            //check the cursor is on a tile during the click
            int row = (me.getY() - OFFSET) / size;
            int col = (me.getX() - OFFSET) / size;
            int button = me.getButton();

            if (me.getX() < size*game.COLUMNS){
            //edit the grid based on how the user clicks
            if (button == 1) {
                game.editTile(row, col, 1);
            } else if (button == 3) {
                game.editTile(row, col, 2);
            }
        }}
        repaint();

    }

    /**
     * When the mountain is pressed
     *
     * @param me
     */
    public void mousePressed(MouseEvent me) {
    }

    /**
     * When the mouse button is released
     *
     * @param me
     */
    public void mouseReleased(MouseEvent me) {
    }

    /**
     * When the mouse enters the area
     *
     * @param me
     */
    public void mouseEntered(MouseEvent me) {
    }

    /**
     * When the mouse exits the panel
     *
     * @param me
     */
    public void mouseExited(MouseEvent me) {

    }

    /**
     * Everything inside this actionPerformed will happen every time the
     * animation timer clicks.
     */
    private class AnimTimerTick implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            //the stuff we want to change every clock tick
            game.step();
            //force redraw
            repaint();
        }
    }
}
