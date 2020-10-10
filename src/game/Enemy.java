package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Enemy {
	public static int PLAYER_WIDTH = 40;
	public static int PLAYER_HEIGHT = 10;

	public boolean right, left;
	
	public double x, y;
	private Color color;
	public int score;
	public Enemy(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		score = 0;
	}
	
	public void tick(Ball ball)
	{
		x = ball.x * 0.8; //(ball.x - x - 6);
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
		//g.setColor(Color.white);
		//g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
		//g.drawString(Integer.toString(score), (int)(x + PLAYER_WIDTH / 2), (int)y);

		g.setColor(color);
		g.fillRect((int)x, (int)y, PLAYER_WIDTH, PLAYER_HEIGHT);
	}
}
