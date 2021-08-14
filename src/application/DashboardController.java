package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class DashboardController {

	@FXML
    private FlowPane flowPane;
	
	@FXML
	private ImageView btnClose;
	
	@FXML
	public void initialize() {
		
		String url = "jdbc:sqlserver://DHARMIK-PARMAR;databaseNAME=User";
		String user = "sa";
		String password2 = "Dharmik@123";
		
		try 
		{
			Connection connection = DriverManager.getConnection(url, user, password2);
			
			Statement statement = connection.createStatement();
			
			ResultSet res = statement.executeQuery("select * from person");
			
			while(res.next())
			{	
				Box b = new Box(res.getString(3).toString().strip(), res.getString(4).toString().strip(), res.getString(6).toString().strip(), res.getString(5).toString().strip(), res.getString(1).toString().strip(), res.getString(2).toString().strip());
				
//				Box b = new Box(temp.split("/")[0], temp.split("/")[1], temp.split("/")[5], temp.split("/")[4], temp.split("/")[2], temp.split("/")[3]);
//				
//				b.username.setText(temp.split("/")[0]);
				
				b.username.setText(res.getString(1).toString().strip());

				flowPane.getChildren().add(b);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
//		try 
//		{   
//		    String file = new File("src/application/Database.txt").getAbsolutePath();
//		    
//			BufferedReader read = new BufferedReader(new FileReader(file));
//			
//			String temp;
//			
//			while((temp = read.readLine()) != null) 
//			{
//				Box b = new Box(temp.split("/")[0], temp.split("/")[1], temp.split("/")[5], temp.split("/")[4], temp.split("/")[2], temp.split("/")[3]);
//				
//				b.username.setText(temp.split("/")[0]);
//
//				flowPane.getChildren().add(b);
//			}
//			
//			read.close();
//		}
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
	}
	
	@FXML
	void closeApp(MouseEvent event) {
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
	}
	
}