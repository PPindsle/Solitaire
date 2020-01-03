import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener, MouseMotionListener {

	private Slot[] slots = new Slot[11];

	private ArrayList<Card> selectedCards = null;
	private Slot selectedSlot = null;

	// mouse points relative to selected cards
	private int relativeMousePosX = 0;
	private int relativeMousePosY = 0;

	// margin for frame
	private int margin = 25;

	// margin from top for bottom row of cards
	private int marginBottom = 250;

	// horizontal space between cards
	private int marginX = 125;

	// space between cards that are stacked on top of each other
	private int stackMarginY = 25;
	private int stackMarginX = 20;

	private Graphics g;

	private boolean running = true;

	public Board() {
		addMouseListener(this);
		addMouseMotionListener(this);

		this.slots = Slot.getSlots();
	}
	
	public void setup(ArrayList<Card> deck) {
		int max = 1;
		int field = 6;
		for (int i = 0; i < 5; i++) {
			for (int y = 1; y <= max; y++) {
				Card card = deck.get(y);
				if (y != max) {
					card.setState(0);
				}
				placeCard(card, field);
				deck.remove(card);
			}
			max++;
			field++;
		}

		placeCards(deck, 0);
	}

	private void placeCard(Card card, int s) {
		slots[s].placeCard(card);
	}
	
	private void placeCards(ArrayList<Card> cards, int s) {
		slots[s].placeCards(cards);
	}

}
