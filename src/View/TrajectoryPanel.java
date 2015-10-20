package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import Model.Game;

public class TrajectoryPanel extends JPanel implements ActionListener{

	private double velX;
	private double velY;
	private double currTime;
	private final double gravity = 9.8;
	private Random rand = new Random();
	private int n;
	
	private Game game;

	//Fucking around
	Timer time = new Timer(5, this);
	int x = 0, speed = 1;

	public TrajectoryPanel(){
		setPreferredSize(new Dimension(100, 100));
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		//More fucking around
		//g.setColor(Color.RED);
		//g.fillRect(x, 30, 50, 30);


		double prevX = 0.0;
		double prevY = 0.0;
		double currX = 0.0;
		double currY = 0.0;

		//top border
		g.setColor(Color.BLUE);
		g2d.drawLine(0,0,getWidth(),0);

		for(int i = 0; i < getWidth(); i += 50){
			g.setColor(Color.GREEN);
			g2d.fillRect(i, 90, 10, 10);
		}
		
		//time.start();

		g.setColor(Color.BLACK);

		AffineTransform old = g2d.getTransform();
	

		int index = 0;
		for(double t = 0.0; t < currTime; t+=.01){
			
			//sets 0,0 to bottom left
			g2d.translate(0, getHeight()-10);
			g2d.scale(1, -1);getLayout();

			
//			//target
		    g.setColor(Color.RED);
			g2d.fillRect((int)(game.getTargetX(0)), (int)(game.getTargetY(0)), 10, 10);
			g.setColor(Color.BLACK);
			
			if (index < game.getPathSize()){
				prevX = game.getPathX(index);
				prevY = game.getPathY(index);
			}
			
			index++;
			
			if (index < game.getPathSize()){
				currX = game.getPathX(index+1);
				currY = game.getPathY(index+1);
			}
			else{
				currX = prevX;
				currY = prevY;
			}
			
		//	System.out.printf("index: %d,pX: %f, pY: %f, cX: %f, cY: %f\n",index,prevX,prevY,currX,currY);
//			if(index < game.getPathX().size())
//				System.out.printf("paX: %f, paY: %f\n", game.getPathX().get(index), game.getPathY().get(index));
			// if the target is not hit, draw the path
			if(!game.targetContains(currX, currY) && index < game.getPathSize() - 1){
				g2d.drawLine((int)game.getPathX(index), (int)prevY, (int)game.getPathX(index+1), (int)currY);
			}
			
			g2d.setTransform(old);
			//new starting points for next iteration
			
			prevX = currX;
			prevY = currY;
		}
		
		index = 0;
	}

	public void changeTime(double chTime){
		currTime += chTime;
		repaint();
	}

	public void changeVel(double xVelocity, double yVelocity){

		velX = xVelocity;
		velY = yVelocity;
	}


	public void resetVelocities(){

		velX = 0.0;
		velY = 0.0;
	}

	//only for moving target
	@Override
	public void actionPerformed(ActionEvent event) {

		if(x < 0 || x > 91){
			speed = -speed;
		}
		x = x + speed;
		repaint();

	}
	
	public void setGame(Game g){
		game = g;
	}

}