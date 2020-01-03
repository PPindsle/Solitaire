import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Board extends JPanel implements MouseListener, MouseMotionListener {

	/*
	 * TODO
	 * animation when cards move
	 */

	private Slot[] slots = new Slot[11];

	private static Card selected = null;
	private static ArrayList<Card> selected_family = null;

	private Slot slot = null;
	private int relative_x = 0;
	private int relative_y = 0;

	private int margin_x = 25;
	private int margin_y = 25;
	private int bottom_y = 250;

	private int margin_between_x = 125;

	private static int stack_margin_y = 25;
	private static int stack_margin_x = 20;

	private static int border_radius = 10;

	private Graphics g;
	
	private boolean running = true;

	public Board() {
		addMouseListener(this);
		addMouseMotionListener(this);
		slots[0] = new Slot(margin_x, margin_y, 1);
		slots[1] = new Slot(margin_x += margin_between_x, margin_y, 2);
		margin_x += margin_between_x;
		slots[2] = new Slot(margin_x += margin_between_x, margin_y, 3);
		slots[3] = new Slot(margin_x += margin_between_x, margin_y, 4);
		slots[4] = new Slot(margin_x += margin_between_x, margin_y, 5);
		slots[5] = new Slot(margin_x += margin_between_x, margin_y, 6);

		margin_x = 25;
		margin_between_x = 187;

		slots[6] = new Slot(margin_x, bottom_y, 7);
		slots[7] = new Slot(margin_x += margin_between_x, bottom_y, 8);
		slots[8] = new Slot(margin_x += margin_between_x, bottom_y, 9);
		slots[9] = new Slot(margin_x += margin_between_x, bottom_y, 10);
		slots[10] = new Slot(margin_x += margin_between_x, bottom_y, 11);
	}

	public static int getBorderRadius() {
		return border_radius;
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

	public void clear() {
		for (int i = 0; i < this.slots.length; i++) {
			slots[i].removeAll();
		}
		reset();
	}

	public void paintComponent(Graphics g) {
		this.g = g;
		paintBackground(g);
		paintFields(g);
	}

	public void placeCards(ArrayList<Card> cards, int slot) {
		slots[slot].placeCards(cards);
	}

	public void placeCard(Card card, int slot) {
		slots[slot].placeCard(card);
	}

	private void paintBackground(Graphics g) {
		g.setColor(Color.decode("#2a9136"));
		g.fillRect(0, 0, Window.width(), Window.height());
	}

	private void paintFields(Graphics g) {
		for (int i = 0; i < slots.length; i++) {
			slots[i].draw(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)) {
			return;
		}
		if (!running)
			return;
		Slot slot = null;
		Card card = null;
		ArrayList<Card> family = null;

		try {
			slot = getSlot(e.getX(), e.getY());
		} catch (Exception ex) {

		}

		if (slot == null) {
			reset();
			return;
		}

		try {
			card = slot.getCard();
		} catch (Exception ex) {

		}

		if (card == null && slot.getField() != 1) {
			reset();
			return;
		}

		if (e.getClickCount() == 2 && card != null && (slot.getField() == 2 || slot.getField() > 6)) {
			try {
				slot = getSlot(e.getX(), e.getY());
				this.slot = slot;
				card = this.slot.getSelectedCard(e.getX(), e.getY());
				this.selected = card;
				family = this.slot.getFamily(this.selected);
			} catch (Exception ex) {
				reset();
			}

			if (family != null && family.size() == 1) {
				moveToTopIfAllowed(card);
			}
		} else if (slot.getField() == 1) {
			if (card != null) {
				family = slot.getCards(3);
				slot.removeCards(family);
				Slot new_pos = this.slots[1];
				new_pos.placeCards(family);
			} else {
				Deck deck = new Deck(slots[1].getCards());
				if (deck.getSize() > 0) {
					//deck.shuffle();
					slots[0].placeCards(deck.getReversedDeck());
					slots[1].removeAll();
					slots[1].printCards();
				}
			}
		} else if (slot.getField() > 6) {
			if (card != null && card.getState() == 0) {
				card.setState(1);
			}
		}

	}

	private void moveToTopIfAllowed(Card card) {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(card);
		Slot slot = null;

		if (card.getState() == 0) {
			reset();
			return;
		}

		for (int i = 2; i < 6; i++) {
			try {
				slot = this.slots[i];
			} catch (Exception ex) {
				reset();
			}

			if (slot == null) {
				reset();
			}

			if (allowed(slot, cards)) {
				moveIfAllowed(slot, cards);
				reset();
				return;
			}
		}
		reset();
	}

	private Slot getSlot(int x, int y) {
		for (int i = 0; i < slots.length; i++) {
			Slot slot = slots[i];

			int min_x = slot.getX();
			int max_x = min_x + Card.width();
			
			int min_y = slot.getY();
			int max_y = min_y + Card.height();
			
			if (slot.getField() > 6) {
				max_y = slot.getYOfCard() + Card.height();
			} else if (slot.getField() == 2) {
				min_x = slot.getXOfCard();
				max_x = min_x + Card.width();
			}
			
			if (x >= min_x && x <= max_x && y >= min_y && y <= max_y) {
				return slot;
			}
		}
		
		return null;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)) {
			return;
		}
		if (!running)
			return;

		Slot slot = null;
		Card card = null;
		ArrayList<Card> family = null;

		try {
			slot = getSlot(e.getX(), e.getY());
			this.slot = slot;


			card = this.slot.getSelectedCard(e.getX(), e.getY());
			this.selected = card;

			if (slot.getField() == 2) {
				family = new ArrayList<Card>();
				family.add(this.selected);
			} else {
				family = this.slot.getFamily(this.selected);
			}
		} catch (Exception ex) {
			reset();
			return;
		}

		if (this.slot.getField() == 1) {
			reset();
			return;
		}

		if (card == null) {
			reset();
			return;
		}

		if (card.getState() == 0) {
			reset();
			return;
		}

		this.selected = card;
		if (family != null) {
			this.selected_family = family;
		}

		this.relative_x = this.selected.getRelativeMousePointX(e);
		this.relative_y = this.selected.getRelativeMousePointY(e);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!running)
			return;
		Card card = null;
		Slot slot = null;

		if (this.selected == null) {
			reset();
			return;
		}

		try {
			int x = e.getX() - this.relative_x + (Card.width() / 2);
			int y = e.getY() - this.relative_y + (Card.height() / 2);
			slot = getSlot(x, y);
		} catch (Exception ex) {
			replace(this.slot, this.selected_family);
			return;
		}

		if (slot == null) {
			replace(this.slot, this.selected_family);
			return;
		}

		if (slot.getField() == 1) {
			replace(this.slot, this.selected_family);
			return;
		}

		if (slot == this.slot) {
			replace(this.slot, this.selected_family);
			return;
		}

		try {
			card = slot.getCard();
		} catch (Exception ex) {
			if (slot == this.slot) {
				replace(this.slot, this.selected_family);
				return;
			}
			// field is top for aces
			if ((slot.getField() > 2 && slot.getField() < 7 && this.selected_family.size() < 2)
					|| this.selected.getRank().getNumber() == 13) {
				moveIfAllowed(slot, this.selected_family);
			} else {
				replace(this.slot, this.selected_family);
			}
			return;
		}

		if (card.getState() == 0) {
			replace(this.slot, this.selected_family);
			return;
		}

		moveIfAllowed(slot, this.selected_family);

		reset();
	}

	private void moveIfAllowed(Slot slot, ArrayList<Card> cards) {
		if (allowed(slot, cards)) {
			slot.placeCards(cards);
			this.slot.removeCards(cards);
		} else {
			replace(this.slot, cards);
		}
		reset();

		checkIfWon();
	}

	private void checkIfWon() {
		for (int i = 2; i < 6; i++) {
			Slot slot = this.slots[i];
			if (!slot.isFilled()) {
				return;
			}
		}
		setWon();
	}

	private void setWon() {
		this.running = false;
	}

	private boolean allowed(Slot slot, ArrayList<Card> cards) {
		Card card = cards.get(0);
		Card last_card = null;

		try {
			last_card = slot.getCard();
		} catch (Exception ex) {

		}

		if (slot.getField() > 2 && slot.getField() < 7) {
			if (cards.size() > 1)
				return false;
			if (last_card == null) {
				if (card.getRank().getNumber() > 1) {
					return false;
				}
			} else if (last_card.getRank().getNumber() != card.getRank().getNumber() - 1
					|| last_card.getSuit() != card.getSuit()) {
				return false;
			}
		}

		if (slot.getField() > 6) {
			if (last_card == null) {
				if (card.getRank().getNumber() != 13) {
					return false;
				}
			} else if (last_card.getRank().getNumber() != card.getRank().getNumber() + 1
					|| last_card.getColor() == card.getColor()) {
				return false;
			}
		}

		return true;
	}

	private void replace(Slot slot, ArrayList<Card> cards) {
		slot.replaceCards(cards);
		reset();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (this.selected != null) {
			for (int i = 0; i < this.selected_family.size(); i++) {
				int card_x = e.getX() - this.relative_x;
				int card_y = e.getY() - this.relative_y + (stack_margin_y * i);
				this.selected_family.get(i).setSlot(card_x, card_y);
			}
			
		}
		repaint();
	}

	private void reset() {
		this.selected = null;
		this.selected_family = null;
		this.slot = null;
		this.relative_x = 0;
		this.relative_y = 0;
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

	public static int getStackMarginY() {
		return stack_margin_y;
	}

	public static int getStackMarginX() {
		return stack_margin_x;
	}
	
	public static ArrayList<Card> getFamily() {
		if (selected_family != null) {
			return selected_family;
		}
		if (selected != null) {
			ArrayList<Card> cards = new ArrayList<Card>();
			cards.add(selected);
			return cards;
		}
		return null;
	}

}
