package titles;

public enum TitleTypes {
	FREIGHT_HAULER{
		
		@Override
		public String getDescription() {
			return "Count the number of cargo components that \r\n"
					+ "have at least one block of goods.";
		}

		@Override
		public void obtainPenalty() {
			// TODO Auto-generated method stub
			
		}
	},
	
	POWER_TRUCKER{

		@Override
		public String getDescription() {
			return " Count the number of components that use \r\n"
					+ "batteries (shields, double engines, and double \r\n"
					+ "cannons).";
		}

		@Override
		public void obtainPenalty() {
			// TODO Auto-generated method stub
			
		}
		
	},
	
	XENOQUARTERMASTER{

		@Override
		public String getDescription() {
			return "For each alien on your ship, add up its \r\n"
					+ "walking distance to the nearest cabin.";
		}

		@Override
		public void obtainPenalty() {
			// TODO Auto-generated method stub
			
		}
		
	},
	
	CRUISE_CAPTAIN{

		@Override
		public String getDescription() {
			return "Count the number of occupied cabins with a \r\n"
					+ "view.";
		}

		@Override
		public void obtainPenalty() {
			// TODO Auto-generated method stub
			
		}
		
	},
	
	MASTER_ENGINEER{

		@Override
		public String getDescription() {
			return "Count the number of occupied cabins with a \r\n"
					+ "view.";
		}

		@Override
		public void obtainPenalty() {
			// TODO Auto-generated method stub
			
		}
		
	},
	
	CORRIDORIST{

		@Override
		public String getDescription() {
			return "Count the number of occupied cabins with a \r\n"
					+ "view.";
		}

		@Override
		public void obtainPenalty() {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	public abstract String getDescription();
	public abstract void obtainPenalty();
}

