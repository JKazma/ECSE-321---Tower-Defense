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
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import CritterRush.controller.CritterManager;
import CritterRush.controller.GameController;
import CritterRush.controller.ICManager;
import CritterRush.controller.IObserver;
import CritterRush.controller.MapManager;
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
    
	@Override
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
    public void updatePlayerStats(){
    	if(game.getPs().getLifeCount() == 0){
    		gameOver();
    	}
		goldAmount.setText("Gold : " + String.valueOf(game.getPs().getCurrencyPoints()));
		livesAmount.setText("Lives : " + String.valueOf(game.getPs().getLifeCount()));
		scoreAmount.setText("Score : " + String.valueOf(game.getPs().getScore()));
    }
    
    /**
     * Update the displayed tower info.
     */
    public void updateTowerInfo(int[] info){
    	if(info.length >= 1) info1.setText("Level:  " + String.valueOf(info[0]));
    	else info1.setText("");
    	if(info.length >= 2) info2.setText("Damage:  " + String.valueOf(info[1]));
    	else info2.setText("");
    	if(info.length >= 3) info3.setText("Range:  " + String.valueOf(info[2]));
    	else info3.setText("");
    	if(info.length >= 4) info4.setText("Fire rate:  " + String.valueOf( ((float)info[3])/10 ));
    	else info4.setText("");
    	if(info.length >= 5) info5.setText("Buy cost:  " + String.valueOf(info[4]));
    	else info5.setText("");
    	if(info.length >= 6) info6.setText("Sell refund:  " + String.valueOf(info[5]));
    	else info6.setText("");
    	repaint();
    }
    
    /**
     * Update the displayed critter waves info.
     */
    public void updateCritterInfo(int[] currentWave, int[] nextWave, int[] waveCountInfo){
    	info1.setText("WAVE INFO");
    	//Display current wave and next wave info.
    	if(waveCountInfo[0] != 987654321){
        	info2.setText("Health: " + String.valueOf(currentWave[0]) + " ==> " + String.valueOf(nextWave[0]));
        	info3.setText("Speed: " + String.valueOf(currentWave[1]) + " ==> " + String.valueOf(nextWave[1]));
        	info4.setText("Amount: " + String.valueOf(currentWave[2]) + " ==> " + String.valueOf(nextWave[2]));
    	}
    	//If current wave is last wave, display done instead of info.
    	else{
    		info2.setText("Health: " + String.valueOf(currentWave[0]) + " ==> Done");
        	info3.setText("Speed: " + String.valueOf(currentWave[1]) + " ==> Done");
        	info4.setText("Amount: " + String.valueOf(currentWave[2]) + " ==> Done");
    	}
    	info5.setText("");
    	info6.setText("Current Wave: " + String.valueOf(waveCountInfo[0]) + " / " + String.valueOf(waveCountInfo[1]));
    	repaint();
    }
    
    
    /**
     * Set the visibility of the tower and critter Info.
     * @param b
     */
    public void setVisibleTCInfo(boolean b){
    	info1.setVisible(b);
    	info2.setVisible(b);
    	info3.setVisible(b);
    	info4.setVisible(b);
    	info5.setVisible(b);
    	info6.setVisible(b);
    	repaint();
    }
    
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
    	
    	fastTower.setEnabled(true);
    	splashTower.setEnabled(true);
    	slowTower.setEnabled(true);
    	powerTower.setEnabled(true);
    }
    /**
     * Player lost, update UI by disabling all buttons.
     */
    public void gameOver(){
    	timer.stop();
    	disableButtons();
		TDG.printMessage("Game Over.\nScore: " + game.getPs().getScore());
		quit.setEnabled(true);

    	
    }
    /**
     * Player Won, update UI by disabling all buttons.
     */
    public void GameWon(){
    	timer.stop();
    	disableButtons();
		TDG.printMessage("Congratulations!\nScore: " + game.getPs().getScore());
		quit.setEnabled(true);
    }
    
    /**
     * Disable all buttons except quit.
     */
    public void disableButtons(){
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
    @SuppressWarnings("unchecked")
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

        setBackground(new java.awt.Color(255, 204, 153));

        shopTitle.setFont(new java.awt.Font("Helvetica", 0, 24)); // NOI18N
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

        stats.setFont(new java.awt.Font("Helvetica", 0, 24)); // NOI18N
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
        powerTower.setIcon(new javax.swing.ImageIcon(getClass().getResource("/towers/powerTower.png"))); // NOI18N
        powerTower.setAlignmentY(0.0F);
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
        inspectWaveToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inspectWaveToggleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(793, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(slowTower, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(powerTower, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(fastTower, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(splashTower, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(shopTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(quit, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(inspectWaveToggle, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nextWave, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(scoreAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(stats, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(livesAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(goldAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(info5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(info4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(info3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(info2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(info1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(info6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sellButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(purchaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(upgradeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(stats, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(goldAmount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(livesAmount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scoreAmount)
                .addGap(7, 7, 7)
                .addComponent(shopTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fastTower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(splashTower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(slowTower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(powerTower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
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
    	TowerManager.getTowers().clear();
    	CritterManager.getCritters().clear();
    	timer.stop();
    	TDG.panelSwap(TDG.gamePanel, TDG.mapSelectionPanel);
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
    	updateTowerInfo(ICManager.fastTowerShop.getInfo());
    	setVisibleTCInfo(true);
    	purchaseButton.setEnabled(true);
    }//GEN-LAST:event_fastTowerActionPerformed

    private void splashTowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_splashTowerActionPerformed
    	updateTowerInfo(ICManager.splashTowerShop.getInfo());
    	setVisibleTCInfo(true);
    	purchaseButton.setEnabled(true);
    }//GEN-LAST:event_splashTowerActionPerformed

    private void slowTowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slowTowerActionPerformed
    	updateTowerInfo(ICManager.slowTowerShop.getInfo());
    	setVisibleTCInfo(true);
    	purchaseButton.setEnabled(true);
    }//GEN-LAST:event_slowTowerActionPerformed

    private void powerTowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_powerTowerActionPerformed
    	updateTowerInfo(ICManager.supremeTowerShop.getInfo());
    	setVisibleTCInfo(true);
    	purchaseButton.setEnabled(true);
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
        	updateTowerInfo(game.getSelectedTower().getInfo());
            repaint();
        }
        		

    }//GEN-LAST:event_upgradeButtonActionPerformed

    private void inspectWaveToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inspectWaveToggleActionPerformed
    	updateCritterInfo(game.getWaveInfo(), game.getNextWaveInfo(), game.getWaveCountInfo());
    	setVisibleTCInfo(true);
    	purchaseButton.setEnabled(false);
        sellButton.setEnabled(false);
        upgradeButton.setEnabled(false);
    }//GEN-LAST:event_inspectWaveToggleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JToggleButton fastTower;
    private javax.swing.JLabel goldAmount;
    private javax.swing.JLabel info1;
    private javax.swing.JLabel info2;
    private javax.swing.JLabel info3;
    private javax.swing.JLabel info4;
    private javax.swing.JLabel info5;
    private javax.swing.JLabel info6;
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
		
		if(e.getComponent() != fastTower || e.getComponent() != splashTower || e.getComponent() != slowTower || e.getComponent() != powerTower){
			buttonGroup1.clearSelection();
			purchaseButton.setEnabled(false);
			upgradeButton.setEnabled(false);
			sellButton.setEnabled(false);
			game.setSelectedTower(null);
			setVisibleTCInfo(false);
		}
	}
	
    //Select Tower on field
	@Override
	public void mouseReleased(MouseEvent e) {
		for (Tower t:TowerManager.getTowers()){
			if (t.getX() == (e.getX()/ICManager.cellSize)*ICManager.cellSize && t.getY() == (e.getY()/ICManager.cellSize)*ICManager.cellSize){
				game.setSelectedTower(t);
				setVisibleTCInfo(true);
				updateTowerInfo(t.getInfo());
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
