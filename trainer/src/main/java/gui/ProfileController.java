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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import persistence.UserProfilePersistence;
import persistence.UsersPersistence;
import persistence.WorkoutPersistence;
import persistence.allWorkoutsPersistence;

public class ProfileController {
	
	/*
	 * 
	 * VARIABLES
	 * 
	 */
	
	//GUI-VARIABLES
	@FXML private Label profile_title;
    @FXML private Button BackButton;
    @FXML private Label Email_Profile_field;
    @FXML private Label Firstname_Profile_field;
    @FXML private Label Lastname_Profile_field;
    @FXML private ListView<String> transactionListView;
    
    //USER-VARIABLES
    private Userprofile thisuser = new Userprofile();
    private Userprofile userOfWorkout = new Userprofile();
    private ArrayList<String> user = new ArrayList<String>();
    private String firstName;
    
    //WORKOUT-VARIABLES
    private Workout workout = new Workout();
    private ObservableList<String> workoutslist = FXCollections.observableArrayList();
    private ObservableList<String> myworkouts = FXCollections.observableArrayList();
    
    //PERSISTENCE
    private String allworkoutsFile = "src/main/java/persistence/allworkouts.txt";
    private String workoutfile = "src/main/java/persistence/workout.txt";
    private String upFile = "src/main/java/persistence/userProfiles.txt";
    private String uspFile = "src/main/java/persistence/allUsers.txt";
    private UserProfilePersistence up = new UserProfilePersistence(upFile);
    private allWorkoutsPersistence wp = new allWorkoutsPersistence(allworkoutsFile);
    private WorkoutPersistence w = new WorkoutPersistence(workout, workoutfile);
    private UsersPersistence usp = new UsersPersistence(uspFile);
    
    /*
     * 
     * METHODS
     * 
     */
    
    
    //FXML-METHODS
    
    /*
     * This method is to call some methods when the scene is loaded.
     * See further comments before the called methods.
     */
    @FXML
    public void initialize() {
    	getUserinfo();
    	getList();
    	populateList();
    	
    }
    
    /**
     * If the user clicks on of the workouts in the list view this method will be called.
     * First the program will find out what workout it is clicked on and then load a new scene with clicked workout.
     * @param event
     */
    @FXML
    void handleTransactionListClick(MouseEvent event) {
    	getWorkout();
		loadWorkoutDetail();
    }
    
    /**
     * If the "Back" button clicked this method will load new scene and go back to main page.
     * @param event
     */
    @FXML
    void handlebackButton(ActionEvent event) {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Workouts.fxml"));
		try {
			Parent root = (Parent) fxmlLoader.load();
			Stage stg = new Stage();
			stg.setScene(new Scene(root));
			stg.show();
			Stage stage = (Stage) BackButton.getScene().getWindow();
		    stage.close();

		} catch(Exception e) {
			e.printStackTrace();
		
		}
    }
    //METHODS
    
    /*
	 * Reads file that was created when this.Userprofile logged in. 
	 * This method is used to populate fields that should contain information about the user.
	 */
	private void getUserinfo() {
    	up.readFile("src/main/java/persistence/userProfiles.txt");
    	user = up.loggedin;
    	
    	thisuser.setFirstName(user.get(1));
    	thisuser.setLastName(user.get(2));
    	thisuser.setEmail(user.get(8));
    	
    	profile_title.setText("Hello " + thisuser.getfirstName() +" " +  thisuser.getlastName() + "!" );
    	Email_Profile_field.setText(thisuser.getEmail());
    	
    }
	
    /*
     * Method to get information from file and finding all workouts that are created by this.Userprofile 
     * and also what workouts that this.Userprofile are subbed to. 
     */
    private void getList() {
    	wp.readFile(allworkoutsFile);
    	workoutslist = wp.allWorkouts;
    	List<Integer> listIndex = new ArrayList<Integer>();
		for (int i = 0; i < workoutslist.size(); i++) {
			String element = workoutslist.get(i);
			if (thisuser.getfirstName().equals(element) || element.contains("["+thisuser.getfirstName())) {
				listIndex.add(i);
			}

		}
		Collections.sort(listIndex);

		for (int x = 0; x < listIndex.size(); x++) {
			int element = listIndex.get(x);
			if(workoutslist.get(element - 5).equals("[Workoutname:") || workoutslist.get(element - 5).equals("Workoutname:")) {
				myworkouts.add(workoutslist.get(element - 4));
			} 
		}
		wp.findSubbedWorkout(thisuser.getfirstName() + " " + thisuser.getlastName());
		myworkouts.add(wp.workoutsSubbedOn.toString().replaceAll("\\[", "").replaceAll("\\]",""));
	}

    /*
     * Method to populate list-view with content that was added in method: getList().
     */
	private void populateList() {
		transactionListView.setItems(myworkouts);
	}

	/*
	 * This method is used to get the correct workout, and then writing to workout.txt, so that the Workout detail scene
	 * can populate fields from information saved to this file. 
	 */
	private void getWorkout() {
		String name = transactionListView.getSelectionModel().getSelectedItem();
		int index = workoutslist.indexOf(name);
		
		workout.setName(name);
		workout.setUniqueID(workoutslist.get(index+2));
		firstName = workoutslist.get(index + 4);
		getUserOfWorkout(); //Call this method to get the user of clicked workout. 
		
		workout.setCreatedBy(userOfWorkout);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate when = LocalDate.parse(workoutslist.get(index+7), formatter);
		workout.setWhen(when);
		workout.setDuration(workoutslist.get(index+9));
		workout.setType(workoutslist.get(index+11));

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

		workout.setExercises(Integer.parseInt(workoutslist.get(index+2)));
		
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
		
		workout.setDescription(desclist.toString());
		
		w.writeFile();
		
	}

	/*
	 * This method is used to get the user of clicked workout. Checking all users and getting correct user. 
	 */
	private void getUserOfWorkout() {
		usp.readFile("src/main/java/persistence/allUsers.txt");
		user = usp.list;
		int index = user.indexOf(firstName);
		userOfWorkout.setFirstName(firstName);
		userOfWorkout.setLastName(user.get(index+1));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthday = LocalDate.parse(user.get(index + 3), formatter);
		userOfWorkout.setBirthday(birthday);
		userOfWorkout.setGender(user.get(index+5).charAt(0));
		userOfWorkout.setEmail(user.get(index+7));
		userOfWorkout.setPassword(user.get(index+9));

	}

	/*
	 * This method is used to load next scene. 
	 */
    private void loadWorkoutDetail() {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("workout_detail.fxml"));
		try {
			Parent root = (Parent) fxmlLoader.load();
			Stage stg = new Stage();
			stg.setScene(new Scene(root));
			stg.show();
			Stage stage = (Stage) transactionListView.getScene().getWindow();
		    stage.close();

		} catch(Exception e) {
			e.printStackTrace();
		
		}
		
	}

	

	

	

}
