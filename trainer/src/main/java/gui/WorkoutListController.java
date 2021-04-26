package gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import core.Userprofile;
import core.Workout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import persistence.UsersPersistence;
import persistence.WorkoutPersistence;
import persistence.allWorkoutsPersistence;

public class WorkoutListController {

	/*
	 * 
	 * VARIABLES
	 * 
	 */
	
	//GUI
	@FXML private Label titleworkout;
	@FXML private Button log_out;
	@FXML private Button create_workout;
	@FXML private Button ProfileButton;
	@FXML private ListView<String> listview_workouts;
	@FXML private ComboBox<String> filter_type;
	
	//USER-VARIABLES
	private ArrayList<String> usersList = new ArrayList<String>();
	private Userprofile user = new Userprofile();
	
	//WORKOUT-VARIABLES
	private ObservableList<String> workoutslist = FXCollections.observableArrayList();
	private ObservableList<String> workoutnames = FXCollections.observableArrayList();
	private Workout workout = new Workout();
	
	//PERSISTENCE
	String allWorkoutsFile = "src/main/java/persistence/allworkouts.txt";
	String UsersFile = "src/main/java/persistence/allUsers.txt";
	String workoutFile = "src/main/java/persistence/workout.txt";
	private allWorkoutsPersistence wp = new allWorkoutsPersistence(allWorkoutsFile);
	private UsersPersistence up = new UsersPersistence(UsersFile);
	private WorkoutPersistence w = new WorkoutPersistence(workout, workoutFile);

	/*
	 * 
	 * METHODS
	 * 
	 */
	
	
	//FXML - METHODS
	
	/*
	 * This method is initialized on load of scene.
	 * Two methods are called: populateList() and getList().
	 */
	@FXML
	public void initialize() {
		populateList();
		getList();
	}
	
	/**
	 * This method is called when button "Log out" are clicked. Closes current scene and opens Login scene.
	 * @param event
	 */
	@FXML
	private void LogoutAction(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("login.fxml"));
		try {
			Parent root = (Parent) fxmlLoader.load();
			Stage stg = new Stage();
			stg.setScene(new Scene(root));
			stg.show();
			Stage stage = (Stage) log_out.getScene().getWindow();
		    stage.close();
 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is called when button "Create Workout" are clicked. Closes current scene and opens scene where user can create 
	 * workout.
	 * @param event
	 */
	@FXML
	private void CreateWorkoutAction(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("createnewworkout.fxml"));
		try {
			Parent root = (Parent) fxmlLoader.load();
			Stage stg = new Stage();
			stg.setScene(new Scene(root));
			stg.show();
			Stage stage = (Stage) create_workout.getScene().getWindow();
		    stage.close();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is called when button "Profile" are clicked. Closes current scene and opens profile of logged in user.
	 * @param event
	 */
	@FXML
	private void GoToProfile(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("profile.fxml"));
		try {
			Parent root = (Parent) fxmlLoader.load();
			Stage stg = new Stage();
			stg.setScene(new Scene(root));
			stg.show();
			Stage stage = (Stage) ProfileButton.getScene().getWindow();
		    stage.close();

		} catch(Exception e) {
			e.printStackTrace();
		
		}
	}
	
	/**
	 * If workout are clicked in listview, this function will run getWorkout() and if successfull loadWorkoutDetail().
	 * @param arg0
	 */
	@FXML
	private void WorkoutDetail(MouseEvent arg0) {
		getWorkout();
		loadWorkoutDetail();
	}
	
	/**
	 * Method used to filter workouts. Will be called when an action happens. 
	 * This method will filter on given value from ComboBox in user interface.
	 * @param event
	 */
	@FXML
	private void filter(ActionEvent event) {
		String filterToken = filter_type.getValue();
		
		//Iterate through workoutslist and get all indexes that matches given filterToken.
		List<Integer> listIndex = new ArrayList<Integer>();
		for (int i = 0; i < workoutslist.size(); i++) {
			String element = workoutslist.get(i);
			
			if (filterToken.equals(element)) {
				listIndex.add(i);
			}
		}
		Collections.sort(listIndex);
		
		workoutnames.clear();
		
		//Iterate through list of indexes and adds correct element to workoutnames.
		for (int x = 0; x < listIndex.size(); x++) {
			int element = listIndex.get(x);
			workoutnames.add(workoutslist.get(element-11));
		}
		
		//Calling populateList after the information are filtered.
		populateList();
	}
	
	//METHODS
	
	/*
	 * Method to get workout when clicked a chosen workout. 
	 */
	private void getWorkout() {
		String name = listview_workouts.getSelectionModel().getSelectedItem();
		
		int index = workoutslist.indexOf(name);
		workout.setName(name);
		workout.setUniqueID(workoutslist.get(index + 2));
		user.setFirstName(workoutslist.get(index + 4));


		getUserOfWorkout(); // Calling this to get user of workout.
		
		workout.setCreatedBy(user);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate when = LocalDate.parse(workoutslist.get(index+7), formatter);
		workout.setWhen(when);
		workout.setDuration(workoutslist.get(index + 9));
		workout.setType(workoutslist.get(index + 11));
		
		ArrayList<String> category = new ArrayList<String>();
		for (int i = index + 13  ; i < workoutslist.size(); i++) {
			String element = workoutslist.get(i);
			if(element.contains("Muscles")){
				break;
			}else {
				category.add(element);
				index = workoutslist.indexOf(element);
			}
		}

		workout.setCategory(category);
		
		ArrayList<String> muscles = new ArrayList<String>();
		for (int y = index + 2 ; y < workoutslist.size(); y++) {
			String element = workoutslist.get(y);
			if(element.contains("Exercises:")) {
				break;
			} else {
				muscles.add(element);
				index = workoutslist.indexOf(element);
			}
		}
		workout.setMuscles(muscles);
		
		
		int amountOfExercises = Integer.parseInt(workoutslist.get(index + 2 ));
		workout.setExercises(amountOfExercises);
		
		ArrayList<String> desclist = new ArrayList<String>();
		
		for (int z = index + 4 ; z < workoutslist.size(); z++) {
			String element = workoutslist.get(z);
			if(element.contains("Users")) {
				break;
			} else {
				desclist.add(element);
				index = workoutslist.indexOf(element);
			}
		}
		
		String description = desclist.toString();
		workout.setDescription(description);
		
		w.writeFile();
		
	}
	
	/*
	 * Using list from getList() to populate listview in user interface.
	 */
	private void populateList() {
		listview_workouts.setItems(workoutnames);
		
	}
	
	/*
	 * Method for collecting information from persistence and saving the names to a list.
	 */
	private void getList() {
		wp.readFile(allWorkoutsFile);
		workoutslist = wp.allWorkouts;
		String text = "Workoutname:";
		
		
		List<Integer> listIndex = new ArrayList<Integer>();
		for (int i = 0; i < workoutslist.size(); i++) {
			String element = workoutslist.get(i);
			
			if (text.equals(element)) {
				listIndex.add(i+1);
			}
		}
		listIndex.add(1);
		Collections.sort(listIndex);
		
		for (int x = 0; x < listIndex.size(); x++) {
			int element = listIndex.get(x);
			workoutnames.add(workoutslist.get(element));
		}
		
	}
	
	
	/*
	 * Method to close current scene and open scene of clicked workout.
	 */
	private void loadWorkoutDetail() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("workout_detail.fxml"));
		try {
			Parent root = (Parent) fxmlLoader.load();
			Stage stg = new Stage();
			stg.setScene(new Scene(root));
			stg.show();
			Stage stage = (Stage) listview_workouts.getScene().getWindow();
		    stage.close();

		} catch(Exception e) {
			e.printStackTrace();
		
		}
	}

	

	private void getUserOfWorkout() {
		/*
		 * First we read file to get all the users, then we use firstname to get the other information
		 * We are using both UsersPersistence-class and Userprofile class here.
		 */
		up.readFile(UsersFile);
		usersList = up.list;
		
		int index = usersList.indexOf(user.getfirstName());
		System.out.println(usersList.get(index+1));
		user.setLastName(usersList.get(index+1));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthday = LocalDate.parse(usersList.get(index + 3), formatter);
		user.setBirthday(birthday);
		user.setGender(usersList.get(index+5).charAt(0));
		user.setEmail(usersList.get(index + 7));
		user.setPassword(usersList.get(index + 9));
		
	}
}

