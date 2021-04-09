package core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import core.Userprofile;

/*
 * Denne klassen brukes til å lagre treningsøktene til en bruker
 * KAN VURDERE Å BRUKE ARRAYLIST ISTEDET FOR HASHSET
 */

public class Subs{

	private List<Userprofile> subbers = new ArrayList<Userprofile>();
	private Userprofile creator;
	private List<Workout> workouts = new ArrayList<Workout>();
	private Workout workout;
	private List<String>SubbedWorkoutsID = new ArrayList<String>();
	private String subbedWorkoutsID;
	
	public Subs() {
		//workoutsMade = new HashSet<String>();
	}
	
	public List<Userprofile> getSubbers() {
		return workout.getSubbs();
	}

	public void setSubbers(List<Userprofile> subbers) {
		this.subbers = subbers;
	}

	public Userprofile getCreator() {
		return creator;
	}

	public void setCreator(Userprofile creator) {
		this.creator = creator;
	}

	public List<Workout> getWorkouts() {
		return workouts;
	}

	public void setWorkouts(List<Workout> workouts) {
		this.workouts = workouts;
	}

	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

	public List<String> getSubbedWorkoutsID() {
		return SubbedWorkoutsID;
	}

	public void setSubbedWorkoutsID(List<String> subbedWorkoutsID) {
		SubbedWorkoutsID = subbedWorkoutsID;
	}
	
	public String getWorkoutsID() {
		return workout.getUniqueID();
	}

	public static void main(String[] args) {
		System.out.println("sfkdjldkfsjlf");
	}
	
}

