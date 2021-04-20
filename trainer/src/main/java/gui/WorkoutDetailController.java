package gui;

import java.util.ArrayList;
import java.util.List;

import core.Userprofile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import persistence.UserProfilePersistence;
import persistence.WorkoutPersistence;

public class WorkoutDetailController {

    @FXML private Button buttonMainpage;
    @FXML private Button buttonProfile;
    @FXML private Button subber_button;
    
    @FXML private Label exercises_field;
    @FXML private Label exercises_label;
    @FXML private Label description_field;
    @FXML private Label desc_label;
    @FXML private Label muscles_label;
    @FXML private Label muscles_field;
    @FXML private Label date_label;
    @FXML private Label date_field;
    @FXML private Label type_label;
    @FXML private Label type_field;
    @FXML private Label category_label;
    @FXML private Label category_field;
    @FXML private Label duration_label;
    @FXML private Label duration_field;
    @FXML private Label workout_name;
    @FXML private Label createdBy;
    
    private ArrayList<String> thisworkout = new ArrayList<String>();
    private WorkoutPersistence wp = new WorkoutPersistence();
    private ArrayList<String> user = new ArrayList<String>();
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthday;
    private char gender;
    private String name;
    private 
    

    @FXML
    public void initialize() {
    	populateFields();
    }

	private void populateFields() {
		wp.readFile("src/main/java/persistence/workout.txt");
		thisworkout = wp.workoutlist;
		System.out.println(thisworkout);
		
		workout_name.setText("Workoutname: "+ thisworkout.get(1));
		createdBy.setText("By: " + thisworkout.get(3 ) + " " + thisworkout.get(4));
		date_field.setText(thisworkout.get(6));
		duration_field.setText(thisworkout.get(8) + " hrs");
		type_field.setText(thisworkout.get(10));
		
		List<String> categories = new ArrayList();
		
		for (int i = 12; i < thisworkout.size(); i ++) {
			String element = thisworkout.get(i);
			if(element.contains("Muscles")) {
				break;
			} else {
				categories.add(element);
			}
		}
		category_field.setText(categories.toString());
		
		int indexOf = thisworkout.indexOf("Muscles");
		List<String> muscles = new ArrayList();
		for (int o = indexOf + 2; o < thisworkout.size(); o ++) {
			String element = thisworkout.get(o);
			if(element.contains("Number")) {
				break;
			} else {
				muscles.add(element);
			}
		}
		muscles_field.setText(muscles.toString());
		
		int indexOfEx = thisworkout.indexOf("Number");
		exercises_field.setText(thisworkout.get(indexOfEx + 3));
		
		List<String> description = new ArrayList();
		for (int x = indexOfEx +5; x < thisworkout.size(); x ++) {
			String element = thisworkout.get(x);
			if(element.contains("Users")) {
				break;
			} else {
				description.add(element);
			}
		}
		description_field.setText(description.toString());
		
	}
	
	@FXML
	private void GoToMain(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Workouts.fxml"));
		try {
			Parent root = (Parent) fxmlLoader.load();
			Stage stg = new Stage();
			stg.setScene(new Scene(root));
			stg.show();
			Stage stage = (Stage) buttonMainpage.getScene().getWindow();
		    stage.close();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    
	@FXML 
	private void goToProfile(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("profile.fxml"));
		try {
			Parent root = (Parent) fxmlLoader.load();
			Stage stg = new Stage();
			stg.setScene(new Scene(root));
			stg.show();
			Stage stage = (Stage) buttonProfile.getScene().getWindow();
		    stage.close();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    
	@FXML 
	private void signUpWorkout(ActionEvent event) {
		getLoggedInUserInfo();
		Userprofile sub = new Userprofile(firstName, lastName, email, password, birthday, gender );
		
		System.out.println("Signed up!");
	}
	
	private void getLoggedInUserInfo() {
    	UserProfilePersistence up = new UserProfilePersistence("src/main/java/persistence/userProfiles.txt");
    	up.readFile("src/main/java/persistence/userProfiles.txt");
    	user = up.loggedin;
    	firstName = user.get(1);
    	lastName = user.get(2);
    	birthday = user.get(4);
    	gender = user.get(6).charAt(0);
    	email = user.get(8);
    	password = user.get(10);
    	
    }
}
