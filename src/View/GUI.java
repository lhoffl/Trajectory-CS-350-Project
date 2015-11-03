package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Game;
import Model.Planet;

/**
 * GUI class for the Trajectory game
 * 
 * @author Matthew Hoffman, Ian Mohr, David Fletcher
 * @version Release One | 10/9/2015
 */
public class GUI extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panelNorth;
	private JPanel panelSouth;
	private JButton fire;
	private JLabel xVel;
	private JLabel yVel;
	private JLabel numOfTurns;
	private JLabel score;
	private JLabel targetLocation;
	private JTextField xField;
	private JTextField yField;
	private JMenuItem reset;
	private JMenuItem exit;
	private JMenuItem planetSelect;
	private JMenuBar bar;
	private JMenu file;
	private JMenu edit;
	private Game game;
	private int numTurns;
	private TrajectoryPanel trajPanel;
	private TrajectoryPanel airPanel;
	private InitializePlanet iPlanet;
	private Planet p;
	
	public GUI(){
		game = new Game();
		p = Planet.EARTH;
		panel = new JPanel();
		panelNorth = new JPanel();
		panelSouth = new JPanel(new GridLayout(4,0));
		fire = new JButton("Fire!");
		reset = new JMenuItem("New Game");
		exit = new JMenuItem("Exit");
		planetSelect = new JMenuItem("Select Planet");
		bar = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		xVel = new JLabel("X-Velocity");
		yVel = new JLabel("Y-Velocity");
		numOfTurns = new JLabel("Number of Shots: " + numTurns);
		targetLocation = new JLabel("Target location:("+ game.getTargetX() + ", " + game.getTargetY() + ")");
		score = new JLabel("Score: " + game.getScore());
		xField = new JTextField(5);
		yField = new JTextField(5);
		trajPanel = new TrajectoryPanel();
		airPanel = new TrajectoryPanel();
		numTurns = 0;

		bar.add(file);
		bar.add(edit);
		file.add(reset);
		file.add(exit);
		edit.add(planetSelect);
		
		setLayout(new BorderLayout());
		
		add(panel, BorderLayout.NORTH);
		panel.add(panelNorth);
		panel.add(panelSouth);
		panel.add(bar);
		panelNorth.add(xVel);
		panelNorth.add(xField);
		panelNorth.add(yVel);
		panelNorth.add(yField);
		panelNorth.add(fire);
		panelSouth.add(targetLocation);
		panelSouth.add(numOfTurns);
		panelSouth.add(score);

		setJMenuBar(bar);
		trajPanel.setGame(game);
		airPanel.setGame(game);
		add(trajPanel, BorderLayout.SOUTH);
		add(airPanel, BorderLayout.CENTER);
		
		pack();
		setTitle("Trajectory!");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
				
		fire.addActionListener(this);
		reset.addActionListener(this);
		exit.addActionListener(this);
		planetSelect.addActionListener(this);

	}
	
	private void resetPanels(){
		trajPanel.removeAll();
		trajPanel.resetVelocities();
		trajPanel.updateUI();
		
		airPanel.removeAll();
		airPanel.resetVelocities();
		airPanel.updateUI();
	}
	
	/**
	 * Create a new game
	 */
	private void newGame() {
		numTurns = 0;
		game.resetScore();
		nextTarget();
	}
	
	/**
	 * Create a new target and reset text fields
	 */
	private void nextTarget() {
		game.newTarget();
		numOfTurns.setText("Number of Shots: " + numTurns);
		targetLocation.setText("Target location:("+ game.getTargetX() + ", " + game.getTargetY() + ")");
		xField.setText("");
		yField.setText("");
		score.setText("Score: " + game.getScore());
		trajPanel.resetVelocities();
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		JComponent e = (JComponent)event.getSource();

		if(e == fire){
			
			resetPanels();
			
			
			//Validate input
			try{
				// retrieve the user's input velocities
				double xVal = Double.parseDouble(xField.getText());
				double yVal = Double.parseDouble(yField.getText());
				
				// If the input is negative, set it to 0
				if(xVal < 0 || yVal < 0){
					xVal = 0;
					yVal = 0;
				}
				
				// set the values of the velocities
				game.setVelX(xVal);
				game.setVelY(yVal);
				
				// throw the ball
				game.throwBall(xVal, yVal);
				trajPanel.setGame(game);
				numTurns++;
				
				//update game info
				targetLocation.setText("Target location:("+ game.getTargetX() + ", " + game.getTargetY() + ")");
				numOfTurns.setText("Number of Shots: " + numTurns);
				
				// fuck
				trajPanel.changeTime(10);
				trajPanel.changeVel(xVal, yVal);
				
				// if the target was hit, let the user know, update the score and generate a new target
				if(game.hitTarget()){
					JOptionPane.showMessageDialog(panel, "You hit the target!");
					score.setText("Score: " + game.getScore());
					game.resetPath();
					nextTarget();
					resetPanels();
				}
			}catch(NumberFormatException n){
				JOptionPane.showMessageDialog(panel, "Please enter a double value");
			}
			xField.setText("");
			yField.setText("");
		}
		if(e == reset){
			newGame();
		}
		if(e == exit)
			System.exit(0);
		
		if(e == planetSelect){
			iPlanet = new InitializePlanet();
			p = iPlanet.getPlanet();
			game.setGravity(p.getGravity());
			trajPanel.setBackground(p.getColor());
			airPanel.setBackground(p.getColor());
			System.out.printf("Planet: %s, gravity: %f\n", p.toString(), p.getGravity());
		}

	}
}