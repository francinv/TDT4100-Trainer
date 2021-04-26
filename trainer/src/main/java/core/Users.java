package core;

import java.util.ArrayList;
import java.util.List;

public class Users {
	
	//Variables
	private List<Userprofile> allUsers = new ArrayList<Userprofile>();
	private Userprofile user;
	
	//Constructor
	public Users(Userprofile user) {
		allUsers.add(user);
	}
	public Users() {
		
	}
	//Method for adding user to list containing all users
	public void addUser(Userprofile user) {
		if (allUsers.contains(user)){
			throw new IllegalArgumentException("User allready exsist");
		}
		else {
			allUsers.add(user);
		}
	}
	
	//Method for adding all users from the list
	public List<Userprofile> getAllUserProfiles(){
		return allUsers;
	}
	
	//To sting method to get just the names of all users
	public String toString() {
		return user.getName();
	}

}
