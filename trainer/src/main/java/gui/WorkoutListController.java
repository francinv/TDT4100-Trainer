package gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		System.out.println(workoutnames);
		
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
		System.out.println("Her kommer alle workouts.");
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
	
}

