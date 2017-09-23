package game_package;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class FlyingImageLoader 
{
	//constants
	private static final int SIZE = 3;
	
	//properties
	private ArrayList<Image> images;
	
	//default constructor
	public FlyingImageLoader()
	{
		images = new ArrayList<>();
	}
	
	//load images
	public void loadImages()
	{
		for(int i = 0; i < SIZE; i++)
		{
			images.add(new ImageIcon(Animation.class.getResource("/flappy_bird_images/birds/bird_part_" + i + ".png")).getImage());
		}
	}
	
	//access the image list
	public ArrayList<Image> getImages()
	{
		return images;
	}
}
