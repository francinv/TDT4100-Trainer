package gui;

import java.util.ArrayList;
import java.util.LinkedList;

import Trainer.TrainerApp;
import core.Userprofile;
import persistence.UserProfilePersistence;
import persistence.UsersPersistence;

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
	
	public String usernametext;
	public String passwordtext;
	
	private UsersPersistence userspersistence = new UsersPersistence("src/main/java/persistence/allUsers.txt");
	private ArrayList<String> alluserlist = new ArrayList<String>();
	
	@FXML private Label title_label;
	@FXML private Label username_label;
	@FXML private TextField username_field;
	@FXML private Label password_label;
	@FXML private PasswordField password_field;
	@FXML private Button LogIn_button;
	@FXML private Hyperlink register_hyperlink;
	@FXML private Label display;
	
	
	
	@FXML
	private void LogInAction(ActionEvent event) {
		usernametext = username_field.getText();
		passwordtext = password_field.getText();
		
		try {
			userspersistence.readFile("src/main/java/persistence/allUsers.txt");
			alluserlist = userspersistence.list;
			if( alluserlist.contains(usernametext) && alluserlist.contains(passwordtext)) {
				logIn();
				loadNext();
				
			} else {
				throw new IllegalArgumentException("Feil brukernavn eller passord");
			}
		}
		catch (Exception E) {
			E.printStackTrace();
		}
		
		System.out.println(usernametext +" " + passwordtext);
	}
	
	private void logIn() {
		int index_username = alluserlist.indexOf(usernametext);
		
		String username = alluserlist.get(index_username);
		String password = alluserlist.get(index_username + 2);

		if(username.equals(usernametext) && password.equals(passwordtext)) {
			char gender = alluserlist.get(index_username - 2).charAt(0);
			String birthday = alluserlist.get(index_username - 4);
			String lastName = alluserlist.get(index_username - 6);
			String firstName = alluserlist.get(index_username - 7);
			Userprofile user1 = new Userprofile(firstName, lastName, username, password, birthday, gender);
			UserProfilePersistence userprofile = new UserProfilePersistence(user1, "src/main/java/persistence/userProfiles.txt");
			try {
				userprofile.writeFile();
				
			}catch(Exception x){
				x.printStackTrace();
			}
			System.out.println(user1);
		} else {
			throw new IllegalArgumentException("Feil passord eller brukernavn");
		}
		
	}

	@FXML
	public void GoToRegister(ActionEvent event) {
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
	
	private void Success() {
		title_label.setText("Login successfull!");
	}
	
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
