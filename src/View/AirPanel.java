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

import Model.Game;

/**
 * AirPanel - a JPanel class that draws the trajectory for the
 * air resistance algorithm
 * 
 * @author Matthew Hoffman, Ian Mohr, David Fletcher
 * @version Release Two | Last Updated: 11/11/2015
 */
public class AirPanel extends JPanel implements ActionListener{

	/** used to draw the arc for x points*/
	private double velX;
	
	/** used to draw the arc for y points */
	private double velY;
	
	/** holds the current time */
	private double currTime;
	
	/** contains the gravity for the draw method*/
	private double gravity = 9.8;

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
	
	/** instance of the game class */
	private Game game;

	/**
	 * Constructor that creates the panel
	 */
	public AirPanel(){
		setPreferredSize(new Dimension(100,150));
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
		g.setColor(Color.GREEN);
		g2d.drawLine(0, 0, getWidth(), 0);
		g.setColor(Color.RED);
		g2d.fillRect((int)game.getTargetX(0), 0, 10, 10);
		
		g.setColor(Color.BLACK);

		//gets the x and y points and draws the arc
		for(int i = 0; i < game.getPathSize()-1; i++){
			prevX = game.getPathXAir(i);
			prevY = game.getPathYAir(i);
			xVel = game.getPathXAir(i+1);
			yVel = game.getPathYAir(i+1);
			g2d.drawLine((int)prevX, (int)prevY, (int)xVel, (int)yVel);
		}

		g2d.setTransform(old);

	}

	/**
	 * Calls repaint from the GUI
	 */
	public void changeTime(){
		repaint();
	}

	/**
	 * Sets the global velocities from the GUI velocities
	 * @param xVelocity is input from the user
	 * @param yVelocity is input from the user
	 */
	public void changeVel(double xVelocity, double yVelocity){
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
	
}