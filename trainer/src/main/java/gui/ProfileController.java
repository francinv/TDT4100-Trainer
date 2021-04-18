package gui;

import java.util.ArrayList;

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

public class ProfileController {
	
	@FXML
	private Label profile_title;

    @FXML
    private Button BackButton;

    @FXML
    private Label Email_Profile_field;

    @FXML
    private Label Firstname_Profile_field;

    @FXML
    private Label Lastname_Profile_field;

    @FXML
    private ListView<String> transactionListView;
    
    private UserProfilePersistence up = new UserProfilePersistence("src/main/java/persistence/userProfiles.txt");
    private ArrayList<String> user = new ArrayList<String>();
    
    @FXML
    public void initialize() {
    	getUserinfo();
    }
    
    private void getUserinfo() {
    	up.readFile("src/main/java/persistence/userProfiles.txt");
    	user = up.loggedin;
    	String firstName = user.get(1);
    	String lastName = user.get(2);
    	String email = user.get(8);
    	profile_title.setText("Hello " + firstName +" " +  lastName + "!" );
    	Email_Profile_field.setText(email);
    	
    }

    @FXML
    void handleTransactionListClick(MouseEvent event) {
    	System.out.println("Her kommer mine workouts");
    }

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

}
