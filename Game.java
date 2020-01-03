import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Game {
	
	private Deck deck;
	private Board board;
	private Window window;
		
	public Game(Window window) {
		this.window = window;
		start();
	}
	
	private void start() {
		this.deck = new Deck();
		this.deck.shuffle();
		this.window.add(this.board = new Board(), BorderLayout.CENTER);
		
		this.board.setup(this.deck.getDeck());
	}

}
