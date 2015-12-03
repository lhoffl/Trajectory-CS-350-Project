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
	MERCURY("Mercury", 3.7, ColorSet.COLORSET_MERCURY), 
	
	/** Planet Venus */
	VENUS("Venus", 8.87, ColorSet.COLORSET_VENUS),
	
	/** Planet Earth */
	EARTH("Earth", 9.788, ColorSet.COLORSET_EARTH),
	
	/** Planet Mars */
	MARS("Mars", 3.71, ColorSet.COLORSET_MARS),
	
	/** Planet Jupiter */
	JUPITER("Jupiter", 24.92, ColorSet.COLORSET_JUPITER),
	
	/** Planet Saturn */
	SATURN("Saturn", 10.44, ColorSet.COLORSET_SATURN),
	
	/** Planet Uranus */
	URANUS("Uranus", 8.87, ColorSet.COLORSET_URANUS),
	
	/** Planet Neptune */
	NEPTUNE("Neptune", 11.15, ColorSet.COLORSET_NEPTUNE),
	
	/** Planet Pluto */
	PLUTO("Pluto", 0.58, ColorSet.COLORSET_PLUTO),
	
	/** Planet Luna */
	LUNA("Luna", 1.62, ColorSet.COLORSET_LUNA),
	
	/** Custom Planet */
	CUSTOM("Custom", 0.0, ColorSet.COLORSET_CUSTOM);

	/** String that holds the planet's name */
	private String name;
	
	/** Double that holds the gravity for the planet */
	private double surfaceGravity;
	
	/** Color of the planet */
	private ColorSet colors;
	
	/**
	 * Constructor that initializes a new Planet
	 * @param name sets the planet's name 
	 * @param surfaceGravity sets the planet's gravity
	 * @param Color sets the planet's color
	 */
	Planet(String name, double surfaceGravity, ColorSet colors){
		this.name = name;
		this.surfaceGravity = surfaceGravity;
		this.colors = colors;
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
	public ColorSet getColors(){
		return colors;
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
	

