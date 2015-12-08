package Model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlanetTest {

	@Test
	public void planetNameTest(){
		Planet Mercury = Planet.MERCURY;
		assertTrue(Mercury.toString().equals("Mercury"));
		Planet Venus = Planet.VENUS;
		assertTrue(Venus.toString().equals("Venus"));
		Planet Earth = Planet.EARTH;
		assertTrue(Earth.toString().equals("Earth"));
		Planet Custom = Planet.CUSTOM;
		Custom.setName("TEST");
		assertTrue(Custom.toString().equals("TEST"));
		Custom.setName("AGAIN");
		assertTrue(Custom.toString().equals("AGAIN"));
	}

	@Test
	public void planetGravityTest(){
		Planet Mercury = Planet.MERCURY;
		assertTrue(Mercury.getGravity() == 3.7);
		Planet Venus = Planet.VENUS;
		assertTrue(Venus.getGravity() == 8.87);
		Planet Earth = Planet.EARTH;
		assertTrue(Earth.getGravity() == 9.788);
		Planet Custom = Planet.CUSTOM;
		Custom.setGravity(100);
		assertTrue(Custom.getGravity() == 100.0);
		Custom.setGravity(0.5);
		assertTrue(Custom.getGravity() == 0.5);
	}

}
