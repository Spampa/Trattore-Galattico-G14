package entities;

public enum GameLevel {
    I(1, 7, 5, 18), II(2, 7, 5, 18), III(3, 9, 6, 23);

    private final int boardX;
    private final int boardY;
    private final int boardSpaces;
    private final int level;
    
    private GameLevel(int level, int boardX, int boardY, int boardSpaces){
        this.boardX = boardX;
        this.boardY = boardY;
        this.boardSpaces = boardSpaces;
        this.level = level;
    }

    public int getBoardX() {
        return boardX;
    }

    public int getBoardY() {
        return boardY;
    }

    public int getLevel() {
        return level;
    }

	public int getBoardSpaces() {
		return boardSpaces;
	}
	
	public static GameLevel intToLevel(int value) {
		switch(value) {
			case 1: return I;
			case 2: return II;
			case 3: return III;
			default: return null;
		}
	}
	
	public static GameLevel stringToLevel(String value) {
		if(value.equals("I")) {
			return I;
		}
		else if(value.equals("IÍ")) {
			return II;
		}
		else if(value.equals("III")) {
			return III;
		}
		return null;
	}
}
