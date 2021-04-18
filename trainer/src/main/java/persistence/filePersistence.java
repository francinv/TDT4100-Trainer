package src.main.java.persistence;

import java.util.List;

public interface filePersistence {
	
	void readFile(String file);
	void writeFile();
	void updateFile(String file, String identifier, List<String> list);
	
	
	

}
