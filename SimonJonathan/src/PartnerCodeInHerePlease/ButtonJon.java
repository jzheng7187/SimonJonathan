package PartnerCodeInHerePlease;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import SimonJonathan.ButtonInterfaceJonathanZ;
import gui.components.Action;
import gui.components.Component;

public class ButtonJon extends Component implements ButtonInterfaceJonathanZ {

	private double cos;
	private Color cr;
	private Color displayColor;
	private double sin;
	private Action action;
	public boolean highlighted;
	private String name;
	private static int count = 15;
	public static final int HEIGHT = 45;
	public static final int WIDTH = 45;
	

	public ButtonJon() {
		super(count * 30,40, WIDTH, HEIGHT);
		count ++;
	}

	@Override
	public void act() {
		action.act();
	}

	@Override
	public boolean isHovered(int x, int y) {
		double distance = Math.sqrt(Math.pow(x-(getX()+WIDTH/2), 2)+Math.pow(y-(getY()+HEIGHT/2), 2));
		return distance < WIDTH/2;
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(displayColor != null){
			g.setColor(displayColor);
		}else{
			g.setColor(Color.gray);
			g.fillOval(0,0, WIDTH, HEIGHT);
			g.setColor(Color.black);		
			g.fillOval(0, 0, WIDTH - 1, HEIGHT - 1);
			if(highlighted){
				g.setColor(Color.white);
				Polygon p = new Polygon();
				
				int s = (int)(5/8.0 * WIDTH);
				int t = (int)(1.0/5*HEIGHT)+6;
				p.addPoint(s-4, t-4);
				p.addPoint(s+7, t-2);
				p.addPoint(s+10, t);
				p.addPoint(s+14, t+10);
				p.addPoint(s+12, t+14);
				p.addPoint(s+8, t+3);
				g.fill(p);
			}
		}
	}

	@Override
	public void setColor(Color color) {
		this.cr = color;
		displayColor = cr;
		update();
		
	}

	@Override
	public void setX(double cos) {
		this.cos = cos;
		
	}

	@Override
	public void setY(double sin) {
		this.sin = sin;
		
	}

	@Override
	public void setAction(Action action) {
		this.action = action;
		
	}

	@Override
	public void highlight() {
		if(cr != null){
			highlighted = true;
			displayColor = cr;
			update();
		}
		
	}

	@Override
	public void dim() {
		cr = Color.gray;
		highlighted = false;
		update();
	}

	@Override
	public void setName(String string) {
		this.name = string;
	}


}
