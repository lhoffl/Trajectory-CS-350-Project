package Model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ProjectileTest {

	@Test
	public void projectileNameTest() {
		Projectile proj = Projectile.GolfBall;
		assertTrue(proj.getName().equals("Golf Ball"));
	}

	@Test
	public void projectileNameTest2() {
		Projectile proj = Projectile.BowlingBall;
		assertTrue(proj.getName().equals("Bowling Ball"));
	}

	@Test
	public void projectileNameTest3() {
		Projectile proj = Projectile.Custom;
		proj.setName("Test");
		assertTrue(proj.getName().equals("Test"));
	}

	@Test
	public void projectileNameTest4() {
		Projectile proj = Projectile.WaterMelon;
		assertTrue(proj.getName().equals("WaterMelon"));
	}

	@Test
	public void projectileMassTest() {
		Projectile proj = Projectile.GolfBall;
		assertTrue(proj.getMass() == .046);
	}

	@Test
	public void projectileMassTest2() {
		Projectile proj = Projectile.BowlingBall;
		assertTrue(proj.getMass() == 6.35);
	}

	@Test
	public void projectileMassTest3() {
		Projectile proj = Projectile.Custom;
		proj.setMass(.050);
		assertTrue(proj.getMass() == .050);
	}

	@Test
	public void projectileMassTest4() {
		Projectile proj = Projectile.WaterMelon;
		assertTrue(proj.getMass() == .254);
	}

	@Test
	public void projectileDiameterTest() {
		Projectile proj = Projectile.Default;
		assertTrue(proj.getDiameter() == .5);
	}

	@Test
	public void projectileDiameterTest2() {
		Projectile proj = Projectile.GolfBall;
		assertTrue(proj.getDiameter() == .043);
	}

	@Test
	public void projectileDiameterTest3() {
		Projectile proj = Projectile.WaterMelon;
		assertTrue(proj.getDiameter() == 11.34);
	}

	@Test
	public void projectileDiameterTest4() {
		Projectile proj = Projectile.BowlingBall;
		assertTrue(proj.getDiameter() == .218);
	}

	@Test
	public void projectileDiameterTest5() {
		Projectile proj = Projectile.Custom;
		proj.setDiameter(5);
		assertTrue(proj.getDiameter() == 5);
	}
}
