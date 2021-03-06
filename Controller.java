package Pentomino;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Pentomino.Interfaces.Control;

public class Controller implements KeyListener,Control{
	
	
	protected boolean[] buttons = new boolean[Control.Buttons.values().length];
	public Controller(){
		
	}
	/**
	 * checks when keys are pressed
	 */
	public void keyPressed(KeyEvent e){
		
		if (KeyEvent.getKeyText(e.getKeyCode()).equals(Configuration.left)){
			System.out.println("left pressed");
			buttons[Control.Buttons.Left.ordinal()]=true;
			
		}else if (KeyEvent.getKeyText(e.getKeyCode()).equals(Configuration.right)){			
			buttons[Control.Buttons.Right.ordinal()]=true;
			System.out.println("right pressed");
			
		}else if (KeyEvent.getKeyText(e.getKeyCode()).equals(Configuration.rotate)){
			buttons[Control.Buttons.RotateLeft.ordinal()]=true;
			System.out.println("rotate pressed");
			
		}else if (KeyEvent.getKeyText(e.getKeyCode()).equals(Configuration.down)){
			buttons[Control.Buttons.Down.ordinal()]=true;
			System.out.println("down pressed");
			
		}else if (KeyEvent.getKeyText(e.getKeyCode()).equals(Configuration.AllTheWayDown)){
			buttons[Control.Buttons.AllTheWayDown.ordinal()]=true;
			System.out.println("SpaceBar pressed");
		}
	}
	
	/**
	 * checks when keys are released
	 */	
	public void keyReleased(KeyEvent e){
		if (KeyEvent.getKeyText(e.getKeyCode()).equals(Configuration.left)){
			System.out.println("left released");
			buttons[Control.Buttons.Left.ordinal()]=false;
			
		}else if (KeyEvent.getKeyText(e.getKeyCode()).equals(Configuration.right)){
			System.out.println("right released");
			buttons[Control.Buttons.Right.ordinal()]=false;
			
		}else if (KeyEvent.getKeyText(e.getKeyCode()).equals(Configuration.rotate)){
			System.out.println("rotate released");
			buttons[Control.Buttons.RotateLeft.ordinal()]=false;
			
		}else if (KeyEvent.getKeyText(e.getKeyCode()).equals(Configuration.down)){
			System.out.println("down released");
			buttons[Control.Buttons.Down.ordinal()]=false;
			
		}else if (KeyEvent.getKeyText(e.getKeyCode()).equals(Configuration.AllTheWayDown)){
			System.out.println("SpaceBar released");
			buttons[Control.Buttons.AllTheWayDown.ordinal()]=false;
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}

	/**
	 *  checks if a button is pressed
	 *  @return which button is pressed
	 */
	public boolean isButtonPressed(Buttons b) {		
		return buttons[b.ordinal()];
	}

	/**
	 *  overrides the current value of a button when it is pressed again
	 *  @param b the button
	 *  @param newValue the new value
	 */	
	public void overRideButton(Buttons b, boolean newValue) {
		buttons[b.ordinal()] = newValue;
		
	}
}