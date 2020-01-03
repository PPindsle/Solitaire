import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {

	private Game game;	
	private Menu menu;
	
	private static int width = 900;
	private static int height = 700;
	
	public static void main(String[] args) {
		new Window();
	}
	
	public Window() {
		getContentPane().setPreferredSize(new Dimension(width(), height()));
		setLayout(new BorderLayout());
		pack();
		setTitle("Solitaire");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.game = new Game(this);

		add(menu = new Menu(this), BorderLayout.NORTH);
		
		setVisible(true);
	}
	
	public static int width() {
		return width;
	}
	
	public static int height() {
		return height;
	}
	
	public void restart() {
		Window test = this;
		new Window();
		test.setVisible(false);
		test.dispose();
	}

}
