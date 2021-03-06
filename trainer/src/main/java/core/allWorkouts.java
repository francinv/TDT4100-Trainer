package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class allWorkouts {
	
	static Userprofile kevinco;
	static Userprofile anoj;
	static Userprofile kavu;
	static Workout workout;
	static Workout workout2;
	static List<Userprofile>trainers = new ArrayList<Userprofile>();
	
	//Variables
	private static Collection<Workout> workouts = new ArrayList<Workout>();
	
	
	//Constructor
	public allWorkouts() {
		
	}
	
	
	//Method for adding workout
	public void addWorkout(Workout workout) {
		if (workouts.contains(workout)){
			throw new IllegalArgumentException("Workout already exists.");
		}
		else {
			workouts.add(workout);
		}
	}
	
	//Getter
	public Collection<Workout> getAllWorkouts(){
		return workouts;
	}
	

	public static void main(String[] args) {
		allWorkouts al = new allWorkouts();
		List<String> gainz = Arrays.asList("Chest", "Triceps","Shoulders");
		List<String> category1 = Arrays.asList("Push");
		kevinco = new Userprofile("Kevin", "Cornolis",
				"kevin@mail.com","1234","1998-04-15"
				,'M');
		anoj = new Userprofile("Francin", "Vincent",
				"francin@mail.com","1234","1998-04-15"
				,'M');
		kavu = new Userprofile("Kavusikan", "Sivasub",
				"kevin@mail.com","1234","1998-04-15"
				,'M');
		trainers = Arrays.asList(anoj,kavu);
		workout = new Workout(kevinco, "DEAD", 7, gainz, "2021-05-02", "Strength", category1, "1-2", "Dette blir en tung økt.", trainers);
		workout2 = new Workout(kevinco, "Workout2", 10, gainz, "2021-05-05", "Hypothraphy", category1, "1-2", "Letsgo trainers!!", trainers);
		workouts.add(workout);
		workouts.add(workout2);
		al.addWorkout(workout);
	
		
	
	}

}
