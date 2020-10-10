package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 240;
	public static int HEIGHT = 120;
	public static int SCALE = 3;
	
	public BufferedImage layer;
	
	public Player player1;
	public Enemy player2;
	private Ball ball;
	
	public Game() {
		this.addKeyListener(this);
		layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		initializePlayers();
	}
	
	private void initializePlayers()
	{
		int screenCenter = (WIDTH / 2) - (Player.PLAYER_WIDTH / 2);
		int screenBottom = HEIGHT - Player.PLAYER_HEIGHT;
		
		player1 = new Player(screenCenter, screenBottom, Color.blue);
		player2 = new Enemy(screenCenter, 0, Color.red);		
		ball = new Ball(50, 50);
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		
		/*Window configurations*/
		JFrame frame = new JFrame("Pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	private void tick()
	{
		player1.tick();
		player2.tick(ball);
		ball.tick(this);
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = layer.getGraphics();
				
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		player1.render(g);		
		player2.render(g);
		ball.render(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(layer,  0,  0,  WIDTH * SCALE,  HEIGHT * SCALE,  null);
		bs.show();
	}
	
	@Override
	public void run() {
		while(true) {
			tick();
			render();
			
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			player1.right = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			player1.left = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			player1.right = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			player1.left = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
