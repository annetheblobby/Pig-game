package application;
import java.io.Serializable;


public class LineItem implements Comparable<LineItem>, Serializable{
	//pointer to toString?
	private static final long serialVersionUID =2L;
	private final Pizza aPizza;
	private int numPizza;
	
	public LineItem(int numOrder, Pizza Pizza1) throws IllegalPizza{
		
		if (numOrder<1)
			throw new IllegalPizza("please enter a pizza");
		else if (numOrder>100)
			throw new IllegalPizza("less than 100 pizzas please");
		if (Pizza1== null)
			throw new IllegalPizza("please enter a valid number of pizzas");
		aPizza=Pizza1;
		numPizza =numOrder;
		
	}
	
	public LineItem(Pizza defaultPizza) throws IllegalPizza{
		this(1, defaultPizza);
	}
	
	public Pizza getPizza() {
		return this.aPizza;
	
	}
	
	public int getNumber() {
		return this.numPizza;	
	}
	
	
	public int compareTo(LineItem aItem) {
		return (int)(aItem.getCost()-getCost());
		
	}
		
	public double getCost() {
		double cost;
		if (numPizza >= 10 && numPizza <= 20)
			cost= aPizza.getCost()* numPizza* 0.9;
		else if (numPizza > 20)
			cost= aPizza.getCost()*numPizza* 0.85;
		else
			cost=aPizza.getCost()*numPizza;
		
		return cost;
	}
	
	@Override
	public String toString() {
		String s;
		if (numPizza< 10) 
			s= " " + numPizza+ " " + aPizza.toString();
		else 
			s= numPizza+ " "+ aPizza.toString();
		
		return s;
	}

	public void setNumber(int num) throws IllegalPizza{
		if (num<1 || num >100)
			throw new IllegalPizza("please order within the range of 1- 100 pizzas");
		numPizza= num;
	}

}


