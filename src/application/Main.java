package application;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Main extends Application {
	
	Stage a;
	
	@Override
	public void start(Stage primaryStage) {
		try
		{
			this.a = primaryStage;
			
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(Main.class.getResource("Main.fxml"));
			
			Pane root = loader.load();
			
			FadeTransition fd = new FadeTransition(Duration.seconds(0.5), root);
			fd.setFromValue(0.0);
			fd.setToValue(1.0);
			fd.play();
			
			Scene s = new Scene(root);
			
			s.setFill(Color.TRANSPARENT);
			
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			
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

//750 449