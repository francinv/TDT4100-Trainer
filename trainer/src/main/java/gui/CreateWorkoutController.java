package gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import core.Workout;
import core.Userprofile;
import persistence.UserProfilePersistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import persistence.allWorkoutsPersistence;

public class CreateWorkoutController {
	
	/*
	 * 
	 * VARIABLES
	 * 
	 */
	
	//GUI - VARIABLES
	static Stage stage;
	Alert a = new Alert(AlertType.NONE);
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
    
    //USER-VARIABLES
    private Userprofile thisuser = new Userprofile();
    private ArrayList<String> user = new ArrayList<String>();
    
    //WORKOUT-VARIABLES
    private ArrayList<String> muscles = new ArrayList<String>();
    private ArrayList<String> category = new ArrayList<String>();
    private Workout workout = new Workout();
    
    //PERSISTENCE
    private String fileup = "src/main/java/persistence/userProfiles.txt";
    private String workoutfile = "src/main/java/persistence/allworkouts.txt";
    
    private allWorkoutsPersistence workouts = new allWorkoutsPersistence(workoutfile);
    private UserProfilePersistence up = new UserProfilePersistence(thisuser,fileup);
    
    /*
     * 
     * METHODS
     * 
     */
    
    //FXML-METHODS
    
    /**
     * Method used when "Create Workout" button is clicked
     * This method get user information so that created by field in txt-file is correct.
     * After this the workout is added to text file.
     * @param event
     */
    @FXML
    private void CreateWorkout(ActionEvent event) {
    	
    	getLoggedInUserInfo();
    	workout.setCreatedBy(thisuser);
    	workout.setName(workoutCreateName.getText());
    	workout.setExercises(Integer.parseInt(NumberOfExercisesCreate.getText()));
    	workout.setDescription(description_field.getText());
    	getMuscles();
    	workout.setMuscles(muscles);
    	workout.setWhen(whenWorkoutCreate.getValue());
    	workout.setType(type_field.getValue());
    	getCategory();
    	workout.setCategory(category);
    	workout.setDuration(duration_field.getValue());
    	
    	thisuser.addMyWorkouts(workout);
    	try {
    		workouts.addWorkoutToFile(workout);
    		up.writeFile();
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    		a.setAlertType(AlertType.ERROR);
    		a.setContentText("One or more fields have error.");
    		a.showAndWait();
    		throw new IllegalStateException("Something went wrong when trying to write file.");
    		
    	}
    	Success();
    	Load();
    	
    }
    
    /*
     * This method is used to get the logged-in user. We are doing this by reading the file that are written when
     * user logs in. After this we are creating a user profile. 
     */
    private void getLoggedInUserInfo() {
    	try {
    		up.readFile(fileup);
    	} catch (Exception e){
    		e.printStackTrace();
    		a.setAlertType(AlertType.ERROR);
    		a.setContentText("Something went wrong when trying to get user information");
    		a.showAndWait();
    		throw new IllegalStateException("Something went wrong when trying to get user information.");
    		
    	}
    	
    	user = up.loggedin;
    	thisuser.setFirstName(user.get(1));
    	thisuser.setLastName(user.get(2));
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthday = LocalDate.parse(user.get(4), formatter);
		thisuser.setBirthday(birthday);
		thisuser.setGender(user.get(6).charAt(0));
		thisuser.setEmail(user.get(8));
		thisuser.setPassword(user.get(10));
		
    }
    
    /*
     * This method is used to check what checkboxes are clicked.
     * If a checkbox is clicked this method will add that to a list called muscles.
     */
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
	}
    
    /*
     * This method is used to check what checkboxes are clicked.
     * If a checkbox is clicked this method will add that to a list called category.
     */
	private void getCategory() {
    	if(push_radio.isSelected()) {
    		category.add("Push");
    	} 
    	if(pull_radio.isSelected()) {
    		category.add("Pull");
    	} 
    	if(legs_radio.isSelected()) {
    		category.add("Legs");
    	} 
    	if(upper_radio.isSelected()) {
    		category.add("Upper");
    	} 
    	if(lower_radio.isSelected()) {
    		category.add("Lower");
    	} 
    	if(full_radio.isSelected()) {
    		category.add("Full");
    	} 
    	if(cardio_radio.isSelected()) {
    		category.add("Cardio");
    	} 
    }

	/*
	 * Method used to success alert.
	 */
	private void Success() {
		a.setAlertType(AlertType.INFORMATION);
		a.setContentText("Workout created");
		a.showAndWait();
	}
	
	/*
	 * Method used to load next scene and close current scene.
	 */
	private void Load() {
		
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
			throw new IllegalStateException("Something went wrong when trying to load new scene.");
		}
	}
}

