package src.main.java.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import main.java.workoutplanner.core.Userprofile;
import main.java.workoutplanner.core.Workout;

public class allWorkoutsPersistence implements filePersistence {
	
	private Collection<Workout> workouts = new ArrayList<Workout>();
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
				if (name.equals("")) {
					name = scan.nextLine();
					if (name.equals("]")) {
						pw.println("]");
						break;
					}
				}
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
				System.out.println(category);
				muscles = scan.nextLine();
				System.out.println(muscles);
				excercises = scan.nextLine();
				System.out.println(excercises);
				description = scan.nextLine();
				System.out.println(description);
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
					pw.println("");
					System.out.println("endret");	
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
					pw.println("");
					System.out.println("endret");
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
		  
	public void findSubbs(String id, String file) {
		Scanner scanner;
		System.out.println("Trying to find subbers");
        try
        {
            scanner = new Scanner(new FileReader(file));
            
            int lineNum = 0;
            String line="";
            while(scanner.hasNextLine()) {	
            	String name = scanner.nextLine();
            	if (name.equals("")) {
					name = scanner.nextLine();
					if (name.equals("]")) {
						break;
					}
            	}
            	String workoutID = scanner.nextLine();
            	for (int i = 0; i < 9; i++) {
            		  line = scanner.nextLine();
            		}
				String[] subbs  = line.split(":");
            	if(workoutID.substring(12).equals(id)) {
            		String[] names = name.split(":");
            		System.out.println("The workout "+names[1].substring(1)+", has these subbers: "+ subbs[1]);
            	}
            }
            System.out.println("All subbers found");
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
