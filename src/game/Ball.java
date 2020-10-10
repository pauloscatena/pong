package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	double x, y;
	double dx, dy;
	double speed = 2.5;
	int width, height;
	
	public Ball(double x, double y) {
		this.x = x;
		this.y = y;
		width = 5;
		height = 5;
		initializeBallAngle();	
	}
	
	public void tick(Game game) {
		x += dx * speed;
		y += dy * speed;
		
		checkScore(game);
		checkWallsColision();
		checkPlayerColision(game);
	}
	
	private void checkScore(Game game)
	{
		if(y > Game.HEIGHT) {
			// ponto inimigo
			game.player2.score++;
			ballReset(game);
		} else if (y < 0) {
			// ponto jogador
			game.player1.score++;
			ballReset(game);
		}
	}
	
	private void ballReset(Game game) {
		x = game.player1.x + (Player.PLAYER_WIDTH / 2);
		y = game.player1.y - Player.PLAYER_HEIGHT - 3;
		dx = new Random().nextGaussian();
		dy = new Random().nextGaussian();
		
		if(dy == 0) {
			initializeBallAngle();
		}
		
		if(dy > 0) {
			dy *= -1;
		}
	}
	
	private void initializeBallAngle() {
		//int anglex = new Random().nextInt(359);
		//int angley = new Random().nextInt(359);
		//dx = Math.cos(Math.toRadians(anglex)) * new Random().nextGaussian();
		//dy = Math.asin(Math.toRadians(angley)) * new Random().nextGaussian();
		
		dx = new Random().nextGaussian();
		dy = new Random().nextGaussian();

	}
	
	private void checkWallsColision()
	{
		if(x + width >= Game.WIDTH || x <= 0)
			dx *= -1;
	}
	
	private void checkPlayerColision(Game game) {
		Rectangle bounds = new Rectangle((int)x, (int)y, width, height);
		Rectangle boundsPlayer = new Rectangle((int)(game.player1.x), (int)game.player1.y, Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT);
		Rectangle boundsEnemy = new Rectangle((int)(game.player2.x), (int)game.player2.y, Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT);
		
		if(bounds.intersects(boundsPlayer) || bounds.intersects(boundsEnemy))
		{
			dy *= -1;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, width, height);
	}
}
