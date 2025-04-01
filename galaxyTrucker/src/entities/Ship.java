package entities;

public class Ship {

	private float firePower;
	private int motorPower;
	private int waresValue;
	private int voidConnectors;
	private int humansCounter;
	private int cellsCounter;
	private int aliensCounter;
	// private Components[][] shipComponents; TODO: implementare classe "Components"

	public void scanShip(){
		// TODO
	}

	public void getHit(){
		// TODO
	}

	public boolean isPlayable(){
		// TODO
		return true;
	}

    public float getFirePower() {
        return firePower;
    }

    public int getMotorPower() {
        return motorPower;
    }

    public int getWaresValue() {
        return waresValue;
    }

    public int getVoidConnectors() {
        return voidConnectors;
    }

    public int getHumansCounter() {
        return humansCounter;
    }

    public int getCellsCounter() {
        return cellsCounter;
    }

    public int getAliensCounter() {
        return aliensCounter;
	}

}
