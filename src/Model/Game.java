package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Game {

	private final double gravity = 9.8;
	
	private double positionX = 0.0;
	private double positionY = 0.0;
	
	private double velocityX = 0.0;
	private double velocityY = 10.0;
	
	private double time = 0.0;
	
	private static double changeInTime = 0.01;
	
	private boolean inMotion = true;
	
	private int bounceCounter = 0;
	
	private int maxBounce = 3;
	
	// ArrayLists for storing the final path of the ball
	private ArrayList<Double> pathX;
	private ArrayList<Double> pathY;
	
	// Dimensions of the board
	private int width = 20;
	private int height = 20;
	
	// Target position
	private double targetSpawnX;
	private double targetSpawnY;
	
	private ArrayList<Double> targetX;
	private ArrayList<Double> targetY;

	public void newTarget(){
		
		Random rand = new Random();
		
		double rangeMax = width-1;
		double rangeMin = width/2;
		
		targetX = new ArrayList<Double>();
		targetY = new ArrayList<Double>();

				
		targetSpawnX = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
		targetSpawnY = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
		
		// Calculate target area
		for(double i = targetSpawnX; i <= targetSpawnX+1; i += 0.01){
			for(double j = targetSpawnY; j >= targetSpawnY-1; j -= 0.01){
				targetX.add(i);
				targetY.add(j);
			}
		}
		
		for(int i = 0; i < targetX.size(); i++){
			System.out.printf("Target: (%.2f, %.2f)\n", targetX.get(i), targetY.get(i));
		}
		
		//System.out.printf("randomly generated target = (%f, %f)\n", targetX, targetY);
	}
	
	public boolean hitTarget(){
		
		return false;
	}
	
	public void throwBall(double posX, double posY, double velX, double velY) {
		
		positionX = posX;
		positionY = posY;
		velocityX = velX;
		velocityY = velY;
		
		width = 20;
		height = 20;
		
		pathX = new ArrayList<Double>();
		pathY = new ArrayList<Double>();
		
		while (inMotion) {
			
			//bounce and update
			if (positionY < 0){	
				velocityY = -velocityY;
				bounceCounter++;
			}
			
			time += changeInTime;
			
			//velocityX = velocityX			This will only change in air resistance
			
			//update velocity and position over time
			velocityY = velocityY - (gravity * changeInTime);
			positionX += velocityX * changeInTime;  //consider bouncing later
			positionY += (velocityY * changeInTime) - (1/2)*(gravity * (Math.pow(changeInTime, 2)));
			
			//stop the ball
			if (bounceCounter > maxBounce) {
				inMotion = false;
			}
			
			//update path of the ball
			pathX.add(positionX);
			pathY.add(positionY);
		}
		
		for(int i = 0; i < pathX.size(); i++){
			System.out.printf("Path: (%f, %f)\n", pathX.get(i), pathY.get(i));
		}
	}
	
	// run the game
	public static void main(String[] args){
		Game game = new Game();
		
		//throw the ball, posx, posy, velx, vely
		//game.throwBall(0,0,2,10);
		
		game.newTarget();
	}
	
}
