package game_package;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject 
{
	//properties
	protected int x, y, height, width;
	
	//to draw/paint
	public abstract void draw(Graphics g);
	
	//to determine the collision if necessary
	public boolean intersect(GameObject object)
	{
		Rectangle rect1 = new Rectangle(this.x, this.y, this.getWidth(), this.getHeight());
		Rectangle rect2 = new Rectangle(object.x, object.y, object.getWidth(), object.getHeight());
		
		return rect1.intersects(rect2);		
	}
	
	//ACCESS
	public int getX()
	{
		return x;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getWidth()
	{
		return width;
	}
}
