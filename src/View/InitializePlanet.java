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

public class InitializePlanet extends JDialog implements ActionListener{

	private JLabel planetName;
	private JLabel gravityLabel;
	private JLabel nameLabel;
	private JPanel mainPanel;
	private JTextField nameField;
	private JTextField gravityField;
	private JButton create;
	private JComboBox list;
	private Planet planet;
	private double gravity;
	private Planet[] planetList = {Planet.MERCURY, Planet.VENUS, Planet.EARTH, Planet.MARS,
					 Planet.JUPITER, Planet.SATURN, Planet.URANUS, Planet.NEPTUNE, Planet.PLUTO, Planet.LUNA, Planet.CUSTOM};
	
	private Game game;

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
		add(mainPanel, BorderLayout.NORTH);
		mainPanel.add(planetName);
		mainPanel.add(list);
		mainPanel.add(nameLabel);
		mainPanel.add(nameField);
		mainPanel.add(gravityLabel);
		mainPanel.add(gravityField);
		add(create, BorderLayout.SOUTH);


		create.addActionListener(this);
		list.addActionListener(this);

		setModal(true);
		pack();
		setVisible(true);
		setResizable(false);
		setTitle("Create Planet");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		JComponent e = (JComponent)event.getSource();

		//Create Planet
		if(e == create){

			double value = 0;
			for(int i = 0; i < planetList.length; i++){
				if(planetList[i] == list.getSelectedItem()){
					value = planetList[i].getGravity();
				}
			}
			System.out.println(planet.toString());
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

			for(int i = 0; i < planetList.length; i++){
				if(planetList[i] == list.getSelectedItem()){
					gravityField.setText("" + planetList[i].getGravity());
				}
			}

		}
	}

}
