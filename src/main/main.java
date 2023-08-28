package main;

import javax.swing.JFrame ; 

public class main {
	public static void main(String args[]) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// the above code is for enabling us to be able to close the game window when we click on the close button
		
		window.setResizable(false);
		window.setTitle("2D adventure");
		
		GamePanel gamepanel = new GamePanel();
		window.add(gamepanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamepanel.startGameThread();
	}
}
