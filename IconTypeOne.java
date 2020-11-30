import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.Icon;

public class IconTypeOne implements Icon {
	private int stoneNum;

	public IconTypeOne(int stoneNum) {
		this.stoneNum = stoneNum;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0, j = 0, offset = 0, row = 0; i < stoneNum; i++, j++, offset++) {
			if (i != 0 && i % 5 == 0) {
				row += 21;
				j = 0;
				offset = 0;
			}
			g2.setColor(Color.orange);
			Ellipse2D.Double ellipse = new Ellipse2D.Double(20 * j + offset, row, 20, 20);
			g2.fill(ellipse);
		}
	}
	
	/**
	 * Dummy method
	 */
	@Override
	public int getIconHeight() {
		return 0;
	}

	/**
	 * Dummy method
	 */
	@Override
	public int getIconWidth() {
		return 0;
	} 
}