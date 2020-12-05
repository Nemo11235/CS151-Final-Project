import javax.swing.*;

/**
 * Mancala tester
 * User will chose the board style and number of stones
 * @author TeamCat
 */
public class MancalaTester {
	public static void main(String args[]) {
		// use JOptionPane to ask the user for game board style and initial stones, and setup the int array
		String[] styles = {"Style 1", "Style 2"};
		String[] stoness = {"3", "4"};
		int data[] = new int[14];
		String style = (String)JOptionPane.showInputDialog(null, "Choose a board style", "Game board selection", JOptionPane.QUESTION_MESSAGE, null, styles, "Style 1");
		int stones = Integer.parseInt((String)JOptionPane.showInputDialog(null, "Choose the initial stones", "stones selection", JOptionPane.QUESTION_MESSAGE, null, stoness, "3"));
		// setup the int array
		for(int i = 0; i < data.length; i++) {
			if(i != 6 && i != 13)
				data[i] = stones;
			else
				data[i] = 0;
		}
		
		Model model = new Model();
		model.setStoneNum(stones);
		if(style.equals("Style 1")) {
			new StyleOne(model);
		} else {
			View initiate = new View(model);
			model.attach(initiate);
			initiate.startGame(stones);
		}
	}
}
