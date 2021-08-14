package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DetailsController {

	@FXML
	private Label fullname;

	@FXML
	private Label Dusername;

	@FXML
	private Label Dpassword;

	@FXML
	private Label Dgender;

	@FXML
	private Label Dbirthdate;
	
	@FXML
	private ImageView btnClose;

	@FXML
	private void initialize() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Stage stage = (Stage) fullname.getScene().getWindow();
				Box b = (Box)stage.getUserData();
				fullname.setText(b.getFname() + " " + b.getLname());
				Dusername.setText(b.getUname());
				Dpassword.setText(b.getPassword());
				Dgender.setText(b.getGender());
				Dbirthdate.setText(b.getBirthdate());
			}
		});
	}
	
	@FXML
	void closeApp(MouseEvent event) {
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
	}
}
