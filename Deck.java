import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Deck {
	
	private ArrayList<Card> cards = new ArrayList<Card>();
	private Random rand = new Random();
	
	public Deck() {	
		for (int i = 0; i < 4; i++) {
			for (int y = 0; y < 13; y++) {
				cards.add(new Card(Suit.getSuits()[i], Rank.getRanks()[y]));
			}
		}
	}
	
	public Deck(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public void print() {
		for (int i = 0; i < 52; i++) {
			System.out.println(cards.get(i).getDescription());
		}
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
