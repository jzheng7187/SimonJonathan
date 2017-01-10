package SimonJonathan;

import gui.GUIApplication;

public class SimonGameJonathanZ extends GUIApplication {

	public SimonGameJonathanZ(int width, int height) {
		super(width, height);
	}

	@Override
	public void initScreen() {
		SimonScreenJonathanZ ss = new SimonScreenJonathanZ(getWidth(),getHeight());
		setScreen(ss);

	}

	public static void main(String[] args) {
		SimonGameJonathanZ sg = new SimonGameJonathanZ(500,500);
		Thread game = new Thread(sg);
		game.start();
	}

}
