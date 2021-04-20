
package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import persistence.UsersPersistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import Trainer.TrainerApp;
import core.Userprofile;
import core.Workout;

public class RegisterController {
	
	private Collection<Userprofile> allUsers = new ArrayList<Userprofile>();
	private UsersPersistence users = new UsersPersistence (allUsers, "src/main/java/persistence/allUsers.txt");
	
	static Stage stage;
    @FXML
    private Label title_label;

    @FXML
    private TextField firstname_field;

    @FXML
    private TextField email_field;

    @FXML
    private TextField lastname_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private RadioButton female_radio;

    @FXML
    private RadioButton male_radio;

    @FXML
    private DatePicker birthdate_field;

    @FXML
    private Label label_email;

    @FXML
    private Label firstname_text;

    @FXML
    private Label lastname_label;

    @FXML
    private Label gender_label;

    @FXML
    private Label birthdate_label;

    @FXML
    private Label password_label;

    @FXML
    private Button register_button;

    @FXML
    private Hyperlink loginhyper;
    
    @FXML private Label display;


    @FXML
    void LogInAction(ActionEvent event) {
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("login.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
			TrainerApp.stg.close();

		}
		catch (Exception E){
			E.printStackTrace();
		}
    	
    }

    @FXML
    void RegisterAction(ActionEvent event) {
    	String firstname = firstname_field.getText();
    	String lastname = lastname_field.getText();
    	String email = email_field.getText();
    	String password = password_field.getText();
    	LocalDate birthdate = birthdate_field.getValue();
    	char gender;
    	if(female_radio.isSelected()) {
    		gender = 'F';
    	} else {
    		gender = 'M';
    	}
    	
    	Collection<Workout> workouts = new ArrayList();
    	System.out.println(firstname);
    	System.out.println(lastname);
    	System.out.println(email);
    	System.out.println(password);
    	System.out.println(birthdate);
    	System.out.println(gender);
    	Userprofile user = new Userprofile(firstname, lastname, email, password, birthdate, gender, workouts);
    	allUsers.add(user);
    	try {
    		users.writeFile();
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    	display.setText("New user registered!");
    	
    }

}
