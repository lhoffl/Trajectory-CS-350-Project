package Model;

import java.awt.Color;

public enum ColorSet {
	
	/** set of colors for planet Mercury */
	COLORSET_MERCURY(Color.DARK_GRAY, Color.RED, Color.WHITE, Color.LIGHT_GRAY),
	
	/** set of colors for planet Venus */
	COLORSET_VENUS(Color.YELLOW, Color.RED, Color.BLACK, Color.BLACK),
	
	/** set of colors for planet Earth */
	COLORSET_EARTH(Color.BLUE, Color.CYAN, Color.WHITE, Color.BLACK),
	
	/** set of colors for planet Mars */
	COLORSET_MARS(Color.RED, Color.BLACK, Color.WHITE, Color.BLACK),
	
	/** set of colors for planet Jupiter */
	COLORSET_JUPITER(Color.ORANGE, Color.DARK_GRAY, Color.BLACK, Color.BLACK),
	
	/** set of colors for planet Saturn */
	COLORSET_SATURN(Color.CYAN, Color.BLUE, Color.BLACK, Color.BLACK),
	
	/** set of colors for planet Uranus */
	COLORSET_URANUS(Color.CYAN, Color.BLUE, Color.BLACK, Color.BLACK),
	
	/** set of colors for planet Neptune */
	COLORSET_NEPTUNE(Color.CYAN, Color.BLUE, Color.BLACK, Color.BLACK),
	
	/** set of colors for planet Pluto */
	COLORSET_PLUTO(Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK, Color.BLACK),
	
	/** set of colors for planet Luna */
	COLORSET_LUNA(Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK, Color.BLACK),
	
	/** set of colors for planet Custom */
	COLORSET_CUSTOM(Color.LIGHT_GRAY, Color.RED, Color.BLACK, Color.GREEN);
	
	/** color of the background */
	private Color background;
	
	/** color of the flagpole */
	private Color target;
	
	/** color of the trajectory arc */
	private Color arc;
	
	/** color of the break line */
	private Color break_line;
	
	/**
	 * Changes the color of the objects on the screen depending on the planet.
	 * @param Color background, sets the color of the background
	 * @param Color target, sets the color of the flagpole
	 * @param Color break_line, sets the color of the break line
	 */
	ColorSet(Color background, Color target, Color arc, Color break_line){
	
		this.background = background;
		this.target = target;
		this.arc = arc;
		this.break_line = break_line;
	}
	
	/**
	 * Paint method that draws the arc and target
	 * @return Color, returns the background color
	 */
	public Color getBackground(){
	
		return background;
	}
	
	/**
	 * Gets the color of the flagpole.
	 * @return Color, returns color of the flagpole
	 */
	public Color getTarget(){
	
		return target;
	}
	
	/**
	 * Gets the color of the trajectory arc.
	 * @return Color, returns the color of the trajectory arc
	 */
	public Color getArc(){
	
		return arc;
	}
	
	/**
	 * Gets the color of the break line.
	 * @return Color, returns the color of the break line
	 */
	public Color getBreak(){
	
		return break_line;
	}
	
}
