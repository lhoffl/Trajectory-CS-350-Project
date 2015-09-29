package Model;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("unused")
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
	private ArrayList<Point2D.Float> path;

	// Dimensions of the board
	private int width;
	private int height;
	
	// Target position
	private Point2D.Float targetPosition;
	private Point2D.Float pathPosition;
	
	private ArrayList<Point2D.Float> target;
	
	public Game(){
		path = new ArrayList<Point2D.Float>();
		target = new ArrayList<Point2D.Float>();
		
		targetPosition = new Point2D.Float();
		pathPosition = new Point2D.Float();

		height = 20;
		width = 20;
		
		Point2D.Float test = new Point2D.Float();
		test.setLocation(12, 2);
		
		//path.add(test);
		//target.add(test);
	}
	public void newTarget(){
		
		Random rand = new Random();
		
		double rangeMax = width-1;
		double rangeMin = width/2;
		
		double tempX, tempY;
		tempX = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
		tempY = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
		
		// Calculate target area 
		// Slightly fixed, off by 0.01 at the end
		for(int i = 0; i < 100; i++){
				
				//modify current location
				tempX += 0.01;
				tempY -= 0.01;
				
				//add them to the target area
				targetPosition.setLocation(tempX, tempY);
				target.add(targetPosition);
		}
		
//		for(int i = 0; i < targetX.size(); i++){
//			System.out.printf("Target: (%.2f, %.2f)\n", targetX.get(i), targetY.get(i));
//		}
		
		//System.out.printf("randomly generated target = (%f, %f)\n", targetX, targetY);
	}
	
	// doesn't work
	public boolean hitTarget(){
		
		for(int i = 0; i < path.size(); i++){
			for(int j = 0; j < target.size(); j++){
		
				if(path.get(i).equals(target.get(j))){
					return true;
				}
			}
		}
		System.out.println("Sorry you missed the target");
		return false;
	}
	
	public void throwBall(double posX, double posY, double velX, double velY) {
		
		//initialize starting position and velocity
		positionX = posX;
		positionY = posY;
		velocityX = velX;
		velocityY = velY;
		
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
			pathPosition.setLocation(positionX, positionY);
			path.add(pathPosition);
		}
		
//		for(int i = 0; i < pathX.size(); i++){
//			System.out.printf("Path: (%f, %f)\n", pathX.get(i), pathY.get(i));
//		}
	}
	
	// run the game
	public static void main(String[] args){
		Game game = new Game();
				
		boolean win = false;
		game.newTarget();
		
		while(!win){
			game.throwBall(0,0,2,10);
			if(game.hitTarget()){
				System.out.println("nice\n");
				win = true;
			}
		}
	}
	
}
