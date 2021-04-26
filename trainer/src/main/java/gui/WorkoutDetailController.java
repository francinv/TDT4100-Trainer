package gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import core.Userprofile;
import core.Workout;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import persistence.UserProfilePersistence;
import persistence.UsersPersistence;
import persistence.WorkoutPersistence;
import persistence.allWorkoutsPersistence;

public class WorkoutDetailController {
	
	/*
	 * 
	 *VARIABLES
	 * 
	 */

	//GUI - VARIABLES
	
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
    Alert a = new Alert(AlertType.NONE);
    
    //USER - VARIABLES
    private ArrayList<String> thisuser = new ArrayList<String>();
    private List<String> thissubbers = new ArrayList<String>();
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthday;
    private char gender;
    private Userprofile userOfworkout = new Userprofile();
    private Userprofile sub = new Userprofile();
    
    //WORKOUT - VARIABLES
    private ArrayList<String> thisworkout = new ArrayList<String>();
    private ArrayList<String> categories = new ArrayList<String>();
    private ArrayList<String> muscles = new ArrayList<String>();
    private int amountOfExercises;
    private String description;
    private Collection<String> subbs = new ArrayList<String>();
    private List<String> subbers = new ArrayList<String>();
    private List<String> workouts = new ArrayList<String>();
    private Workout workout = new Workout();
    
    //PERSISTENCE
    private String userfile = "src/main/java/persistence/userProfiles.txt";
    private String usersfile = "src/main/java/persistence/allUsers.txt";
    private String workoutfile = "src/main/java/persistence/workout.txt";
    private String allWorkoutsfile = "src/main/java/persistence/allworkouts.txt";
    private WorkoutPersistence wp = new WorkoutPersistence();
    private allWorkoutsPersistence w = new allWorkoutsPersistence(allWorkoutsfile);
    private UserProfilePersistence up = new UserProfilePersistence(userfile);
    private UsersPersistence u = new UsersPersistence(usersfile);
    

    /*
     * 
     * METHODS
     * 
     */
    
    //FXML - METHODS
    
    /*
     * This method is called when the scene is loaded.
     */
    @FXML
    void initialize() {
    	getWorkout();
    	populateFields();
    }
    
    /**
	 * Method to load scene if "Back"-button is clicked. 
	 * This will load main page. 
	 * @param event
	 */
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
    
	/**
	 * Method to load profile scene if "Profile" is clicked. 
	 * @param event
	 */
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
    
	/**
	 * If "Sign-up" button is clicked this method will set a sub and then add that to allWorkouts.txt;
	 * If the user that are logged in tries to sub it should throw an IllegalArgumentException.
	 * @param event
	 */
	@FXML 
	private void signUpWorkout(ActionEvent event) {
		getLoggedInUserInfo();
		sub = new Userprofile(firstName, lastName, email, password, birthday, gender);
		if(!(sub.getEmail() == userOfworkout.getEmail())) {
			String ID = workout.getName();
			w.findSubbs(ID, allWorkoutsfile);
			thissubbers = w.subbs;
			String subb = sub.getName();
			thissubbers.add(subb);
			w.updateFile(allWorkoutsfile, ID, thissubbers);
			a.setAlertType(AlertType.INFORMATION);
			a.setContentText("You have subbed to this workout!");
			a.showAndWait();
		} else {
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Du kan ikke melde deg på din egen workout.");
			a.showAndWait();
			throw new IllegalArgumentException("Du kan ikke melde deg på din egen workout");
		}
	}
	
	//METHODS

    /*
     * This method is one of the methods that is called when the scene is loaded.
     * This method reads the workout.txt file that was created when an element was clicked in the listview.
     * After the workout is set from the text file we use populateFields().
     */
	private void getWorkout() {
		wp.readFile(workoutfile);
		thisworkout = wp.workoutlist;
		
		workout.setName(thisworkout.get(1));
		getUserofWorkout(); //Method to get user of this.workout
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate when = LocalDate.parse(thisworkout.get(8), formatter);
		workout.setWhen(when);
		workout.setDuration(thisworkout.get(10));
		workout.setType(thisworkout.get(12));
		
		
		int index = 0;
		for (int i = 14; i < thisworkout.size(); i ++) {
			String element = thisworkout.get(i);
			if (element.contains("Muscles")) {
				break;
			} else {
				categories.add(element);
				index = thisworkout.indexOf(element);
			}
		}
		workout.setCategory(categories);
		
		for (int x = index + 3; x < thisworkout.size(); x ++) {
			String element = thisworkout.get(x);
			if(element.contains("Exercises:")) {
				break;
			} else {
				muscles.add(element);
				index = thisworkout.indexOf(element);
			}
		}
		workout.setMuscles(muscles);
		amountOfExercises = Integer.parseInt((thisworkout.get(index + 2)));
		workout.setExercises(amountOfExercises);
		
		List<String> desc = new ArrayList<String>();
		for (int z = index + 4; z < thisworkout.size(); z ++) {
			String element = thisworkout.get(z);
			if(element.contains("Users")) {
				break;
			} else {
				desc.add(element);
				index = thisworkout.indexOf(element);
			}
		}
		workout.setDescription(desc.toString());
		
		
		for (int y = index + 5; y < thisworkout.size(); y++) {
			String element = thisworkout.get(y);
			if(element.contains("]")) {
				break;
			} else {
				subbers.add(element);
			}
		}
		
	}

	/*
	 * Method to determine and set the user of current workout. 
	 */
	private void getUserofWorkout() {
		userOfworkout.setFirstName(thisworkout.get(5));
		u.readFile(usersfile);
		List<String> thisuser = new ArrayList<String>();
		thisuser = u.list;
		int index = thisuser.indexOf(userOfworkout.getfirstName());
		userOfworkout.setLastName(thisuser.get(index+1));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthday = LocalDate.parse(thisuser.get(index+3), formatter);
		userOfworkout.setBirthday(birthday);
		
		userOfworkout.setGender(thisuser.get(index+5).charAt(0));
		userOfworkout.setEmail(thisuser.get(index + 7));
		userOfworkout.setPassword(thisuser.get(index + 9));
		
	}

	/*
	 * After getWorkout() is finished this method will be called. 
	 * This method should populate all the fields from this.workout
	 */
	private void populateFields() {
		workout_name.setText(workout.getName());
		createdBy.setText(userOfworkout.getName());
		date_field.setText(workout.getWhen().toString());
		duration_field.setText(workout.getDuration());
		type_field.setText(workout.getType());
		category_field.setText(workout.getCategory().toString());
		muscles_field.setText(workout.getMuscles().toString());
		exercises_field.setText(String.valueOf(workout.getExercises()));
		description_field.setText(workout.getDescription());
		
	}
	
	/*
	 * Method to get logged-in user. 
	 */
	private void getLoggedInUserInfo() {
    	up.readFile(userfile);
    	thisuser = up.loggedin;
    	
    	firstName = thisuser.get(1);
    	lastName = thisuser.get(2);
    	birthday = thisuser.get(4);
    	gender = thisuser.get(6).charAt(0);
    	email = thisuser.get(8);
    	password = thisuser.get(10);
    	
    }
}
