/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CritterRush.view;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import CritterRush.controller.GameController;
import CritterRush.controller.ICManager;
import CritterRush.controller.IObserver;
import CritterRush.controller.TowerManager;
import CritterRush.model.Mouse;
import CritterRush.model.tower.Tower;

public class GamePanel extends javax.swing.JPanel implements MouseListener, MouseMotionListener, ActionListener, IObserver{

	private static final long serialVersionUID = 1L;
	private TowerDefenseGame TDG;
	private GameController game;
	
	private Timer timer;
    

	/**
     * Creates new form GamePanel
     */
    public GamePanel(TowerDefenseGame frame, boolean visibility) {
        initComponents();
        this.TDG = frame;
        
        setBackground(new java.awt.Color(255, 204, 153));
		setDoubleBuffered(true);
		setVisible(visibility);
		this.setFocusable(true);
		this.requestFocus(true);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		//calls action performed every 10 milliseconds
		timer = new Timer(16, this);
    }
    
	
	/**
	 * Stop the timer when no critter are on the map.
	 * @param b
	 */
	public void update(String s) {
		
		//Wave Started
		if("waveStarted".equals(s)){
			timer.start();
			game.setReady(true);
			nextWave.setEnabled(false);
		}
		//Wave Complete
		else if("waveComplete".equals(s)){
			timer.stop();
			nextWave.setEnabled(true);
		}
		//Game Won
		else if("gameWon".equals(s)){
			GameWon();
		}
		
	}
    
	/**
	 * Perform the following actions every frame when the timer is on.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		game.spawnCritterWave();
		game.updateEntities();
		updatePlayerStats();
		repaint();
	}
    
    /**
     * Update the displayed player stats.
     */
    private void updatePlayerStats(){
		goldAmount.setText( String.valueOf(game.getPs().getCurrencyPoints()));
		livesAmount.setText(String.valueOf(game.getPs().getLifeCount()));
		scoreAmount.setText("Score : " + String.valueOf(game.getPs().getScore()));
    	
		if(game.getPs().getLifeCount() == 0){
    		gameOver();
    	}
    }
    
    /**
     * Update the displayed tower or critter info.
     * @param info
     */
    private void updateTCInfo(String[] info){
    	if(info.length >= 1) info1.setText(info[0]);
    	else info1.setText("");
    	if(info.length >= 2) info2.setText(info[1]);
    	else info2.setText("");
    	if(info.length >= 3) info3.setText(info[2]);
    	else info3.setText("");
    	if(info.length >= 4) info4.setText(info[3]);
    	else info4.setText("");
    	if(info.length >= 5) info5.setText(info[4]);
    	else info5.setText("");
    	if(info.length >= 6) info6.setText(info[5]);
    	else info6.setText("");
    	if(info.length >= 7) info7.setText(info[6]);
    	else info7.setText("");
    	repaint();
    }
    
    /**
     * Set the visibility of the tower and critter Info.
     * @param b
     */
    private void setVisibleTCInfo(boolean b){
    	info1.setVisible(b);
    	info2.setVisible(b);
    	info3.setVisible(b);
    	info4.setVisible(b);
    	info5.setVisible(b);
    	info6.setVisible(b);
    	info7.setVisible(b);
    	repaint();
    }
    
    /**
     * Get the timer.
     * @return Timer
     */
    public Timer getTimer() {
		return timer;
	}
    
    /**
     * Resets the layout.
     */
    public void reset(){
    	quit.setEnabled(true);
    	sellButton.setEnabled(false);
    	purchaseButton.setEnabled(false);
    	upgradeButton.setEnabled(false);
    	nextWave.setEnabled(true);
    	inspectWaveToggle.setEnabled(true);
    	buttonGroup1.clearSelection();
    	
    	fastTower.setEnabled(true);
    	splashTower.setEnabled(true);
    	slowTower.setEnabled(true);
    	powerTower.setEnabled(true);
    	
		updatePlayerStats();
		setVisibleTCInfo(false);
		repaint();
    }
    /**
     * Player lost, update UI by disabling all buttons.
     */
    private void gameOver(){
    	disableButtons();
		TDG.printMessage("Game Over.\nScore: " + game.getPs().getScore());
		quit.setEnabled(true);
		timer.stop();
    	
    }
    /**
     * Player wins, update UI by disabling all buttons.
     */
    private void GameWon(){
    	disableButtons();
		TDG.printMessage("Congratulations!\nScore: " + game.getPs().getScore());
		quit.setEnabled(true);
		timer.stop();
    }
    
    /**
     * Disable all buttons except quit.
     */
    private void disableButtons(){
    	sellButton.setEnabled(false);
    	purchaseButton.setEnabled(false);
    	upgradeButton.setEnabled(false);
    	nextWave.setEnabled(false);
    	inspectWaveToggle.setEnabled(false);
    	fastTower.setEnabled(false);
    	splashTower.setEnabled(false);
    	slowTower.setEnabled(false);
    	powerTower.setEnabled(false);
    	buttonGroup1.clearSelection();
    }
    
    
    /**
     * Update UI when a menu tower is selected
     */
    private void selectMenuTower(){
    	setVisibleTCInfo(true);
    	purchaseButton.setEnabled(true);
    	sellButton.setEnabled(false);
    	upgradeButton.setEnabled(false);
    	TowerManager.setSelectedTower(null);
    }
    
    
    /**
     * Paint the GUI
     */
    @Override
   public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(ICManager.squareHighlightTickness));
		
		game.draw(g);
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        shopTitle = new javax.swing.JTextField();
        nextWave = new javax.swing.JButton();
        stats = new javax.swing.JTextField();
        quit = new javax.swing.JButton();
        fastTower = new javax.swing.JToggleButton();
        splashTower = new javax.swing.JToggleButton();
        slowTower = new javax.swing.JToggleButton();
        powerTower = new javax.swing.JToggleButton();
        purchaseButton = new javax.swing.JButton();
        sellButton = new javax.swing.JButton();
        upgradeButton = new javax.swing.JButton();
        goldAmount = new javax.swing.JLabel();
        livesAmount = new javax.swing.JLabel();
        scoreAmount = new javax.swing.JLabel();
        info1 = new javax.swing.JLabel();
        info2 = new javax.swing.JLabel();
        info3 = new javax.swing.JLabel();
        info4 = new javax.swing.JLabel();
        info5 = new javax.swing.JLabel();
        info6 = new javax.swing.JLabel();
        inspectWaveToggle = new javax.swing.JToggleButton();
        info7 = new javax.swing.JLabel();
        goldLabel = new javax.swing.JLabel();
        heartLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 204, 153));

        shopTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        shopTitle.setText("Shop");
        shopTitle.setBorder(null);
        shopTitle.setCaretColor(new java.awt.Color(204, 51, 0));
        shopTitle.setFocusable(false);
        shopTitle.setMargin(new java.awt.Insets(0, 0, 0, 0));
        shopTitle.setOpaque(false);
        shopTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shopTitleActionPerformed(evt);
            }
        });

        nextWave.setText("Next Wave");
        nextWave.setFocusable(false);
        nextWave.setMargin(new java.awt.Insets(0, 0, 0, 0));
        nextWave.setPreferredSize(new java.awt.Dimension(87, 20));
        nextWave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextWaveActionPerformed(evt);
            }
        });

        stats.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        stats.setText("Stats");
        stats.setBorder(null);
        stats.setCaretColor(new java.awt.Color(204, 51, 0));
        stats.setFocusable(false);
        stats.setOpaque(false);
        stats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statsActionPerformed(evt);
            }
        });

        quit.setText("Quit");
        quit.setFocusable(false);
        quit.setMaximumSize(new java.awt.Dimension(53, 15));
        quit.setMinimumSize(new java.awt.Dimension(53, 15));
        quit.setPreferredSize(new java.awt.Dimension(53, 15));
        quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitActionPerformed(evt);
            }
        });

        buttonGroup1.add(fastTower);
        fastTower.setIcon(new javax.swing.ImageIcon(getClass().getResource("/towers/fastTower.png"))); // NOI18N
        fastTower.setAlignmentY(0.0F);
        fastTower.setFocusable(false);
        fastTower.setMargin(new java.awt.Insets(0, 0, 0, 0));
        fastTower.setMaximumSize(new java.awt.Dimension(50, 50));
        fastTower.setMinimumSize(new java.awt.Dimension(50, 50));
        fastTower.setPreferredSize(new java.awt.Dimension(50, 50));
        fastTower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fastTowerActionPerformed(evt);
            }
        });

        buttonGroup1.add(splashTower);
        splashTower.setIcon(new javax.swing.ImageIcon(getClass().getResource("/towers/splashTower.png"))); // NOI18N
        splashTower.setAlignmentY(0.0F);
        splashTower.setFocusable(false);
        splashTower.setMargin(new java.awt.Insets(0, 0, 0, 0));
        splashTower.setMaximumSize(new java.awt.Dimension(50, 50));
        splashTower.setMinimumSize(new java.awt.Dimension(50, 50));
        splashTower.setPreferredSize(new java.awt.Dimension(50, 50));
        splashTower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                splashTowerActionPerformed(evt);
            }
        });

        buttonGroup1.add(slowTower);
        slowTower.setIcon(new javax.swing.ImageIcon(getClass().getResource("/towers/slowTower.png"))); // NOI18N
        slowTower.setAlignmentY(0.0F);
        slowTower.setFocusable(false);
        slowTower.setMargin(new java.awt.Insets(0, 0, 0, 0));
        slowTower.setMaximumSize(new java.awt.Dimension(50, 50));
        slowTower.setMinimumSize(new java.awt.Dimension(50, 50));
        slowTower.setPreferredSize(new java.awt.Dimension(50, 50));
        slowTower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slowTowerActionPerformed(evt);
            }
        });

        buttonGroup1.add(powerTower);
        powerTower.setIcon(new javax.swing.ImageIcon(getClass().getResource("/towers/supremeTower.png"))); // NOI18N
        powerTower.setAlignmentY(0.0F);
        powerTower.setFocusable(false);
        powerTower.setMargin(new java.awt.Insets(0, 0, 0, 0));
        powerTower.setMaximumSize(new java.awt.Dimension(50, 50));
        powerTower.setMinimumSize(new java.awt.Dimension(50, 50));
        powerTower.setPreferredSize(new java.awt.Dimension(50, 50));
        powerTower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                powerTowerActionPerformed(evt);
            }
        });

        purchaseButton.setText("Purchase");
        purchaseButton.setEnabled(false);
        purchaseButton.setFocusable(false);
        purchaseButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        purchaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseButtonActionPerformed(evt);
            }
        });

        sellButton.setText("Sell");
        sellButton.setEnabled(false);
        sellButton.setFocusable(false);
        sellButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        sellButton.setMaximumSize(new java.awt.Dimension(77, 23));
        sellButton.setMinimumSize(new java.awt.Dimension(77, 23));
        sellButton.setPreferredSize(new java.awt.Dimension(77, 23));
        sellButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellButtonActionPerformed(evt);
            }
        });

        upgradeButton.setText("Upgrade");
        upgradeButton.setEnabled(false);
        upgradeButton.setFocusable(false);
        upgradeButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        upgradeButton.setMaximumSize(new java.awt.Dimension(77, 23));
        upgradeButton.setMinimumSize(new java.awt.Dimension(77, 23));
        upgradeButton.setPreferredSize(new java.awt.Dimension(77, 23));
        upgradeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upgradeButtonActionPerformed(evt);
            }
        });

        goldAmount.setText("Gold : ");

        livesAmount.setText("Lives: ");

        scoreAmount.setText("Score:");

        info1.setText("jLabel1");

        info2.setText("jLabel2");

        info3.setText("jLabel3");

        info4.setText("jLabel4");

        info5.setText("jLabel5");

        info6.setText("jLabel6");

        buttonGroup1.add(inspectWaveToggle);
        inspectWaveToggle.setText("Inspect waves");
        inspectWaveToggle.setFocusable(false);
        inspectWaveToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inspectWaveToggleActionPerformed(evt);
            }
        });

        info7.setText("jLabel7");

        goldLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stats/gold.png"))); // NOI18N

        heartLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stats/life.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(794, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(quit, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(inspectWaveToggle, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nextWave, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(scoreAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sellButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(purchaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(upgradeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(info5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(info4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(info3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(info2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(info1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(info6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(info7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(goldLabel)
                                    .addComponent(heartLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(livesAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(goldAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(shopTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(slowTower, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(powerTower, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(fastTower, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(splashTower, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(stats, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(stats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goldAmount)
                    .addComponent(goldLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(livesAmount)
                    .addComponent(heartLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreAmount)
                .addGap(7, 7, 7)
                .addComponent(shopTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fastTower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(splashTower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(slowTower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(powerTower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(info1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(upgradeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sellButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(purchaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(inspectWaveToggle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextWave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quit, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void shopTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shopTitleActionPerformed
        
    }//GEN-LAST:event_shopTitleActionPerformed

    private void nextWaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextWaveActionPerformed
        update("waveStarted");
        setVisibleTCInfo(false);
        buttonGroup1.clearSelection();
        purchaseButton.setEnabled(false);
        sellButton.setEnabled(false);
        upgradeButton.setEnabled(false);
    }//GEN-LAST:event_nextWaveActionPerformed

    private void statsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statsActionPerformed

    private void quitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitActionPerformed
    	timer.stop();
    	TDG.panelSwap(TowerDefenseGame.gamePanel, TowerDefenseGame.mapSelectionPanel);
    }//GEN-LAST:event_quitActionPerformed

    private void purchaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseButtonActionPerformed
    	String purchaseSuccesful = "";
    	
    	if(fastTower.isSelected()){
    		purchaseSuccesful = game.purchaseTower(ICManager.fastTowerShop);
    	}else if(splashTower.isSelected()){
    		purchaseSuccesful = game.purchaseTower(ICManager.splashTowerShop);
    	}else if(slowTower.isSelected()){
    		purchaseSuccesful = game.purchaseTower(ICManager.slowTowerShop);
    	}else if(powerTower.isSelected()){
    		purchaseSuccesful = game.purchaseTower(ICManager.supremeTowerShop);
    	}
    	
    	//Notify user if purchase fails
    	if( "lowResource".equals(purchaseSuccesful))
    		TDG.printMessage("Not enough gold.");
    	else if(purchaseSuccesful == "noRoom")
    		TDG.printMessage("No more room left.");
    	
    	//Otherwise, disable buttons to place tower
    	else{
    		updatePlayerStats();
    		for (Component b : getComponents()) {
    		    if(b instanceof JButton || b instanceof JToggleButton)
    		    	b.setEnabled(false);
    		}
    	}
    	
    }//GEN-LAST:event_purchaseButtonActionPerformed

    private void fastTowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fastTowerActionPerformed
    	updateTCInfo(ICManager.fastTowerShop.getInfo());
    	selectMenuTower();

    }//GEN-LAST:event_fastTowerActionPerformed

    private void splashTowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_splashTowerActionPerformed
    	updateTCInfo(ICManager.splashTowerShop.getInfo());
    	selectMenuTower();

    }//GEN-LAST:event_splashTowerActionPerformed

    private void slowTowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slowTowerActionPerformed
    	updateTCInfo(ICManager.slowTowerShop.getInfo());
    	selectMenuTower();

    }//GEN-LAST:event_slowTowerActionPerformed

    private void powerTowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_powerTowerActionPerformed
    	updateTCInfo(ICManager.supremeTowerShop.getInfo());
    	selectMenuTower();

    }//GEN-LAST:event_powerTowerActionPerformed

    private void sellButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellButtonActionPerformed
        game.sellTower();
        updatePlayerStats();
        sellButton.setEnabled(false);
        upgradeButton.setEnabled(false);
        setVisibleTCInfo(false);
        repaint();
    }//GEN-LAST:event_sellButtonActionPerformed

    private void upgradeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upgradeButtonActionPerformed
        String result = game.upgradeTower();
    	if("lowResource".equals(result)){
        	TDG.printMessage("Not enough gold.");
        }else if("maxLevel".equals(result)){
        	TDG.printMessage("Max level reached.");
        }else{
        	updateTCInfo(game.getSelectedTower().getInfo());
        	updatePlayerStats();
        	repaint();
        }
        		

    }//GEN-LAST:event_upgradeButtonActionPerformed

    private void inspectWaveToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inspectWaveToggleActionPerformed
    	updateTCInfo(game.getWaveInfo());
    	setVisibleTCInfo(true);
    	purchaseButton.setEnabled(false);
        sellButton.setEnabled(false);
        upgradeButton.setEnabled(false);
    }//GEN-LAST:event_inspectWaveToggleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JToggleButton fastTower;
    private javax.swing.JLabel goldAmount;
    private javax.swing.JLabel goldLabel;
    private javax.swing.JLabel heartLabel;
    private javax.swing.JLabel info1;
    private javax.swing.JLabel info2;
    private javax.swing.JLabel info3;
    private javax.swing.JLabel info4;
    private javax.swing.JLabel info5;
    private javax.swing.JLabel info6;
    private javax.swing.JLabel info7;
    private javax.swing.JToggleButton inspectWaveToggle;
    private javax.swing.JLabel livesAmount;
    private javax.swing.JButton nextWave;
    private javax.swing.JToggleButton powerTower;
    private javax.swing.JButton purchaseButton;
    private javax.swing.JButton quit;
    private javax.swing.JLabel scoreAmount;
    private javax.swing.JButton sellButton;
    private javax.swing.JTextField shopTitle;
    private javax.swing.JToggleButton slowTower;
    private javax.swing.JToggleButton splashTower;
    private javax.swing.JTextField stats;
    private javax.swing.JButton upgradeButton;
    // End of variables declaration//GEN-END:variables


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) 
			
			//Enable buttons when tower is placed
			if(game.placeTower()){
		  		for (Component b : getComponents()) {
	    		    if(b instanceof JButton || b instanceof JToggleButton)
	    		    	b.setEnabled(true);
	    		}
			}
		repaint();
		
		//If a mouse press happens outside of the shop menu, set the tower related buttons to false.
		if(e.getComponent() != fastTower || e.getComponent() != splashTower || e.getComponent() != slowTower || e.getComponent() != powerTower){
			buttonGroup1.clearSelection();
			purchaseButton.setEnabled(false);
			upgradeButton.setEnabled(false);
			sellButton.setEnabled(false);
			game.setSelectedTower(null);
			setVisibleTCInfo(false);
		}
	}
	
    //Select Tower on field and enable the upgrade and sell buttons
	@Override
	public void mouseReleased(MouseEvent e) {
		for (Tower t:TowerManager.getTowers()){
			if (t.getX() == (e.getX()/ICManager.cellSize)*ICManager.cellSize && t.getY() == (e.getY()/ICManager.cellSize)*ICManager.cellSize){
				game.setSelectedTower(t);
				setVisibleTCInfo(true);
				updateTCInfo(t.getInfo());
				upgradeButton.setEnabled(true);
				sellButton.setEnabled(true);
				repaint();
			}
		}
	}

    @Override
	public void mouseDragged(MouseEvent e) {
		Mouse.move(e.getX(), e.getY());
		repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Mouse.move(e.getX(), e.getY());
		game.getTt().updatePosition();
		repaint();
	}
	
	public void setGame(GameController gc){
		game = gc;
	}

	public GameController getGame() {
		return game;
	}
}
