import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class View implements ChangeListener {
	private Model model;
	private BoardStyle style;
	private int[] pitIndex;
	private JButton[] pits;
	private JButton undo;
	private boolean board1Used;
	//private boolean board2Used;

	public View(Model model) {
		board1Used = false;
	//	board2Used = false;
		this.model = model;
		pits = new JButton[14];
		undo = new JButton("Undo");
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.undo();
				undo.setEnabled(false);
			}
		});
		undo.setEnabled(false);
	}

	public void selectStone() {
		JFrame stoneNum = new JFrame("Select the number of stones.");
		stoneNum.setLayout(new BorderLayout());
		JButton three = new JButton("3");
		three.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.setStoneNum(3);
				stoneNum.dispose();
				selectBoardStyle(3);
			}
		});
		JButton four = new JButton("4");
		four.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setStoneNum(4);
				stoneNum.dispose();
				selectBoardStyle(4);
			}
		});
		stoneNum.add(new JLabel("Select the number of stones.", SwingConstants.CENTER), BorderLayout.CENTER);
		stoneNum.add(three, BorderLayout.WEST);
		stoneNum.add(four, BorderLayout.EAST);
		stoneNum.pack();
		stoneNum.setSize(350, 100);
		stoneNum.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stoneNum.setVisible(true);
	}
	
	private void selectBoardStyle(final int size) {
		pitIndex = model.getcurBoard();
		for (int i = 0; i < 14; i++) {
			JButton b = new JButton();
			final int position = i;
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					model.move(position);
				}
			});
			pits[i] = b;
		}
		pits[Model.PLAYER_A].setEnabled(false);
		pits[Model.PLAYER_B].setEnabled(false);
		
		final JFrame boardStyle = new JFrame("Select board style");
		boardStyle.setLayout(new BorderLayout());
		
		JButton board1 = new JButton("Board 1");
		board1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				board1Used = true;
				boardStyle.dispose();
				style = new BoardDesign1();
				style.makeBoard(size, undo, pits, model);
				exchangeTurn();
			}
		});
		JButton TBD = new JButton("Board 2 TBD");
		TBD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		//		board2Used = true;
				boardStyle.dispose();
		//		style = new BoardDesign2();
				style.makeBoard(size, undo, pits, model);
				exchangeTurn();
			}
		});
		boardStyle.add(new JLabel("Select board style.", SwingConstants.CENTER), BorderLayout.CENTER);
		boardStyle.add(board1, BorderLayout.WEST);
		boardStyle.add(TBD, BorderLayout.EAST);
		boardStyle.pack();
		boardStyle.setSize(350, 100);
		boardStyle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boardStyle.setVisible(true);
	}

	private void exchangeTurn() {
		if (model.getTurn() == 0) {
			for (int i = 0; i < Model.PLAYER_A; i++) {
				if (pitIndex[i] == 0) {
					pits[i].setEnabled(false);
					style.setInactive(pits[i]);
				} else {
					pits[i].setEnabled(true);
					style.setActive(pits[i]);
				}
			}
			for (int i = 7; i < Model.PLAYER_B; i++) {
				pits[i].setEnabled(false);
				style.setInactive(pits[i]);
			}
		} else {
			for (int i = 7; i < Model.PLAYER_B; i++) {
				if (pitIndex[i] == 0) {
					pits[i].setEnabled(false);
					style.setInactive(pits[i]);
				} else {
					pits[i].setEnabled(true);
					style.setActive(pits[i]);
				}
			}
			for (int i = 0; i < Model.PLAYER_A; i++) {
				pits[i].setEnabled(false);
				style.setInactive(pits[i]);
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (model.gameOver()) {
			JFrame resultFrame = new JFrame();
			JTextArea results = new JTextArea(4, 10);
			results.setText(model.getResults());
			results.setEditable(false);
			resultFrame.add(results);
			resultFrame.pack();
			resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			resultFrame.setVisible(true);
		}
		pitIndex = model.getcurBoard();
		for (int i = 0; i < 14; i++) {
			pits[i].setToolTipText(Integer.toString(pitIndex[i]));
			style.setIcons(i, pitIndex[i]);
		}
		if (model.isUndoable()) {
			undo.setEnabled(true);
		}
		if(board1Used == true) {
			BoardDesign1.update();
		}
		else {
			//BoardDesign2.update();
		}
		exchangeTurn();
		style.pack();
		style.repaint();
	}
} 
