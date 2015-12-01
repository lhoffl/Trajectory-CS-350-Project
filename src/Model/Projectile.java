package Model;


public enum Projectile {

	Default("Default", 250, .5),
	GolfBall("Golf Ball", .046, .043),
	WaterMelon("Water Melon", .254, 11.34),
	BowlingBall("Bowling Ball", 6.35, .218),
	Custom("Custom", 0.0, 0.0);

	private String name;
	private double mass;
	private double diameter;


	Projectile(String name, double mass, double diameter){

		this.name = name;
		this.mass = mass;
		this.diameter = diameter;
	}

	public void setName(String name){

		this.name = name;
	}

	public void setMass(double mass){

		this.mass = mass;
	}

	public void setDiameter(double diameter){

		this.diameter = diameter;
	}

	public double getDiameter(){

		return diameter;
	}

	public double getMass(){

		return mass;
	}

	public String getName(){

		return name;
	}

	@Override
	public String toString(){
		return name;
	}

}
