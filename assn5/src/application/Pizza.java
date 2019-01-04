package application;
import java.io.Serializable;


/**
 * A class to define and create a pizza.
 * <p>
 * The size, the amount of cheese, amount of pineapple, greenpepper and ham is recorded.
 * It includes the default constructor, a toString method, an equals method, an accessor getCost and a clone method
 * 
 * @author Anne Liu 20069271
 */
public class Pizza implements Comparable<Pizza>, Serializable {

	private static final long serialVersionUID =1L ; //cannot find the number 
	
	private String Size;
	
	private String Cheese;
	private String Pineapple;
	private String Greenpepper;
	private String Ham;
	

	   /**
     * Full parameter constructor.
     * @param yr The year when the data was collected.
     * @param numKids The number of Trick or Treaters!
     * @param temps The air temperatures in degrees Centigrade in an array of int of any size.
     * @param weather The weather condition: "clear", "snow" or "rain".
     * @throws IllegalHalloweenException If arguments are not legal.
     */	
	public Pizza (String size, String cheese, String pineapple, String greenpepper, String ham) throws IllegalPizza {

		
		if ((size == null)|| (cheese==null)|| (pineapple==null)||(greenpepper==null)||(ham==null))
			throw new IllegalPizza ("something is null");
		if ((size.equalsIgnoreCase("small")) ||(size.equalsIgnoreCase("medium")) ||(size.equalsIgnoreCase("large"))) {
            this.Size = size;
        } else
        	throw new IllegalPizza ("Illegal size"); 
		
		if ((cheese.equalsIgnoreCase("single")) || (cheese.equalsIgnoreCase("double")) || (cheese.equalsIgnoreCase("triple"))) {
            this.Cheese = cheese;
        } else
        	throw new IllegalPizza ("Illegal amount of cheese"); 
			
		if ((ham.equalsIgnoreCase("none")) || (ham.equalsIgnoreCase("single"))) {
            this.Ham = ham;
        } else
        	throw new IllegalPizza ("Illegal amount of ham"); 
        
		if ((pineapple.equalsIgnoreCase("none")) || (pineapple.equalsIgnoreCase("single") && ham.equalsIgnoreCase("single"))) {
            this.Pineapple = pineapple;
        } else
        	throw new IllegalPizza ("Illegal amount of pineapple"); 
		
		if ((greenpepper.equalsIgnoreCase("none")) || (greenpepper.equalsIgnoreCase("single") && ham.equalsIgnoreCase("single"))) {
            this.Greenpepper = greenpepper;
        } else
        	throw new IllegalPizza ("Illegal amount of greenpepper"); 
		
	}
	
	//second constructor 
	public Pizza () {
		//sets conditions for small single cheese and ham
		this.Size= "small";
		this.Cheese= "single";
		this.Ham= "single";
		this.Greenpepper="none";
		this.Pineapple="none";
		
	}
	

	
	@Override
	public String toString() {
		/*
		medium pizza, double cheese. Cost: $10.50 each.
		small pizza, single cheese, pineapple, green pepper, ham. Cost: $11.50 each.
		large pizza, triple cheese, pineapple, ham. Cost: $17.00 each.
		*/
		String s;
		s= Size.toLowerCase()+ " pizza, "+ Cheese.toLowerCase() + " cheese";
		if(Pineapple.equalsIgnoreCase("single"))
			s=s+ ", pineapple";
		if(Greenpepper.equalsIgnoreCase("single"))
			s=s+ ", green pepper";
		if(Ham.equalsIgnoreCase("single"))
			s=s+ ", ham";
		
		s+=". Cost: $" + String.format("%1$,.2f", this.getCost()) +" each.";
		
		return s;
	}
	
	
	public boolean equals(Object otherObject) {
		if (otherObject instanceof Pizza) {
			Pizza otherP= (Pizza)otherObject;
			if (this.Size.equalsIgnoreCase(otherP.Size) &&
					this.Cheese.equalsIgnoreCase(otherP.Cheese) &&
					this.Pineapple.equalsIgnoreCase(otherP.Pineapple)&&
					this.Greenpepper.equalsIgnoreCase(otherP.Greenpepper)&&
					this.Ham.equalsIgnoreCase(otherP.Ham))
				return true;		
		}
		return false;
	}
	
	public double getCost () {
		double cost;
		int toppings;
		
		if (Size.equalsIgnoreCase("small"))
			cost= 7.00;
		else if (Size.equalsIgnoreCase("medium"))
			cost= 9.00;
		else 
			cost= 11.00;
		
		if(Cheese.equalsIgnoreCase("single"))
			toppings=0;
		else if (Cheese.equalsIgnoreCase("double"))
			toppings= 1;
		else
			toppings=2;
		
		if(Ham.equalsIgnoreCase("single")) {
			toppings++;
			if (Pineapple.equalsIgnoreCase("single")) 
				toppings++;
			if (Greenpepper.equalsIgnoreCase("single"))
				toppings++;
			
		}
		
		cost+=toppings* 1.5;	
	
		return cost;
	}
	
	public Pizza clone() {
		Pizza pCopy= null;
		try {
			pCopy= new Pizza(Size, Cheese,Pineapple,Greenpepper,Ham);
		} catch(IllegalPizza e) {
			return null;
		}
		
		return pCopy;
		
	}

	@Override
	public int compareTo(Pizza arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
