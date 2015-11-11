package Model;

import java.awt.Color;

/**
 * Planet class that allows the user to customize their
 * experience.
 * 
 * @author Matthew Hoffman, Ian Mohr, David Fletcher
 * @version Release Two | Last Updated: 11/11/2015
 */
public enum Planet {	

	/** Planet Mercury */
	MERCURY("Mercury", 3.7, Color.DARK_GRAY), 
	
	/** Planet Venus */
	VENUS("Venus", 8.87, Color.YELLOW),
	
	/** Planet Earth */
	EARTH("Earth", 9.788, Color.BLUE),
	
	/** Planet Mars */
	MARS("Mars", 3.71, Color.RED),
	
	/** Planet Jupiter */
	JUPITER("Jupiter", 24.92, Color.ORANGE),
	
	/** Planet Saturn */
	SATURN("Saturn", 10.44, Color.GRAY),
	
	/** Planet Uranus */
	URANUS("Uranus", 8.87, Color.CYAN),
	
	/** Planet Neptune */
	NEPTUNE("Neptune", 11.15, Color.CYAN),
	
	/** Planet Pluto */
	PLUTO("Pluto", 0.58, Color.LIGHT_GRAY),
	
	/** Planet Luna */
	LUNA("Luna", 1.62, Color.LIGHT_GRAY),
	
	/** Custom Planet */
	CUSTOM("Custom", 0.0, Color.GREEN);

	/** String that holds the planet's name */
	private String name;
	
	/** Double that holds the gravity for the planet */
	private double surfaceGravity;
	
	/** Color of the planet */
	private Color color;
	
	/**
	 * Constructor that initializes a new Planet
	 * @param name sets the planet's name 
	 * @param surfaceGravity sets the planet's gravity
	 * @param Color sets the planet's color
	 */
	Planet(String name, double surfaceGravity, Color color){
		this.name = name;
		this.surfaceGravity = surfaceGravity;
		this.color = color;
	}
	
	/**
	 * Sets the name of the planet
	 * @param name sets the planet's name
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Sets the gravity of the planet
	 * @param surfaceGravity sets the gravity of the planet
	 */
	public void setGravity(double surfaceGravity){
			this.surfaceGravity = surfaceGravity;
	}
	
	/**
	 * Changes the planet's color
	 * @param Color sets the color of the planet
	 */
	public void setBackground(Color color){
			this.color = color;
	}

	/**
	 * Returns the current planet's gravity
	 * @return returns gravity
	 */
	public double getGravity(){
		return surfaceGravity;
	}
	
	/**
	 * Gets the current planet's color
	 * @return returns planet's color
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * Returns the planet's name
	 * @return planet's name
	 */
	@Override
	public String toString(){
		return name;
	}
	
}
	

