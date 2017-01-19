package SimonJonathan;

import java.awt.Color;
import java.util.ArrayList;

import PartnerCodeInHerePlease.ButtonJon;
import PartnerCodeInHerePlease.Move;
import PartnerCodeInHerePlease.MoveJon;
import PartnerCodeInHerePlease.ProgressJon;
import gui.components.Action;
import gui.components.Button;
import gui.components.TextLabel;
import gui.components.Visible;
import gui.screens.ClickableScreen;

public class SimonScreenJonathanZ extends ClickableScreen implements Runnable {
	
	private TextLabel label;
	private ButtonInterfaceJonathanZ[] buttons;
	private ProgressInterfaceJonathanZ progress;
	private ArrayList<MoveInterfaceJonathanZ> sequence; 
	private int roundNumber;
	private boolean acceptingInput;
	private int sequenceIndex;
	private int lastSelected;

	public SimonScreenJonathanZ(int width, int height) {
		super(width, height);
		Thread screen = new Thread(this);
		screen.start();
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		addButton(viewObjects);
		progress = getProgress();
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
		sequence = new ArrayList<MoveInterfaceJonathanZ>();
		//add 2 moves to start
		lastSelected = -1;
		sequence.add(randomMove());
		sequence.add(randomMove());
		roundNumber = 0;

		viewObjects.add(progress);
		viewObjects.add(label);
	}

	public void gameOver() {
		progress.gameOver();
	}

	public void addButton(ArrayList<Visible> viewObjects){
		Color[] colors = {Color.red, Color.blue, Color.yellow, Color.green};
		String[] names = {"RED", "BLUE", "YELLOW", "GREEN"};
		int buttonCount = 4;
		buttons = new ButtonInterfaceJonathanZ[buttonCount];
		for(int i = 0; i < buttonCount; i++ ){
			buttons[i] = getAButton();
			buttons[i].setName(names[i]);
			buttons[i].setColor(colors[i]);
			buttons[i].setX(160 + (int)(buttonCount * 10));
			buttons[i].setY(200 - (int)(buttonCount * 10));
			final ButtonInterfaceJonathanZ b = buttons[i];
			b.dim();
			buttons[i].setAction(new Action() {
				
				public void act() {
					
					Thread buttonPress = new Thread(new Runnable() {
						
						public void run() {
							b.highlight();
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							b.dim();
							
						}
					});
					buttonPress.start();
					
					
					if(acceptingInput && sequence.get(sequenceIndex).getButton() == b){
						sequenceIndex++;
					}else if(acceptingInput){
						gameOver();
						return;
					}
					if(sequenceIndex == sequence.size()){
						Thread nextRound = new Thread(SimonScreenJonathanZ.this);
						nextRound.start();
					}
				}
				
			});
			viewObjects.add(buttons[i]);
		}
		
	}
	
	public void nextRound() {
		acceptingInput = false;
		roundNumber ++;
		progress.setRound(roundNumber);
		sequence.add(randomMove());
		progress.setSequenceSize(sequence.size());
		changeText("Simon's turn.");
		label.setText("");
		playSequence();
		changeText("Your turn.");
		label.setText("");
		acceptingInput = true;
		sequenceIndex = 0;
	}


	private MoveInterfaceJonathanZ randomMove() {
		int select = (int) (Math.random()*buttons.length);
		while(select == lastSelected){
			select = (int) (Math.random()*buttons.length);
		}
		lastSelected = select;
		return new Move(buttons[select]);
	}

	private ProgressInterfaceJonathanZ getProgress() {
		return new ProgressJon();
	}

	private ButtonInterfaceJonathanZ getAButton() {
		return new ButtonJon();
	}

	private void changeText(String string) {
		try{
			label.setText(string);
			Thread.sleep(800);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void run() {
		changeText("");
		nextRound();

	}


	private void playSequence() {
		ButtonInterfaceJonathanZ b = null;
		for(MoveInterfaceJonathanZ m: sequence){
			if(b!=null)b.dim();
			b = m.getButton();
			b.highlight();
			try {
				Thread.sleep((long)(2000*(2.0/(roundNumber+2))));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		b.dim();
	}
}
