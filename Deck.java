import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
	
	private ArrayList<Card> cards;
	private Random rand = new Random();
	
	public Deck() {
		this.cards = newDeck();
	}
	
	public Deck(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	private ArrayList<Card> newDeck() {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < 4; i++) {
			for (int y = 1; y <= 13; y++) {
				cards.add(new Card(Suit.getSuits()[i], y));
			}
		}
		return cards;
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public ArrayList<Card> getDeck() {
		return this.cards;
	}
	
	public int getSize() {
		return this.cards.size();
	}
	
	public void clear() {
		this.cards.clear();
	}
	
	public ArrayList<Card> getReversedDeck() {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = this.getSize() - 1; i >= 0; i--) {
			cards.add(this.getDeck().get(i));
		}
		return cards;
	}

}
