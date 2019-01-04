package application;
	
import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
/*	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	private BorderPane rootLayout;
	

	
	@Override
	public void start(Stage primaryStage) {
		
        //this.primaryStage = primaryStage;
        primaryStage.setTitle("Pizza Delivery System");
        
     // Set the application icon.
        primaryStage.getIcons().add(new Image("file:resources/images/Pizza.png"));

        initRootLayout(primaryStage);

        showPizzaOrder();
		
	}
	
	//initializes root layout
    private void initRootLayout(Stage primaryStage) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }	
    
    // shows pizza order inside root layout
    private void showPizzaOrder() {
        try {
            // Load pizza order
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("PizzaOrder.fxml"));
            AnchorPane pizzaOrder = (AnchorPane) loader.load();
            
            // Set pizza order into the center of root layout.
            rootLayout.setCenter(pizzaOrder);
            

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
    
	
	public static void main(String[] args) {
		launch(args);
	}
}