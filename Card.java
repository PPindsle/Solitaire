import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class Card extends JComponent {

	private Suit suit;
	private Rank rank;

	private int x, y;

	private int state = 1;

	private static int width = 100;
	private static int height = 138;

	private Sprite card_sprite;
	private Sprite card_back_sprite;

	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;

		this.card_sprite = new Sprite("src/img/card_spritesheet.png", this.getCol(), this.getRow(), 14, 4);
		this.card_back_sprite = new Sprite("src/img/card_spritesheet.png", 13, 1, 14, 4);
	}
	
	public int getCol() {
		return this.rank.getNumber() - 1;
	}

	public int getRow() {
		return this.suit.getNumber() - 1;
	}

	public String getDescription() {
		return this.rank.getDescription() + " of " + this.suit.getDescription();
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}

	public Rank getRank() {
		return this.rank;
	}

	public Suit getSuit() {
		return this.suit;
	}

	public Color getColor() {
		return this.suit.getColor();
	}

	public static int width() {
		return width;
	}

	public static int height() {
		return height;
	}

	public void draw(Graphics g) {
		if (state == 1) {
			BufferedImage sprite = this.card_sprite.getSprite();

			if (sprite != null) {
				g.drawImage(sprite, this.x, this.y, this.width, this.height, this);
			}
		} else {
			BufferedImage sprite = this.card_back_sprite.getSprite();

			if (sprite != null) {
				g.drawImage(sprite, this.x, this.y, this.width, this.height, this);
			}
		}
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setSlot(int x, int y) {
		while (this.x != x || this.y != y) {
			this.x = x;
			this.y = y;
			repaint();
		}
		
		
		
	}

	public int getRelativeMousePointX(MouseEvent e) {
		return e.getX() - this.getX();
	}

	public int getRelativeMousePointY(MouseEvent e) {
		return e.getY() - this.getY();
	}

}
