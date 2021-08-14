package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Dashboard extends Application{
	public void start(Stage primaryStage) {
		try
		{	
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(Main.class.getResource("Dashboard.fxml"));
			
			Pane root = loader.load();
			
			Scene s = new Scene(root);
			
			s.setFill(Color.TRANSPARENT);
			
			primaryStage.setScene(s);
			
			primaryStage.show();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
