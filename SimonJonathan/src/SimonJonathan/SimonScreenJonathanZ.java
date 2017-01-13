package SimonJonathan;

import java.awt.Color;
import java.util.ArrayList;

import PartnerCodeInHerePlease.ButtonJon;
import PartnerCodeInHerePlease.Move;
import PartnerCodeInHerePlease.MoveJon;
import PartnerCodeInHerePlease.ProgressJon;
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
					Thread.sleep((long)(2000*(2.0/(roundNumber+2))));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		b.dim();
	}

	private void nextRound() {
//		acceptingInput = false;
//		roundNumber++;
//		sequence.add(randomMove());
//		changeText("Simon's Turn");
//		label.setText("");
//		playSequence();
//		changeText("Your Turn");
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

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		addButtons(viewObjects);
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

	/**
	 * 
	 * Placeholder until partner finishes implementation of ProgressInterface
	 */
	private ProgressInterfaceJonathanZ getProgress() {
		return new ProgressJon();
	}

	private void addButtons(ArrayList<Visible> viewObjects) {
		Color[] colors = {Color.red, Color.blue, Color.yellow, Color.green,};
		String[] name = {"RED", "BLUE", "YELLOW", "GREEN"};
		int numberOfButtons = 4;
		button = new ButtonInterfaceJonathanZ[numberOfButtons];
		for(int i = 0; i < numberOfButtons; i++){
			button[i] = getAButton();
			button[i].setName(name[i]);
			button[i].setColor(colors[i]);
			button[i].setX(0);
			button[i].setY(0);
			final ButtonInterfaceJonathanZ b = button[i];
			button[i].dim();
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
							}
						});
						blink.start();
						if(b == sequence.get(sequenceIndex).getButton() && acceptingInput){
							sequenceIndex++;
						}else if(acceptingInput){
							ProgressInterfaceJonathanZ.gameOver();
							return;
						};
						if(sequenceIndex == sequence.size()){
							Thread nextRound = new Thread(SimonScreenJonathanZ.this);
							nextRound.start();
						}
					}

				}

			});
			viewObjects.add(button[i]);
		}
	}

	private ButtonInterfaceJonathanZ getAButton() {
		return new ButtonJon();
	}

	private void changeText(String string){
		try {
			label.setText(string);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gameOver() {
		ProgressInterfaceJonathanZ.gameOver();
	}

}
