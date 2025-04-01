package entities;

public enum GameLevel {
    I(1, 7, 5), II(2, 7, 5), III(3, 9, 6);

    private final int boardX;
    private final int boardY;
    private final int level;
    
    private GameLevel(int level, int boardX, int boardY){
        this.boardX = boardX;
        this.boardY = boardY;
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
}
