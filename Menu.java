import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
/**
 * This class will prompts the users to select the number of stones and the board style
 * Begin the prompt process by setting the select number of stones frame to visible,
 * After user selects a number
 * dispose the stone frame and set the boardStyleframe to visible
 * @author Shaoyue Liu
 *
 */
public class Menu {
	private int stones;
	private BoardStyle style;
	
	public void select() {
		//Select board style
		JFrame boardStyle = new JFrame();
		boardStyle.setTitle("Select board style.");
		boardStyle.setLayout(new BorderLayout());
		boardStyle.add(new JLabel("Select board style.", SwingConstants.CENTER), BorderLayout.CENTER);
		
		//User selects board 1
		JButton board1 = new JButton("Board 1: TBD");
		board1.addActionListener(event -> {
			//Passes the number of stones to create GameRule object
			GameRule rule = new GameRule(stones);
			//Calls specific board class to create a board of that style
			style = new Board1(rule);
			//Now we have the number of stones and board style
			//We can generate the View object
			View game = new View(stones, style);
			//The model class handles data fetch and data change request 
			Model data = new Model(game);
			//Controller class calls the model class to change the data when player makes a move
			//Whether the player clicks on the pit or the undo button
			Controller control = new Controller(data);
			control.attach(game);
			boardStyle.dispose();
		});
		//Adds the button to prompt frame
		boardStyle.add(board1, BorderLayout.WEST);
		
		//User selects board 2
		JButton board2 = new JButton("Board 2: TBD");
		board2.addActionListener(event -> {
			//Passes the number of stones to create GameRule object
			GameRule rule = new GameRule(stones);
			//Calls specific board class to create a board of that style
			style = new Board2(rule);
			//Now we have the number of stones and board style
			//We can generate the View object
			View game = new View(stones, style);
			//The model class handles data fetch and data change request 
			Model data = new Model(game);
			//Controller class calls the model class to change the data when player makes a move
			//Whether the player clicks on the pit or the undo button
			Controller control = new Controller(data);
			control.attach(game);
			boardStyle.dispose();
		});
		//Adds the button to prompt frame
		boardStyle.add(board2, BorderLayout.EAST);
		
		//Select the number of stones
		JFrame stoneNum = new JFrame();
		stoneNum.setTitle("Select the number of stones.");
		stoneNum.setLayout(new BorderLayout());
		stoneNum.add(new JLabel("Select the number of stones.", SwingConstants.CENTER), BorderLayout.NORTH);
				
		//User selects 3 stones
		JButton threeStones = new JButton("3");
		threeStones.addActionListener(event -> {
			stones = 3;
			stoneNum.dispose();
			//Prompt user to select board style 
			//Sets the boardStyle frame to visible
			boardStyle.setSize(300, 100);
			boardStyle.setVisible(true);
		});
			//Add the button the the prompt frame
		stoneNum.add(threeStones, BorderLayout.WEST);
				
		//User selects 4 stones
		JButton fourStones = new JButton("4");
		threeStones.addActionListener(event -> {
			stones = 4;
			stoneNum.dispose();
			//Prompt user to select board style 
			//Sets the boardStyle frame to visible
			boardStyle.setSize(300, 100);
			boardStyle.setVisible(true);
		});
		//Add the button the the prompt frame
		stoneNum.add(fourStones, BorderLayout.WEST);
		
		//Begin by prompting the user to select the number of stone
		stoneNum.setSize(350, 100);
		stoneNum.setVisible(true);
	}
}
