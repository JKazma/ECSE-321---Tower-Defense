package CritterRush.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import CritterRush.model.ToolBox;
import CritterRush.model.Mouse;



public class InputManager implements MouseListener, MouseMotionListener{

	@Override
	public void mouseDragged(MouseEvent e) {
		Mouse.move(e.getX(), e.getY());
		ToolBox.update();
		
		if (SwingUtilities.isLeftMouseButton(e)) 
			ToolBox.performAction1();
		else if (SwingUtilities.isRightMouseButton(e)) 
			ToolBox.performAction2();
		
		repaint(e);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Mouse.move(e.getX(), e.getY());
		ToolBox.update();
		repaint(e);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) 
			ToolBox.performAction1();
		else if (SwingUtilities.isRightMouseButton(e)) 
			ToolBox.performAction2();
		
		repaint(e);
	}

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
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//Repaint parent panel
	public void repaint(MouseEvent e){
		Mouse.move(e.getX(), e.getY());
		ToolBox.update();
		JPanel panel = (JPanel)e.getSource();
		panel.repaint();
	}

}
