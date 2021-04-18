package src.main.java.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import main.java.workoutplanner.core.Userprofile;
import main.java.workoutplanner.core.Workout;

public class WorkoutPersistence implements filePersistence{
	
	private Workout workout;
	private String file;
	
	//"src/main/java/workoutplanner/persistence/workout.txt"
	
	public WorkoutPersistence() {
	}
	
	public WorkoutPersistence(Workout workout, String file) {
		this.workout = workout;
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
		System.out.println("Trying");
		  try
	        {
	            PrintWriter outFile = new PrintWriter("src/main/java/workoutplanner/persistence/workout.txt");
	            outFile.println(workout);
	            outFile.close();
	            System.out.println("Done");
	        }
	        catch (FileNotFoundException e)
	        {
	            System.err.println("Error: file 'test.txt' could not be opened for writing.");
	            System.exit(1);
	        }
	}
		  
	@Override
	public void updateFile(String File, String wantedID, List<String> allSubs ) {
		System.out.println("Trying to update file");
		String tempFile = "src/main/java/workoutplanner/persistence/workoutTemp.txt";
		File oldFile = new File(file);
		File newFile = new File(tempFile);
		String name = ""; String id = ""; String creator= ""; String when = ""; String duration = "";
		String type = ""; String category = ""; String muscles= ""; String excercises = "";
		String description = ""; String subbers = "";
		try {
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			scan = new Scanner(new File(file));
			//scan.useDelimiter("\n");
			
			while(scan.hasNext()) {
				name = scan.nextLine();
				System.out.println(name);
				id = scan.nextLine();
				System.out.println(id);
				creator = scan.nextLine();
				System.out.println(creator);
				when = scan.nextLine();
				System.out.println(when);
				duration = scan.nextLine();
				System.out.println(duration);
				type = scan.nextLine();
				System.out.println(type);
				category = scan.nextLine();
				muscles = scan.nextLine();
				excercises = scan.nextLine();
				description = scan.nextLine();
				subbers = scan.nextLine();
				System.out.println(subbers);
				String id2 = id.substring(12);
				System.out.println(id2);
				
				if(id2.equals(wantedID)) {
					System.out.println("derp");
					pw.println(name);
					pw.println(id);
					pw.println(creator);
					pw.println(when);
					pw.println(duration);
					pw.println(type);
					pw.println(category);
					pw.println(muscles);
					pw.println(excercises);
					pw.println(description);
					pw.println("Users using your workout: " + allSubs);
					
				}
				else{
					pw.println(name);
					pw.println(id);
					pw.println(creator);
					pw.println(when);
					pw.println(duration);
					pw.println(type);
					pw.println(category);
					pw.println(muscles);
					pw.println(excercises);
					pw.println(description);
					pw.println(subbers);
				}
			}
			System.out.println("Updating finished");
			scan.close();
			pw.flush();
			pw.close();
			File dump = new File(file);
			newFile.renameTo(dump);
		}
		catch (Exception e)
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
            System.err.println("Error: file could not be read. Does it exist?");
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
		WorkoutPersistence wp = new WorkoutPersistence(workout, file);
		WorkoutPersistence wp2 = new WorkoutPersistence(workout, file);
		wp.writeFile();
		wp.readFile(file);
		wp2.writeFile();
	}

}
