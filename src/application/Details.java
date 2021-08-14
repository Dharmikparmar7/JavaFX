package application;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Details extends Application{
	
	@FXML
    public Label Dusername;

    @FXML
    public Label Dpassword;

    @FXML
    public Label Dgender;

    @FXML
    public Label Dbirthdate;
	
	public void start(Stage primaryStage) {
		try
		{	
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(Main.class.getResource("Details.fxml"));
			
			Pane root = loader.load();
			
			FadeTransition fd = new FadeTransition(Duration.seconds(0.5), root);
			fd.setFromValue(0.0);
			fd.setToValue(1.0);
			fd.play();
			
			Scene s = new Scene(root);
			
			s.setFill(Color.TRANSPARENT);
			
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			
			primaryStage.setScene(s);
			
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			
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
