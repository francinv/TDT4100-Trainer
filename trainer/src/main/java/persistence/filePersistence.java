package persistence;

import java.util.Collection;

public interface filePersistence {
	
	void readFile(String file);
	void writeFile();
	void updateFile(String file, String wantedID, Collection<String> allSubs);
	
	
	

}
