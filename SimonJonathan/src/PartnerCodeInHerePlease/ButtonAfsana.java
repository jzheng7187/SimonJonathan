package PartnerCodeInHerePlease;

import java.awt.Color;
import java.awt.Graphics2D;

import SimonJonathan.ButtonInterfaceJonathanZ;
import gui.components.Action;
import gui.components.Component;

public class ButtonAfsana extends Component implements ButtonInterfaceJonathanZ {
	
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private Action action;
	private Color c;
	private Color displayColor;
	private boolean highlight;

	public ButtonAfsana() {
		super(0, 0, WIDTH, HEIGHT);
	}

	public void act() {
		action.act();
	}

	public boolean isHovered(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setX(int X) {
		
	}

	public void setY(int Y) {
		// TODO Auto-generated method stub

	}

	public void setColor(Color color) {
		this.c = color;
		displayColor = c;
		update();
	}

	public void setAction(Action action) {
		this.action = action;

	}

	public void highlight() {
		if(c != null) displayColor = c;
		highlight = true;
		update();
	}

	public void dim() {
		displayColor = Color.gray;
		highlight = false;
		update();

	}

	public void update(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setX(double d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setY(double d) {
		// TODO Auto-generated method stub
		
	}

}
