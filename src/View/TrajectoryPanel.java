package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import Model.ColorSet;
import Model.Game;

/**
 * TrajectoryPanel - a JPanel class that draws the trajectory for the
 * game algorithm
 * 
 * @author Matthew Hoffman, Ian Mohr, David Fletcher
 * @version Release Two | Last Updated: 11/11/2015
 */
public class TrajectoryPanel extends JPanel{

	/** used to draw the arc for x points*/
	private double velX;
	
	/** used to draw the arc for y points */
	private double velY;
	
	/** holds the current time */
	private double currTime;
	
	/** contains the gravity for the draw method*/
	private final double gravity = 9.8;

	/** ArrayList that holds the x values for the path */
	private ArrayList<Double> x;
	
	/** ArrayList that holds the y values for the path */
	private ArrayList<Double> y;
	
	/** holds the end point for velocity x */
	private double xVel;
	
	/** holds the end point for velocity y */
	private double yVel;
	
	/** holds the previous x value to draw the line */
	private double prevX;
	
	/** holds the previous y value to draw the line */
	private double prevY;
	
	/** width of the panel */
	private int width;
	
	/** instance of the Game class */
	private Game game;

	private Color arc;

	private Color break_line;

	private Color targetColor;

	/**
	 * Constructor that creates the panel
	 */
	public TrajectoryPanel(){
	
		setPreferredSize(new Dimension(100,300));
		game = Game.getGameObject();
	}

	/**
	 * Paint method that draws the arc and target
	 * @param Graphics instance used for accessing methods
	 */
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform old = g2d.getTransform();
		
		g2d.translate(0, getHeight()-10);
		g2d.scale(1, -1);
		getLayout();
		
		g.setColor(break_line);
		g2d.drawLine(0, 0, getWidth(), 0);
		
		g.setColor(targetColor);
		//g2d.fillRect(game.randomTargetX(), 0, 10, 10);
		g2d.fill3DRect((int) game.getTargetX(0)+4, 0, 3, 30, true);
		g.setColor(break_line);
		g2d.fill3DRect((int) game.getTargetX(0)+4, 30, 20, 10, true);
		g.setColor(arc);
		g2d.fillOval((int) (game.getTargetX(0)), 0, 10, 5);
		
		if(game.getMode() == 0){
			//loops through the path 
			for(int i = 0; i < game.getPathSize()-1; i++){
				prevX = game.getPathX(i);
				prevY = game.getPathY(i);
				xVel = game.getPathX(i+1);
				yVel = game.getPathY(i+1);
				g2d.drawLine((int)prevX, (int)prevY, (int)xVel, (int)yVel);
			}
		}
		else if(game.getMode() == 1){
			
			//loops through the path 
			for(int i = 0; i < game.getPathSize()-1; i++){
				prevX = game.getPathX(i);
				prevY = game.getPathY(i);
				xVel = game.getPathX(i+1);
				yVel = game.getPathY(i+1);
				g2d.drawLine((int)prevX, (int)prevY, (int)xVel, (int)yVel);
			}
			
			g.setColor(targetColor);
			g2d.fillOval((int) (game.getTargetX(0)), 0, 10, 5);
			//gets the x and y points and draws the arc
			for(int i = 0; i < game.getAirPathSize()-1; i++){
				prevX = game.getPathXAir(i);
				prevY = game.getPathYAir(i);
				xVel = game.getPathXAir(i+1);
				yVel = game.getPathYAir(i+1);
				g2d.drawLine((int)prevX, (int)prevY, (int)xVel, (int)yVel);
			}
		}
		repaint();
		g2d.setTransform(old);
	}
	
	/**
	 * Calls repaint from the GUI
	 */
	public void changeTime() {
	
		repaint();
	}
	
	/**
	 * Sets the global velocities from the GUI velocities
	 * @param xVelocity is input from the user
	 * @param yVelocity is input from the user
	 */
	public void changeVel(double xVelocity, double yVelocity) {
	
		velX = xVelocity;
		velY = yVelocity;
	}

	/**
	 * Resets the velocities to 0 and clears the panel
	 */
	public void resetVelocities(){

		velX = 0.0;
		velY = 0.0;
		this.removeAll();
		this.updateUI();
		
	}

	public void setColors(ColorSet colors) {
		arc = colors.getArc();
		break_line = colors.getBreak();
		targetColor = colors.getTarget();
	}

	
}