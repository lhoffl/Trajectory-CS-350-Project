package Model;


public class AirResistance {

	private double mass;
	private double gravity;
	private double diameter;
	private double linearDrag;
	private double quadDrag;
	private double initialX;
	private double initialY;
	private double velX;
	private double velY;
	private double time;
	private double changeInTime;
	private double force;
	private double accelerationX;
	private double accelerationY;
	private static AirResistance airGame;

	private AirResistance(){

	}

	public void throwBall(double velX, double velY, double mass, double diameter){

		force = 1000;
		time = 0.0;
		changeInTime = 0.01;
		initialX = 0.0;
		initialY = 0.0;
		this.velX = velX;
		this.velY = velY;
		this.mass = mass;
		this.diameter = diameter;
		accelerationX = 0.0;
		accelerationY = 0.0;
		System.out.println("Mass: " + mass);
		System.out.println("Diameter: " + diameter);

		linearDrag = (1.6 * (Math.pow(10,-4))) * diameter;
		quadDrag = 0.25 * (Math.pow(diameter,2));

		for(int i = 0; i < force; i++){

			//Iterate time
			time += changeInTime;

			//Calculate accelerations
			accelerationX = -(linearDrag/mass)*velX-(quadDrag/mass)*
					velX*Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));
			accelerationY = -gravity-(linearDrag/mass)*velY-(quadDrag/mass)*
					velY*Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));

			//Calculate new velocities
			velX += accelerationX * changeInTime;
			velY += accelerationY * changeInTime;

			//Calculate the new positions
			initialX = velX * changeInTime;
			initialY = velY * changeInTime;

			if (initialY <= 0)
				break;
		}
	}

	public void setGravity(double gravity){
		this.gravity = gravity;
	}

	public void setMass(double mass){
		this.mass = mass;
	}

	public void setDiameter(double diameter){
		this.diameter = diameter;
	}
}