
public class Rank {
	
	private int number;
	private String description;
	
	private static Rank[] ranks = new Rank[13];
	
	private static Rank ace = ranks[0] = new Rank(1, "Ace");
	private static Rank two = ranks[1] = new Rank(2, "Two");
	private static Rank three = ranks[2] = new Rank(3, "Three");
	private static Rank four = ranks[3] = new Rank(4, "Four");
	private static Rank five = ranks[4] = new Rank(5, "Five");
	private static Rank six = ranks[5] = new Rank(6, "Six");
	private static Rank seven = ranks[6] = new Rank(7, "Seven");
	private static Rank eight = ranks[7] = new Rank(8, "Eight");
	private static Rank nine = ranks[8] = new Rank(9, "Nine");
	private static Rank ten = ranks[9] = new Rank(10, "Ten");
	private static Rank jack = ranks[10] = new Rank(11, "Jack");
	private static Rank queen = ranks[11] = new Rank(12, "Queen");
	private static Rank king = ranks[12] = new Rank(13, "King");
	
	public Rank(int number, String description) {
		this.number = number;
		this.description = description;
	}
	
	public static Rank[] getRanks() {
		return ranks;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public String getDescription() {
		return this.description;
	}

}
