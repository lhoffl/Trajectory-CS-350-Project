package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Game;
import Model.Planet;

/**
 * InitializePlanet - gives the user the option to choose or create
 * a new planet.
 * 
 * @author Matthew Hoffman, Ian Mohr, David Fletcher
 * @version Release Two | Last Updated: 11/11/2015
 */
public class InitializePlanet extends JDialog implements ActionListener{

    /** label that displays the planet name */
	private JLabel planetName;
	
	/** label that displays the current gravity */
	private JLabel gravityLabel;
	
	/** label that displays the user created name */
	private JLabel nameLabel;
	
	/** main panel that holds the various fields */
	private JPanel mainPanel;
	
	/** text field for user to input name */
	private JTextField nameField;
	
	/** text field for user to input gravity */
	private JTextField gravityField;
	
	/** button to create the planet */
	private JButton create;
	
	/** lets the user select other planets */
	private JComboBox list;
	
	/** instance of the Planet class */
	private Planet planet;
	
	/** holds the gravity value */
	private double gravity;
	
	/** array that holds the planet's name */
	private Planet[] planetList = {Planet.MERCURY, Planet.VENUS, Planet.EARTH, Planet.MARS,
					 Planet.JUPITER, Planet.SATURN, Planet.URANUS, Planet.NEPTUNE, Planet.PLUTO, Planet.LUNA, Planet.CUSTOM};
					
	/** instance of the planet class */
	Planet p = null;
	
	/** instance of the game class */
	private Game game;

	/**
	 * Constructor that creates the planet dialog box
	 */
	public InitializePlanet(){

		list = new JComboBox(planetList);
		
		planetName = new JLabel("Choose Planet: ");
		nameLabel = new JLabel("Name: ");
		nameLabel.setEnabled(false);
		gravityLabel = new JLabel("Gravity: " );
		gravityLabel.setEnabled(false);
		nameField = new JTextField(10);
		nameField.setEnabled(false);
		gravityField = new JTextField(10);
		gravityField.setEnabled(false);
		gravityField.setText("3.7");
		mainPanel = new JPanel(new GridLayout(3,2));
		create = new JButton("Create");

		setLayout(new BorderLayout());
		
		//adds various fields to the panels
		add(mainPanel, BorderLayout.NORTH);
		mainPanel.add(planetName);
		mainPanel.add(list);
		mainPanel.add(nameLabel);
		mainPanel.add(nameField);
		mainPanel.add(gravityLabel);
		mainPanel.add(gravityField);
		add(create, BorderLayout.SOUTH);

		//adds action listeners
		create.addActionListener(this);
		list.addActionListener(this);

		//panel options
		setModal(true);
		pack();
		setVisible(true);
		setResizable(false);
		setTitle("Create Planet");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/**
	 * Returns the planet the user selected
	 * @return Planet selected
	 */
	public Planet getPlanet(){
		for(int i = 0; i < planetList.length; i++){
			if(list.getSelectedItem() == planet.CUSTOM){
			}
			else if (planetList[i] == list.getSelectedItem()){
				p = planetList[i];
			}
		}
			return p;
	}
	
	/**
	 * ActionPerformed method that allows events
	 * @param event for the action listener
	 */
	@Override
	public void actionPerformed(ActionEvent event) {

		JComponent e = (JComponent)event.getSource();

		//Create Planet
		if(e == create){

			double value = 0.0;
			String name = "Custom";
			
			//loops through the planets array 
			for(int i = 0; i < planetList.length; i++){
			
				//if the selected planet is custom
				if(list.getSelectedItem() == Planet.CUSTOM){
					p = Planet.CUSTOM;
					try{
						p.setGravity(Double.parseDouble(gravityField.getText()));
					}catch(Exception x){
						p.setGravity(value);
					}
					try{
						p.setName(nameField.getText());
					}catch(Exception f){
						p.setName("CUSTOM");
					}
				}
				else if (planetList[i] == list.getSelectedItem()){
					value = planetList[i].getGravity();
				}
			}
			//game.setGravity(value);
			dispose();
		}

		//If custom is selected, enable fields
		if(list.getSelectedItem() == Planet.CUSTOM){
			nameLabel.setEnabled(true);
			nameField.setEnabled(true);
			gravityLabel.setEnabled(true);
			gravityField.setEnabled(true);
			gravityField.setText("");
		}

		//If custom is not selected, disable fields
		else {
			nameLabel.setEnabled(false);
			nameField.setEnabled(false);
			gravityLabel.setEnabled(false);
			gravityField.setEnabled(false);
			
			//loops through the planet list
			for(int i = 0; i < planetList.length; i++){
				if(planetList[i] == list.getSelectedItem()){
					gravityField.setText("" + planetList[i].getGravity());
					nameField.setText(planetList[i].toString());
				}
			}
		}
	}

}
