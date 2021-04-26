package gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import Trainer.TrainerApp;
import core.Userprofile;
import persistence.UserProfilePersistence;
import persistence.UsersPersistence;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController {
	
	/*
	 * 
	 * VARIABLES
	 * 
	 */
	
	//GUI - VARIABLES
	static Stage stage;
	@FXML private Label title_label;
	@FXML private Label username_label;
	@FXML private TextField username_field;
	@FXML private Label password_label;
	@FXML private PasswordField password_field;
	@FXML private Button LogIn_button;
	@FXML private Hyperlink register_hyperlink;
	@FXML private Label display;
	Alert a = new Alert(AlertType.NONE);
	
	//USER 
	private Userprofile user1 = new Userprofile();
	private ArrayList<String> alluserlist = new ArrayList<String>();
	
	//PERSISTENCE 
	private UsersPersistence userspersistence = new UsersPersistence("src/main/java/persistence/allUsers.txt");
	
	//INPUT
	public String usernametext;
	public String passwordtext;
	
	
	
	
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	
	//FXML-METHODS
	
	
	/**
	 * This method is used to handle action when user clicks the login-button on the user interface.
	 * @param event 
	 */
	@FXML
	private void LogInAction(ActionEvent event) {
		usernametext = username_field.getText();
		passwordtext = password_field.getText();
		
		try {
			userspersistence.readFile("src/main/java/persistence/allUsers.txt");
			alluserlist = userspersistence.list;
			if( alluserlist.contains(usernametext) || alluserlist.contains(passwordtext)) {
				logIn(); // Calling logIn method 
				Success();
				loadNext();
			} else {
				 a.setAlertType(AlertType.ERROR);
				 a.setContentText("You do not have a user registered.");
				 a.showAndWait();
				throw new IllegalArgumentException("You do not have a user registered.");
			}
		}
		catch (Exception E) {
			E.printStackTrace();
		}
		
	}
	
	
	/**
	 * This method is used to handle action when user clicks the register-button on the user interface.
	 * @param event
	 */
	@FXML
	private void GoToRegister(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("register.fxml"));
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
	
	//METHODS
	
	/*
	 * We are using logIn() to set a user. After this is done the method also checks if the input 
	 * is correct.  
	 */
	private void logIn() {
		int index_username = alluserlist.indexOf(usernametext);
		
		user1.setEmail(alluserlist.get(index_username));
		user1.setPassword(alluserlist.get(index_username + 2 ));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		user1.setGender(alluserlist.get(index_username -2).charAt(0));
		LocalDate birthday = LocalDate.parse(alluserlist.get(index_username-4), formatter);
		user1.setBirthday(birthday);
		user1.setFirstName(alluserlist.get(index_username - 7));
		user1.setLastName(alluserlist.get(index_username - 6));
		
		if(user1.getEmail().equals(usernametext) && user1.getPassword().equals(passwordtext)) {
			UserProfilePersistence userprofile = new UserProfilePersistence(user1, "src/main/java/persistence/userProfiles.txt");
			try {
				userprofile.writeFile();
				
			}catch(Exception x){
				x.printStackTrace();
			}
		} else {
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Wrong password or username.");
			a.showAndWait();
			throw new IllegalArgumentException("Wrong password or username.");
		}
		
	}

	
	/*
	 * If all the information is correct this function will be called and the user will get an alert.
	 */
	private void Success() {
		a.setAlertType(AlertType.INFORMATION);
		a.setContentText("You are logged in!");
		a.showAndWait();
	}
	
	/*
	 * Loads next scene and closes current scene. 
	 */
	private void loadNext() {
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
	}
}
