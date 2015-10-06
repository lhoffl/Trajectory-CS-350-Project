package Model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Trajectory! - does stuff
 * 
 * @author Matthew Hoffman, Ian Mohr, David Fletcher
 * @version 10/6/2015
 */
public class Game {

	private final double gravity = 9.8;
	
	// current ball position
	private double positionX = 0.0;
	private double positionY = 0.0;
	
	// current ball velocity
	private double velocityX = 0.0;
	private double velocityY = 10.0;
	
	private final double changeInTime = 0.01;
	
	private boolean inMotion = true;
	
	private int bounceCounter = 0;
	private int maxBounce = 3;
	
	private DecimalFormat df;
	
	// ArrayLists for storing the final path of the ball
	private ArrayList<Double> pathX;
	private ArrayList<Double> pathY;

	// Dimensions of the board
	private int width;
	//private int height;
	
	// ArrayLists for storing the entire target area
	private ArrayList<Double> targetX;
	private ArrayList<Double> targetY;
	
	private static Scanner sc;

	public Game(){
		pathX = new ArrayList<Double>();
		pathY = new ArrayList<Double>();
		
		targetX = new ArrayList<Double>();
		targetY = new ArrayList<Double>();
		
		//height = 20;
		width = 20;
		
		df = new DecimalFormat("#.###");
		
		sc = new Scanner(System.in);
	}
	
	public void newTarget(){
		
		Random rand = new Random();
		
		double rangeMax = width-1;
		double rangeMin = width/2;
		
		double tempX, tempY;
		tempX = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
		tempY = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
		
		// reset the target area
		targetX.clear();
		targetY.clear();
		
		//test point
		tempX = 20;
		tempY = 0;
		
		// Calculate target area 
		for(int i = 0; i < 100; i++){
				
				//modify current location
				tempX += 0.01;
				tempY -= 0.01;
				
				//add them to the target area
				targetX.add(tempX);
				targetY.add(tempY);
		}
	}
	
	// did you hit the target?
	public boolean hitTarget(){
		
		for(int i = 0; i < pathX.size(); i++){
			for(int j = 0; j < targetX.size(); j++){
				
			    //covert points to 3 decimal places and check if the target was hit
				boolean checkX = false, checkY = false;
				
				if(Math.abs(Double.parseDouble(df.format(pathX.get(i))) - Double.parseDouble(df.format(targetX.get(j)))) <= 0.0001){
					checkX = true;
				}
				if(Math.abs(Double.parseDouble(df.format(pathX.get(i))) - Double.parseDouble(df.format(targetX.get(j)))) <= 0.0001){
					checkY = true;
				}
				
				// the target was hit
				if(checkX && checkY){
					return true;
				}
			}
		}
		return false;
	}
	
	public void throwBall(double velX, double velY) {
		
		//initialize starting position and velocity
		positionX = 0;
		positionY = 0;
		velocityX = velX;
		velocityY = velY;
		
		// empty a path if one existed 
		pathX.clear();
		pathY.clear();
		
		// reset counters and motion
		bounceCounter = 0;
		inMotion = true;
		
		while (inMotion) {
			
			//bounce and update
			if (positionY < 0){	
				velocityY = -velocityY;
				bounceCounter++;
			}
						
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
	}
	
	/**
	 * 
	 */
	public static void main(String[] args){
		Game game = new Game();
		double input;
		boolean win = false;
		game.newTarget();
		
		while(!win){
			System.out.print("Please enter a velocity: ");
			input = Double.parseDouble(sc.nextLine());
			System.out.println("Entered velocity: " + input);
			game.throwBall(input, game.gravity);
			if(game.hitTarget()){
				System.out.println("nice\n");
				win = true;
			}
		}
	}
	
	/**
	 * 
	 * @return targetX
	 */
	public ArrayList<Double> getTargetX(){
		return targetX;
	}
	
	/**
	 * 
	 * @return pathX
	 */
	public ArrayList<Double> getPathX(){
		return pathX;
	}
	
	/**
	 * 
	 * @return targetY
	 */
	public ArrayList<Double> getTargetY(){
		return targetY;
	}
	
	/**
	 * 
	 * @return pathY
	 */
	public ArrayList<Double> getPathY(){
		return pathY;
	}	
}
