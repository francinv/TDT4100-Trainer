package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Userprofile{

	//Variables
	private String firstName;
	private String lastName;
	private LocalDate birthday;
	private LocalDate today = LocalDate.now();
	private char gender;
	private List<Character>legalGenders = Arrays.asList('F','M');
	private String email;
	private String password;
	private Collection<Workout>myWorkouts = new ArrayList<Workout>();
	
	
	//Constructors
	public Userprofile() {
		
	}
	public Userprofile(String firstName, String lastName, String email, String password,
		String birthday, char gender) {	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.birthday = LocalDate.parse(birthday, formatter);
		this.gender = gender;
		 
	}
	
	public Userprofile(String firstName, String lastName, String email, String password,
			String birthday, char gender, Collection<Workout> myWorkout) {
		this(firstName, lastName, email, password, birthday, gender);
		this.myWorkouts=myWorkout;
	}
	
	
	//Getters and setters
	public String getName() {
		return firstName + " " + lastName;
	}
	
	public String getfirstName() {
		return firstName;
	}
	
	public String getlastName() {
		return lastName;
	}
	public void setFirstName(String firstname) {
		if(!isValidName(firstname)) {
			throw new IllegalArgumentException("Ugyldig navn");
		}
		this.firstName=firstname;
	}
	
	public void setLastName(String lastName) {
		if(!isValidName(lastName)) {
			throw new IllegalArgumentException("Ugyldig navn");
		}
		this.lastName=lastName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if (isValidEmail(email)) {
			this.email=email;
		}
		else {
			throw new IllegalArgumentException("Ugyldig e-post");
		}
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		if (password.length()>8 && password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")) {
			this.password = password;
		}
		else {
			throw new IllegalArgumentException("Ugyldigformat");
		}
	}
	
	public char getGender() {
		return gender;
	}
	
	public void setGender(char gender) {
		if (legalGenders.contains(gender)) {
			this.gender = gender;
		}
		else {
			throw new IllegalArgumentException("Ugyldig kjønn");
		}
	}	
	
	public LocalDate getBirthday() {
		return birthday;
	}
	
	public void setBirthday(LocalDate birthday) {
		if (birthday.isAfter(today)) {
			throw new IllegalArgumentException("Ugyldig fødselsdag");
		}
		this.birthday=birthday;
	}

	public Collection<Workout> getMyWorkouts() {
		return myWorkouts;
	}

	
	//Method for adding workout
	public void addMyWorkouts(Workout workout) {
		myWorkouts.add(workout);
	}
	
	
	//Method for deleting workout
	public void deleteWorkout(Workout workout) {
		myWorkouts.remove(workout);
	}
	
	
	//Validation methods for setters
	private boolean isValidName(String name) {
		if (name.length()>=1 && name.matches("[a-zA-Z]+")) {
			return true;
		}
		return false;
	}
	
	private boolean isValidEmail(String email) {
		if (email.contains("@") && !email.substring(0, 1).equals("@")) {
			String[] parts = email.split("@");
			if(parts.length==2 ) {
				if (parts[1].contains(".") && !parts[1].substring(0,1).equals(".")
						&& !parts[0].contains(".")) {
					String[] parts2 = parts[1].split("\\.");
					if(parts2.length==2) {
						return true;
					}	
				}
			}
		}
		return false;
	}
	
	
	//toString method
	//This method defines how the persistnece class writes to file
	public String toString() {
		List<String> workoutNames = new ArrayList<String>();
		for (Workout workout : myWorkouts) {
			workoutNames.add(workout.getName());
		}
		return "Navn: " + firstName +" " +lastName + "\n" 
				+ "birthday: "+ birthday + "\n" 
				+ "gender: " + gender + "\n"
				+ "email: " + email + "\n"
				+ "password: " + password + "\n" 
				+ "Workouts: " + workoutNames + "\n\n";
	}
	
	
	
}

