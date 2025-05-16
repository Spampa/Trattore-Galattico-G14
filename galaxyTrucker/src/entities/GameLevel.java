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
}
