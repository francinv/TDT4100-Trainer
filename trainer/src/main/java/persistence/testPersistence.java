package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class testPersistence {
	final static String filePath = "src/main/java/persistence/allUsers.txt";
	
	public static void main(String[] args)
	{
	
	    // read text file to HashMap
	    Map<String, String> mapFromFile
	        = HashMapFromTextFile();
	
	    // iterate over HashMap entries
	    Iterator it = mapFromFile.entrySet().iterator();
	    while (it.hasNext()) {
	    	Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    System.out.println(mapFromFile.get("email"));
	}

	public static Map<String, String> HashMapFromTextFile()
	{
	
	    Map<String, String> map = new HashMap<String, String>();
	    BufferedReader br = null;
	
	    try {
	
	        // create file object
	        File file = new File(filePath);
	
	        // create BufferedReader object from the File
	        br = new BufferedReader(new FileReader(file));
	
	        String line = null;
	
	        // read file line by line
	        while ((line = br.readLine()) != null) {
	
	            // split the line by :
	            String[] parts = line.split(":");
	
	            // first part is name, second is number
	            String name = parts[0].trim();
	            String number = parts[1].trim();
	
	            // put name, number in HashMap if they are
	            // not empty
	            if (!name.equals("") && !number.equals(""))
	                map.put(name, number);
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	
	        // Always close the BufferedReader
	        if (br != null) {
	            try {
	                br.close();
	            }
	            catch (Exception e) {
	            };
	        }
	    }
	
	    return map;
	}
}

