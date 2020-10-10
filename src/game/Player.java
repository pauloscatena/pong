package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Player {

	public static int PLAYER_WIDTH = 40;
	public static int PLAYER_HEIGHT = 10;

	public boolean right, left;
	
	public int x, y;
	public int score;
	private Color color;
	public Player(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		score = 0;
	}
	public void tick()
	{
		if(right)
			x+=3;
		
		if(left)
			x-=3;

		checkColision();
	}
	
	private void checkColision()
	{
		if(x + PLAYER_WIDTH >= Game.WIDTH)
			x = Game.WIDTH - PLAYER_WIDTH;
		
		if(x <= 0)
			x = 0;		
	}
	
	public void render(Graphics g)
	{
		//g.setColor(Color.black);
		//g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
		//g.drawString(Integer.toString(score), (int)(x + PLAYER_WIDTH / 2), (int)y);
		
		g.setColor(color);		
		g.fillRect(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
	}
}
