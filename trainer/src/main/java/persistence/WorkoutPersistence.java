package persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import core.Workout;

public class WorkoutPersistence implements filePersistence{
	
	//Variables
	private Workout workout;
	private String file;
	public ArrayList<String> workoutlist = new ArrayList<String>();
	
	
	//Constructors
	public WorkoutPersistence() {
	}
	
	public WorkoutPersistence(Workout workout, String file) {
		this.workout = workout;
		this.file = file;
	}

	
	//Method for reading file
	@Override
	public void readFile(String file) {
		Scanner in;
		System.out.println("Trying to read file");
        try
        {
            in = new Scanner(new FileReader(file));
             
            while(in.hasNext()){
                workoutlist.add(in.next());
            }
             
            in.close();
            System.out.println("Reading finished");
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error: file " + file +  "could not be opened. Does it exist?");
            System.exit(1);
        }
		
	}

	//Method for writing to file 
	@Override
	public void writeFile() {
		System.out.println("Trying to write file");
		  try
	        {
	            PrintWriter outFile = new PrintWriter("src/main/java/persistence/workout.txt");
	            outFile.println(workout);
	            outFile.close();
	            System.out.println("Writing finished");
	        }
	        catch (FileNotFoundException e)
	        {
	            System.err.println("Error: file " + file + "could not be opened for writing.");
	            System.exit(1);
	        }
	}

	//This method updates the workout textfile with the subbers who have subbed on a specific workout
	@Override
	public void updateFile(String File, String wantedID, Collection<String> allSubs ) {
		System.out.println("Trying to update file");
		String tempFile = "src/main/java/persistence/workoutTemp.txt";
		File oldFile = new File(file);
		File newFile = new File(tempFile);
		String name = ""; String id = ""; String creator= ""; String when = ""; String duration = "";
		String type = ""; String category = ""; String muscles= ""; String excercises = "";
		String description = ""; String subbers = "";
		try {
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			Scanner scan = new Scanner(new File(file));

			while(scan.hasNext()) {
				name = scan.nextLine();
				id = scan.nextLine();
				creator = scan.nextLine();
				when = scan.nextLine();
				duration = scan.nextLine();
				type = scan.nextLine();
				category = scan.nextLine();
				muscles = scan.nextLine();
				excercises = scan.nextLine();
				description = scan.nextLine();
				subbers = scan.nextLine();
				String id2 = id.substring(12);
				
				if(id2.equals(wantedID)) {
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
            System.err.println("Error: file " + file + "could not be opened for writing.");
            System.exit(1);
        }
	}

	
	//This method finds all subbers subbed on a specific workout
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
            System.err.println("Error: " + file + "could not be read. Does it exist?");
            System.exit(1);
        }

	}

}

