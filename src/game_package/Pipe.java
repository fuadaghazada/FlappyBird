package game_package;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

import javax.swing.ImageIcon;

public class Pipe extends GameObject
{
	//Constants
	private static final int GAP = 150;
	private static final int WIDTH = 50;
	private static final int HEIGHT = 528;
	
	//properties
	private Image pipe_up, pipe_down;
	private int randY;
	private int speed;

	//constructor
	public Pipe()
	{
		this.generateRandY();

		this.setX(GameFrame.getFrameWidth() + 50);
		this.setY(randY);

		this.speed = 1;
		
		pipe_up = new ImageIcon(Pipe.class.getResource("/flappy_bird_images/pipes/pipe_up.png")).getImage();
		pipe_down = new ImageIcon(Pipe.class.getResource("/flappy_bird_images/pipes/pipe_down.png")).getImage();
	}
	
	@Override
	public void draw(Graphics g) 
	{
		g.drawImage(pipe_up, this.x, this.y, null);
		g.drawImage(pipe_down, this.x, this.y + GAP + HEIGHT, null);
	}
	
	//to update 
	public void update()
	{
		this.setX(x -= speed);
	}
	
	//random y
	public void generateRandY()
	{
		randY = (int)(Math.random() * (-300) - 100);
	}
		
	@Override
	public boolean intersect(GameObject g)
	{	
		Rectangle bird = new Rectangle(g.x, g.y, g.getWidth(), g.getHeight());
		
		Rectangle up = new Rectangle(x, y, this.getWidth(), this.getHeight());
		Rectangle down = new Rectangle(x, this.getRandY() + this.getHeight() + GAP, this.getWidth(), this.getHeight());
		
		Rectangle ground =  new Rectangle(0, Background.getY(), GameFrame.getFrameWidth(), GameFrame.getFrameHeight() - Background.getY());
		Line2D.Double up_line = new Line2D.Double(0, 0, GameFrame.getFrameWidth(), 0);
		
		return bird.intersects(up) || bird.intersects(down) || bird.intersects(ground) || bird.intersectsLine(up_line);
	}

	//ACCESS-
	
	@Override
	public int getWidth() 
	{
		return WIDTH;
	}
	
	@Override
	public int getHeight() 
	{
		return HEIGHT;
	}
	
	public int getRandY()
	{
		return randY;
	}

	public int getSpeed()
	{
		return speed;
	}
	
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
}
