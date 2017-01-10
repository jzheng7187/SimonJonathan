package SimonJonathan;

public class MoveJonathanZ implements MoveInterfaceJonathanZ {

	private ButtonInterfaceJonathanZ b;

	public MoveJonathanZ(ButtonInterfaceJonathanZ b) {
		this.b = b;
	}

	@Override
	public ButtonInterfaceJonathanZ getButton() {
		// TODO Auto-generated method stub
		return b;
	}

}
