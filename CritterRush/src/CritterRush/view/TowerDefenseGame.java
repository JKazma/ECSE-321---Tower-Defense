package CritterRush.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

import CritterRush.controller.*;
import CritterRush.model.ToolBox;
import CritterRush.model.Mouse;

import javax.swing.JOptionPane;

public class TowerDefenseGame extends JFrame {
	
	//Constants
	public static final int SCREEN_WIDTH = 960;
	public static final int SCREEN_HEIGHT = 600;
	public static final String GAME_NAME = "Critter Rush";
	public static final int TIMEOUT = 50;
	public static JPanel currentPanel;
    
	//Panels
    public static CreditsPanel creditsPanel;
    public static MainMenuPanel mainMenuPanel;
    public static MapSelectionPanel mapSelectionPanel;
    public static EditorPanel editorPanel;
    public static GamePanel gamePanel;

	public TowerDefenseGame(){	
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setTitle(GAME_NAME);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        requestFocus();
        
        //Managers
        new ICManager();
        new CellTypeManager();
        new MapManager();
        InputManager iman = new InputManager();
        
        new ToolBox();

        mainMenuPanel = new MainMenuPanel(this, true);
        creditsPanel = new CreditsPanel(this, false);
        mapSelectionPanel = new MapSelectionPanel(this, false);
        editorPanel = new EditorPanel(this, false);
        gamePanel = new GamePanel (this, false);
     
        editorPanel.addMouseListener(iman);
        editorPanel.addMouseMotionListener(iman);
        
        this.add(mainMenuPanel);
        this.pack();

	}
	
	//Swap panels
    public void panelSwap(JPanel from, JPanel to){
	    from.setVisible(false);
	    remove(from);
	    add(to);
	    validate();
        repaint();
	    to.setVisible(true);
    }
    
    public void printMessage(String message){
    JOptionPane.showMessageDialog(this, message);
    }

	public static void main(String args[]){
		EventQueue.invokeLater(new Runnable() {
           
			@Override
            public void run() {
                TowerDefenseGame TDG = new TowerDefenseGame();
                TDG.setVisible(true);
            }
        });
	}
}
