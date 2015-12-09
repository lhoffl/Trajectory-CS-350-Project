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

import Model.Projectile;


/**
 * JDialog class that allows the user to change the projectile being launched.
 * 
 * @author Matthew Hoffman, Ian Mohr, David Fletcher
 * @version Release Three | Last Updated: 12/09/2015
 */
public class ChangeProjectile extends JDialog implements ActionListener{

	/** label that displays mass */
	private JLabel massLabel;
	
	/** label that displays diameter */
	private JLabel diameterLabel;
	
	/** label that displays name of projectile */
	private JLabel nameLabel;
	
	/** textfield that allows for mass input */
	private JTextField massBox;
	
	/** textfield that allows for diameter input */
	private JTextField diameterBox;
	
	/** textfield that allows for name input */
	private JTextField nameBox;
	
	/** creates the projectile when clicked */
	private JButton create;
	
	/** list containing all the projectiles */
	private JComboBox list;
	
	/** panel that contains the main components */
	private JPanel panel;
	
	/** array of projectiles */
	private Projectile[] projectileList = {Projectile.Default,
			Projectile.BowlingBall,
			Projectile.GolfBall,
			Projectile.WaterMelon,
			Projectile.Custom};
			
	/** instance of projectile class */
	private Projectile projectile = null;

	/**
	 * Constructor that creates the projectile dialog box
	 */
	public ChangeProjectile(){
	
		//instantiations 
		panel = new JPanel(new GridLayout(3, 2));
		massLabel = new JLabel("Mass");
		massLabel.setEnabled(false);
		diameterLabel = new JLabel("Diameter");
		diameterLabel.setEnabled(false);
		nameLabel = new JLabel("Name");
		nameLabel.setEnabled(false);
		massBox = new JTextField("250", 10);
		massBox.setEnabled(false);
		diameterBox = new JTextField(".5", 10);
		diameterBox.setEnabled(false);
		nameBox = new JTextField("Default", 10);
		nameBox.setEnabled(false);
		create = new JButton("Create");
		list = new JComboBox(projectileList);

		//adding components
		add(panel);
		panel.add(nameLabel);
		panel.add(massLabel);
		panel.add(diameterLabel);
		panel.add(nameBox);
		panel.add(massBox);
		panel.add(diameterBox);
		panel.add(list);
		panel.add(create);

		//super methods
		setVisible(true);
		pack();
		setResizable(false);
		setTitle("Change Projectile");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setLayout(new BorderLayout());

		//action listeners
		create.addActionListener(this);
		list.addActionListener(this);
	}

	/**
	 * Returns the projectile the user selected
	 * @return Projectile that is selected
	 */
	public Projectile getProjectile(){
	
		for(int i = 0; i < projectileList.length; i++){
			if(list.getSelectedItem() == Projectile.Custom){
			}
			else if (projectileList[i] == list.getSelectedItem()){
				projectile = projectileList[i];
			}
		}
		return projectile;
	}

	/**
	 * ActionPerformed method that allows events
	 * @param event for the action listener
	 */
	@Override
	public void actionPerformed(ActionEvent event) {

		JComponent e = (JComponent)event.getSource();

		if(e == create){

			//Must be a double or integer
			try{
				projectile.setDiameter(Double.parseDouble(diameterBox.getText()));
			}
			catch(NumberFormatException n){
				System.out.println("Exception One - Diameter");
			}
			//Must be a double or integer
			try{
				projectile.setMass(Double.parseDouble(massBox.getText()));
			}
			catch(NumberFormatException n){
				System.out.println("Exception Two - Mass");
			}
			projectile.setName(nameBox.getText());
			dispose();
		}

		//Custom
		if(list.getSelectedItem() == Projectile.Custom){
			massLabel.setEnabled(true);
			massBox.setEnabled(true);
			nameBox.setEnabled(true);
			nameLabel.setEnabled(true);
			diameterBox.setEnabled(true);
			diameterLabel.setEnabled(true);
			massBox.setText("");
			diameterBox.setText("");
			nameBox.setText("");

		}

		//Default
		if(list.getSelectedItem() == Projectile.Default){
			nameBox.setText("Default");
			massBox.setText("250");
			diameterBox.setText(".5");
		}

		//Bowlingball
		if(list.getSelectedItem() == Projectile.BowlingBall){
			nameBox.setText("Bowling Ball");
			massBox.setText("6.35");
			diameterBox.setText(".218");
		}
		
		//Golf ball
		if(list.getSelectedItem() == Projectile.GolfBall){
			nameBox.setText("Golf Ball");
			massBox.setText(".046");
			diameterBox.setText(".043");
		}
		
		//Watermelon
		if(list.getSelectedItem() == Projectile.WaterMelon){
			nameBox.setText("Watermelon");
			massBox.setText("10.0");
			diameterBox.setText("11.34");
		}

	}
}
