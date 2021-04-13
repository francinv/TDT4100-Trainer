package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Userprofile {

	private String firstName;
	private String lastName;
	private LocalDate birthday;
	private LocalDate today = LocalDate.now();
	private char gender;
	private List<Character>legalGenders = Arrays.asList('F','M');
	private String email;
	private String password;
	private Collection<Workout>myWorkouts = new ArrayList<Workout>();
	
	public Userprofile(String firstName, String lastName, String email, String password,
		LocalDate birthday, char gender) {	
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.gender = gender;
		
	}
	
	public Userprofile(String firstName, String lastName, String email, String password,
			LocalDate birthday, char gender, Collection<Workout> myWorkout) {
		this(firstName, lastName, email, password, birthday, gender);
		this.myWorkouts=myWorkout;
	}
	
	public Userprofile(String firstName, String lastName, String email, String password, String birthday,
			char gender) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.birthday = LocalDate.parse(birthday, formatter);
		this.gender = gender;
		
	}

	public String getName() {
		return firstName + " " + lastName;
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
	
	private boolean isValidName(String name) {
		if (name.length()>=1 && name.matches("[a-zA-Z]+")) {
			return true;
		}
		return false;
	}
	
	public void setEmail(String email) {
		if (isValidEmail(email)) {
			this.email=email;
		}
		else {
			throw new IllegalArgumentException("Ugyldig e-post");
		}
	}
	
	private boolean isValidEmail(String email) {
		if (email.contains("@") && email.contains(".")) {
			return true;
		}
		return false;
	}
	
	private String getPassword() {
		return password;
	}
	
	private void setPassword(String password) {
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

	public void addMyWorkouts(Workout workout) {
		myWorkouts.add(workout);
	}
	
	public void deleteWorkout(Workout workout) {
		myWorkouts.remove(workout);
	}
	
	public String toString() {
		List<String> workoutNames = new ArrayList<String>();
		for (Workout workout : myWorkouts) {
			workoutNames.add(workout.getName());
		}
		return "Navn: " + firstName + " " +lastName + "\n" 
				+ "birthday: "+ birthday + "\n" 
				+ "gender: " + gender + "\n"
				+ "email: " + email + "\n"
				+ "password: " + password + "\n"
				+ "Workouts: " + workoutNames + "\n\n";
	}
	
	public static void main(String[] args) {
		Userprofile kevinco = new Userprofile("Kevin", "Cornolis",
				"kevinco@ntnu.no","123","15/04/1998"
				,'M');
		Userprofile anoj = new Userprofile("Francin", "Vincent",
				"francin@mail.com","1234","15/04/1998"
				,'M');
		Userprofile kavu = new Userprofile("Kavusikan", "Sivasub",
				"kevin@mail.com","1234","15/04/1998"
				,'M');
		List<Userprofile>trainers = Arrays.asList(anoj,kavu);
		List<String> gainz = Arrays.asList("chest", "triceps","shoulders");
		Workout workout = new Workout(kevinco,"død",
				7, gainz, "02-02-21",
				"Strength","push",2 ,
				"Du skal svette", trainers);
		System.out.println(kevinco);
		kevinco.addMyWorkouts(workout);
		System.out.println(kevinco);
	}

}
