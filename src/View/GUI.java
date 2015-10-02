package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.*;

import Model.Game;

import com.sun.javafx.geom.QuadCurve2D;

import sun.java2d.loops.DrawRect;

public class GUI extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel label;
	private Game game;
	
	
	public GUI(){
		panel = new JPanel(new BorderLayout());
		label = new JLabel("SDfasdfadfgbvjxdflkgh");
				
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Trajectory!");
		setResizable(false);
		setSize(500,500);
		
		add(panel);
		add(new drawTarget());
		//panel.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
	public static void main(String args[]){
		new GUI();
	}
	
	private Rectangle2D drawTarget(Point2D.Double t){		

		
		double x = t.x;
		double y = t.y;
		
		double w = 100.0;
		double h = 100.0;	
		
		Rectangle2D target = new Rectangle2D.Double(x, y, w, h);
		return target;
	}
}	
// do things
class drawTarget extends JPanel{
	public Game game = new Game();
	
	@Override
	public Dimension getMinimumSize(){
		return new Dimension(100,100);
	}
	protected void paintComponent(Graphics g){
		game.newTarget();
		game.throwBall(2,15);
		Point2D.Double t = new Point2D.Double();
		//t = game.getTargetX().get(0);
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		Rectangle2D target = new Rectangle2D.Double(t.x * 10,t.y * 10, 10.0, 10.0);
		g2.draw(target);
		
		//for(int i = 0; i < game.getPath().size() - 1; i++){
		//	Line2D.Double temp = new Line2D.Double(game.getPath().get(i).x * 10, game.getPath().get(i).y * 10, game.getPath().get(i+1).x * 10, game.getPath().get(i+1).y * 10);
		//	g2.draw(temp);
		}
	}
//}