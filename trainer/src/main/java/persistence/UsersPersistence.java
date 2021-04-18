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

public class UsersPersistence implements filePersistence {

private Collection<Userprofile> allUsers = new ArrayList<Userprofile>();
private String file;
	
	public UsersPersistence(String file) {
		this.file = file;
	}
	
	public UsersPersistence(Collection<Userprofile> allUsers, String file) {
		this.allUsers = allUsers;
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
            	list.add(in.next());
                //String line = in.nextLine();

            }
            System.out.println(list);
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
	            PrintWriter outFile = new PrintWriter("src/main/java/persistence/allUsers.txt");
	            outFile.println(allUsers);
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
	public void updateFile(String File, String email, List<String> trening  ) {
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
			//scan.useDelimiter(",");

			while(scan.hasNext()) {
				name = scan.nextLine();
				System.out.println(name);
				if (name.equals("")) {
					pw.println("]");
					break;
				}
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
				System.out.println("sqrtsqrt");
				System.out.println(scan.hasNext());
				if(mail2.equals(email)) {
					pw.println(name);
					pw.println(birthday);
					pw.println(gender);
					pw.println(mail);
					pw.println("Workouts: "+trening);
					System.out.println("endret trening");

				}
				else{
					pw.println(name);
					pw.println(birthday);
					pw.println(gender);
					pw.println(mail);
					pw.println(workouts);
					System.out.println("skrev trening");
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
            System.err.println("Error: file could not be opened for updating.");
            System.exit(1);
        }
	}



	public static void main(String[] args) {
		String file = "src/main/java/workoutplanner/persistence/allUsers.txt";
		Userprofile kevinco = new Userprofile("Kevin", "Cornolis",
				"kevinco@ntnu.no","123","15/04/1998"
				,'M');
		Collection<Userprofile> allUsers = new ArrayList<Userprofile>();
		Userprofile anoj = new Userprofile("Francin", "Vincent",
				"francin@mail.com","1234","15/04/1998"
				,'M');
		Userprofile kavu = new Userprofile("Kavusikan", "Sivasub",
				"kevin@mail.com","1234","15/04/1998"
				,'M');
		allUsers.add(kevinco);
		allUsers.add(anoj);
		allUsers.add(kavu);
		UsersPersistence up = new UsersPersistence(allUsers, file);
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
<<<<<<< HEAD

=======
>>>>>>> kevin2
