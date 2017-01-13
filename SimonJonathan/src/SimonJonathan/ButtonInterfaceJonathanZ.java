package SimonJonathan;

import java.awt.Color;

import gui.components.Action;
import gui.components.Clickable;

public interface ButtonInterfaceJonathanZ extends Clickable {

	void setX(double d);

	void setY(double d);

	void setColor(Color color);

	public void setAction(Action action);
	
	void highlight();

	void dim();

	void setName(String string);

}
