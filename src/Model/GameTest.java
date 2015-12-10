package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {

	@Test
	public void randomTargetGenerationWorks() {
		
		Game game = new Game();
		game.newTarget();
		String one, two;
		one = game.getTargetX();
		game.newTarget();
		two = game.getTargetX();
		
		assertTrue(!one.equals(two));	
		
		game.newTarget();
		one = game.getTargetX();
		
		assertTrue(!one.equals(two));
	}	
	
	@Test
	public void hitTargetWorks() {
		
		Game game = new Game();
		game.newTarget();
		String one;
		one = game.getTargetX();
		
		game.throwBall(Double.parseDouble(one)/2, 9.8);
		boolean test = game.hitTarget();
		assertTrue(test == true);	
		
		one = game.getTargetX();
		
		game.throwBall(Double.parseDouble(one)/2, 9.8);
		test = game.hitTarget();
		assertTrue(test == true);	
		
		one = game.getTargetX();
		
		game.throwBall(0, 0);
		test = game.hitTarget();
		assertTrue(test == false);		
	}	
	
	@Test
	public void throwBallWorks(){
		
		Game game = new Game();
		game.newTarget();
		
		game.throwBall(-5, -9.8);
		
		for(int i = 0; i < game.getPathSize() - 1; i++){
			assertTrue(game.getPathX(i) != game.getPathX(i+1));
		}
		
		game.throwBall(100, 700);
		
		for(int i = 0; i < game.getPathSize() - 1; i++){
			assertTrue(game.getPathX(i) != game.getPathX(i+1));
		}
	}

	@Test
	public void testGetVelX(){
		Game game = new Game();
		game.setVelX(10);
		double newVel = game.getVelX();
		assertTrue(newVel == 10);
	}

	@Test
	public void testGetVelY(){
		Game game = new Game();
		game.setVelY(10);
		double newVelY = game.getVelY();
		assertTrue(newVelY == 10.0);
	}

	@Test
	public void testScore(){
		Game game = new Game();
		game.resetScore();
		assertTrue(game.getScore() == 0);
	}
	
	@Test
	public void testResetPath(){
		Game game = new Game();
		game.throwBall(10, 10);
		game.resetPath();
		
		assertTrue(game.getPathSize() == 0);
	}
	
	@Test
	public void airResistanceWorks(){
			Game game = new Game();
			
			game.throwBall(-5, -9.8, 10, 10);
			
			for(int i = 0; i < game.getPathSizeAir() - 1; i++){
				assertTrue(game.getPathXAir(i) != game.getPathXAir(i+1));
			}
			
			game.throwBall(100, 700, 4, 6);
			
			for(int i = 0; i < game.getPathSize() - 1; i++){
				assertTrue(game.getPathXAir(i) != game.getPathXAir(i+1));
			}
	}
	
	
}
	
