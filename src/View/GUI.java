package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
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
import Model.Projectile;

/**
 * GUI class for the Trajectory game
 *
 * @author Matthew Hoffman, Ian Mohr, David Fletcher
 * @version Release One | 10/9/2015
 */
public class GUI extends JFrame implements ActionListener{

	DecimalFormat df = new DecimalFormat("##.##");

	ImageIcon target = new ImageIcon("button.jpg");


	/** serialVersion */
	private static final long serialVersionUID = 1L;

	/** main panel */
	private JPanel panel;

	/** panel for velocities */
	private JPanel panelNorth;

	/** panel for labels */
	private JPanel panelSouth;

	/** fire the ball */
	private JButton fire;

	private JButton autoSolve;

	/** x velocity label */
	private JLabel velocityLabel;

	/** y velocity label */
	private JLabel thetaLabel;

	/** number of turns label */
	private JLabel numOfTurns;

	/** score label */
	private JLabel score;

	/** target location label */
	private JLabel targetLocation;

	/** field for x velocity input */
	private JTextField velocityField;

	/** field for y velocity input */
	private JTextField thetaField;

	/** resets the game */
	private JMenuItem reset;

	/** exits the game */
	private JMenuItem exit;

	/** change the planet */
	private JMenuItem planetSelect;

	/** change the projectile */
	private JMenuItem projectileSelect;

	/** select golf mode */
	private JCheckBoxMenuItem golf;

	/** change the planet */
	private JMenuItem bounceOn;

	/** select air resistance mode */
	private JCheckBoxMenuItem airResistanceMode;

	/** menu bar that contains the menu items */
	private JMenuBar bar;

	/** file that contains new game and exit */
	private JMenu file;

	/** menu that contains planet select */
	private JMenu edit;

	/** menu that contains planet select */
	private JMenu view;

	/** menu that contains planet select */
	private JMenu mode;

	/** instance of Game class */
	private Game game;

	/** keeps track of number of turns */
	private int numTurns;

	/** length of text box */
	private int length;

	/** instance of TrajectoryPanel for displaying the trajectory for game */
	private TrajectoryPanel trajPanel;

	/** instance of Airpanel that contains the air resistance arc */
	private AirPanel airPanel;

	/** instance of InitializePlanet class */
	private InitializePlanet iPlanet;

	/** instance of the planet class */
	private Planet p;

	/** instance of projectile class */
	private Projectile projectile;

	/** instance of ChangeProjectile class */
	private ChangeProjectile changeProjectile;


	private JLabel currPlanet;
	private JLabel currGravity;

	private JLabel modeLabel;

	private JLabel airLabel;

	private JMenuItem leaderboardMenu;

	/**
	 * Constructor that initializes the user interface
	 */
	public GUI(){

		game = game.getGameObject();
		p = Planet.EARTH;
		projectile = Projectile.Default;
		length = 5;
		panel = new JPanel();
		panelNorth = new JPanel();
		panelSouth = new JPanel(new GridLayout(8,0));

		fire = new JButton("Fire");
		fire.setSize(10, 10);
		fire.setContentAreaFilled(false);

		autoSolve = new JButton("Auto Solve");
		autoSolve.setBackground(Color.RED);
		reset = new JMenuItem("New Game");
		exit = new JMenuItem("Exit");
		golf = new JCheckBoxMenuItem("Golf mode");
		airResistanceMode = new JCheckBoxMenuItem("Air Resistance");
		bounceOn = new JMenuItem("Bounce on");
		planetSelect = new JMenuItem("Select Planet");
		projectileSelect = new JMenuItem("Select Projectile");
		bar = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		view = new JMenu("View");
		mode = new JMenu("Mode");
		velocityLabel = new JLabel("Velocity");
		thetaLabel = new JLabel("Theta");
		numOfTurns = new JLabel("Number of Shots: " + numTurns);
		targetLocation = new JLabel("Target location: (" + game.getTargetX() + ", " + game.getTargetY() + ")");
		currPlanet = new JLabel("Planet: " + p);
		currGravity = new JLabel("Gravity: " + p.getGravity());
		score = new JLabel("Score: " + game.getScore());
		velocityField = new JTextField(length);
		thetaField = new JTextField(length);

		leaderboardMenu = new JMenuItem("Show Leaderboard");

		modeLabel = new JLabel("No Resistance");
		trajPanel = new TrajectoryPanel();
		//trajPanel.add(modeLabel);
		airPanel = new AirPanel();
		//airPanel.add(airLabel);
		numTurns = 0;

		//menu bar
		bar.add(file);
		bar.add(edit);
		bar.add(view);
		bar.add(mode);
		file.add(reset);
		file.add(exit);
		//mode.add(golf);
		mode.add(airResistanceMode);
		edit.add(planetSelect);
		edit.add(projectileSelect);
		view.add(leaderboardMenu);

		setLayout(new BorderLayout());

		add(panel, BorderLayout.NORTH);
		panel.add(panelNorth);
		panel.add(panelSouth);
		panel.add(bar);
		panelNorth.add(velocityLabel);
		panelNorth.add(velocityField);
		panelNorth.add(thetaLabel);
		panelNorth.add(thetaField);
		panelNorth.add(fire);
		panelNorth.add(autoSolve);
		panelSouth.add(targetLocation);
		panelSouth.add(numOfTurns);
		panelSouth.add(score);
		panelSouth.add(new JLabel(" "));
		panelSouth.add(currPlanet);
		panelSouth.add(currGravity);
		panelSouth.add(modeLabel);

		panel.setBorder(BorderFactory.createBevelBorder(0,Color.BLACK, Color.BLACK));

		setJMenuBar(bar);

		//adds trajectory panels to GUI
		add(trajPanel, BorderLayout.SOUTH);
		//add(airPanel, BorderLayout.CENTER);

		//frame options
		pack();
		setTitle("Trajectory!");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		//action listeners
		fire.addActionListener(this);
		reset.addActionListener(this);
		exit.addActionListener(this);
		planetSelect.addActionListener(this);
		projectileSelect.addActionListener(this);
		golf.addActionListener(this);
		airResistanceMode.addActionListener(this);
		leaderboardMenu.addActionListener(this);
		autoSolve.addActionListener(this);
		
		game.setWidth(this.size().width);
	}

	/**
	 * Resets the panels by clearing the arcs
	 */
	private void resetPanels(){
		if(airResistanceMode.isSelected()){
			airPanel.removeAll();
			airPanel.resetVelocities();
			airPanel.updateUI();
		}
		else {
			trajPanel.removeAll();
			trajPanel.resetVelocities();
			trajPanel.updateUI();

		}
		game.resetPath();
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
		velocityField.setText("");
		thetaField.setText("");
		score.setText("Score: " + game.getScore());
		trajPanel.resetVelocities();
		airPanel.resetVelocities();
	}

	/**
	 * ActionPerformed method that allows events
	 * @param event for the action listener
	 */
	@Override
	public void actionPerformed(ActionEvent event) {

		JComponent e = (JComponent)event.getSource();

		//if fire button is clicked
		if(e == fire) {

			resetPanels();

			//Validate input
			try{
				// retrieve the user's input velocities
				double xVal = Double.parseDouble(velocityField.getText());
				double yVal = Double.parseDouble(thetaField.getText());

				// If the input is negative, set it to 0
				if(xVal < 0 || yVal < 0){
					xVal = 0;
					yVal = 0;
				}

				// set the values of the velocities
				yVal = Math.toRadians(yVal);
				double x = game.getXComponent(xVal, yVal);
				double y = game.getYComponent(xVal, yVal);

				game.setVelX(x);
				game.setVelY(y);

				// throw the ball
				game.throwBall(x, y);
				game.throwBall(x, y, projectile.getMass(), projectile.getDiameter());

				numTurns++;

				//update game info
				targetLocation.setText("Target location:("+ game.getTargetX() + ", " + game.getTargetY() + ")");
				numOfTurns.setText("Number of Shots: " + numTurns);

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
			velocityField.setText("");
			thetaField.setText("");
		}

		//if reset on menubar is clicked
		if(e == reset){
			int check = game.checkScore(game.getScore());
			if(check != -1){
				String name = JOptionPane.showInputDialog("You made the leaderboard, enter your name: ");
				game.updateLeaderboard(check, game.getScore(), name);
				JOptionPane.showMessageDialog(this, game.printLeaderboard());
			}
			
			newGame();
		}

		//if exit is clicked
		if(e == exit){
			int check = game.checkScore(game.getScore());
			if(check != -1){
				String name = JOptionPane.showInputDialog("You made the leaderboard, enter your name: ");
				game.updateLeaderboard(check, game.getScore(), name);
				JOptionPane.showMessageDialog(this, game.printLeaderboard());
			}
			System.exit(0);
		}

		//if planetSelect is clicked on menubar
		if(e == planetSelect){
			iPlanet = new InitializePlanet();
			p = iPlanet.getPlanet();
			game.setGravity(p.getGravity());

			trajPanel.setBackground(p.getColors().getBackground());

			trajPanel.setColors(p.getColors());

			currPlanet.setText("Planet: " + p);
			currGravity.setText("Gravity: " + p.getGravity());
		}

		if(e == projectileSelect){
			changeProjectile = new ChangeProjectile();
			projectile = changeProjectile.getProjectile();
		}

		if(e == golf){
			if(golf.isSelected()){
				modeLabel.setText("Golf Mode");
				autoSolve.setEnabled(false);
				game.golfMode(true);
				airResistanceMode.setEnabled(false);

			}
			if(!golf.isSelected()){
				modeLabel.setText("No Resistance");
				autoSolve.setEnabled(true);
				airResistanceMode.setEnabled(true);
			}
		}

		if(e == leaderboardMenu){
			JOptionPane.showMessageDialog(this, game.printLeaderboard(),"Leaderboard", JOptionPane.INFORMATION_MESSAGE);
		}

		if(e == autoSolve){
			double theta = game.autoSolverTheta(70, p.getGravity(), game.getTargetX(0));
			velocityField.setText("" + 70);
			thetaField.setText("" + df.format(theta));

			System.out.println("Theta: " + theta);
			// set the values of the velocities
			theta = Math.toRadians(theta);
			//System.out.println("Radians: " + theta);
			double x = game.getXComponent(70, theta);
			double y = game.getYComponent(70, theta);

			game.throwBall(x, y);
		}

		if(airResistanceMode.isSelected()){
			golf.setEnabled(false);
			game.setMode(1);
			modeLabel.setText("Air Resistance overlay");
			revalidate();
		}

		if(!airResistanceMode.isSelected()){
			game.setMode(0);
			modeLabel.setText("No Resistance");
		}
	}
}