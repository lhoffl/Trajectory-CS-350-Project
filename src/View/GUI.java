package View;

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

public class GUI extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panelNorth;
	private JPanel panelSouth;
	private JButton fire;
	private JLabel xVel;
	private JLabel yVel;
	private JLabel numOfTurns;
	private JLabel targetLocation;
	private JLabel result;
	private JTextField xField;
	private JTextField yField;
	private JMenuItem reset;
	private JMenuItem exit;
	private JMenuBar bar;
	private JMenu file;
	private Game game;
	private int numTurns;


	public GUI(){
		game = new Game();
		panel = new JPanel();
		panelNorth = new JPanel();
		panelSouth = new JPanel(new GridLayout(3,0));
		fire = new JButton("Fire!");
		reset = new JMenuItem("New Game");
		exit = new JMenuItem("Exit");
		bar = new JMenuBar();
		file = new JMenu("File");
		xVel = new JLabel("X-Velocity");
		yVel = new JLabel("Y-Velocity");
		numOfTurns = new JLabel("Number of Shots: " + numTurns);
		targetLocation = new JLabel("Target location:("+ game.getTargetX() + ", " + game.getTargetY() + ")");
		result = new JLabel("");
		xField = new JTextField(5);
		yField = new JTextField(5);
		numTurns = 0;

		bar.add(file);
		file.add(reset);
		file.add(exit);

		add(panel);
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
		panelSouth.add(result);

		setJMenuBar(bar);
		
		setSize(350,150);
		setTitle("Trajectory!");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		fire.addActionListener(this);
		reset.addActionListener(this);
		exit.addActionListener(this);
		game.newTarget();
	}
	private void newGame() {
		game.newTarget();
		numTurns = 0;
		numOfTurns.setText("Number of Shots: " + numTurns);
		targetLocation.setText("Target location:("+ game.getTargetX() + ", " + game.getTargetY() + ")");
		xField.setText("");
		yField.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		JComponent e = (JComponent)event.getSource();

		if(e == fire){
			try{
				double xVal = Double.parseDouble(xField.getText());
				double yVal = Double.parseDouble(yField.getText());
				
				if(xVal < 0 || yVal < 0){
					xVal = 0;
					yVal = 0;
					result.setText("U SUCK");
				}else{
					result.setText("");
				}
				
				game.setVelX(xVal);
				game.setVelY(yVal);
				
				game.throwBall(xVal, yVal);
				numTurns++;
				targetLocation.setText("Target location:("+ game.getTargetX() + ", " + game.getTargetY() + ")");
				numOfTurns.setText("Number of Shots: " + numTurns);
				if(game.hitTarget()){
					JOptionPane.showMessageDialog(panel, "You hit the target!");
					newGame();
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

	}
}