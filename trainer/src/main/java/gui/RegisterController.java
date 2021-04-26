package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import persistence.UsersPersistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import core.Userprofile;

public class RegisterController {
	
	/*
	 * 
	 * VARIABLES
	 * 
	 */
	
	//GUI - VARIABLES
	static Stage stage;
    @FXML private Label title_label;
    @FXML private TextField firstname_field;
    @FXML private TextField email_field;
    @FXML private TextField lastname_field;
    @FXML private PasswordField password_field;
    @FXML private RadioButton female_radio;
    @FXML private RadioButton male_radio;
    @FXML private DatePicker birthdate_field;
    @FXML private Label label_email;
    @FXML private Label firstname_text;
    @FXML private Label lastname_label;
    @FXML private Label gender_label;
    @FXML private Label birthdate_label;
    @FXML private Label password_label;
    @FXML private Button register_button;
    @FXML private Hyperlink loginhyper;
    @FXML private Label display;
    Alert a = new Alert(AlertType.NONE);
    
    //PERSISTENCE
    private Collection<Userprofile> allUsers = new ArrayList<Userprofile>();
	private UsersPersistence users = new UsersPersistence (allUsers, "src/main/java/persistence/allUsers.txt");
	
	//USER
	private Userprofile user = new Userprofile();
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private LocalDate birthdate;
	private char gender;
	
	
	

	/*
	 * 
	 * METHODS
	 * 
	 */
	
	
	//FXML-METHODS
	
	/**
	 * This method is called when user click on "Log in here", closes current scene and opens new scene.
	 * @param event
	 */

    @FXML
    void LogInAction(ActionEvent event) {
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("login.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
			Stage stg = (Stage) loginhyper.getScene().getWindow();
		    stg.close();

		}
		catch (Exception E){
			E.printStackTrace();
		}
    	
    }

    /**
     * This method is called when user clicks on "Register",
     * the method calls checkUser() and if successfull calls Success();
     * @param event
     */
    @FXML
    void RegisterAction(ActionEvent event) {
    	firstname = firstname_field.getText();
    	lastname = lastname_field.getText();
    	email = email_field.getText();
    	password = password_field.getText();
    	birthdate = birthdate_field.getValue();
    	if(female_radio.isSelected()) {
    		gender = 'F';
    	} else {
    		gender = 'M';
    	}
    	try {
    		checkUser();
    		allUsers.add(user);
    		try {
        		users.addUserToFile(user);
        	} catch (Exception e){
        		e.printStackTrace();
        	}
        	Success();
    	}
    	catch (Exception x){
    		a.setAlertType(AlertType.ERROR);
			a.setContentText("One or more fields contains an error.");
			a.showAndWait();
			x.printStackTrace();
    	}
    }

    
    //METHODS 
    
    /*
     * Method that uses User model to check if information is correct.
     */
	private void checkUser() {
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setEmail(email);
		user.setPassword(password);
		user.setBirthday(birthdate);
		user.setGender(gender);
	}

	/*
	 * Method that displays Alert.
	 */
	private void Success() {
		a.setAlertType(AlertType.INFORMATION);
		a.setContentText("User registered.");
		a.showAndWait();
		
	}

}
