import javax.swing.JButton;

public interface BoardStyle {
	
	public void makeBoard(int stoneNum, final JButton undo, JButton[] pits, Model m);
	
	public void setInactive(JButton pit);
	
	public void setActive(JButton pit);
	
	public void setIcons(int pitIndex, int stoneNum);
	
	public void pack();
	
	public void repaint();
} 