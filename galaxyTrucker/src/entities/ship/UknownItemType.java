package entities.ship;

public class UknownItemType extends RuntimeException{
	public UknownItemType() {
		super("item type not supported for adding/romiving");
	}
}
