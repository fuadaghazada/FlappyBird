package game_package;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Background 
{
	//properties
	private int x, speed;
	private Image bg_image;
	private Image ground_image1;
	private Image ground_image2;
	private static int y;
	
	//constructor
	public Background()
	{
		bg_image = new ImageIcon(Background.class.getResource("/flappy_bird_images/backgrounds/background.jpg")).getImage();
		ground_image1 = new ImageIcon(Background.class.getResource("/flappy_bird_images/backgrounds/ground.jpg")).getImage();
		ground_image2 = new ImageIcon(Background.class.getResource("/flappy_bird_images/backgrounds/ground.jpg")).getImage();
		
		this.setSpeed(1);
		
		this.setX(0);
		this.setY(625);
	}
	
	//draw/paint the background
	public void drawGround(Graphics g)
	{
		g.drawImage(ground_image1, x, y, null);
		g.drawImage(ground_image2, x + GameFrame.getFrameWidth() - 10  , y, null);
	}
	
	public void drawBackground(Graphics g)
	{
		g.drawImage(bg_image, 0, 0, null); //ends in 625th pixel
	}
	
	//to update the background so that it moves continuously during the game play
	public void update()
	{
		if(x + GameFrame.getFrameWidth() - 10 == 0)
		{
			this.setX(0);
		}
		this.setX(x -= speed);
	}

	//ACCESS - MUTATE
	
	public int getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public static int getY() 
	{
		return y;
	}

	public void setY(int ny) 
	{
		y = ny;
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
