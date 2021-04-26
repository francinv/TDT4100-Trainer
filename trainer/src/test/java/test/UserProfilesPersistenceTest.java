package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import core.Userprofile;
import persistence.UserProfilePersistence;

class UserProfilesPersistenceTest {
	
	Userprofile kevinco;
	String file;
	Scanner checker;
	
	@BeforeEach
	void setUp() {
		kevinco = new Userprofile("Kevin", "Cornolis",
				"kevinco@ntnu.no","123","1998-04-15"
				,'M');
		List<String> gainz = Arrays.asList("chest", "triceps","shoulders");
	}
	
	void testerFunction(String file) {
		String expected = "Navn:KevinCornolis"
				+ "birthday:1998-04-15"
				+ "gender:M"
				+ "email:kevinco@ntnu.no"
				+ "Workouts:[dø,dø2]";
		String actual = "";
		UserProfilePersistence usp = new UserProfilePersistence(kevinco,file);
		usp.writeFile();
		try {
			checker = new Scanner(new File (file));
			while(checker.hasNext()){
                actual+=checker.next();
                System.out.println(actual);
            }
			System.out.println(actual);
			System.out.println("");
			System.out.println(expected);
			Assertions.assertEquals(expected, actual);
		}
		catch(FileNotFoundException e) {
		}
	}
	
	@Test
	void testRead() {
		String rightFile = "src/main/java/persistence/userProfilesTest.txt";
		testerFunction(rightFile);
		String wrongFile = "src/main/java/persistence/userProfilesTest2.txt";
		testerFunction(wrongFile);
	}
}
