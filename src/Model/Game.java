package Model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Trajectory! - a physics based game, where the user aims to hit a random target
 * 
 * @author Matthew Hoffman, Ian Mohr, David Fletcher
 * @version Release One | Last Updated: 10/6/2015
 */
public class Game {
	
	/* the value of gravity on Earth */
	private double gravity = 9.788;
	
	/* the current X position of the ball */
	private double positionX = 0.0;
	
	/* the current Y position of the ball */
	private double positionY = 0.0;
	
	/* the current X velocity of the ball */
	private double velocityX = 0.0;
	
	/* the current Y velocity of the ball */
	private double velocityY = 10.0;
	
	/* constant used to calculate velocities */
	private double changeInTime = 0.01;
	
	/* is the ball currently moving */
	private boolean inMotion = true;
	
	/* counts the number of bounces that have occurred */
	private int bounceCounter = 0;
	
	/* the maximum number of bounces allowed */
	private int maxBounce = 1;
	
	/* truncates double values for readability/calculations */
	private DecimalFormat df;
	
	/* contains all the X positions the ball traveled through */
	private ArrayList<Double> pathX;
	
	/* contains all the Y positions the ball traveled through */
	private ArrayList<Double> pathY;
	
	/* contains all the X positions the ball traveled through */
	private ArrayList<Double> pathXAir;
	
	/* contains all the Y positions the ball traveled through */
	private ArrayList<Double> pathYAir;

	/* the width of the 'board' area */
	private int width;
	
	/* the height of the 'board' area */
	private int height;
	
	/* contains the X positions the target is located in*/
	private ArrayList<Double> targetX;
	
	/* contains the Y positions the target is located in*/
	private ArrayList<Double> targetY;

	/* the number of targets the player has hit this game */
	private int score;
	
	/**
	 * Constructor that initializes the game
	 */
	public Game(){
		// instantiate the path array lists
		pathX = new ArrayList<Double>();
		pathY = new ArrayList<Double>();
		
		// instantiate the path array lists
		pathXAir = new ArrayList<Double>();
		pathYAir = new ArrayList<Double>();
		
		// instantiate the target array lists
		targetX = new ArrayList<Double>();
		targetY = new ArrayList<Double>();
		
		//set the default 'board' area
		height = 100;
		width = 500;
		
		//instantiate the decimal format for the doubles
		df = new DecimalFormat("#.###");

		//create an initial target
		this.newTarget();
		
		// reset the score
		score = 0;
	}
	
	/**
	 * Creates a new random target within a certain area of the 'board'
	 */
	public void newTarget(){
		
		// If a target exists remove it 1
		targetX.clear();
		targetY.clear();
		
		Random rand = new Random();
		
		// variables used to determine where the target may spawn
		double rangeMax = width-1;
		double rangeMin = width/2;
		
		// temporary variables used to store the targets current location
		double tempX, tempY;
		
		// create a random (X,Y) pair for the target
		tempX = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
		//tempY = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();		//for use in a later release
		
		// the Y coordinate will always be on the X-axis for release one
		tempY = 0;
		
		//add the random starting point to the target area
		targetX.add(tempX);
		targetY.add(tempY);
		
		// variable used to determine if the area should be above or below the Y value
		boolean Ydirection = false; 
		if (tempY - 1 > 0){
			Ydirection = true;
		}
		
		// Calculate target area 
		for(int i = 0; i < 100; i++){
				
				//modify current location
				tempX += 0.01;
				
				// based on the Y value starting location, either build up or down
				if(Ydirection)
					tempY -= 0.01;
				else
					tempY += 0.01;
				
				//add them to the target area
				targetX.add(tempX);
				targetY.add(tempY);
		}
	}
	
	/**
	 * Function that determines whether or not the target was hit
	 * @return a boolean, true if the target was hit, false otherwise
	 */
	public boolean hitTarget(){
		
		int size;
		if(pathX.size() > pathXAir.size()){
			size = pathX.size();
		}
		else{
			size = pathXAir.size();
		}
		// Look through all the possible path values
		for(int i = 0; i < size; i++){
			// Look through all the target area
			for(int j = 0; j < targetX.size(); j++){
				
				boolean checkX = false, checkY = false;
				
			    //covert points to 3 decimal places and check if the target was hit
				if(size <= pathX.size()){
					if(Math.abs(Double.parseDouble(df.format(pathX.get(i))) - Double.parseDouble(df.format(targetX.get(j)))) <= 0.5)
						checkX = true;
					if(Math.abs(Double.parseDouble(df.format(pathY.get(i))) - Double.parseDouble(df.format(targetY.get(j)))) <= 0.5)
						checkY = true;
				}
				if(size <= pathXAir.size()){
					if(Math.abs(Double.parseDouble(df.format(pathXAir.get(i))) - Double.parseDouble(df.format(targetX.get(j)))) <= 0.5)
						checkX = true;
					if(Math.abs(Double.parseDouble(df.format(pathYAir.get(i))) - Double.parseDouble(df.format(targetY.get(j)))) <= 0.5)
						checkY = true;
				}
			
				// the target was hit
				if(checkX && checkY){
					score++;
					return true;
				}
			}
		}
		
		// the target was not hit
		return false;
	}
	
	// ???
	public boolean targetContains(double x, double y){
		
		for(int i = 0; i < targetX.size(); i++){
			boolean checkX = false, checkY = false;
			
		    //covert points to 3 decimal places and check if the target was hit
			if(Math.abs(Double.parseDouble(df.format(x)) - Double.parseDouble(df.format(targetX.get(i)))) <= 0.1)
				checkX = true;
			
			if(Math.abs(Double.parseDouble(df.format(y)) - Double.parseDouble(df.format(targetY.get(i)))) <= 0.1)
				checkY = true;
			
			// the target was hit
			if(checkX && checkY)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Function that converts user input double variables into the X and Y velocities of the ball
	 * @param velX, the ball's X velocity
	 * @param velY, the ball's Y velocity
	 */
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
			
			while (inMotion && positionX < width) {
				
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
				
				//update the path of the ball
				pathX.add(positionX);
				pathY.add(positionY);			
			}			
		}
	
	public void throwBall(double velX, double velY, double mass, double diameter){

		int force = 1000;
		double time = 0.0;
		changeInTime = 0.01;
		double initialX = 0.0;
		double initialY = 0.0;
		velocityX = velX;
		velocityY = velY;
		
		double accelerationX = 0.0;
		double accelerationY = 0.0;
		System.out.println("Mass: " + mass);
		System.out.println("Diameter: " + diameter);

		double linearDrag = (1.6 * Math.pow(10,-4)) * diameter;
		double quadDrag = 0.25 * (Math.pow(diameter,2));

		for(int i = 0; i < force; i++){

			//Iterate time
			time += changeInTime;

			//Calculate accelerations
			accelerationX = -(linearDrag/mass)*velX-(quadDrag/mass)*velX*Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));
			accelerationY = -gravity-(linearDrag/mass)*velY-(quadDrag/mass)*velY*Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));

			//Calculate new velocities
			velX += accelerationX * changeInTime;
			velY += accelerationY * changeInTime;

			//Calculate the new positions
			initialX += velX * changeInTime;
			initialY += velY * changeInTime;
			
			pathXAir.add(initialX);
			pathYAir.add(initialY);
			System.out.printf("%d, x: %f, y: %f\n", i, initialX, initialY);
			
			if(initialY <= 0 || initialX > width){
				break;
			}
		}
	}
	
	/**
	 * Returns the current X starting point of the target
	 * @return targetX
	 */
	public double getTargetX(int i){
		return targetX.get(i);
		//return df.format(targetX.get(0));
	}
	
	public String getTargetX(){
		return df.format(targetX.get(0));
	}
	
	/**
	 * Returns the current X path of the ball
	 * @return pathX
	 */
	public double getPathX(int i){
		if(i < getPathSize())
			return pathX.get(i);
		return 0;
	}
	
	public int getPathSize(){
		return pathX.size();
	}
	
	/**
	 * Returns the current Y starting point of the target
	 * @return targetY
	 */
	public double getTargetY(int i){
		return targetY.get(i);
		//return df.format(targetY.get(0));
	}
	
	public String getTargetY(){
		return df.format(targetY.get(0));
	}
	
	/**
	 * Returns the current Y path of the ball
	 * @return pathY
	 */
	public double getPathY(int i){
		if(i < getPathSize())
			return pathY.get(i);
		return 0;
	}
	

	public int getPathSizeAir() {
		return pathXAir.size();
	}

	public double getPathXAir(int i) {
		if(i < getPathSizeAir())
			return pathXAir.get(i);
		return 0;
	}

	public double getPathYAir(int i) {
		if(i < getPathSizeAir()){
			return pathYAir.get(i);
		}
		return 0;
	}
	
	/**
	 * Assigns an input value to the ball's X velocity
	 * @param x
	 */
	public void setVelX(double x){
		velocityX = x;
	}
	
	/**
	 * Assigns an input value to the ball's Y velocity
	 * @param y
	 */
	public void setVelY(double y){
		velocityY = y;
	}
	
	/**
	 * Returns the ball's current X velocity
	 * @return velocityX
	 */
	public double getVelX(){
		return velocityX;
	}
	
	/**
	 * Returns the ball's current Y velocity
	 * @return velocityY
	 */
	public double getVelY(){
		return velocityY;
	}
	
	/**
	 * Returns the current height of the board
	 * @return height
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * Returns the current width of the board
	 * @return width
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * Returns the current score
	 * @return score
	 */
	public int getScore(){
		return score;
	}
	
	public void resetPath(){
		pathX = new ArrayList<Double>();
		pathY = new ArrayList<Double>();
		
		pathXAir = new ArrayList<Double>();
		pathYAir = new ArrayList<Double>();
	}
	
	/**
	 * Reset the game score to 0
	 */
	public void resetScore(){
		score = 0;
	}
	
	public void setGravity(double gravity){
		this.gravity = gravity;
	}
	
	public ArrayList<Double> PathX(){
		return pathX;
	}
	
	public ArrayList<Double> PathY(){
		return pathX;
	}
	
	public ArrayList<Double> PathXAir(){
		return pathX;
	}
	
	public ArrayList<Double> PathYAir(){
		return pathX;
	}

	
}
