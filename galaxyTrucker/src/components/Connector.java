package components;

public enum Connector {
	EMPTY(0), SINGLE(1), DOUBLE(2), UNIVERSAL(3);
	
	private int number;
	
	Connector(int number){
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	public static Connector intToConnector(int value) {
		switch(value) {
			case 0:
				return EMPTY;
			case 1:
				return SINGLE;
			case 2:
				return DOUBLE;
			default:
				return UNIVERSAL;
			
		}
	}
	
	public static boolean checkConnectors(Connector c1, Connector c2){

        switch (c1) {
            case UNIVERSAL -> {
                if(c2 != Connector.EMPTY) return true;
            }
            case DOUBLE -> {
                if(c2 == Connector.DOUBLE || c2 == Connector.UNIVERSAL) return true;
            }
            case SINGLE -> {
                if(c2 == Connector.SINGLE || c2 == Connector.UNIVERSAL) return true;
            }
            case EMPTY ->{
                if(c2 == Connector.EMPTY) return true;  //rischio falso positivo
            }
        }

        return false;
    }
}
