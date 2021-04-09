package persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import core.Userprofile;
import core.Workout;

public class UserDataPersistence implements filePersistence {
	
private Userprofile user;
	
	public UserDataPersistence() {
	}
	
	public UserDataPersistence(Userprofile user) {
		this.user=user;
	}

	@Override
	public void readFile() {
		Scanner in;
        try
        {
            in = new Scanner(new FileReader("test.txt"));
             
            while(in.hasNext()){
                String line = in.nextLine();
                System.out.println(line);
            }
             
            in.close();
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error: file 'test.txt' could not be opened. Does it exist?");
            System.exit(1);
        }
		
	}

	@Override
	public void writeFile() {
		System.out.println("Trying");
		  try
	        {
	            PrintWriter outFile = new PrintWriter("src/main/java/workoutplanner/persistence/userData.txt");
	            outFile.println(user);
	            outFile.close();
	            System.out.println("Done");
	        }
	        catch (FileNotFoundException e)
	        {
	            System.err.println("Error: file 'test.txt' could not be opened for writing.");
	            System.exit(1);
	        }
	   
		
	}
	
	public static void main(String[] args) {
		List<String> gainz = Arrays.asList("chest", "triceps","shoulders");
		Userprofile kevinco = new Userprofile("Kevin", "Cornolis",
				"kevin@mail.com","1234","15/04/1998"
				,'M');
		Userprofile anoj = new Userprofile("Francin", "Vincent",
				"kevin@mail.com","1234","15/04/1998"
				,'M');
		Userprofile kavu = new Userprofile("Kavusikan", "Sivasub",
				"kevin@mail.com","1234","15/04/1998"
				,'M');
		List<Userprofile>trainers = Arrays.asList(anoj,kavu);
		Workout workout = new Workout(kevinco,"død",
				7, gainz, "02-02-21",
				"Strength","push",2 ,
				"Du skal svette",trainers);
		WorkoutPersistence wp = new WorkoutPersistence(workout);
		wp.writeFile();
	}

}

