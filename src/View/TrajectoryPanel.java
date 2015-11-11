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

public class TrajectoryPanel extends JPanel implements ActionListener{

	private double velX;
	private double velY;
	private double currTime;
	private final double gravity = 9.8;

	private ArrayList<Double> x;
	private ArrayList<Double> y;
	

	private double xVel;
	private double yVel;
	private double prevX;
	private double prevY;
	private int width;
	private Game game;
	private Timer time = new Timer(150, this);

	public TrajectoryPanel(){
		setPreferredSize(new Dimension(100,150));
		game = Game.getGameObject();
	}

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
		//if(game.getNumTurns() == 0){
		g.setColor(Color.RED);
		//g2d.fillRect(game.randomTargetX(), 0, 10, 10);
		g2d.fillRect((int)game.getTargetX(0), 0, 10, 10);
		//}
		/*if(game.getNumTurns() != 0){
			g.setColor(Color.RED);
			//g2d.fillRect(game.getTargetX(), 0, 10, 10);
			g2d.fillRect(game.getTarget(), 0, 10, 10);
		}*/
		g.setColor(Color.BLACK);

		for(int i = 0; i < game.getPathSize()-1; i++){
			prevX = game.getPathX(i);
			prevY = game.getPathY(i);
			xVel = game.getPathX(i+1);
			yVel = game.getPathY(i+1);
			g2d.drawLine((int)prevX, (int)prevY, (int)xVel, (int)yVel);

		}

		g2d.setTransform(old);

	}

	public void changeTime(){
		repaint();
	}

	public void changeVel(double xVelocity, double yVelocity){
		velX = xVelocity;
		velY = yVelocity;
	}

	public void resetVelocities(){

		velX = 0.0;
		velY = 0.0;
		this.removeAll();
		this.updateUI();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}