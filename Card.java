
public class Card {
	
	private int state = 1;
	private int x, y;
	
	public Card(Suit s, int n) {
		
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setSlot(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
