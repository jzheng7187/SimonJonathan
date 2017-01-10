package SimonJonathan;

import java.awt.Color;
import java.util.ArrayList;

import gui.components.Action;
import gui.components.TextLabel;
import gui.components.Visible;
import gui.screens.ClickableScreen;

public class SimonScreenJonathanZ extends ClickableScreen implements Runnable {
	
	public TextLabel label;
	public ButtonInterfaceJonathanZ[] button;
	public ProgressInterfaceJonathanZ progress;
	public ArrayList<MoveInterfaceJonathanZ> sequence;
	public int roundNumber;
	public boolean acceptingInput;
	public int sequenceIndex;
	public int lastSelectedButton;
	

	public SimonScreenJonathanZ(int width, int height) {
		super(width, height);
		Thread app = new Thread(this);
		app.start();
	}

	@Override
	public void run() {
		label.setText("");
		nextRound();
	}

	private void playSequence() {
		ButtonInterfaceJonathanZ b = null;
		for(MoveInterfaceJonathanZ m: sequence){
			if(b != null){
				b.dim();
				b = m.getButton();
				b.highlight();
				try {
					Thread.sleep((long)(2*roundNumber/10));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				b.dim();
			}
		}
	}

	private void nextRound() {
		acceptingInput = false;
		roundNumber++;
		sequence.add(randomMove());
		changeText("Simon's Turn");
		label.setText("");
		playSequence();
		changeText("YOur Turn");
		
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		addButtons();
		progress = getProgress();
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
		sequence = new ArrayList<MoveInterfaceJonathanZ>();
		//add 2 moves to start
		lastSelectedButton = -1;
		sequence.add(randomMove());
		sequence.add(randomMove());
		roundNumber = 0;
		viewObjects.add(progress);
		viewObjects.add(label);
	}

	
	private MoveInterfaceJonathanZ randomMove() {
		int select = (int) (Math.random()*button.length);
		while(select == lastSelectedButton){
			select = (int) (Math.random()*button.length);
		}
		lastSelectedButton = select;
		return new Move(button[select]);
	}

	private MoveInterfaceJonathanZ getMove(ButtonInterfaceJonathanZ b) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * Placeholder until partner finishes implementation of ProgressInterface
	 */
	private ProgressInterfaceJonathanZ getProgress() {
		return null;
	}

	private void addButtons() {
		int numberOfButtons = 4;
		Color[] colors = {Color.red, Color.blue, Color.yellow, Color.green,};
		button = new ButtonInterfaceJonathanZ[numberOfButtons];
		for(int i = 0; i < numberOfButtons; i++){
			final ButtonInterfaceJonathanZ b = getAButton();
			for(int j = 0; j < colors.length; j++){				
				button[i].setColor(colors[j]);
			}
			button[i].setX(Math.sin(i));
			button[i].setY(Math.cos(i));
			button[i].setAction(new Action(){

				@Override
				public void act() {
					if(acceptingInput){
						Thread blink = new Thread(new Runnable(){

							public void run(){
								b.highlight();
								try {
									Thread.sleep(800);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								b.dim();
								if(b == sequence.get(sequenceIndex).getButton()){
									sequenceIndex++;
								}else{
									ProgressInterfaceJonathanZ.gameOver();
									};
								if(sequenceIndex == sequence.size()){
									Thread nextRound = new Thread(SimonScreenJonathanZ.this);
									nextRound.start();
								}
							}
							});
						blink.start();
					}
					
				}
				
			});
		}
	}

	private ButtonInterfaceJonathanZ getAButton() {
		return null;
	}

	private void changeText(String string){
		label.setText(string);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
