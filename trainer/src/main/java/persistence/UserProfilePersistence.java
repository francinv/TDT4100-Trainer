package src.main.java.persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import main.java.workoutplanner.core.Userprofile;
import main.java.workoutplanner.core.Workout;

public class UserProfilePersistence implements filePersistence {
	
private Userprofile user;
private String file;
	
	public UserProfilePersistence(String file) {
		this.file = file;
	}
	
	public UserProfilePersistence(Userprofile user, String file) {
		this.user = user;
		this.file = file;
	}

	@Override
	public void readFile(String file) {
		Scanner in;
		System.out.println("Trying to read file");
        try
        {
            in = new Scanner(new FileReader(file));
             
            while(in.hasNext()){
                String line = in.nextLine();
                System.out.println(line);
            }
             
            in.close();
            System.out.println("Reading finished");
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error: file 'test.txt' could not be opened. Does it exist?");
            System.exit(1);
        }
		
	}

	@Override
	public void writeFile() {
		System.out.println("Trying to write file");
		  try
	        {
	            PrintWriter outFile = new PrintWriter("src/main/java/workoutplanner/persistence/userProfiles.txt");
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
		String file = "src/main/java/workoutplanner/persistence/userProfiles.txt";
		Userprofile kevinco = new Userprofile("Kevin", "Cornolis",
				"kevinco@ntnu.no","123","15/04/1998"
				,'M');
		UserProfilePersistence up = new UserProfilePersistence(kevinco, file);
		List<String> gainz = Arrays.asList("chest", "triceps","shoulders");
		Workout workout = new Workout(kevinco,"Idag skal jeg dø",
				7, gainz, "02-02-21",
				"Strength","push",2 ,
				"Du skal svette");
		Workout workout2 = new Workout(kevinco,"RIP",
				7, gainz, "02-02-21",
				"Strength","push",2 ,
				"Du skal svette");
		kevinco.addMyWorkouts(workout);
		kevinco.addMyWorkouts(workout2);
		up.writeFile();
		up.readFile(file); 
		System.out.println(kevinco.getMyWorkouts());
	}

}
