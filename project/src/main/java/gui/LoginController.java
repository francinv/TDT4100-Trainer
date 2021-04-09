package gui;

import Trainer.TrainerApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	static Stage stage;
	
	@FXML
	private Label title_label;
	
	@FXML
	private Label username_label;
	
	@FXML
	private TextField username_field;
	
	@FXML
	private Label password_label;
	
	@FXML
	private PasswordField password_field;
	
	@FXML 
	private Button LogIn_button;
	
	@FXML
	private Hyperlink register_hyperlink;
	
	@FXML
	public void LogInAction(ActionEvent event) {
		String usernametext = username_field.getText();
		String passwordtext = password_field.getText();
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Workouts.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
			TrainerApp.stg.close();

		}
		catch (Exception e){
			e.printStackTrace();
		}
		System.out.println(usernametext + passwordtext);
	}
	
	@FXML
	public void GoToRegister(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
			TrainerApp.stg.close();

		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
