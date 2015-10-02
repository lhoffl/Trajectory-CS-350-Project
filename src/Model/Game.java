package Model;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.text.DecimalFormat;
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
	
	private DecimalFormat df;
	
	// ArrayLists for storing the final path of the ball
	private ArrayList<Double> pathX;
	private ArrayList<Double> pathY;
	

	// Dimensions of the board
	private int width;
	private int height;
	
	// Target position
	private double targetPositionX;
	private double targetPositionY;

	private double pathPositionX;
	private double pathPositionY;

	
	private ArrayList<Double> targetX;
	private ArrayList<Double> targetY;
	
	public Game(){
		pathX = new ArrayList<Double>();
		pathY = new ArrayList<Double>();

		targetX = new ArrayList<Double>();
		targetY = new ArrayList<Double>();

		height = 20;
		width = 20;
		
		df = new DecimalFormat("#.###");

	}
	public void newTarget(){
		
		Random rand = new Random();
		
		double rangeMax = width-1;
		double rangeMin = width/2;
		
		double tempX, tempY;
//		tempX = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
//		tempY = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
		tempX = 10;
		tempY = 2;
		
		// Calculate target area 
		// Slightly fixed, off by 0.01 at the end
		for(int i = 0; i < 10000; i++){
				
				//modify current location
				tempX += 0.01;
				tempY -= 0.01;
				
				//add them to the target area
				targetPositionX = tempX;
				targetPositionY = tempY;
				
				targetX.add(targetPositionX);
				targetY.add(targetPositionY);

		}
//		for(int i = 0; i < target.size(); i++){
//			System.out.println(target);
//		}
	}
	
	// did you hit the target????!?!?!?!
	public boolean hitTarget(){
		
		for(int i = 0; i < pathX.size(); i++){
			for(int j = 0; j < targetX.size(); j++){
			
				int checkX = Double.compare(Math.round(pathX.get(i)), Math.round(targetX.get(i)));
				int checkY = Double.compare(Math.round(pathY.get(i)), Math.round(targetY.get(i)));
				//System.out.printf("Path: (%f,%f)  |  Target: (%f,%f)\n", path.get(i).x, path.get(i).y, target.get(i).x, target.get(i).y);
				if(checkX == 0 && checkY == 0){
					return true;
				}
			}
		}
		//System.out.println("Sorry you missed the target");
		return false;
	}
	
	public void throwBall(double velX, double velY) {
		
		//initialize starting position and velocity
		positionX = 0;
		positionY = 0;
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
			pathPositionX = positionX;
			pathPositionY = positionY;
			
			pathX.add(pathPositionX);
			pathY.add(pathPositionY);			
		}
		for(int i = 0; i < pathX.size(); i++){
			System.out.println("(" + pathX.get(i) +" , " + pathY.get(i) +")");
		}
	}
	
	public ArrayList<Double> getTargetX(){
		return targetX;
	}
	
	public ArrayList<Double> getPathX(){
		return pathX;
	}
	
	// run the game
	public static void main(String[] args){
		Game game = new Game();
				
		boolean win = false;
		game.newTarget();
		
		while(!win){
			game.throwBall(10, 9.8);
			if(game.hitTarget()){
				System.out.println("nice\n");
				win = true;
			}
		}
	}
	
}
