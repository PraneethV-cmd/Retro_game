package main;

import javax.swing.JPanel;

import entity.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {
	
	// screen settings
	final int originalTileSize = 16; //16x16 tile size in this game
	 final int scale = 3;
	 
	public  final int tileSize = originalTileSize * scale;
	 final int maxScreenCol = 16;
	 final int maxScreenRow = 12;
	 final int screenWidth = tileSize * maxScreenCol ; // 768 pixels
	 final int screenHeight = tileSize * maxScreenRow ;  // 576 pixels
	 
	 int FPS = 60;
	 
	 KeyHandler keyH = new KeyHandler();
	 
	 Thread gameThread;
	 Player player = new Player(this,keyH);	 
	 // set player's default position
	 
	 int playerX = 100;
	 int playerY = 100;
	 int playerSpeed = 4;
	 
	 public GamePanel() {
		 this.setPreferredSize(new Dimension(screenWidth , screenHeight));
		 this.setBackground(Color.black);
		 this.setDoubleBuffered(true);
		 this.addKeyListener(keyH);
		 this.setFocusable(true);
	 }
	 
	 public void startGameThread() {
		 
		 gameThread = new Thread(this); // we are passing the game panel class in here
		 gameThread.start();  
	 }
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; // 0.016666 seconds
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		long frameCount = 0;
		long startTime = System.nanoTime()
;		while(gameThread != null) {
			
			
			// 1. updating the information such as the character positions are seen and
			
			update();
			// 2. draw : draw the screen with the updated information
			repaint();
			// so for the above two tasks, we will create two methods
			
			frameCount++;
			if(System.nanoTime() - startTime >= 1000000000) {
				System.out.println("FPS: " + frameCount);
				frameCount = 0;
				startTime = System.nanoTime();
			}
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval ; 
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void update() {
		
		player.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		player.draw(g2);
		
		g2.dispose();
	}
	
}
