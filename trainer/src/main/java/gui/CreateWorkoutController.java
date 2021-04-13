package gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import core.Workout;
import core.Userprofile;
import gui.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import persistence.WorkoutPersistence;

public class CreateWorkoutController {

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

    @FXML
    void CreateWorkout(ActionEvent event) {
    	//Userprofile createdBy = LoginController.usernametext;
    	String name = workoutCreateName.getText();
    	int number_of_exercises = Integer.parseInt(NumberOfExercisesCreate.getText());
    	String description = description_field.getText();
    	getMuscles();
    	LocalDate when = whenWorkoutCreate.getValue();
    	String type = type_field.getValue();
    	getCategory();
    	String duration = duration_field.getValue();
    	
    	Workout workout = new Workout( name, number_of_exercises, muscles, when, type, category,duration, description);
    	WorkoutPersistence workouts = new WorkoutPersistence (workout, "src/main/java/persistence/workout.txt" );
    	
    	try {
    		workouts.writeFile();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    private void getMuscles() {
    	if (chest_radio.isSelected()) {
    		muscles.add("Chest");
    	} else {
    		System.out.println("Chest not selected");
    	}
    	if (triceps_radio.isSelected()) {
    		muscles.add("Triceps");
    	} else {
    		System.out.println("Triceps not selected");
    	}
    	if(back_radio.isSelected()) {
    		muscles.add("Back");
    	} else {
    		System.out.println("Back not selected");
    	}
    	if(biceps_radio.isSelected()) {
    		muscles.add("Biceps");    		
    	} else {
    		System.out.println("Biceps not selected");
    	}
    	if(shoulder_box.isSelected()) {
    		muscles.add("Shoulder");    		
    	} else {
    		System.out.println("Shoulder not selected");
    	}
    	if(legs_box.isSelected()) {
    		muscles.add("Legs");
    	} else {
    		System.out.println("Legs not selected");
    	}
    	if(glutes_box.isSelected()) {
    		muscles.add("Glutes");
    	} else {
    		System.out.println("Glutes not selected");
    	}
     	if(core_box.isSelected()) {
    		muscles.add("Core");
    	} else {
    		System.out.println("Core not selected");
    	}
     	if(other_box.isSelected()) {
    		muscles.add("Other");
    	} else {
    		System.out.println("Other not selected");
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

}

