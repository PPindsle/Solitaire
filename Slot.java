import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Slot extends Thread {

	private int x, y, field;
	private int width = Card.width();
	private int height = Card.height();

	private ArrayList<Card> cards = new ArrayList<Card>();

	private Color color = Color.decode("#15421a");
	

	public Slot(int x, int y, int field) {
		this.x = x;
		this.y = y;
		this.field = field;
		
		start();
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillRoundRect(x, y, width, height, Board.getBorderRadius(), Board.getBorderRadius());

		if (this.cards.size() > 0) {
			drawCards(g);
		}
	}

	public int getYOfCard() {
		Card card = null;
		try {
			card = this.getCard();
		} catch (Exception ex) {

		}

		if (card == null) {
			return this.y;
		}

		return card.getY();
	}

	public int getXOfCard() {
		Card card = null;
		try {
			card = this.getCard();
		} catch (Exception ex) {

		}
		if (card == null) {
			return this.x;
		}

		return card.getX();
	}

	private void drawCards(Graphics g) {
		ArrayList<Card> family = null;
		try {
			family = Board.getFamily();
		} catch(Exception ex) {
			
		}
		
		for (int i = 0; i < this.cards.size(); i++) {
			Card card = this.cards.get(i);
			
			// dont draw the cards that are selected
			if (family != null) {
				for (int y = 0; y < family.size(); y++) {
					if (card != family.get(y)) {
						card.draw(g);
					}
				}
			} else {
				card.draw(g);
			}

		}
		
		// draw them last so that they are on top
		if (family != null) {
			for (int y = 0; y < family.size(); y++) {
					family.get(y).draw(g);
			}
		}
	}

	public void placeCards(ArrayList<Card> cards) {
		for (int i = 0; i < cards.size(); i++) {
			if (this.getField() == 1) {
				cards.get(i).setState(0);
			} else if (this.getField() == 2) {
				cards.get(i).setState(1);
			}
			placeCard(cards.get(i));
		}
		if (this.getField() == 2) {
			stack();

		}
	}

	public boolean isFilled() {
		return this.cards.size() == 13;
	}

	public void placeCard(Card card) {
		this.cards.add(card);

		int y_pos = this.y;
		int x_pos = this.x;

		if (this.getField() > 6) {
			for (int i = 1; i < this.cards.size(); i++) {
				y_pos += Board.getStackMarginY();
			}
		} else if (this.getField() == 2) {
			x_pos = card.getX();
			y_pos = card.getY();
		}

		card.setSlot(x_pos, y_pos);
	}

	public void replaceCards(ArrayList<Card> cards) {
		int y_pos = this.y;

		if (this.getField() > 6) {
			for (int i = 0; i < this.cards.size(); i++) {
				for (int y = 0; y < cards.size(); y++) {
					if (cards.get(y) == this.cards.get(i)) {
						cards.get(y).setSlot(this.x, y_pos);
					}
				}
				y_pos += Board.getStackMarginY();
			}
		} else {
			for (int i = 0; i < cards.size(); i++) {
				cards.get(i).setSlot(this.x, y_pos);
			}

			if (this.getField() == 2) {
				stack();
			}
		}
	}

	public void stack() {
		for (int i = 0; i < this.cards.size(); i++) {
			this.cards.get(i).setSlot(this.x, this.y);
		}

		ArrayList<Card> cards = this.getCards(3);
		int x_pos = this.x;
		for (int i = cards.size() - 1; i >= 0; i--) {
			Card card = cards.get(i);
			card.setSlot(x_pos, card.getY());
			x_pos += Board.getStackMarginX();
		}
	}

	public void removeCards(ArrayList<Card> cards) {
		for (int i = 0; i < cards.size(); i++) {
			this.cards.remove(cards.get(i));
		}
		if (this.getField() == 2) {
			stack();
		}
	}

	public void removeAll() {
		this.cards.clear();
	}

	public void printCards() {
		for (int i = 0; i < this.cards.size(); i++) {
			System.out.println(this.cards.get(i).getDescription());
		}
	}

	public int getField() {
		return this.field;
	}

	public ArrayList<Card> getFamily(Card card) {
		int index = this.cards.indexOf(card);

		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = index; i < this.cards.size(); i++) {
			cards.add(this.cards.get(i));
		}
		return cards;
	}

	public Card getCard() {
		Card card = cards.get(cards.size() - 1);
		if (card != null) {
			return card;
		}
		return null;
	}

	public Card getSelectedCard(int x, int y) {
		Card card = null;
		for (int i = 0; i < this.cards.size(); i++) {
			card = this.cards.get(i);

			int height = Board.getStackMarginY();
			// int width = Board.getStackMarginX();

			if (card == getCard()) {
				height = Card.height();
			}

			if (x >= card.getX() && x <= card.getX() + Card.width()) {
				if (y >= card.getY() && y <= card.getY() + height) {
					return card;
				}
			}
		}
		return null;
	}

	public ArrayList<Card> getCards() {
		return this.cards;
	}

	public ArrayList<Card> getCards(int amount) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = this.cards.size() - 1; i > this.cards.size() - 1 - amount; i--) {
			Card card = null;
			try {
				card = this.cards.get(i);
			} catch (Exception ex) {

			}
			if (card != null) {
				cards.add(this.cards.get(i));
			}
		}
		return cards;
	}

}
