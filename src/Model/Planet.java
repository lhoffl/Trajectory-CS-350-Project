package Model;

import java.awt.Image;

public enum Planet {
	MERCURY(3.7, true, null), 
	VENUS(8.87, true, null),
	EARTH(9.788, true, null),
	MARS(3.71, true, null),
	JUPITER(24.92, true, null),
	SATURN(10.44, true, null),
	URANUS(8.87, true, null),
	NEPTUNE(11.15, true, null),
	PLUTO(0.58, false, null),
	LUNA(1.62, false, null),
	CUSTOM(0.0, false, null);
	
	private final double surfaceGravity;
	private final boolean atmosphere;
	private final Image background;
	
	PlanetType(double surfaceGravity, boolean atmosphere, Image background){
		this.surfaceGravity = surfaceGravity;
		this.atmosphere = atmosphere;
		this.background = background;
	}
	
	
}
	

