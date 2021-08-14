package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController {

	@FXML
	private TextField firstname;

	@FXML
	private TextField password;

	@FXML
	private TextField Rpassword;

	@FXML
	private TextField username;

	@FXML
	private TextField Rusername;

	@FXML
	private TextField lastname;

	@FXML
	private DatePicker birthdate;

	@FXML
	private Button btnRegister;

	@FXML
	private Label labelRegister;

	@FXML
	private Button btnBackToLogin;

	@FXML
	private Pane PaneLogin;

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnGoToRegister;

	@FXML
	private ImageView btnClose;

	@FXML
	private Pane paneImage;
	
	@FXML
	private ToggleGroup gender;

	@FXML
	private Button btnRYES;

	@FXML
	private Button btnRNO;
	
	@FXML
	private Label askUsername;
	
	@FXML
	private RadioButton female;
	
	@FXML
	private RadioButton male;
	
	@FXML
	private Label askPassword;
	
	@FXML
	private Button btnPYES;
	
	@FXML
	private Button btnPNO;
	
	@FXML
	private Pane paneRegistered;
	
	@FXML
	private Pane paneLoggedin;
	
	@FXML
	private Label labelInvalid;
	
	@FXML
	private Label statusLogin;

	@FXML
	private ImageView statusLoginImage;

	@FXML
	void backToLogin(ActionEvent event) {
		TranslateTransition image = new TranslateTransition(Duration.seconds(0.5), paneImage);
		image.setToX(0);
		image.play();

		TranslateTransition login = new TranslateTransition(Duration.seconds(0.5), PaneLogin);
		login.setToX(0);
		login.play();
	}

	@FXML
	void goToRegister(ActionEvent event) {
		TranslateTransition image = new TranslateTransition(Duration.seconds(0.5), paneImage);
		image.setToX(755);
		image.play();

		TranslateTransition login = new TranslateTransition(Duration.seconds(0.5), PaneLogin);
		login.setToX(755);
		login.play();
	}

	@FXML
	void login(ActionEvent event) {
		String url = "jdbc:sqlserver://lenovo;databaseNAME=User";
		String user = "sa";
		String password2 = "password";
		
		try 
		{
			boolean isValid = false;
			
			Connection connection = DriverManager.getConnection(url, user, password2);
			
			String query = "select count(1) from person where username = '"+ username.getText() +"' and password = '"+ password.getText() +"'";
			
			Statement statement = connection.createStatement();
			
			ResultSet res = statement.executeQuery(query);
			
			while(res.next())
			{
				if(res.getInt(1) == 1)
				{
					paneLoggedin.setVisible(true);
					
					statusLogin.setText("Successfully Logged In");
					
					statusLoginImage.setVisible(true);
					
					Stage s = (Stage)btnLogin.getScene().getWindow();
					
					PauseTransition visiblePause = new PauseTransition(Duration.seconds(1));
					visiblePause.setOnFinished(e2 -> {
						new Dashboard().start(s);
					});
					
					visiblePause.play();
					isValid = true;
					break;
				}
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	void login1(ActionEvent event) {
		try 
		{
		    String path = new File("src/application/Database.txt").getAbsolutePath();
		    BufferedReader read = new BufferedReader(new FileReader(path));
		    
			String temp;
			boolean isValid = true;
			
			while((temp = read.readLine())!= null)
			{
				String[] up = temp.split("/");
				if(username.getText().equals(up[0])) 
				{
					if(password.getText().equals(up[1]))
					{
						paneLoggedin.setVisible(true);
						
						statusLogin.setText("Successfully Logged In");
						
						statusLoginImage.setVisible(true);
						
						Stage s = (Stage)btnLogin.getScene().getWindow();
						
						PauseTransition visiblePause = new PauseTransition(Duration.seconds(1));
						visiblePause.setOnFinished(e2 -> {
							new Dashboard().start(s);
						});
						
						visiblePause.play();
						
						isValid = false;
						break;
					}
				}
			}
			
			if (isValid)
			{
				paneLoggedin.setVisible(true);
				
				paneLoggedin.setPrefWidth(240);
				
				statusLogin.setText("Invalid Username/Password");
				
				statusLogin.setPrefWidth(250);
				
				statusLoginImage.setVisible(false);						
				
				PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));

				visiblePause.setOnFinished(e2 ->
				{
					
				});
//				
				visiblePause.play();
			}
			
			read.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void register(ActionEvent event)
	{
		String url = "jdbc:sqlserver://lenovo;databaseNAME=User";
		String user = "sa";
		String password2 = "password";
		
		try 
		{
			Connection connection = DriverManager.getConnection(url, user, password2);
			RadioButton x;
			x = (RadioButton) gender.getSelectedToggle();
			String data = "insert into person values('" + firstname.getText() + "', '"+ lastname.getText() + "', '"+ Rusername.getText() +"', '" + Rpassword.getText() + "', '" + birthdate.getValue() + "', '"+ x.getText() +"')";
			
			Statement statement = connection.createStatement();
			
			statement.executeUpdate(data);
			
			TranslateTransition reg = new TranslateTransition(Duration.seconds(1), paneRegistered);
			reg.setToY(-39);
			reg.play();
			
			PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
			
			visiblePause.setOnFinished(e2 -> {
				TranslateTransition image = new TranslateTransition(Duration.seconds(0.5), paneImage);
				image.setToX(0);
				image.play();
				
				TranslateTransition login = new TranslateTransition(Duration.seconds(0.5), PaneLogin);
				login.setToX(0);
				login.play();
			});
			
			visiblePause.play();
			
			username.setText(Rusername.getText());
			password.setText(Rpassword.getText());
			
			System.out.println("\n1 Row Affected\n");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	void register2(ActionEvent event) {
		try {
			
			String file = new File("src/application/Database.txt").getAbsolutePath();
			
			FileWriter fr = new FileWriter(file, true);
			BufferedWriter br = new BufferedWriter(fr);
			PrintWriter pw = new PrintWriter(br);
			
			//try {
			BufferedReader br2 = new BufferedReader(new FileReader(file));
			
			String temp;
			
			boolean isValid = true;
			
			while((temp = br2.readLine())!=null) 
			{
				String[] up = temp.split("/");
				
				if(Rusername.getText().equals(up[0])) 
				{
					isValid = false;
					break;
				}
			}
			
			RadioButton x;
			x = (RadioButton) gender.getSelectedToggle();
			
			if(isValid) {
				
				if(!Rusername.getText().isEmpty() && !Rpassword.getText().isEmpty() && !firstname.getText().isEmpty() && !lastname.getText().isEmpty() && birthdate.getValue() != null && x != null)
				{	
					String pwd = Rpassword.getText();
					
					String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([^/])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
					
					if(pwd.matches(regex)) {
						
						String temp2 = Rusername.getText() + "/" + Rpassword.getText() + "/" + firstname.getText()+ "/" + lastname.getText() + "/" + birthdate.getValue() + "/" + x.getText() + "\n";
						pw.append(temp2);
						
						TranslateTransition reg = new TranslateTransition(Duration.seconds(1), paneRegistered);
						reg.setToY(-39);
						reg.play();
						
						PauseTransition visiblePause = new PauseTransition(Duration.seconds(2));
						
						visiblePause.setOnFinished(e2 -> {
							TranslateTransition image = new TranslateTransition(Duration.seconds(0.5), paneImage);
							image.setToX(0);
							image.play();
							
							TranslateTransition login = new TranslateTransition(Duration.seconds(0.5), PaneLogin);
							login.setToX(0);
							login.play();
						});
						
						visiblePause.play();
						
						username.setText(Rusername.getText());
						password.setText(Rpassword.getText());
					}
					else
					{		
						askPassword.setText("Password must contains [0-9], [a-z], [A-Z], Symbol and length >= 8");
					}
				}
				else
				{
					labelRegister.setText("Fill all details");
				}
			}
			
			else
			{
				askUsername.setText("Username is already exist");
			}
			
			br2.close();
			br.close();
			pw.close();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	
	@FXML
	public void initialize() {
		firstname.focusedProperty().addListener(event -> {
			if (!firstname.getText().isEmpty()) {
				firstname.setText(firstname.getText().substring(0, 1).toUpperCase() + firstname.getText().substring(1).toLowerCase());
			}
		});
		
		lastname.focusedProperty().addListener(event -> {
			if (!lastname.getText().isEmpty()) {
				lastname.setText(lastname.getText().substring(0, 1).toUpperCase() + lastname.getText().substring(1).toLowerCase());
			}
		});
		
		Rusername.focusedProperty().addListener(event -> {
			askUsername.setText("Can i fill username for you ?");
			btnRYES.setVisible(true);
			btnRNO.setVisible(true);
		});
	}
	
	
	@FXML
	void usernameYES(ActionEvent event) {
		Rusername.setText(firstname.getText().toLowerCase() + lastname.getText().toLowerCase());
		btnRYES.setVisible(false);
		btnRNO.setVisible(false);
		askUsername.setText("");
	}
	
	@FXML
	void usernameNO(ActionEvent event) {
		btnRYES.setVisible(false);
		btnRNO.setVisible(false);
		askUsername.setText("");
	}
	
	@FXML
	void PasswordYES(ActionEvent event) {
		
//		String password = "";
//		
//		if(!firstname.getText().isEmpty() && !lastname.getText().isEmpty())
//		{
//			char c = firstname.gextText().charAt(0);
//			int a =  Integer.parseInt(c);
//			System.out.println(a);
//			
//			password = firstname.getText().substring(0, 2).toUpperCase() + lastname.getText().substring(2, 0).toLowerCase();
//		}
	}
	
	@FXML
	void PasswordNO(ActionEvent event) {
//		askPassword.setText("");
//		btnPYES.setVisible(false);
//		btnPNO.setVisible(false);
	}

	@FXML
	void closeApp(MouseEvent event) {
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
	}
}