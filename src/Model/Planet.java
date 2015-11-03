package Model;

import java.awt.Color;

public enum Planet {	
	MERCURY("Mercury", 3.7, true, Color.DARK_GRAY), 
	VENUS("Venus", 8.87, true, Color.YELLOW),
	EARTH("Earth", 9.788, true, Color.BLUE),
	MARS("Mars", 3.71, true, Color.RED),
	JUPITER("Jupiter", 24.92, true, Color.ORANGE),
	SATURN("Saturn", 10.44, true, Color.GRAY),
	URANUS("Uranus", 8.87, true, Color.CYAN),
	NEPTUNE("Neptune", 11.15, true, Color.CYAN),
	PLUTO("Pluto", 0.58, false, Color.LIGHT_GRAY),
	LUNA("Luna", 1.62, false, Color.LIGHT_GRAY),
	CUSTOM("Custom", 0.0, false, Color.GREEN);

	private String name;
	private double surfaceGravity;
	private boolean atmosphere;
	private Color color;
	
	Planet(String name, double surfaceGravity, boolean atmosphere, Color color){
		this.name = name;
		this.surfaceGravity = surfaceGravity;
		this.atmosphere = atmosphere;
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
	
	public void setAtmosphere(boolean atmosphere){
			this.atmosphere = atmosphere;
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
	

