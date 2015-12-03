package Model;

import java.awt.Color;

public enum ColorSet {
	
	COLORSET_MERCURY(Color.DARK_GRAY, Color.RED, Color.WHITE, Color.LIGHT_GRAY),
	COLORSET_VENUS(Color.YELLOW, Color.RED, Color.BLACK, Color.BLACK),
	COLORSET_EARTH(Color.BLUE, Color.CYAN, Color.WHITE, Color.BLACK),
	COLORSET_MARS(Color.RED, Color.BLACK, Color.WHITE, Color.BLACK),
	COLORSET_JUPITER(Color.ORANGE, Color.DARK_GRAY, Color.BLACK, Color.BLACK),
	COLORSET_SATURN(Color.CYAN, Color.BLUE, Color.BLACK, Color.BLACK),
	COLORSET_URANUS(Color.CYAN, Color.BLUE, Color.BLACK, Color.BLACK),
	COLORSET_NEPTUNE(Color.CYAN, Color.BLUE, Color.BLACK, Color.BLACK),
	COLORSET_PLUTO(Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK, Color.BLACK),
	COLORSET_LUNA(Color.LIGHT_GRAY, Color.DARK_GRAY, Color.BLACK, Color.BLACK),
	COLORSET_CUSTOM(Color.LIGHT_GRAY, Color.RED, Color.BLACK, Color.GREEN);
	
	private Color background;
	private Color target;
	private Color arc;
	private Color break_line;

	ColorSet(Color background, Color target, Color arc, Color break_line){
		this.background = background;
		this.target = target;
		this.arc = arc;
		this.break_line = break_line;
	}
	
	public Color getBackground(){
		return background;
	}
	
	public Color getTarget(){
		return target;
	}
	
	public Color getArc(){
		return arc;
	}
	
	public Color getBreak(){
		return break_line;
	}
	
}
