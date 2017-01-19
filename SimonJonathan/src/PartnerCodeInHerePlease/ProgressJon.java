package PartnerCodeInHerePlease;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import SimonJonathan.ProgressInterfaceJonathanZ;
import gui.components.Component;

public class ProgressJon extends Component implements ProgressInterfaceJonathanZ {

	public static final int HEIGHT = 50;
	public static final int WIDTH = 120;

	public boolean gameOver;
	public String round;
	public String sequence;
	
	public ProgressJon() {
		super(100, 100, WIDTH, HEIGHT);
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		FontMetrics fm = g.getFontMetrics();
		if(gameOver){
			g.setColor(new Color(255,55,90));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.white);
			String gameOver = "Game Over!";
			g.drawString(gameOver, WIDTH - fm.stringWidth(gameOver), 20);
			g.drawString(sequence,  WIDTH - fm.stringWidth(sequence), 40);
			
		}else{
			g.setColor(new Color(220,255,230));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.black);
			g.drawRect(0, 0, WIDTH-1, HEIGHT-1);
			if(round != null && sequence != null){
				g.drawString(round, WIDTH - fm.stringWidth(round), 15);
				g.drawString(sequence, WIDTH - fm.stringWidth(sequence), 40);
			}
		}

	}
	
	public void gameOver(){
		gameOver = true;
		update();
	}

	public void setRound(int roundNumber) {
		round = "Round: " + roundNumber;
		update();
		
	}

	public void setSequenceSize(int size) {
		size = 0;
		sequence = "Sequence Size:" + size;
		update();
		
	}
}
