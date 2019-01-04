package application;

public class IllegalPizza extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalPizza() {
		super("Illegal parameter value supplied to Pizza object.");
		
	}
	
	public IllegalPizza(String message) {
		super(message);
	}
}
