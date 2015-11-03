package Model;

import java.awt.Color;

public enum Planet {	
	MERCURY("Mercury", 3.7, Color.DARK_GRAY), 
	VENUS("Venus", 8.87, Color.YELLOW),
	EARTH("Earth", 9.788, Color.BLUE),
	MARS("Mars", 3.71, Color.RED),
	JUPITER("Jupiter", 24.92, Color.ORANGE),
	SATURN("Saturn", 10.44, Color.GRAY),
	URANUS("Uranus", 8.87, Color.CYAN),
	NEPTUNE("Neptune", 11.15, Color.CYAN),
	PLUTO("Pluto", 0.58, Color.LIGHT_GRAY),
	LUNA("Luna", 1.62, Color.LIGHT_GRAY),
	CUSTOM("Custom", 0.0, Color.GREEN);

	private String name;
	private double surfaceGravity;
	private Color color;
	
	Planet(String name, double surfaceGravity, Color color){
		this.name = name;
		this.surfaceGravity = surfaceGravity;
		this.color = color;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public void setGravity(double surfaceGravity){
			this.surfaceGravity = surfaceGravity;
	}
	
	public void setBackground(Color color){
			this.color = color;
	}

	public double getGravity(){
		return surfaceGravity;
	}
	
	public Color getColor(){
		return color;
	}
	
	@Override
	public String toString(){
		return name;
	}
	
}
	

