package test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.Userprofile;
import core.Workout;

class workoutTest {
	
	private Workout workout;
	private Userprofile kevinco;
	
	
	@BeforeEach
	public void setup() {
		workout = new Workout();
		kevinco = new Userprofile("Kevin", "Cornolis",
				"kevin@mail.com","AA1234fakflaflka","1998-04-15"
				,'M');
	}
	
	@Test
	void testCreator() {
		workout.setCreatedBy(kevinco);
		Assertions.assertEquals(kevinco, workout.getCreatedBy());
		
	}

	@Test
	void testName() {
		workout.setName("adbkjsdb");
		Assertions.assertEquals("adbkjsdb",workout.getName());	
	}
	
	@Test
	void testWhen() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			LocalDate wrongdate = LocalDate.of(2021, 03, 20);
			workout.setWhen(wrongdate);
		});
		LocalDate correctdate = LocalDate.of(2021, 05, 01);
		workout.setWhen(correctdate);
		Assertions.assertEquals(correctdate, workout.getWhen());
	}
	
	@Test
	void testAmountOfExcersise() {
		workout.setExercises(5);
		Assertions.assertEquals(5,workout.getExercises());
	}
	
	@Test
	void testMuscles() {
		List<String> muscleGroup = Arrays.asList("Chest","Triceps","Back","Biceps","Shoulder","legs",
				"Glutes", "Core", "Other");
		Random random = new Random();
		boolean state;
		int intRandom = random.nextInt(8);
		workout.setMuscles(muscleGroup.get(intRandom));
		if (muscleGroup.contains(workout.getMuscles().get(0))) {
			state = true;
		}
		else {
			state = false;
		}
		Assertions.assertTrue(state);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    workout.setMuscles(null);
		 });
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    workout.setMuscles("Traps");
		 });
	}
	
	@Test
	void testType() {
		List<String> types = Arrays.asList("Strength", "Hypothraphy","Endurance");
		Random random = new Random();
		boolean state;
		int intRandom = random.nextInt(2);
		workout.setType(types.get(intRandom));
		if (types.contains(workout.getType())) {
			state = true;
		}
		else {
			state = false;
		}
		Assertions.assertTrue(state);	
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    workout.setType(null);
		 });
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    workout.setType("Cardio");
		 });
		
	}
	
	@Test
	void testCategory() {
		List<String> categories = Arrays.asList("Push", "Pull", "Legs","Upper body","Lower body"
				,"Full body", "Cardio");
		Random random = new Random();
		boolean state;
		int intRandom = random.nextInt(2);
		workout.setCategory(categories.get(intRandom));
		if (categories.contains(workout.getCategory().get(0))) {
			state = true;
		}
		else {
			state = false;
		}
		Assertions.assertTrue(state);
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    workout.setCategory(null);
		 });
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    workout.setCategory("Bro Split");
		 });
	}
	
	@Test
	void testDuration() {
		workout.setDuration("1-2");
		Assertions.assertEquals("1-2", workout.getDuration());
		
	}
	
	@Test
	void testDescription() {
		workout.setDescription("dlansjfbaøfbøabfan");
		Assertions.assertEquals("dlansjfbaøfbøabfan",workout.getDescription());
		
		
	}
	
	
}
