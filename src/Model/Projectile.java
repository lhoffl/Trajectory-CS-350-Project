package Model;


/**
 * Planet class that allows the user to customize their
 * experience.
 * 
 * @author Matthew Hoffman, Ian Mohr, David Fletcher
 * @version Release Three | Last Updated: 12/09/2015
 */
public enum Projectile {

	/** Default projectile to be thrown */
	Default("Default", 250, .5),
	
	/** If user selects golf ball projectile */
	GolfBall("Golf Ball", .046, .043),
	
	/** If user selects watermelon projectile */
	WaterMelon("Water Melon", .254, 11.34),
	
	/** If user selects bowling ball projectile */
	BowlingBall("Bowling Ball", 6.35, .218),
	
	/** User can input own mass and diameter for projectile */
	Custom("Custom", 0.0, 0.0);

	/** name of the projectile */
	private String name;
	
	/** mass of the projectile */
	private double mass;
	
	/** diameter of the projectile */
	private double diameter;

	/**
	 * Constructor that initializes a new Projectile
	 * @param name sets the projectile's name 
	 * @param mass sets the projectile's mass
	 * @param diameter sets the projectile's diameter
	 */
	Projectile(String name, double mass, double diameter){

		this.name = name;
		this.mass = mass;
		this.diameter = diameter;
	}

	/**
	 * Sets the name of the projectile.
	 * @param name sets the projectile's name 
	 */
	public void setName(String name){

		this.name = name;
	}

	/**
	 * Sets the mass of the projectile.
	 * @param double that sets the projectile's mass
	 */
	public void setMass(double mass){

		this.mass = mass;
	}

	/**
	 * Sets the diameter of the projectile.
	 * @param double that sets the projectile's diameter
	 */
	public void setDiameter(double diameter){

		this.diameter = diameter;
	}

	/**
	 * Gets the current diameter of the projectile.
	 * @return returns the diameter of the projectile.
	 */
	public double getDiameter(){

		return diameter;
	}

	/**
	 * Gets the current mass of the projectile.
	 * @return returns the mass of the projectile.
	 */
	public double getMass(){

		return mass;
	}

	/**
	 * Gets the current name of the projectile.
	 * @return returns the name of the projectile.
	 */
	public String getName(){

		return name;
	}

	/**
	 * Returns the name of the projectile.
	 */
	@Override
	public String toString(){
	
		return name;
	}

}
