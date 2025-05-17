package components.enums;

public enum Ware {
    BLUE(1), GREEN(2), YELLOW(3), RED(4);

    private final int value;

    private Ware(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
