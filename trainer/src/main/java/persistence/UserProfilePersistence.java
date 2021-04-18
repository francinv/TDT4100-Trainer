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
	
	@Override
	public void updateFile(String File, String email, List<String> trening ) {
		System.out.println("Trying to update file");
		String tempFile = "src/main/java/workoutplanner/persistence/userProfilesTemp.txt";
		File oldFile = new File(file);
		File newFile = new File(tempFile);
		String name = ""; String birthday = ""; String gender = ""; String mail = ""; String workouts = ""; 
		try {
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			scan = new Scanner(new File(file));
			//scan.useDelimiter("\n");
			
			while(scan.hasNext()) {
				name = scan.nextLine();
				System.out.println(name);
				birthday = scan.nextLine();
				System.out.println(birthday);
				gender = scan.nextLine();
				System.out.println(gender);
				mail = scan.nextLine();
				System.out.println(mail);
				String mail2 = mail.substring(7);
				System.out.println(mail2);
				workouts = scan.nextLine();
				System.out.println(workouts);
				if(mail2.equals(email)) {
					System.out.println("derp");
					pw.println(name);
					pw.println(birthday);
					pw.println(gender);
					pw.println(mail);
					pw.println("Workouts: "+trening);
					
				}
				else{
					pw.println(name);
					pw.println(birthday);
					pw.println(gender);
					pw.println(mail);
					pw.println(workouts);
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
