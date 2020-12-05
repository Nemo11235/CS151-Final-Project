import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * Contains the model and controller
 * This class holds the mancala board data and defines the controller method
 * @author TeamCat
 * @version 12/04/2020
 */
public class Game {
	private JFrame frame = new JFrame();
	final static int MANCALA_A = 6;
	final static int MANCALA_B = 13;
	
	/**
	 * Initialize game
	 * @param pits - array of mancala pits and stones
	 */
	public Game(int[] pits) {
	}
	
	/**
	 * This is a function that will complete a player's turn
	 * Controller
	 * @param pits - int array that keeps track of the # of pebbles in each pit
	 * @param player - 0 for player A, 1 for player B
	 * @param total - the total number of pebbles on the board
	 * @return whether if player goes again
	 */
	public boolean move(int[] pits, int player, int total) {
		int cur; // int that indicates the current pit
		
		// get the actual index of the array corresponding to the user's choice
		if(player == 0) 
			cur = total - 1;
		else 
			cur = total + 6;
				
		int pebbles = pits[cur]; // get the number of pebbles in the pit which the player wants to start with
		pits[cur] = 0; // clear the current pit's pebbles
				
		// loop until all the pebbles in that pit has been distributed
		while(pebbles != 0) {
			cur++;
			if(cur == MANCALA_B && player == 0)
				cur = 0;
			else if (cur == MANCALA_A && player == 1)
				cur++;
			else if (cur > 13)
				cur = 0;
			pits[cur]++;
			pebbles -= 1; // remaining pebbles - 1
		}
				
		// if the last pebble lands in an empty spot of the player's side, he gets to collect this pebble and the opposite pit's pebble to his mancala
		if(pits[cur] == 1 && cur < 6 && player == 0 && pits[12 - cur] != 0) {
			pits[MANCALA_A] += (1 + pits[12 - cur]);
			pits[cur] = 0;
			pits[12 - cur] = 0;
		} else if (pits[cur] == 1 && cur < 13 && cur > 6 && player == 1 && pits[12 - cur] != 0) {
			pits[MANCALA_B] += (1 + pits[12 - cur]);
			pits[cur] = 0;
			pits[12 - cur] = 0;
		}
				
		// check if the game is over after every move
		if (gameOver(pits)) {
			JFrame resultFrame = new JFrame();
			JTextArea results = new JTextArea(4, 10);
			results.setText(getResults(pits));
			results.setEditable(false);
			resultFrame.add(results);
			resultFrame.pack();
			resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			resultFrame.setVisible(true);		
		}
				
		// if it lands in the current player's mancala and the game is not over yet, then that player gets another chance
		if ((cur == MANCALA_A && player == 0) || (cur == MANCALA_B && player == 1)) {
			JOptionPane.showMessageDialog(frame, "Scored, another chance granted");
			return true;
		}
		return false;
	}

	/**
	 * Gets end game message
	 * @param pits - mancala board
	 * @return winner - result message
	 */
	private String getResults(int[] pits) {
		String winner = "";
		//Calculate Player A score
		for (int i = 0; i < MANCALA_A; i++) {
			pits[MANCALA_A] += pits[i];
			pits[i] = 0;
		}
		//Calculate Player B score
		for (int i = 7; i < MANCALA_B; i++) {
			pits[MANCALA_B] += pits[i];
			pits[i] = 0;
		}
		//Compare scores
		if (pits[MANCALA_A] > pits[MANCALA_B]) {
			winner = "Player A Wins\n\n";
		} else if (pits[MANCALA_B] > pits[MANCALA_A]) {
			winner = "Player B Wins\n\n";
		}
		return winner + "Results\n" + "Player A: " + pits[MANCALA_A] + "\nPlayer B: " + pits[MANCALA_B];
	
	}

	/**
	 * check if the game is over
	 * @param pits - mancala board
	 * @return gameOver - whether one row of pits is empty
	 */
	public boolean gameOver(int[] pits) {
		boolean gameOver = false;
		int AScore = 0;
		int BScore = 0;
		//Check if stones > 0
		for (int i = 0; i < MANCALA_A; i++) {
			AScore += pits[i];
		}
		//Check if stones > 0
		for (int i = 7; i < MANCALA_B; i++) {
			BScore += pits[i];
		}
		//End the game if one of the rows is empty
		if (AScore == 0 || BScore == 0) {
			gameOver = true;
		}
		return gameOver;
	}
}
