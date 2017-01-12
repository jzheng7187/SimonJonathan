package PartnerCodeInHerePlease;

import SimonJonathan.ButtonInterfaceJonathanZ;
import SimonJonathan.MoveInterfaceJonathanZ;

public class Move implements MoveInterfaceJonathanZ {
	
	private ButtonInterfaceJonathanZ b;

	public Move(ButtonInterfaceJonathanZ b) {
		this.b = b;
	}

	public ButtonInterfaceJonathanZ getButton() {
		return b;
	}

}
