package persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import core.Workout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class allWorkoutsPersistence implements filePersistence {
	
	//Variables
	private Collection<Workout> workouts = new ArrayList<Workout>();
	public ObservableList<String> allWorkouts = FXCollections.observableArrayList();
	private String file;
	private Scanner scan;
	public ArrayList<String> subbs = new ArrayList<String>();
	public ArrayList<String> workoutsSubbedOn = new ArrayList<String>();
	
	
	//Constuctors
	public allWorkoutsPersistence(String file) {
		this.file=file;
	}
	
	public allWorkoutsPersistence(String file, Collection<Workout> workouts) {
		this.file=file;
		this.workouts = workouts;
	}
		
	
	//Read method
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
            System.err.println("Error: file" + file + "could not be opened. Does it exist?");
            System.exit(1);
        }
		
	}

	
	//Write method
	@Override
	public void writeFile() {
		System.out.println("Trying to write file.");
		  try
	        {
	            PrintWriter outFile = new PrintWriter("src/main/java/persistence/allworkouts.txt");
	            outFile.println(workouts);
	            outFile.close();
	            System.out.println("Writing finished.");
	        }
	        catch (FileNotFoundException e)
	        {
	            System.err.println("Error: file " + file + "could not be opened for writing.");
	            System.exit(1);
	        }
	}


	//This method updates the allWorkout text file with the wich user have subbed on a workout
	@Override
	public void updateFile(String file, String wantedID, Collection<String> allSubs ) {
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
			scan = new Scanner(new File(file));
			
			while(scan.hasNext()) {
				name = scan.nextLine();
				if (name.equals("")) {
					name = scan.nextLine();
					if (name.equals("]")) {
						pw.println("]");
						break;
					}
				}
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
				
				String[] id2 = name.split(":");
				String name2 = id2[1].substring(1);

				if(name2.equals(wantedID)) {
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
					System.out.println("Updated.");	
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
					System.out.println("Updated.");
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
		  
	
	//This method finds all subbers to a workout
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
				String[] sub  = line.split(":");
            	if(workoutID.substring(12).equals(id)) {
            		String[] names = name.split(":");
            		subbs.add(sub[1].substring(1));
            	}
            }
            System.out.println("All subbers found.");
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error: file "+ file + "could not be opened. Does it exist?");
            System.exit(1);
        }
		
	}
	
	
	//This method adds a newly made workout to the workout text file;
	public void addWorkoutToFile(Workout workout) {
		System.out.println("Trying to add workout to file");
		String tempFile = "src/main/java/persistence/addWorkoutTemp.txt";
		File oldFile = new File("src/main/java/persistence/allworkouts.txt");
		File newFile = new File(tempFile);
		String name = ""; String id = ""; String creator= ""; String when = ""; String duration = "";
		String type = ""; String category = ""; String muscles= ""; String excercises = "";
		String description = ""; String subbers = "";
		
		try {
			
			FileWriter fw = new FileWriter(tempFile,true);
			
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			Scanner scan = new Scanner(new File("src/main/java/persistence/allworkouts.txt"));
			
			
			
			while(scan.hasNext()) {
				name = scan.nextLine();
				if (name.equals("")) {
					name = scan.nextLine();
					if (name.equals("]")) {
						pw.println(", "+workout+"]");
						break;
					}
				}

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
				
			}
			System.out.println("Workout added to file.");
			scan.close();
			pw.flush();
			pw.close();
			File dump = new File("src/main/java/persistence/allworkouts.txt");
			newFile.renameTo(dump);
		}
		catch (Exception e)
        {
			e.printStackTrace();
            System.err.println("Error: file "+ file + "could not be opened for writing.");
            System.exit(1);
        }
	}
	
	
	//Method for finding workouts that this.user are subbed to.
	public List<String> findSubbedWorkout(String subber) {
		Scanner scanner;
		System.out.println("Trying to find workouts "+subber+" has subbed on");
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
            	for (int i = 0; i<10; i++) {
            		String check = scanner.nextLine();
            		String splits[] = check.split(":");
            		if (splits[0].equals("Users using your workout")) {
            			String split = splits[1].substring(2,splits[1].length()-1);
            			String subbers[] = split.split(", ");
            			for (int j = 0; j<subbers.length;j++) {
            				if (subbers[j].equals(subber)) {
            					String nameParts[] = name.split(": ");
            					workoutsSubbedOn.add(nameParts[1]);
            				}
            			}
            		}
            	}
            	}
            System.out.println("All workouts found.");
            return workoutsSubbedOn;
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error, not able to find subbers.");
            System.exit(1);
        }
		return workoutsSubbedOn;	
	}

}


