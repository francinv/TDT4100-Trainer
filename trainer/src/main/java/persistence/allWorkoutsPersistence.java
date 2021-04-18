package persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import core.Userprofile;
import core.Workout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class allWorkoutsPersistence implements filePersistence {
	
	private Collection<Workout> workouts = new ArrayList<Workout>();
	public ObservableList<String> allWorkouts = FXCollections.observableArrayList();
	private String file;
	
	public allWorkoutsPersistence(String file) {
		this.file=file;
	}
	
	public allWorkoutsPersistence(String file, Collection<Workout> workouts) {
		this.file=file;
		this.workouts = workouts;
	}
		
	
	@Override
	public void readFile(String file) {
		Scanner in;
		System.out.println("Trying to read file");
        try
        {
            in = new Scanner(new FileReader(file));
             
            while(in.hasNext()){
                allWorkouts.add(in.next());
            }
             
            in.close();
            System.out.println("Reading finished");
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error: file 'test.txt' could not be opened. Does it exist?");
            System.exit(1);
        }
		System.out.println(allWorkouts);
	}

	@Override
	public void writeFile() {
		System.out.println("Trying");
		  try
	        {
	            PrintWriter outFile = new PrintWriter("src/main/java/persistence/allworkouts.txt");
	            outFile.println(workouts);
	            outFile.close();
	            System.out.println("Done");
	        }
	        catch (FileNotFoundException e)
	        {
	            System.err.println("Error: file 'test.txt' could not be opened for writing.");
	            System.exit(1);
	        }
	}
		  
	public void findSubbs(String file) {
		Scanner scanner;
        try
        {
            scanner = new Scanner(new FileReader(file));
            
            int lineNum = 0;
            while(scanner.hasNextLine()) {
            	String line = scanner.nextLine();
				String[] subbs  = line.split(":");
            	lineNum++;
            	if(subbs[0].equals("Users using your workout")) {
            		System.out.println("The subbers are: "+ subbs[1]);
            	}
            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error: file 'test.txt' could not be opened. Does it exist?");
            System.exit(1);
        }
		
	}
	
	public static void main(String[] args) {
		String file = "src/main/java/workoutplanner/persistence/workout.txt";
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
		Workout workout2 = new Workout(anoj,"RIP",
				5, gainz, "04-02-21",
				"Strength","pull",2 ,
				"Du skal svette");
		Collection<Workout>workouts = Arrays.asList(workout,workout2);
		allWorkoutsPersistence wp = new allWorkoutsPersistence(file,workouts);
		wp.writeFile();
		wp.readFile(file);
	}

}

