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

import core.Userprofile;

public class UserProfilePersistence implements filePersistence {
	
	//Varaiables
	private Userprofile user;
	private String file;

	public ArrayList<String> loggedin = new ArrayList<String>();
	
	//Constructors
	public UserProfilePersistence(String file) {
		this.file = file;
	}
	
	public UserProfilePersistence(Userprofile user, String file) {
		this.user = user;
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
            	loggedin.add(in.next());
            }
             
            in.close();
            System.out.println("Reading finished");
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error: file " + file + "could not be opened. Does it exist?");
            System.exit(1);
        }
		
	}

	//Method for writing to file.
	@Override
	public void writeFile() {
		System.out.println("Trying to write file");
		  try
	        {
	            PrintWriter outFile = new PrintWriter("src/main/java/persistence/userProfiles.txt");
	            outFile.println(user);
	            outFile.close();
	            System.out.println("Writing finished.");
	        }
	        catch (FileNotFoundException e)
	        {
	            System.err.println("Error: file " + file + "could not be opened for writing.");
	            System.exit(1);
	        }
	   
		
	}

	
	//Updates the userprofile textfile with the newly subbed subber
	@Override
	public void updateFile(String file, String wantedID, Collection<String> trening) {
		System.out.println("Trying to update file");
		String tempFile = "src/main/java/workoutplanner/persistence/userProfilesTemp.txt";
		File newFile = new File(tempFile);
		String name = ""; String birthday = ""; String gender = ""; String mail = ""; String workouts = ""; 
		try {
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			Scanner scan = new Scanner(new File(file));
			
			while(scan.hasNext()) {
				name = scan.nextLine();
				birthday = scan.nextLine();
				gender = scan.nextLine();
				mail = scan.nextLine();
				String mail2 = mail.substring(7);
				workouts = scan.nextLine();
				if(mail2.equals(mail)) {
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
            System.err.println("Error: file " + file + " could not be opened for writing.");
            System.exit(1);
        }
	}
}


