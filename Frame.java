import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Frame extends JFrame {

	private Game game;
	
	private int width, height;
	
	public Frame(int w, int h) {
		this.width = w;
		this.height = h;
		
		getContentPane().setPreferredSize(new Dimension(w, h));
		setLayout(new BorderLayout());
		pack();
		setTitle("Solitaire");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.game = new Game(this);

		add(new Menu(game), BorderLayout.NORTH);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Frame(900, 700);
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
}
