package persistence;

import java.util.Collection;
import java.util.List;

import core.Userprofile;

public interface filePersistence {
	
	void readFile(String file);
	void writeFile();
	void updateFile(String file, String identifier, Collection<Userprofile> list);
	
	
	

}
