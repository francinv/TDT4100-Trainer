package gui;

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

public class WorkoutListController {

	@FXML private Label titleworkout;
	@FXML private Button log_out;
	@FXML private Button create_workout;
	@FXML private Button ProfileButton;
	@FXML private ListView<String> listview_workouts;
	@FXML private ComboBox<String> filter_type;
	
	@FXML
	public void initialize() {
		populateList();
	}

	private void populateList() {
		// TODO Auto-generated method stub
		
	}
	
	private void LogoutAction(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Login.fxml"));
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
	
	private void WorkoutDetail(MouseEvent arg0) {
		System.out.println("Her kommer alle workouts.");
	}
	
	private void filter(ActionEvent event)
}

