package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	private List<String> category = new ArrayList<String>();
	private List<String> categories = Arrays.asList("Push", "Pull", "Legs","Upper body","Lower body"
			,"Full body", "Cardio"); 
	private String duration;
	private String description;
	private Userprofile createdBy;
	private Userprofile subs;
	private List<Userprofile> subbers = new ArrayList<Userprofile>();
	private List<String> subbs = new ArrayList<String>();
	
	
	public Workout(Userprofile createdBy, String name, int amountOfExercises,
			List<String> muscles, String when, String type, List<String> category,
			String duration, String description, List<String> subbs) {
		this(createdBy, name, amountOfExercises, muscles, when, type, category, 
				duration, description);
		this.subbs = subbs;
		
	}
	


	public Workout(Userprofile createdBy, String name, int amountOfExercises, List<String> muscles, String when, String type,
			List<String> category, String duration, String description) {
		this.createdBy = createdBy;
		this.name = name;
		this.amountOfExercises = amountOfExercises;
		this.muscles = muscles;
		this.when = when;
		this.type = type;
		this.category = category;
		this.duration = duration;
		this.description = description;
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
	
	public List<String> getCategory() {
		return category;
	}
	
	public void setCategory(List<String> category) {
		if (categories.contains(category)) {
			this.category = category;
		}
		else{
			throw new IllegalArgumentException("Error");
		}
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
	
	public String toString() {
		List<String> userNames = new ArrayList<String>();
		for (Userprofile user : subbers) {
			userNames.add(user.getName());
		};
		return 
				"Workoutname: "+ name +  "\n" +
				"Workout ID: "+ uniqueID + "\n" +
				"By: " + createdBy.getName() +"\n" +
				"When: "+ when + "\n" +
				"Duration: "+ duration + "\n" +
				"Workouttype: "+ type + "\n" +
				"Category: "+ category + "\n" +
				"Muscles trained: "+ muscles + "\n" +
				"Number of excercises: "+ amountOfExercises+ "\n" +
				"Description: "+ description + "\n" +
				"Users using your workout: "+ userNames + "\n\n"; 
				
				
	}

}
