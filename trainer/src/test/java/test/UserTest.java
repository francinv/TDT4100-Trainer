package test;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import core.Userprofile;
import core.Users;

class UserTest {

	@Test
	void testAddUser() {
		Users u = new Users();
		Userprofile kevinco = new Userprofile("Kevin", "Cornolis",
				"kevinco@ntnu.no","AAAAAAA14411515knkankfna","1998-04-15"
				,'M');
		Userprofile anoj = new Userprofile("Francin", "Vincent",
				"francin@mail.com","BBAAAAA14411515knkankfna","1998-04-15"
				,'M');
		Collection<Userprofile> allUsers = new ArrayList<Userprofile>();
		u.addUser(anoj);
		u.addUser(kevinco);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    u.addUser(kevinco);
		 });
	}

}
