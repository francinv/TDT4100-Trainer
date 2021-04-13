package persistence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.Userprofile;
import core.Workout;

public class UsersPersistence implements filePersistence {

public ArrayList<String> list = new ArrayList<String>();
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
	
	public static void main(String[] args) {
		String file = "src/main/java/persistence/allUsers.txt";
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

