package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.Userprofile;
import core.Workout;
import persistence.WorkoutPersistence;

class WorkoutPersistenceTest {
	
	private Scanner checker;
	private Userprofile kevinco;
	private Workout workout;
	
	
	@BeforeEach
	void setUp() {
		List<String> gainz = Arrays.asList("chest", "triceps","shoulders");
		List<String> category = Arrays.asList("Push", "Upper body");
		kevinco = new Userprofile("Kevin", "Cornolis",
				"kevin@mail.com","1234","1998-04-15"
				,'M');
		workout = new Workout(kevinco, "død", 7, gainz, "2021-05-01", "Strength", category, "1-2", "Sweat fest");
		
	}
	
	void testerFunction(String file) {
		String expected = "Nameofworkout:død"
				+ "WorkoutID:bbc7123f-7642-4b1f-a497-531da05fe716"
				+ "By:KevinCornolisWhen:02-02-21"
				+ "Duration:2hours"
				+ "Typeofworkout:Strength"
				+ "Category:push"
				+ "Musclestrained:[chest,triceps,shoulders]"
				+ "Numberofexcercises:7"
				+ "Description:Duskalsvette"
				+ "Usersusingyourworkout:[]";
		String actual = "";
		WorkoutPersistence wp = new WorkoutPersistence(workout, file);
		wp.writeFile();
		try {
			checker = new Scanner(new File (file));
			while(checker.hasNext()){
                actual+=checker.next();
            }
			Assertions.assertEquals(expected, actual);
		}
		catch(FileNotFoundException e) {
		}
	}

	@Test
	void testRead() {
		String rightFile = "src/main/java/persistence/workoutTest.txt";
		testerFunction(rightFile);
		String wrongFile = "src/main/java/persistence/workoutTest2.txt";
		testerFunction(wrongFile);
	}

}
