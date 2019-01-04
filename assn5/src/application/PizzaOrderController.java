package application;

import java.util.ArrayList;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class PizzaOrderController {
	
	private static ArrayList<LineItem> orders = new ArrayList<>();
	
	@FXML 
	private ChoiceBox<String> choiceBoxSize;
	
	@FXML 
	private ChoiceBox<String> choiceBoxCheese;
	
	@FXML 
	private ChoiceBox<String> choiceBoxHam;
	
	@FXML 
	private ChoiceBox<String> choiceBoxPinapple;
	
	@FXML 
	private ChoiceBox<String> choiceBoxGreenpepper;
	
	@FXML 
	private ComboBox<Integer> comboBoxNum;
	
	@FXML
    private Label eachCost;
	
//	@FXML
//    private Label totalCost;
	
	@FXML
    private TextArea outText;
	
	
    @FXML
    private void initialize() {
    	
    	// add pizza order number from 1 to 100 to the combobox
    	for(int i=1;i<101;i++)
    	{
    		comboBoxNum.getItems().add(i);
    	}
    	comboBoxNum.getSelectionModel().selectFirst();
    	
    	// adding choices and listeners to choiceboxes
    	choiceBoxSize.getItems().setAll(FXCollections.observableArrayList("small", "medium", "large"));
    	choiceBoxSize.getSelectionModel().selectFirst();
    	choiceBoxSize.getSelectionModel().selectedItemProperty().addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> setEachCost() );
    	
    	choiceBoxCheese.getItems().setAll(FXCollections.observableArrayList("single", "double", "triple"));
    	choiceBoxCheese.getSelectionModel().selectFirst();
    	choiceBoxCheese.getSelectionModel().selectedItemProperty().addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> setEachCost() );
   	
    	choiceBoxHam.getItems().setAll(FXCollections.observableArrayList("none", "single"));
    	choiceBoxHam.getSelectionModel().selectLast();
    	choiceBoxHam.getSelectionModel().selectedItemProperty().addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> hamCheck() );
   	
    	choiceBoxPinapple.getItems().setAll(FXCollections.observableArrayList("none", "single"));
    	choiceBoxPinapple.getSelectionModel().selectFirst();
    	choiceBoxPinapple.getSelectionModel().selectedItemProperty().addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> setEachCost() );
    	
    	choiceBoxGreenpepper.getItems().setAll(FXCollections.observableArrayList("none", "single"));
    	choiceBoxGreenpepper.getSelectionModel().selectFirst();
    	choiceBoxGreenpepper.getSelectionModel().selectedItemProperty().addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> setEachCost() );
   	
    	eachCost.setText("8.50 $");// default price
    	outText.setEditable(false);//set to uneditable
    }
    
    @FXML
    private void onPizzaPriceCheck(){
    	System.out.println("1");
    }
    
    @FXML
    //action of displaying orders when place order is pressed
    private void onOrderClick(){
    	float totalCost = 0;

    	try {
			Pizza aPizza = new Pizza(choiceBoxSize.getSelectionModel().getSelectedItem(),
					choiceBoxCheese.getSelectionModel().getSelectedItem(),
					choiceBoxPinapple.getSelectionModel().getSelectedItem(),
					choiceBoxGreenpepper.getSelectionModel().getSelectedItem(),
					choiceBoxHam.getSelectionModel().getSelectedItem());

			LineItem lineItem = new LineItem(comboBoxNum.getSelectionModel().getSelectedItem(), aPizza);
			
			orders.add(lineItem);
			
			//orders.sort(null);
			outText.clear();
			for (LineItem order : orders) {
				totalCost += order.getCost();
				outText.appendText(order.toString()+"\n");

			}
			outText.appendText("Total Cost is "+String.valueOf(totalCost)+" $");

		} catch (IllegalPizza ip) {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("IllegalPizza");
        	alert.setHeaderText("exception happen");
        	alert.setContentText(ip.getMessage());
        	
        	alert.showAndWait();
		}
		

    }
    //make topping restrictions
    private void hamCheck()
    {
    	if(choiceBoxHam.getSelectionModel().getSelectedItem().equalsIgnoreCase("none")) 
    	{
    		choiceBoxPinapple.getSelectionModel().selectFirst();
    		choiceBoxGreenpepper.getSelectionModel().selectFirst();
    		choiceBoxPinapple.setDisable(true);
    		choiceBoxGreenpepper.setDisable(true);
    		
    	}
    	else
    	{
    		choiceBoxPinapple.setDisable(false);
    		choiceBoxGreenpepper.setDisable(false);
    		
    	}
    	setEachCost();
    }
    
    //set cost to display
    private void setEachCost()
    {
    	eachCost.setText(String.valueOf(getEachCost())+" $");
    }
    
    //get cost of each pizza
	private double getEachCost() {
		double cost;
		int toppings;
		String sTemp;
		
		sTemp = choiceBoxSize.getSelectionModel().getSelectedItem();
		if (sTemp.equalsIgnoreCase("small"))
			cost= 7.00;
		else if (sTemp.equalsIgnoreCase("medium"))
			cost= 9.00;
		else 
			cost= 11.00;
		
		sTemp = choiceBoxCheese.getSelectionModel().getSelectedItem();
		
		if(sTemp.equalsIgnoreCase("single"))
			toppings=0;
		else if (sTemp.equalsIgnoreCase("double"))
			toppings= 1;
		else
			toppings=2;
		
		if(choiceBoxHam.getSelectionModel().getSelectedItem().equalsIgnoreCase("single")) {
			toppings++;
			if (choiceBoxPinapple.getSelectionModel().getSelectedItem().equalsIgnoreCase("single")) 
				toppings++;
			if (choiceBoxGreenpepper.getSelectionModel().getSelectedItem().equalsIgnoreCase("single"))
				toppings++;
			
		}
		
		cost+=toppings* 1.5;	
	
		return cost;
	}
}
