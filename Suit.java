import java.awt.Color;

public class Suit {
	
	private Color color;
	private String description;
	

	private static Suit[] suits = new Suit[4];
	
	private static Suit hearts = suits[0] = new Suit(Color.RED, "Hearts");
	private static Suit diamonds = suits[1] = new Suit(Color.RED, "Diamonds");
	private static Suit spades = suits[2] = new Suit(Color.BLACK, "Spades");
	private static Suit clubs = suits[3] = new Suit(Color.BLACK, "Clubs");
	
	public Suit(Color color, String description) {
		this.color = color;
		this.description = description;
	}
	
	public static Suit[] getSuits() {
		return suits;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public int getNumber() {
		if (this.description == "Clubs") return 1;
		if (this.description == "Spades") return 2;
		if (this.description == "Hearts") return 3;
		if (this.description == "Diamonds") return 4;
		return 0;
	}
	
	public String getDescription() {
		return this.description;
	}

}
