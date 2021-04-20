package gui;

import java.util.ArrayList;
import java.util.Arrays;
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
import persistence.UserProfilePersistence;
import persistence.UsersPersistence;
import persistence.WorkoutPersistence;
import persistence.allWorkoutsPersistence;

public class WorkoutListController {

	@FXML private Label titleworkout;
	@FXML private Button log_out;
	@FXML private Button create_workout;
	@FXML private Button ProfileButton;
	@FXML private ListView<String> listview_workouts;
	@FXML private ComboBox<String> filter_type;
	
	private ObservableList<String> workoutslist = FXCollections.observableArrayList();
	private ObservableList<String> workoutnames = FXCollections.observableArrayList();
	private List<String> subbers = new ArrayList<String>();
	private ArrayList<String> user = new ArrayList<String>();
	private String firstName;
	private String lastName;
	private String mail;
	private String birthday;
	private char gender;
	private String password;
	private List<String> myworkouts = new ArrayList<String>();
	private allWorkoutsPersistence wp = new allWorkoutsPersistence("src/main/java/persistence/allworkouts.txt");
	
	@FXML
	public void initialize() {
		populateList();
		getList();
	}

	private void populateList() {
		listview_workouts.setItems(workoutnames);
		
	}
	
	private void getList() {
		wp.readFile("src/main/java/persistence/allworkouts.txt");
		workoutslist = wp.allWorkouts;
		String text = "Workoutname:";
		
		
		List<Integer> listIndex = new ArrayList();
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
	
	@FXML
	private void WorkoutDetail(MouseEvent arg0) {
		getWorkout();
		loadWorkoutDetail();
		
		
	}
	
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

	@FXML
	private void filter(ActionEvent event) {
		String filterToken = filter_type.getValue();
		
		List<Integer> listIndex = new ArrayList();
		for (int i = 0; i < workoutslist.size(); i++) {
			String element = workoutslist.get(i);
			
			if (filterToken.equals(element)) {
				listIndex.add(i);
			}
		}
		Collections.sort(listIndex);
		System.out.println(listIndex);
		
		workoutnames.clear();
		for (int x = 0; x < listIndex.size(); x++) {
			int element = listIndex.get(x);
			workoutnames.add(workoutslist.get(element-10));
		}
		populateList();
		System.out.println(workoutnames);
	}
	
	private void getWorkout() {
		String name = listview_workouts.getSelectionModel().getSelectedItem();
		
		int index = workoutslist.indexOf(name);
		String ID = workoutslist.get(index + 3 );
		firstName = workoutslist.get(index + 5);
		System.out.println(workoutslist);
		getUserOfWorkout();
		Userprofile user = new Userprofile(firstName, lastName, mail, password, birthday, gender);
		
		String when = workoutslist.get(index + 8);
		String duration = workoutslist.get(index + 10);
		String type = workoutslist.get(index + 12);
		System.out.println(index);
		List<String> category = new ArrayList();
		for (int i = index + 14  ; i < workoutslist.size(); i++) {
			String element = workoutslist.get(i);
			if(element.contains("Muscles")){
				break;
			}else {
				category.add(element);
				index = workoutslist.indexOf(element);
			}
		}
		List<String> muscles = new ArrayList();
		for (int y = index + 3 ; y < workoutslist.size(); y++) {
			String element = workoutslist.get(y);
			if(element.contains("Number")) {
				break;
			} else {
				muscles.add(element);
				index = workoutslist.indexOf(element);
			}
		}

		int amountOfExercises = Integer.parseInt(workoutslist.get(index + 4 ));
		
		List<String> desclist = new ArrayList();
		
		for (int z = index + 6 ; z < workoutslist.size(); z++) {
			String element = workoutslist.get(z);
			if(element.contains("Users")) {
				break;
			} else {
				desclist.add(element);
				index = workoutslist.indexOf(element);
			}
		}
		
		String description = desclist.toString();
		List<String> subbers = new ArrayList();
		
		for (int c = index + 13 ; c < workoutslist.size(); c++) {
			String element = workoutslist.get(c);
			if(element.contains("]")) {
				break;
			} else {
				subbers.add(element);
			}
		}

		
		
		Workout workout = new Workout(user, name, amountOfExercises, muscles, when, type, category, duration, description, subbers );
		
		WorkoutPersistence w = new WorkoutPersistence(workout, "src/main/java/persistence/workout.txt");
		w.writeFile();
		
	}

	private void getUserOfWorkout() {
		UsersPersistence up = new UsersPersistence("src/main/java/persistence/allUsers.txt");
		up.readFile("src/main/java/persistence/allUsers.txt");
		user = up.list;
		System.out.println(user);
		int index = user.indexOf(firstName);
		System.out.println(index);
		lastName = user.get(index +1);
		birthday = user.get(index + 3);
		gender = user.get(index + 5).charAt(0);
		mail = user.get(index + 7);
		password = user.get(index + 9);
		
		
		for (int i = index + 11; i < user.size(); i++) {
			String element = user.get(i);
			if(element.contains("]")) {
				break;
			} else {
				user.add(element);
			}
		}
	}
}

