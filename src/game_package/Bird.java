package game_package;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class Bird extends GameObject
{
	//constants 
	private static final int WIDTH = 57;
	private static final int HEIGHT = 47;
	private static final int FORCE = 7;
	
	private static final double FORCE_ANGLE = Math.PI/45;
	
	
	//properties
	private static int gravity;
	private double gravity_angle;
	
	private Image bird_image;
	private boolean isJump;
	
	private int dir = 1;
	private double angle = 0;	
	
	//constructor
	public Bird()
	{
		this.setGravity(3);
		this.setGravity_angle(Math.PI/180);
		this.setX(GameFrame.getFrameWidth()/2 - WIDTH/2 - 70);
		this.setY(GameFrame.getFrameHeight()/2);
	}
	
	@Override
	public void draw(Graphics g) 
	{
		Graphics2D g2 = (Graphics2D) g;	
        
		//reference - https://stackoverflow.com/questions/8807717/java-rotate-rectangle-around-the-center
		
		AffineTransform transform = new AffineTransform();
				
		transform.rotate(angle, x + WIDTH/2, y + HEIGHT/2);
		
		AffineTransform old = g2.getTransform();
		
		g2.transform(transform);

		g2.drawImage(bird_image, x, y, null);

		g2.setTransform(old);
				
	}
	
	//to update the bird by the effect of the gravity or the force of flying
	public void update()
	{
		if(isJump)
		{
			if(angle >= -Math.PI/6)
			{
				angle -= FORCE_ANGLE;
			}
			
			this.setY(y - FORCE);
		}
		else
		{
			this.fall();		
		}
	}
	
	//falling because of gravity
	public void fall()
	{
		if(y <= GameFrame.getFrameHeight() - 118)
		{
			this.setY(y + gravity);
			
			//give rotation
			if(angle + gravity_angle <= Math.PI/2)
			{
				angle += gravity_angle;
			}
		}
	}
	
	//initial animation up/down
	public void upAndDown()
	{	
		this.setY(y + dir);
		 
		if(y == 370 || y == 320)
			dir *= -1;
	}
	
	//ACCESS--MUTATE
	
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

	public static int getGravity() 
	{
		return gravity;
	}
	
	public void setGravity(int ngravity)
	{
		gravity = ngravity;
	}

	public boolean isJump() 
	{
		return isJump;
	}

	public void setJump(boolean isJump) 
	{
		this.isJump = isJump;
	}

	public Image getBird_image() 
	{
		return bird_image;
	}

	public void setBird_image(Image bird_image)
	{
		this.bird_image = bird_image;
	}

	public double getGravity_angle() 
	{
		return gravity_angle;
	}

	public void setGravity_angle(double gravity_angle) 
	{
		this.gravity_angle = gravity_angle;
	}

	public static double getForceAngle() 
	{
		return FORCE_ANGLE;
	}
	
}
