package View;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Game;

public class AutoSolvePanel extends JPanel{
	
	private JLabel xVelocity;
	private JLabel yVelocity;
	private JLabel gravity;
	private JLabel distance;
	private Game game;

	public AutoSolvePanel(){
		
		xVelocity = new JLabel("X-Velocity: ");
		yVelocity = new JLabel("Y-Velocity: ");
		gravity = new JLabel("Gravity: ");
		distance = new JLabel("Distance: ");
		
		add(xVelocity);
		add(yVelocity);
		add(gravity);
		add(distance);
		
		setVisible(true);
		setSize(300,300);
	}
	
	public void displayXVel(double xVel){
		
		xVelocity.setText("X-Velocity: " + xVel);
	}
	
	public void displayYVel(double yVel){
		
		yVelocity.setText("Y-Velocity: " + yVel);
	}
	
	public void displayGravity(double grav){
		
		gravity.setText("Gravity: " + grav);
	}
	
	public void displayDistance(double dist){
		
		distance.setText("Distance: " + dist);
	}
}
