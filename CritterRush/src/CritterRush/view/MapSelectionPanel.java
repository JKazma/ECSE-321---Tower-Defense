package CritterRush.view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

import CritterRush.controller.GameController;
import CritterRush.controller.ICManager;
import CritterRush.controller.MapManager;
import CritterRush.controller.TowerManager;
import CritterRush.model.tools.EditorTools;

public class MapSelectionPanel extends javax.swing.JPanel {

    private TowerDefenseGame TDG;
    private JRadioButton[] rButtons;
    private JLabel[] mapLabels;
    
    public MapSelectionPanel(TowerDefenseGame frame, boolean visibility) {
        initComponents();
        this.TDG = frame;
        
        setBackground(Color.BLACK);
		setDoubleBuffered(true);
		setVisible(visibility);
		this.setFocusable(true);
		this.requestFocus(true);
		
		mapLabels = new JLabel[] {Map1,Map2,Map3,Map4};
        rButtons = new JRadioButton[] {map1Button, map2Button, map3Button, map4Button};
        
        updateMapIcons();
    }
    
    //set map Icons
    public void updateMapIcons(){
    	for (int i = 1; i < MapManager.getMaxCount() + 1; i++){
    		if(i <= MapManager.getMapCount()){
    			mapLabels[i - 1].setIcon(ICManager.maps[i-1]);
    			rButtons[i - 1].setEnabled(true);
    			
    		}
    		else{
    			mapLabels[i - 1].setIcon(new javax.swing.ImageIcon(getClass().getResource("/map/empty.png")));
    			rButtons[i - 1].setEnabled(false);
    		}
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
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        backButton = new javax.swing.JButton();
        Map1 = new javax.swing.JLabel();
        map1Button = new javax.swing.JRadioButton();
        map2Button = new javax.swing.JRadioButton();
        Map2 = new javax.swing.JLabel();
        map3Button = new javax.swing.JRadioButton();
        map4Button = new javax.swing.JRadioButton();
        Map3 = new javax.swing.JLabel();
        Map4 = new javax.swing.JLabel();
        createMapButton = new javax.swing.JButton();
        deleteMapButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        
        
 
        

        setBackground(new java.awt.Color(0, 0, 0));
        setLayout(new java.awt.GridBagLayout());

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buttons/back unpressed.png"))); // NOI18N
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton.setDefaultCapable(false);
        backButton.setFocusable(false);
        backButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/buttons/back pressed.png"))); // NOI18N
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(backButton, gridBagConstraints);

        Map1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Map1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buttons/back pressed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        add(Map1, gridBagConstraints);

        buttonGroup1.add(map1Button);
        map1Button.setText("Map 1");
        map1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                map1ButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        add(map1Button, gridBagConstraints);

        buttonGroup1.add(map2Button);
        map2Button.setText("Map 2");
        map2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                map2ButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        add(map2Button, gridBagConstraints);

        Map2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Map2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buttons/back pressed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        add(Map2, gridBagConstraints);

        buttonGroup1.add(map3Button);
        map3Button.setText("Map 3");
        map3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                map3ButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        add(map3Button, gridBagConstraints);

        buttonGroup1.add(map4Button);
        map4Button.setText("Map 4");
        map4Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                map4ButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        add(map4Button, gridBagConstraints);

        Map3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Map3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buttons/back pressed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        add(Map3, gridBagConstraints);

        Map4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Map4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buttons/back pressed.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 50;
        add(Map4, gridBagConstraints);

        createMapButton.setText("Create Map");
        createMapButton.setPreferredSize(new java.awt.Dimension(100, 50));
        createMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createMapButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(50, 50, 50, 50);
        add(createMapButton, gridBagConstraints);

        deleteMapButton.setText("Delete Map");
        deleteMapButton.setPreferredSize(new java.awt.Dimension(100, 50));
        deleteMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMapButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new java.awt.Insets(50, 50, 50, 50);
        add(deleteMapButton, gridBagConstraints);

        startButton.setText("Start");
        startButton.setPreferredSize(new java.awt.Dimension(100, 50));
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        add(startButton, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    //Swing control functions
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        TDG.panelSwap(TDG.mapSelectionPanel, TDG.mainMenuPanel);
    }//GEN-LAST:event_backButtonActionPerformed

    private void map1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_map1ButtonActionPerformed
        MapManager.setSelectedMap(MapManager.getMap(1));
        //System.out.println("RETARD");
    }//GEN-LAST:event_map1ButtonActionPerformed
    
    private void map2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_map2ButtonActionPerformed
    	MapManager.setSelectedMap(MapManager.getMap(2));
    }//GEN-LAST:event_map2ButtonActionPerformed

    private void map3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_map3ButtonActionPerformed
    	MapManager.setSelectedMap(MapManager.getMap(3));
    }//GEN-LAST:event_map3ButtonActionPerformed
    
    private void map4ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_map4ButtonActionPerformed
    	MapManager.setSelectedMap(MapManager.getMap(4));
    }//GEN-LAST:event_map4ButtonActionPerformed

    private void createMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createMapButtonActionPerformed
        if(MapManager.getMapCount() < MapManager.getMaxCount()){
            TDG.panelSwap(TDG.mapSelectionPanel, TDG.editorPanel);
        	EditorTools.setCurrentTool(EditorTools.getTextureTool());
        } else 
            TDG.printMessage("The maximum number of maps has been reached.");
    }//GEN-LAST:event_createMapButtonActionPerformed

    private void deleteMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMapButtonActionPerformed
        if(MapManager.getSelectedMap() != null){
           MapManager.deleteMap(MapManager.getSelectedMap());
           MapManager.setSelectedMap(null);
           updateMapIcons();
           buttonGroup1.clearSelection();
        } else 
        	TDG.printMessage("No map selected.");
    }//GEN-LAST:event_deleteMapButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
    	if(MapManager.getSelectedMap() != null){
    		TDG.gamePanel.setGame(new GameController());
    		TDG.gamePanel.getGame().setObserver(TDG.gamePanel);
    		TDG.gamePanel.reset();
    		TDG.panelSwap(TDG.mapSelectionPanel, TDG.gamePanel);
    	}else 
    		TDG.printMessage("No map selected.");
    }//GEN-LAST:event_startButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Map1;
    private javax.swing.JLabel Map2;
    private javax.swing.JLabel Map3;
    private javax.swing.JLabel Map4;
    private javax.swing.JButton backButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton createMapButton;
    private javax.swing.JButton deleteMapButton;
    private javax.swing.JRadioButton map1Button;
    private javax.swing.JRadioButton map2Button;
    private javax.swing.JRadioButton map3Button;
    private javax.swing.JRadioButton map4Button;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
