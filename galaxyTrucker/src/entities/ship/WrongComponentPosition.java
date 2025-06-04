package entities.ship;

public class WrongComponentPosition extends RuntimeException{
	public WrongComponentPosition() {
		super("specified position doesn't refer to an accepted component type");
	}
}
