package src.main.java.core;

import java.util.ArrayList;
import java.util.Collection;

public class allWorkouts {
	
	private Collection<Workout> workouts = new ArrayList<Workout>();
	private Workout workout;
	
	public allWorkouts() {
		
	}
	
	public void addWorkout(Workout workout) {
		if (workouts.contains(workout)){
			throw new IllegalArgumentException("Workout allready exsist");
		}
		else {
			workouts.add(workout);
		}
	}
	
	public Collection<Workout> getAllWorkouts(){
		return workouts;
	}
	
	public static void main(String[] args) {
	}

}
