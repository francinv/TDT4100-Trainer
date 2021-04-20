package gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Trainer.TrainerApp;
import core.Workout;
import core.Userprofile;
import persistence.UserProfilePersistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import persistence.WorkoutPersistence;
import persistence.allWorkoutsPersistence;

public class CreateWorkoutController {
	
	static Stage stage;

    @FXML private Label title_create;
    @FXML private Label name_label;
	@FXML private Label exercises_label;
    @FXML private Label muscles_label;
	@FXML private Label type_label;
	@FXML private Label category_label;
	@FXML private Label duration_label;

	@FXML private TextField workoutCreateName;
    @FXML private TextField NumberOfExercisesCreate;
    @FXML private DatePicker whenWorkoutCreate;    
    @FXML private ComboBox<String> type_field;
    @FXML private ComboBox<String> duration_field;
    @FXML private Button create_button;
    @FXML private TextArea description_field;
    @FXML private CheckBox chest_radio;
    @FXML private CheckBox triceps_radio;
    @FXML private CheckBox back_radio;
    @FXML private CheckBox biceps_radio;
    @FXML private CheckBox legs_box;
    @FXML private CheckBox glutes_box;
    @FXML private CheckBox core_box;
    @FXML private CheckBox other_box;
    @FXML private CheckBox shoulder_box;
    @FXML private CheckBox push_radio;
    @FXML private CheckBox pull_radio;
    @FXML private CheckBox legs_radio;
    @FXML private CheckBox upper_radio;
    @FXML private CheckBox lower_radio;
    @FXML private CheckBox full_radio;
    @FXML private CheckBox cardio_radio;
    
    private List<String> muscles = new ArrayList<String>();
    private List<String> category = new ArrayList<String>();
    private ArrayList<String> user = new ArrayList<String>();
    private Collection<Workout> allWorkouts = new ArrayList<Workout>();
    
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthday;
    private char gender;
    
    
    @FXML
    void CreateWorkout(ActionEvent event) {
    	getLoggedInUserInfo();
    	Userprofile thisuser = new Userprofile(firstName, lastName, email, password, birthday, gender);
    	System.out.println(thisuser);
    	String name = workoutCreateName.getText();
    	int number_of_exercises = Integer.parseInt(NumberOfExercisesCreate.getText());
    	String description = description_field.getText();
    	getMuscles();
    	String when = whenWorkoutCreate.getValue().toString();
    	String type = type_field.getValue();
    	getCategory();
    	String duration = duration_field.getValue();
    	
    	Workout workout = new Workout(thisuser, name, number_of_exercises, muscles, when, type, category,duration, description);
    	
    	UserProfilePersistence up = new UserProfilePersistence(thisuser, "src/main/java/persistence/userProfiles.txt");
    	String file = "src/main/java/persistence/allworkout.txt";
    	allWorkoutsPersistence workouts = new allWorkoutsPersistence(file, allWorkouts);
    	allWorkouts.add(workout);
    	thisuser.addMyWorkouts(workout);
    	try {
    		workouts.writeFile();
    		up.writeFile();
    		
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	Success();
    	Load();
    	
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
    
    private void getMuscles() {
    	if (chest_radio.isSelected()) {
    		muscles.add("Chest");
    	}
    	if (triceps_radio.isSelected()) {
    		muscles.add("Triceps");
    	} 
    	if(back_radio.isSelected()) {
    		muscles.add("Back");
    	} 
    	if(biceps_radio.isSelected()) {
    		muscles.add("Biceps");    		
    	} 
    	if(shoulder_box.isSelected()) {
    		muscles.add("Shoulder");    		
    	} 
    	if(legs_box.isSelected()) {
    		muscles.add("Legs");
    	} 
    	if(glutes_box.isSelected()) {
    		muscles.add("Glutes");
    	} 
     	if(core_box.isSelected()) {
    		muscles.add("Core");
    	} 
     	if(other_box.isSelected()) {
    		muscles.add("Other");
    	} 
    	System.out.println(muscles);
	}

	private void getCategory() {
    	if(push_radio.isSelected()) {
    		category.add("Push");
    	} else {
    		System.out.println("Push not selected");
    	}
    	if(pull_radio.isSelected()) {
    		category.add("Pull");
    	} else {
    		System.out.println("Pull not selected");
    	}
    	if(legs_radio.isSelected()) {
    		category.add("Legs");
    	} else {
    		System.out.println("Legs not selected");
    	}
    	if(upper_radio.isSelected()) {
    		category.add("Upper");
    	} else {
    		System.out.println("Upper not selected");
    	}
    	if(lower_radio.isSelected()) {
    		category.add("Lower");
    	} else {
    		System.out.println("Lower not selected");
    	}
    	if(full_radio.isSelected()) {
    		category.add("Full");
    	} else {
    		System.out.println("Full not selected");
    	}
    	if(cardio_radio.isSelected()) {
    		category.add("Cardio");
    	} else {
    		System.out.println("Cardio not selected");
    	}
    	
    	System.out.println(category);
    }

	private void Success() {
		title_create.setText("Workout created!");
	}
	
	void Load() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Workouts.fxml"));
		try {
			Parent root = (Parent) fxmlLoader.load();
			Stage stg = new Stage();
			stg.setScene(new Scene(root));
			stg.show();
			Stage stage = (Stage) create_button.getScene().getWindow();
		    stage.close();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

