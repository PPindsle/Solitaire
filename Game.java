import java.awt.BorderLayout;

public class Game {
	
	private Frame frame;
	private Deck deck;
	private Board board;
	
	public Game(Frame f) {
		this.frame = f;
		start();
	}
	
	private void start() {
		this.deck = new Deck();
		deck.shuffle();
		
		this.board = new Board();
		this.frame.add(board, BorderLayout.CENTER);
		board.setup(deck.getDeck());
	}
	
	public void restart() {
		Frame f = this.frame;
		new Frame(f.getWidth(), f.getHeight());
		f.setVisible(false);
		f.dispose();
	}

}
