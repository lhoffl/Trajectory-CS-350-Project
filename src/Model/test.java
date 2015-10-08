package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class test {

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
		
		for(int i = 0; i < game.getPathX().size() - 1; i++){
			assertTrue(game.getPathX().get(i) != game.getPathX().get(i+1));
		}
		
		game.throwBall(100, 700);
		
		for(int i = 0; i < game.getPathX().size() - 1; i++){
			assertTrue(game.getPathX().get(i) != game.getPathX().get(i+1));
		}
		
		game.throwBall(0, 0);
		
		for(int i = 0; i < game.getPathX().size() - 1; i++){
			assertTrue(game.getPathX().get(i) != game.getPathX().get(i+1));
		}
	}
	
	

}
