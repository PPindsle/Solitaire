import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Sprite extends JPanel {
	
	private BufferedImage image;
	private String path;
	
	private int row, col;
	private int cols, rows;
	
	private static float scale = (float) 0.7;
	
	private int width;
	private int height;
	
	public Sprite(String path, int col, int row, int cols, int rows) {
		this.path = path;
		this.row = row;
		this.col = col;
		
		this.cols = cols;
		this.rows = rows;
		
		this.image = getImage(this.col * this.width, this.row * this.height);		
	}
	
	public BufferedImage getImage(int col, int row) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (Exception e) {
			
		}
		
		if (img != null) {
			this.width = img.getWidth() / this.cols;
			this.height = img.getHeight() / this.rows;
			
			return img.getSubimage(this.col * this.width, this.row * this.height, (int)(Card.width() * scale), (int)(Card.height() * scale));
		}
		return null;
	}
	
	public BufferedImage getSprite() {
		return this.image;
	}
	
}
