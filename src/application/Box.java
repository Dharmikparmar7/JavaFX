package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Box extends Pane{
	
	@FXML
	public Label username;

	@FXML
	private Button btnViewDetails;

	@FXML
	private Button btnDelete;
	
	private String uname, password, gender, birthdate, Fname, Lname;
	
	public Box(String uname, String password, String gender,String birthdate,String Fname,String Lname)
	{
		FXMLLoader loader = new FXMLLoader();
		
		loader.setLocation(getClass().getResource("Box.fxml"));
		
		loader.setRoot(this);
		
		loader.setController(this);
		
		try
		{
			loader.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		this.Fname = Fname;
		this.Lname = Lname;
		this.uname = uname;
		this.password = password;
		this.gender = gender;
		this.birthdate = birthdate;
	}
	
    @FXML
    void details(ActionEvent event) {
    	Stage s = new Stage();
    	s.setUserData(this);
		new Details().start(s);
    }
	
	@FXML
    void delete(ActionEvent event) {
		
		String url = "jdbc:sqlserver://DHARMIK-PARMAR;databaseNAME=User";
		String user = "sa";
		String password2 = "Dharmik@123";
		
		try 
		{
			Connection connection = DriverManager.getConnection(url, user, password2);
			
			String query = "DELETE FROM person WHERE username = '"+ uname +"'";
			
			Statement statement = connection.createStatement();
			
			statement.executeUpdate(query);
			
			((FlowPane)this.getParent()).getChildren().remove(this);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
//		try 
//		{
//			File file = new File("src/application/Database.txt");
//			BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
//			
//			File tempFile = new File("src/application/temp.txt");
//			FileWriter fr = new FileWriter(tempFile.getAbsolutePath(), true);
//			BufferedWriter br = new BufferedWriter(fr);
//			PrintWriter pw = new PrintWriter(br);
//			
//			String forCheck, forPaste;
//			
//			while((forCheck = reader.readLine()) != null)
//			{
//				forPaste = forCheck + "\n";
//				
//				String[] t = forCheck.split("/");
//				
//				if(!t[0].equals(username.getText()))
//				{
//					pw.append(forPaste);
//					pw.flush();
//				}
//			}
//			
//			pw.close();
//			reader.close();
//		
//			file.delete();
//			if(tempFile.renameTo(file))
//				((FlowPane)this.getParent()).getChildren().remove(this);
//		}
//		
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
		
    }

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		Fname = fname;
	}

	public String getLname() {
		return Lname;
	}

	public void setLname(String lname) {
		Lname = lname;
	}
}
