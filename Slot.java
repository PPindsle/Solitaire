import java.util.ArrayList;

public class Slot {
	
	private static Slot[] slots = new Slot[11];
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	private int x, y, field;
	
	public Slot(int x, int y, int f) {
		this.x = x;
		this.y = y;
		this.field = f;
	}
	
	public static Slot[] getSlots() {
		int margin_x = 25;
		int margin_y = 25;
		int margin_between_x = 125;
		int bottom_y = 250;
		
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
		
		return slots;
	}
	
	public void placeCard(Card card) {
		this.cards.add(card);
		
		int yPos = this.y;
		int xPos = this.x;
		
		if (this.getField() > 6 || this.getField() == 2) { // TODO changed
			xPos = card.getX();
			yPos = card.getY();
		}
		
		card.setSlot(xPos, yPos);
	}
	
	public void placeCards() {
		
	}
	
	private int getField() {
		return this.field;
	}

}
