package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Trajectory! - a physics based game, where the user aims to 
 * hit a random target
 * 
 * @author Matthew Hoffman, Ian Mohr, David Fletcher
 * @version Release Three | Last Updated: 12/09/2015
 */
public class Game {
	
	/* Instance of the Game class */
	private static Game game;

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
	private int maxBounce = 0;
	
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
	
	/** HashMap that stores the place number and name of the leaderboard */
	private HashMap<Integer, String> leaderboard;
	
	/** shot number of the current turn */
	private int shotNumber;

	/** if the game is only vaccuum */
	private final int vaccuumOnly = 0;
	
	/** vaccuum and air resistance modes */
	private final int bothModes = 1;
	
	/** keeps track of which game mode is being played */
	private int mode;
	
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
		height = 300;
		width = 500;
		
		//instantiate the decimal format for the doubles
		df = new DecimalFormat("#.###");

		//create an initial target
		this.newTarget();
		retrieveLeaderboard();
		
		// reset the score
		score = 0;
		
		mode = vaccuumOnly;
	}
	
	/**
	 * Creates a new instance of the Game class if there isn't one yet created.
	 * @return returns a new Game instance
	 */
	public static Game getGameObject(){
	
		if(game == null)
			game = new Game();
		return game;
	}
	
	/**
	 * Displays the leaderboard.
	 * @return String, returns the names of the leaderboard
	 */
	public String printLeaderboard(){
		String printMe = "";
		for(int i = 1; i < leaderboard.size()+1; i++){
			printMe += i + "     " + leaderboard.get(i) +"\n";
		}
		
		return printMe;
	}
	
	/**
	 * Creates a new random target within a certain area of the 'board'
	 */
	public void newTarget(){

		// If a target exists remove it 1
		targetX.clear();
		targetY.clear();

		//New random instance
		Random rand = new Random();

		// variables used to determine where the target may spawn
		double rangeMax = width-1;
		double rangeMin = width/2;

		// temporary variables used to store the targets current location
		double tempX = 0, tempY = 0;

		// create a random (X,Y) pair for the target
		tempX = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
	
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
	
	/**
	 * Function that determines whether or not the target was hit
	 * @param double x is the x value for the path
	 * @param double y is the y value for the path
	 * @return true if the target contains the arcs path, false otherwise
	 */
	public boolean targetContains(double x, double y){
		
		//Loops through the target ArrayList
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
			
			//While the arc is generating and the arc is still on the screen
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
	
	/**
	 * Function that converts user input double variables into the X and Y velocities of the ball
	 * @param velX, the ball's X velocity
	 * @param velY, the ball's Y velocity
	 * @param mass, the ball's mass
	 * @param diameter, the ball's diameter
	 */
	public void throwBall(double velX, double velY, double mass, double diameter){

		int force = 1000;
		double time = 0.0;
		changeInTime = 0.01;
		double initialX = 0.0;
		double initialY = 0.0;
		velocityX = velX;
		velocityY = velY;
		pathXAir.clear();
		pathYAir.clear();
		
		double accelerationX = 0.0;
		double accelerationY = 0.0;

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
			
			if(initialY <= 0 || initialX > width){
				break;
			}
		}
	}
	
	/**
	 * Checks the score of the current game to the leaderboard.
	 * @param int score, the current score of the game
	 * @return returns the score of the leaderboard
	 */
	public int checkScore(int score){
		
		String currPlayer;
		String[] tokens;
		int currScore;
		
		System.out.println(leaderboard.size());
		for(int i = 1; i < leaderboard.size(); i++){
			currPlayer = leaderboard.get(i);
			tokens = currPlayer.split(":");
			currPlayer = tokens[0];
			currScore = Integer.parseInt(tokens[1]);
			
			if(score >= currScore) return i;
			
		}
		return -1;
	}
	
	/**
	 * Updates the leaderboard if the current score places high enough.
	 * @param int i is the index location on the leaderboard
	 * @param int score is the current score of the game
	 * @param name is the name of the current player
	 */
	public void updateLeaderboard(int i, int score, String name){
		int j = i;
		String temp = leaderboard.get(j);
		leaderboard.remove(j);
		leaderboard.put(j, name+":"+score);
		leaderboard.put(j+1, temp);
		
		// move the rest of the board down 1 place
		int x = j+2;
		temp = leaderboard.get(x);
		while(temp != null && x < leaderboard.size()){
			System.out.println(x + " " + temp);
			
			leaderboard.remove(x+1);
			leaderboard.put(x+1, temp);
			
			x++;
			temp = leaderboard.get(x+1);
		}
		saveLeaderboard();
	}
	
	/**
	 * Gets the most up to date leaderboard.
	 */
	public void retrieveLeaderboard(){
	
		leaderboard = new HashMap<Integer, String>();
		File file = new File("leaderboard.txt");
		Scanner sc;
		
		try {
			sc = new Scanner(file);
			while(sc.hasNext()){
				String temp = sc.nextLine();
				String[] tokens = temp.split(" ");
				int position = Integer.parseInt(tokens[0]);
				String player = tokens[1];
				leaderboard.put(position, player);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Saves the leaderboard for future use.
	 */
	public void saveLeaderboard(){
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("leaderboard.txt"), StandardCharsets.UTF_8))) {
			for(int i = 1; i < leaderboard.size()+1; i++){
				writer.write(i + " " + leaderboard.get(i) + '\n');
			}
		} catch (IOException ex) {
		}  
	}
	
	/**
	 * Returns the current X starting point of the target
	 * @param i is the index value
	 * @return targetX
	 */
	public double getTargetX(int i){
	
		return targetX.get(i);
	}
	
	/**
	 * Returns the current X starting point of the target
	 * @return targetX in string form
	 */
	public String getTargetX(){
	
		return df.format(targetX.get(0));
	}
	
	/**
	 * Returns the current X path of the ball
	 * @param i is the index location
	 * @return pathX
	 */
	public double getPathX(int i){
	
		if(i < getPathSize())
			return pathX.get(i);
		return 0;
	}
	
	/**
	 * Returns the size of the path
	 * @return size of path
	 */
	public int getPathSize(){
	
		return pathX.size();
	}
	
	
	/**
	 * Returns the size of the path
	 * @return size of path
	 */
	public int getAirPathSize(){
	
		return pathXAir.size();
	}

	
	/**
	 * Returns the current Y starting point of the target
	 * @param i is the index location
	 * @return targetY
	 */
	public double getTargetY(int i){
	
		return targetY.get(i);
	}
	
	/**
	 * Returns the location of the target
	 * @return the target location in String form
	 */
	public String getTargetY(){
	
		return df.format(targetY.get(0));
	}
	
	/**
	 * Returns the current Y path of the ball
	 * @param i is the index location
	 * @return pathY
	 */
	public double getPathY(int i){
	
		if(i < getPathSize())
			return pathY.get(i);
		return 0;
	}
	
	/**
	 * Returns the size of the path 
	 * @return int of path size for air resistance arc
	 */
	public int getPathSizeAir() {
	
		return pathXAir.size();
	}

	/**
	 * Returns the current X path of the ball for air resistance
	 * @param i is the index location
	 * @return pathXAir
	 */
	public double getPathXAir(int i) {
	
		if(i < getPathSizeAir())
			return pathXAir.get(i);
		return 0;
	}

	/**
	 * Returns the current Y path of the ball for air resistance
	 * @param i is the index location
	 * @return pathYAir
	 */
	public double getPathYAir(int i) {
	
		if(i < getPathSizeAir()){
			return pathYAir.get(i);
		}
		return 0;
	}
	
	/**
	 * Assigns an input value to the ball's X velocity
	 * @param x sets the global velocityX to input velocity
	 */
	public void setVelX(double x){
	
		velocityX = x;
	}
	
	/**
	 * Assigns an input value to the ball's Y velocity
	 * @param y sets the global velocityY to input velocity
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
	
	/**
	 * Resets the paths for both panels
	 */
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
	
	/**
	 * Sets the gravity for the algorithms
	 * @param gravity sets the global variable gravity
	 */
	public void setGravity(double gravity){
	
		this.gravity = gravity;
	}
	
	/**
	 * Returns the ArrayList that contains the path for X
	 * @return pathX
	 */
	public ArrayList<Double> PathX(){
	
		return pathX;
	}
	
	/**
	 * Returns the ArrayList that contains the path for Y
	 * @return pathY
	 */
	public ArrayList<Double> PathY(){
	
		return pathX;
	}
	
	/**
	 * Returns the ArrayList that contains the path for X
	 * @return pathXAir
	 */
	public ArrayList<Double> PathXAir(){
	
		return pathX;
	}
	
	/**
	 * Returns the ArrayList that contains the path for Y
	 * @return pathYAir
	 */
	public ArrayList<Double> PathYAir(){
	
		return pathX;
	}

	 /**
	 * Converts the inputs into a y component for the shot.
	 * @param velocity is the velocity the user inputs
	 * @param theta is the angle the user inputs
	 * @return returns a y-component double based on velocity, theta
	 */
	public double getYComponent(double velocity, double theta){
	
		double yComponent = velocity * Math.sin(theta);
		return yComponent;
	}

	 /**
	 * Converts the inputs into a x component for the shot.
	 * @param velocity is the velocity the user inputs
	 * @param theta is the angle the user inputs
	 * @return returns a x-component double based on velocity, theta
	 */
	public double getXComponent(double velocity, double theta){
	
		double xComponent = velocity * Math.cos(theta);
		return xComponent;
	}
	
	 /**
	 * Gets the current shot number for the turn.
	 * @return returns the current shot number
	 */
	public int getShotNumber(){
	
		return shotNumber;
	}

	/**
	 * Sets the shots to 0 after new turn.
	 */
	public void resetShotNumber(){
	
		shotNumber = 0;
	}

	/**
	 * Increments the shot by 1 after a user fires the projectile.
	 */
	public void incrementShotNumber(){
	
		shotNumber++;
	}
	
	/**
	 * Solves the current hole based on the velocity given by the user.
	 * @param velocity is the velocity input by the user.
	 * @param gravity is the current gravity of the planet.
	 * @param range is the location of the target.
	 * @return returns a theta that can be used to hit the target.
	 */
	public double autoSolverTheta(double velocity, double gravity, double range){
	
		double theta;

		theta = (range * gravity) / (Math.pow(velocity,  2));
		if (theta > 1.0 || theta < -1.0){
			return -1000;
		}
		else{
			theta = Math.asin(theta);
			theta = Math.toDegrees(theta);
			theta = theta / 2;
		}
		return theta;
	}
	
	/**
	 * Gets the current mode of the game, either vaccuum or vaccuum and air resistance.
	 * @return returns the current mode of the game.
	 */
	public int getMode(){
	
		return mode;
	}
	
	/**
	 * Sets the mode to either vaccuum or vaccuum and air resistance.
	 * @param the mode of the current game
	 */
	public void setMode(int mode){
	
		if(mode == vaccuumOnly || mode == bothModes)
			this.mode = mode;
	}
	
	/**
	 * Sets the width of the game.
	 * @param sets the width of the game.
	 */
	public void setWidth(int width){
	
		this.width = width;
	}
}
