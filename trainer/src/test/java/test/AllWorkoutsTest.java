package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import core.Userprofile;
import core.Workout;
import core.allWorkouts;

class AllWorkoutsTest {
	
	Userprofile kevinco;
	Userprofile anoj;
	Userprofile kavu;
	Workout workout;
	Workout workout2;
	allWorkouts al = new allWorkouts();
	Collection<Workout>workouts = new ArrayList<Workout>();
	List<Userprofile>trainers = new ArrayList<Userprofile>();
	
	@BeforeEach
	void setUp() {
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
		al.addWorkout(workout);
		al.addWorkout(workout2);
	}

	@Test
	void testAddWorkouts() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			al.addWorkout(workout);
		});
		
	}
	
}
