package game_package;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Score 
{
	//constants
	private static final int NUMBERS = 10;
	private static final int WIDTH = 24;
	private static final int HEIGHT = 44;
	
	//properties
	private int score;
	private int n;						//number of digits
	private ArrayList<Image> numbers;
	
	
	public Score()
	{
		this.score = 0;
		numbers = new ArrayList<>();
	}
	
	//to draw
	public void draw(Graphics g)
	{
		String scoreInString = "" + score;
		
		this.setN(scoreInString.length()); //Math.floor(Math.log10(score) + 1))
		
		System.out.println(n);
		
		int margin = (GameFrame.getFrameWidth() - n * WIDTH)/2;
		
		for(int i = 0; i < n; i++)
		{
			int digit = Integer.parseInt(scoreInString.substring(i, i + 1));
			
			System.out.println(digit);
			
			g.drawImage(numbers.get(digit), margin + i * WIDTH, 100, null);
		}
	}

	//to increment the score
	public void increment()
	{
		this.setScore(++score);
	}
	
	//to reset the score
	public void reset()
	{
		this.setScore(0);
	}
	
	//load the images for the numbers
	public void loadImages()
	{
		for(int i = 0; i < NUMBERS; i++)
		{
			numbers.add(new ImageIcon(Score.class.getResource("/flappy_bird_images/numbers/" + i + ".png")).getImage());
		}
	}
	
	//ACCESS
	
	public int getScore() 
	{
		return score;
	}

	public void setScore(int score) 
	{
		this.score = score;
	}

	public static int getNumbers() 
	{
		return NUMBERS;
	}

	public static int getWidth() 
	{
		return WIDTH;
	}

	public static int getHeight() 
	{
		return HEIGHT;
	}

	public int getN() 
	{
		return n;
	}

	public void setN(int n) 
	{
		this.n = n;
	}
}
