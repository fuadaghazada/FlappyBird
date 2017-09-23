 package game_package;

import javax.swing.JFrame;

public class GameFrame
{
	//constants
	private static final int FRAME_WIDTH = 390;
	private static final int FRAME_HEIGHT = 700;
	
	public static void main(String[] args) 
	{
		JFrame game_frame = new JFrame();
		GamePanel game_panel = new GamePanel();
		
		game_frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		game_frame.setTitle("Flappy Bird");
		game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game_frame.setResizable(false);
		game_frame.setLocationRelativeTo(null);
		game_frame.add(game_panel);
		game_frame.setVisible(true);
	}

	public static int getFrameWidth() 
	{
		return FRAME_WIDTH;
	}

	public static int getFrameHeight() 
	{
		return FRAME_HEIGHT;
	}

}
