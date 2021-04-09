package core;

import java.util.ArrayList;
import java.util.List;

public class Users {
	
	private List<Userprofile> allUsers = new ArrayList<Userprofile>();
	private Userprofile user;
	
	public Users(Userprofile user) {
		allUsers.add(user);
	}
	
	public void addUser(Userprofile user) {
		if (allUsers.contains(user)){
			throw new IllegalArgumentException("User allready exsist");
		}
		else {
			allUsers.add(user);
		}
	}
	
	public List<Userprofile> getAllUserProfiles(){
		return allUsers;
	}
	
	public String toString() {
		return user.getName();
	}

}
