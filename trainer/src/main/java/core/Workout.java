package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Workout {
	
	//variables
	
	private String name;
	private String uniqueID = UUID.randomUUID().toString();
	private int amountOfExercises;
	private List<String> muscles = new ArrayList<String>();
	private List<String> muscleGroup = Arrays.asList("Chest","Triceps","Back","Biceps","Shoulder","legs",
			"Glutes", "Core", "Other");
	private LocalDate when;
	private LocalDate today = LocalDate.now();
	private String type;
	private List<String> types = Arrays.asList("Strength", "Hypothraphy","Endurance");
	private List<String> categories = new ArrayList<String>();
	private List<String> categoriesGroup = Arrays.asList("Push", "Pull", "Legs","Upper body","Lower body"
			,"Full body", "Cardio"); 
	private String duration;
	private String description;
	private Userprofile createdBy;
	private List<Userprofile> subbers = new ArrayList<Userprofile>();
	
	
	//Constructers
	
	public Workout () {
		
	}
	
	public Workout(Userprofile createdBy, String name, int amountOfExercises,
			List<String> muscles, String when, String type, List<String> categories,
			String duration, String description) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		this.name = name;
		this.amountOfExercises = amountOfExercises;
		this.muscles = muscles;
		this.when = LocalDate.parse(when, formatter);
		this.type = type;
		this.categories = categories;
		this.duration = duration;
		this.createdBy = createdBy;
		this.description = description;
	}
	
	public Workout(Userprofile createdBy, String name, int amountOfExercises,
			List<String> muscles, String when, String type, List<String> categories,
			String duration, String description, List<Userprofile> subbers) {
		this(createdBy, name, amountOfExercises, muscles, when, type, categories, 
				duration, description);
		this.subbers = subbers;
		
	}
	
	//Getters and Setters
	
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
	
	public void setMuscles(List<String> muscles) {
		this.muscles = muscles;
	}
	
	public LocalDate getWhen() {
		return when;
	}
	
	public void setWhen(LocalDate when) {
		if(when.isBefore(today)) {
			throw new IllegalArgumentException("Chosen date is before today, that is not possible.");
		}
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
			throw new IllegalArgumentException("Chosen type is not accepted.");
		}
	}
	
	public List<String> getCategory() {
		return categories;
	}
	
	public void setCategory(List<String> categories) {
		this.categories = categories;
	}
	
	public String getDuration() {
		return duration;
	}
	
	public void setDuration(String duration) {
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
	
	//toString method
	//This method defines how the persistence class will write to file
	
	public String toString() {
		List<String> userNames = new ArrayList<String>();
		for (Userprofile user : subbers) {
			userNames.add(user.getName());
		};
		return 
				"Workoutname: "+ name +  "\n" +
				"WorkoutID: "+ uniqueID + "\n"+
				"By: " + createdBy.getName() +"\n" +
				"When: "+ when + "\n" +
				"Duration: "+ duration + "\n" +
				"Workouttype: "+ type + "\n" +
				"Category: "+ categories + "\n" +
				"Muscles: "+ muscles + "\n" +
				"Exercises: "+ amountOfExercises+ "\n" +
				"Description: "+ description + "\n" + 
				"Users using your workout: "+ userNames + "\n\n"; 
				
				
	}
	
}
