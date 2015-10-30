package Model;

import java.awt.Image;

public class Planet{
	
	private String name;
	private PlanetType type;
	private double gravity;
	private boolean hasAtmosphere;
	private Image background;
	
	public Planet(String name, PlanetType type, double gravity, boolean hasAtmosphere){
		if(type == PlanetType.Custom){
			this.name = name;
			this.gravity = gravity;
			this.hasAtmosphere = hasAtmosphere;
		}else{
			this.name = type.toString();
		}
	}
	
	public String getName(){
		return name;
	}
	
	public double getGravity(){
		switch(type){
		case Mercury:
			return 1.0;
		case Venus:
			return 1.0;
		case Earth:
			return 9.8;
		case Mars:
			return 1.0;
		case Jupiter:
			return 1.0;
		case Saturn:
			return 1.0;
		case Uranus:
			return 1.0;
		case Neptune:
			return 1.0;
		case Pluto:
			return 1.0;
		case Custom:
			return gravity;
		}
		return -1.0;
	}
	
	public boolean hasAtmosphere(){
		switch(type){
		case Mercury:
			return true;
		case Venus:
			return true;
		case Earth:
			return true;
		case Mars:
			return true;
		case Jupiter:
			return true;
		case Saturn:
			return true;
		case Uranus:
			return true;
		case Neptune:
			return true;
		case Pluto:
			return true;
		case Custom:
			return hasAtmosphere;
		}
		return false;
	}
	
	public Image getBackground(){
		switch(type){
		case Mercury:
			return null;
		case Venus:
			return null;
		case Earth:
			return null;
		case Mars:
			return null;
		case Jupiter:
			return null;
		case Saturn:
			return null;
		case Uranus:
			return null;
		case Neptune:
			return null;
		case Pluto:
			return null;
		case Custom:
			return background;
		}
		return -1.0;
	}
}
