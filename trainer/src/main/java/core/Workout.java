package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Workout {

	private String name;
	private String uniqueID = UUID.randomUUID().toString();
	private int amountOfExercises;
	private List<String> muscles = new ArrayList<String>();
	private List<String> muscleGroup = Arrays.asList("Chest","Triceps","Back","Biceps","Shoulder","legs",
			"Glutes", "Core", "Other");
	private String when;
	private Date today = new Date();
	private String type;
	private List<String> types = Arrays.asList("Strength", "Hypothraphy","Endurance");
	private String category;
	private List<String> categories = Arrays.asList("Push", "Pull", "Legs","Upper body","Lower body"
			,"Full body", "Cardio"); 
	private int duration;
	private String description;
	private Userprofile createdBy;
	private Userprofile subs;
	private List<Userprofile> subbers = new ArrayList<Userprofile>();
	
	
	public Workout(Userprofile createdBy, String name, int amountOfExercises,
			List<String> muscles, String when, String type, String category,
			int duration, String description) {
		this.name = name;
		this.amountOfExercises = amountOfExercises;
		this.muscles = muscles;
		/*if (when.before(today)) {
			throw new IllegalArgumentException("The date is invalid");
		}
		*/
		this.when = when;
		this.type = type;
		this.category = category;
		this.duration = duration;
		this.createdBy = createdBy;
		this.description = description;
	}
	
	public Workout(Userprofile createdBy, String name, int amountOfExercises,
			List<String> muscles, String when, String type, String category,
			int duration, String description, List<Userprofile> subbers) {
		this(createdBy, name, amountOfExercises, muscles, when, type, category, 
				duration, description);
		this.subbers = subbers;
		
	}
	
	public Userprofile getCreater() {
		return createdBy;
	}
	
	public void setCreator(Userprofile creator) {
		this.createdBy = creator;
	}
	

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	
	public int getExercises() {
		return amountOfExercises;
	}
	
	public void setExercises(int amountOfExercises) {
		this.amountOfExercises= amountOfExercises;
	}
	
	public List<String> getMuscles() {
		return muscles;
	}
	
	public void setMuscles(String muscle) {
		if (muscleGroup.contains(muscle)) {
			muscles.add(muscle);
		}
		else{
			throw new IllegalArgumentException("Error");
		}
		
	}
	
	public String getWhen() {
		return when;
	}
	
	public void setWhen(String when) {
		/*if (when.before(today)) {
			throw new IllegalArgumentException("The date is invalid");
		}
		*/
		this.when = when;
	}
	
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		if (types.contains(type)) {
			this.type = type;
		}
		else{
			throw new IllegalArgumentException("Error");
		}
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		if (categories.contains(category)) {
			this.category = category;
		}
		else{
			throw new IllegalArgumentException("Error");
		}
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Userprofile getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Userprofile createdBy) {
		this.createdBy = createdBy;
	}
	
	public List<Userprofile> getSubbs(){
		return subbers;
	}
	
	public void setUserToWorkout(Userprofile sub) {
		subbers.add(sub);
	}
	
	public String toString() {
		List<String> userNames = new ArrayList<String>();
		for (Userprofile user : subbers) {
			userNames.add(user.getName());
		};
		return 
				"Name of workout: "+ name +  "\n" +
				"By: " + createdBy.getName() +"\n" +
				"When: "+ when + "\n" +
				"Duration: "+ duration + " hours\n" +
				"Type of workout: "+ type + "\n" +
				"Category: "+ category + "\n" +
				"Muscles trained: "+ muscles + "\n" +
				"Number of excercises: "+ amountOfExercises+ "\n" +
				"Description: "+ description + "\n" + 
				"Users using your workout: "+ userNames + "\n\n"; 
				
				
	}
	
	
	public static void main(String[] args) {
		List<String> gainz = Arrays.asList("chest", "triceps","shoulders");
		Userprofile kevinco = new Userprofile("Kevin", "Cornolis",
				"kevin@mail.com","1234","15/04/1998"
				,'M');
		Userprofile anoj = new Userprofile("Francin", "Vincent",
				"francin@mail.com","1234","15/04/1998"
				,'M');
		Userprofile kavu = new Userprofile("Kavusikan", "Sivasub",
				"kevin@mail.com","1234","15/04/1998"
				,'M');
		List<Userprofile>trainers = Arrays.asList(anoj,kavu);
		Workout workout = new Workout(kevinco,"død",
				7, gainz, "02-02-21",
				"Strength","push",2 ,
				"Du skal svette",trainers);
		//System.out.println(kevinco);
		System.out.println(workout);
		System.out.println(workout.getUniqueID());
	
	}

}
