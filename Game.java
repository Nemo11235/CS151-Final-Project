import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game {
	final static int MANCALA_A = 6;
	final static int MANCALA_B = 13;
	
	public Game(int[] pits) {
		sum = 0;
		for(int i = 0; i < pits.length; i++) {
			sum += pits[i];
		}
	}
	
	/**
	 * This is a function that will complete a player's turn
	 * 
	 * @param pits int array that keeps track of the # of pebbles in each pit
	 * @param player 0 for player A, 1 for player B
	 * @param total the total number of pebbles on the board
	 */
	public boolean move(int[] pits, int player, int start) {
		int cur; // int that indicates the current pit
		
		// get the actual index of the array corresponding to the user's choice
		if(player == 0) 
			cur = start - 1;
		else 
			cur = start + 6;
				
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
				
		if (gameOver(pits) && (pits[MANCALA_A] > pits[MANCALA_B])) {
			JOptionPane.showMessageDialog(frame, "Game over, player blue wins!!");
			return false;
		} else if (gameOver(pits) && (pits[MANCALA_A] < pits[MANCALA_B])) {
			JOptionPane.showMessageDialog(frame, "Game over, player red wins!!");
			return false;
		} else if (gameOver(pits)){
			JOptionPane.showMessageDialog(frame, "Game over, draw!!");
			return false;
		}
				
		// if it lands in the current player's mancala and the game is not over yet, then that player gets another chance
		if ((cur == MANCALA_A && player == 0) || (cur == MANCALA_B && player == 1)) {
			JOptionPane.showMessageDialog(frame, "Scored, another chance granted");
			return true;
		}
		return false;
	}

	
	
	// check if the game is over
	public static Boolean gameOver(int []pits)
	{	return (pits[13] + pits[6] == sum);	}
	
	
	private JFrame frame = new JFrame();
	private int[] copy = new int[14];
	private int player;
	private static int sum;
}
