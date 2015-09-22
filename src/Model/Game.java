package Model;

public class Game {

	private final double gravity = 9.8;
	
	private double positionX = 0.0;
	
	private double positionY = 0.0;
	
	private double velocityX = 0.0;
	
	private double velocityY = 10.0;
	
	private double time = 0.0;
	
	private double changeInTime = 0.01;
	
	private boolean inMotion = true;
	
	private int bounceCounter = 0;
	
	private int maxBounce = 3;
	
	
	public void throwBall(double posX, double posY, double velX, double velY) {
		
		positionX = posX;
		positionY = posY;
		velocityX = velX;
		velocityY = velY;
		
		while (inMotion) {
			
			if (positionY < 0){
				
				velocityY = -velocityY;
				bounceCounter++;
			}
			
			time += changeInTime;
			//velocityX = velocityX			This will only change in air resistance
			velocityY = velocityY - (gravity * changeInTime);
			positionX += velocityX * changeInTime;  //consider bouncing later
			positionY += (velocityY * changeInTime) - (1/2)*(gravity * (Math.pow(changeInTime, 2)));
			
			if (bounceCounter > maxBounce) {
				inMotion = false;
			}
			
			System.out.println("Y position = " + positionY);
		}
		
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.throwBall(0.0,0.0,0.0,10.0);
	}
	
}
