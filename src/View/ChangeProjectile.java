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

public class ChangeProjectile extends JDialog implements ActionListener{

	private JLabel massLabel;
	private JLabel diameterLabel;
	private JLabel nameLabel;
	private JTextField massBox;
	private JTextField diameterBox;
	private JTextField nameBox;
	private JButton create;
	private JComboBox list;
	private JPanel panel;
	private Projectile[] projectileList = {Projectile.Default,
			Projectile.BowlingBall,
			Projectile.GolfBall,
			Projectile.WaterMelon,
			Projectile.Custom};
	private Projectile projectile = null;

	public ChangeProjectile(){

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

		add(panel);
		panel.add(nameLabel);
		panel.add(massLabel);
		panel.add(diameterLabel);
		panel.add(nameBox);
		panel.add(massBox);
		panel.add(diameterBox);
		panel.add(list);
		panel.add(create);

		setVisible(true);
		pack();
		setResizable(false);
		setTitle("Change Projectile");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setLayout(new BorderLayout());

		create.addActionListener(this);
		list.addActionListener(this);
	}

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

		if(list.getSelectedItem() == Projectile.Default){
			nameBox.setText("Default");
			massBox.setText("250");
			diameterBox.setText(".5");
		}

		if(list.getSelectedItem() == Projectile.BowlingBall){
			nameBox.setText("Bowling Ball");
			massBox.setText("6.35");
			diameterBox.setText(".218");
		}

		if(list.getSelectedItem() == Projectile.GolfBall){
			nameBox.setText("Golf Ball");
			massBox.setText(".046");
			diameterBox.setText(".043");
		}

		if(list.getSelectedItem() == Projectile.WaterMelon){
			nameBox.setText("Watermelon");
			massBox.setText("10.0");
			diameterBox.setText("11.34");
		}

	}
}
