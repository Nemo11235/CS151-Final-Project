import java.awt.Graphics2D;

public interface BoardStyle {
	void draw(Graphics2D g);
	void setRules(GameRule m);
}
