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

public class UsersPersistence implements filePersistence {
	
	//Variables
	private Collection<Userprofile> allUsers = new ArrayList<Userprofile>();
	public ArrayList<String> list = new ArrayList<String>();
	private String file;
	
	
	//Constructors
	public UsersPersistence(String file) {
		this.file = file;
	}
	
	public UsersPersistence(Collection<Userprofile> allUsers, String file) {
		this.allUsers = allUsers;
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
            	list.add(in.next());

            }
            System.out.println(list);
            in.close();
            System.out.println("Reading finished");
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error: file " + file +  "could not be opened. Does it exist?");
            System.exit(1);
        }
		
	}

	
	//Method for writing file
	@Override
	public void writeFile() {
		System.out.println("Trying to write file");
		  try
	        {
	            PrintWriter outFile = new PrintWriter("src/main/java/persistence/allUsers.txt");
	            outFile.println(allUsers);
	            outFile.close();
	            System.out.println("Writing finished.");
	        }
	        catch (FileNotFoundException e)
	        {
	            System.err.println("Error: file " + file + "could not be opened for writing.");
	            System.exit(1);
	        }
	   
		
	}
	
	
	//These method updates the textfile containing all users with the workouts they have subbed on
	@Override
	public void updateFile(String file, String email, Collection<String> trening  ) {
		System.out.println("Trying to update file");
		String tempFile = "src/main/java/persistence/userProfilesTemp.txt";
		File newFile = new File(tempFile);
		String name = ""; String birthday = ""; String gender = ""; String mail = ""; String workouts = "";
		try {
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			Scanner scan = new Scanner(new File(file));

			while(scan.hasNext()) {
				name = scan.nextLine();
				if (name.equals("")) {
					pw.println("]");
					break;
				}
				birthday = scan.nextLine();
				gender = scan.nextLine();
				mail = scan.nextLine();
				String mail2 = mail.substring(7);
				workouts = scan.nextLine();
				
				if(mail2.equals(email)) {
					pw.println(name);
					pw.println(birthday);
					pw.println(gender);
					pw.println(mail);
					pw.println("Workouts: "+trening);
					System.out.println("Workout changed.");

				}
				else{
					pw.println(name);
					pw.println(birthday);
					pw.println(gender);
					pw.println(mail);
					pw.println(workouts);
					System.out.println("Workout writed.");
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
            System.err.println("Error: " + file + "could not be opened for updating.");
            System.exit(1);
        }
	}
	
	
	////This method updates the file containg all users with a new user that have registered
	public void addUserToFile( Userprofile user) {
		System.out.println("Trying to update file");
		String tempFile = "src/main/java/persistence/addUserTemp.txt";
		File newFile = new File(tempFile);
		String name = ""; String birthday = ""; String gender = ""; String mail = ""; String workouts = ""; String password = "";
		try {
			FileWriter fw = new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			Scanner scan = new Scanner(new File(file));
			
			while(scan.hasNext()) {
				name = scan.nextLine();
				if (name.equals("]")) {
					pw.println(", "+user+"]");
					break;
				}
				birthday = scan.nextLine();
				gender = scan.nextLine();
				mail = scan.nextLine();
				password = scan.nextLine();
				workouts = scan.nextLine();

				pw.println(name);
				pw.println(birthday);
				pw.println(gender);
				pw.println(mail);
				pw.println(password);
				pw.println(workouts);
			}
			System.out.println("User added to file");
			scan.close();
			pw.flush();
			pw.close();
			File dump = new File(file);
			newFile.renameTo(dump);
		}
		catch (Exception e)
        {
            System.err.println("Error: " + file + "could not be opened for updating.");
            System.exit(1);
        }
	}
}

