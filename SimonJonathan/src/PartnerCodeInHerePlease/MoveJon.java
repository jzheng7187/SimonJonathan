package PartnerCodeInHerePlease;

import SimonJonathan.ButtonInterfaceJonathanZ;
import SimonJonathan.MoveInterfaceJonathanZ;

public class MoveJon implements MoveInterfaceJonathanZ {

	private ButtonInterfaceJonathanZ b;

	public void Move(ButtonInterfaceJonathanZ b) {
		this.b = b;
	}
	
	public ButtonInterfaceJonathanZ getButton(){
		return b;
		
	}

}
