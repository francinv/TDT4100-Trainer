package gui;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import persistence.UserProfilePersistence;
import persistence.UsersPersistence;
import persistence.WorkoutPersistence;
import persistence.allWorkoutsPersistence;

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
    private ArrayList<String> thisuser = new ArrayList<String>();
    
  //Filer
    private String userfile = "src/main/java/persistence/userProfiles.txt";
    private String usersfile = "src/main/java/persistence/allUsers.txt";
    private String workoutfile = "src/main/java/persistence/workout.txt";
    private String allWorkoutsfile = "src/main/java/persistence/allworkouts.txt";
    
    //PERSISTENCE
    private WorkoutPersistence wp = new WorkoutPersistence();
    private allWorkoutsPersistence w = new allWorkoutsPersistence(allWorkoutsfile);
    private UserProfilePersistence up = new UserProfilePersistence(userfile);
    private UsersPersistence u = new UsersPersistence(usersfile);
    
    
    
    //INNLOGGET BRUKER
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthday;
    private char gender;
    
    //WORKOUT VARIABLER
    private String name;
    private Userprofile userworkout;
    private String date;
    private String duration;
    private String type;
    private List<String> categories = new ArrayList<String>();
    private List<String> muscles = new ArrayList<String>();
    private int amountOfExercises;
    private String description;
    private Collection<Userprofile> subbs = new ArrayList<Userprofile>();
    private List<String> subbers = new ArrayList<String>();
    private Workout workout;
    private Userprofile sub;
    private List<String> workouts = new ArrayList<String>();
    
    
    

    @FXML
    void initialize() {
    	getWorkout();
    	setWorkoutConstructor();
    	populateFields();
    }

	private void getWorkout() {
		wp.readFile("src/main/java/persistence/workout.txt");
		thisworkout = wp.workoutlist;
		
		name = thisworkout.get(1);
		getUserofWorkout();
		date = thisworkout.get(9);
		duration = thisworkout.get(11);
		type = thisworkout.get(13);
		
		int index = 0;
		for (int i = 15; i < thisworkout.size(); i ++) {
			String element = thisworkout.get(i);
			if (element.contains("Muscles")) {
				break;
			} else {
				categories.add(element);
				index = thisworkout.indexOf(element);
			}
		}
		
		for (int x = index + 3; x < thisworkout.size(); x ++) {
			String element = thisworkout.get(x);
			if(element.contains("Number")) {
				break;
			} else {
				muscles.add(element);
				index = thisworkout.indexOf(element);
			}
		}
		
		amountOfExercises = Integer.parseInt((thisworkout.get(index + 4)));
		
		List<String> desc = new ArrayList<String>();
		for (int z = index + 6; z < thisworkout.size(); z ++) {
			String element = thisworkout.get(z);
			if(element.contains("Users")) {
				break;
			} else {
				desc.add(element);
				index = thisworkout.indexOf(element);
			}
		}
		description = desc.toString();
		
		
		for (int y = index + 5; y < thisworkout.size(); y++) {
			String element = thisworkout.get(y);
			if(element.contains("]")) {
				break;
			} else {
				subbers.add(element);
			}
		}
		
		
	}

	private void getUserofWorkout() {
		String firstName = thisworkout.get(6);
		u.readFile(usersfile);
		List<String> thisuser = new ArrayList<String>();
		thisuser = u.list;
		int index = thisuser.indexOf(firstName);
		String lastName = thisuser.get(index + 1);
		String birthday = thisuser.get(index + 3);
		char gender = thisuser.get(index + 5).charAt(0);
		String email = thisuser.get(index + 7);
		String password = thisuser.get(index + 9);
		
		List<String> allWorkouts = new ArrayList<String>();
		for (int i = index + 11; i < thisuser.size(); i ++) {
			String element = thisuser.get(i);
			if(element.contains("Navn:")) {
				break;
			} else {
				allWorkouts.add(element);
			}
		}
		
		userworkout = new Userprofile(firstName,lastName, email, password, birthday, gender, allWorkouts );
		
	}

	private void populateFields() {
		workout_name.setText(workout.getName());
		createdBy.setText(userworkout.getName());
		date_field.setText(workout.getWhen().toString());
		duration_field.setText(workout.getDuration());
		type_field.setText(workout.getType());
		category_field.setText(workout.getCategory().toString());
		muscles_field.setText(workout.getMuscles().toString());
		exercises_field.setText(String.valueOf(workout.getExercises()));
		description_field.setText(workout.getDescription());
		
	}
	
	private void setWorkoutConstructor() {
		workout = new Workout( userworkout, name, amountOfExercises, muscles, date, type, categories, duration, description, subbers);
		
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
		sub = new Userprofile(firstName, lastName, email, password, birthday, gender);
		if(!(sub.getEmail() == userworkout.getEmail())) {
			String ID = workout.getUniqueID();
			subbs.add(sub);
			w.updateFile(allWorkoutsfile, ID, subbs);
		}
		
		System.out.println("Signed up!");
	}
	
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
