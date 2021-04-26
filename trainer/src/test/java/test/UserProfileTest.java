package test;

import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import core.Userprofile;

class UserProfileTest {
	
	private Userprofile user;

	

	@BeforeEach
	void setUp() {
		user = new Userprofile();
	}
	
	@Test
	void testBirthday() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			LocalDate wrongdate = LocalDate.of(2021, 04, 27);
			user.setBirthday(wrongdate);
		});
		LocalDate correctdate = LocalDate.of(1998, 04, 15);
		user.setBirthday(correctdate);
		Assertions.assertEquals(correctdate, user.getBirthday());
	}
	
	@Test
	void testGender () {
		//List<Character>legalGenders = Arrays.asList('F','M');
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			user.setGender('Ø');
		 });
		user.setGender('F');
		Assertions.assertEquals('F', user.getGender());
		user.setGender('M');
		Assertions.assertEquals('M', user.getGender());
	}
	
	@Test
	void testEmail() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    user.setEmail("derp.com");
		 });
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    user.setEmail("derp@.com");
		 });
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    user.setEmail("derp@com");
		 });
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    user.setEmail("d@erp@com");
		 });
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    user.setEmail("@derp.com");
		 });
		user.setEmail("derp@abc.com");
		Assertions.assertEquals("derp@abc.com", user.getEmail());
		
	}
	
	@Test
	void testPassword() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    user.setPassword("123");
		 });
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    user.setPassword("abjdb@$&//fnslv");
		 });
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    user.setPassword("1234sajhgdhjkgj");
		 });
		user.setPassword("Abs22234555d");
		Assertions.assertEquals("Abs22234555d", user.getPassword());
		user.setPassword("N1234sajhgdhjkgj");
		Assertions.assertEquals("N1234sajhgdhjkgj", user.getPassword());
	}
	
	
	@Test
	void testSetName() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    user.setFirstName("kadls141");
		 });
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    user.setFirstName("fal faf");
		 });
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    user.setFirstName("fjiw@@%");
		 });
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    user.setLastName("kadls141");
		 });
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    user.setLastName("fal faf");
		 });
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    user.setLastName(" ");
		 });
		
	}
	
	@Test
	void testGetName() {
		user.setFirstName("Francin");
		user.setLastName("Vincent");
		Assertions.assertEquals("Francin Vincent", user.getName());
	}
	
	

}
