package game_package;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, MouseListener
{	
	//constants
	private static final long serialVersionUID = 1L;
	private static final int GAP_BETWEEN_PIPES = 200;
	
	//properties
	private Timer game_timer, animation_timer;
	private Background background;
	private ArrayList<Pipe> pipes;
	private Bird bird;
	private Score score;
	private Animation animation;
	private FlyingImageLoader flying_images;
	
	//sounds
	private Sound wing_sound, point_sound, die_sound, hit_sound, swoosh_sound;
	
	//game state
	private boolean start, isGameOver;
	

	//constructor
	public GamePanel()
	{
		this.init();
		this.setFocusable(true);
		this.addMouseListener(this);
		
		game_timer.start();
		animation_timer.start();
	}
	
	//to initialize the properties
	public void init()
	{
		start = false;
		isGameOver = false;
		game_timer = new Timer(10, this);
		animation_timer = new Timer(100, new AnimationActionListener());
		
		//background
		background = new Background();
		
		//pipes
		pipes = new ArrayList<>();
		
		//bird
		bird = new Bird();
		
		//score
		score = new Score();
		score.loadImages();
		
		//images
		flying_images = new FlyingImageLoader();
		flying_images.loadImages();
		
		//animation
		animation = new Animation(flying_images.getImages(), true);
		
		//sounds 
		wing_sound = new Sound("wing.wav");
		point_sound = new Sound("sfx_point.wav");
		die_sound = new Sound("sfx_die.wav");
		hit_sound = new Sound("sfx_hit.wav");
		swoosh_sound = new Sound("sfx_swooshing.wav");
	}
	
	//to draw/paint
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		//background
		background.drawBackground(g);
		
		//pipes
		for(Pipe pipe : pipes)
			pipe.draw(g);
		
		//ground
		background.drawGround(g);
		
		//bird
		bird.setBird_image(animation.getCurrentImage());
		bird.draw(g);
		
		//score
		score.draw(g);
		
		//states
		if(!start && !isGameOver)
		{
			g.drawImage(new ImageIcon(GamePanel.class.getResource("/flappy_bird_images/game_states/ready.png")).getImage(), GameFrame.getFrameWidth()/2 - 112, GameFrame.getFrameHeight()/2 - 160, null);
			g.drawImage(new ImageIcon(GamePanel.class.getResource("/flappy_bird_images/game_states/tap.png")).getImage(), GameFrame.getFrameWidth()/2 - 85, GameFrame.getFrameHeight()/2, null);
		}
		
		if(isGameOver)
		{
			g.drawImage(new ImageIcon(GamePanel.class.getResource("/flappy_bird_images/game_states/game_over.png")).getImage(), GameFrame.getFrameWidth()/2 - 112, GameFrame.getFrameHeight()/2 - 160, null);
		}
			
	}
	
	//update the pipes
	public void updatePipes()
	{
		if(pipes.size() == 0)
		{
			pipes.add(new Pipe());
		}
		else
		{
			for(int i = 0; i < pipes.size(); i++)
			{
				if(pipes.get(i).x + pipes.get(i).getWidth() <= 0)
				{
					pipes.remove(i);
				}
				else
				{
					if(GameFrame.getFrameWidth() + 50 - pipes.get(i).x == GAP_BETWEEN_PIPES)
					{
						pipes.add(new Pipe());
					}
					pipes.get(i).update();
				}
			}
		}
	}
	
	//to update the score
	public void updateScore()
	{
		for(Pipe pipe : pipes)
		{
			if(bird.x + bird.getWidth()/2 == pipe.x + pipe.getWidth()/2)
			{
				point_sound.playSound();
				score.increment();
				break;
			}
		}
	}
	
	//to check the game state 
	public void checkGameState()
	{
		for(Pipe p : pipes)
		{
			if(p.intersect(bird))
			{
				hit_sound.playSound();
				die_sound.playSound();
				
				isGameOver = true;			
				start = false;
						
				break;
			}
		}
	}
	
	//to update everything needed
	public void update()
	{
		if(start)
		{
			checkGameState();
			updatePipes();
			bird.update();
			updateScore();
		}
		
		if(!isGameOver)
			background.update();
		
		if(isGameOver)
		{
			bird.setGravity(5);
			bird.setGravity_angle(Math.PI/9);
			bird.fall();
		}
		
		//up/down animation
		if(!start && !isGameOver)
			bird.upAndDown();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.update();
		this.repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		
		start = true;
			
		if(isGameOver)
		{
			isGameOver = false;
			swoosh_sound.playSound();
			init();
		}
	
		if(start)
		{
			wing_sound.playSound();
			bird.setJump(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		if(start)
		{
			bird.setJump(false);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	
	//////////
	
	private class AnimationActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(!isGameOver)
				animation.updateImages();
		}
		
	}
}
