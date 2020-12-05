import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Creates mancala board and visualizes it
 * Contains view
 * @author TeamCat
 * @version 12/04/2020
 */
public class StyleOne extends JFrame implements ChangeListener {
	private static final long serialVersionUID = 1L;
	private int[] data;
    private Model dataModel; 
    private int undo; // the number of undo left for the current player.
    private int player; // a number that shows it's currently which player's turn, 0 for player A, 1 for player B
    ArrayList<JLabel> labels;
	private int copy[] = new int[14];
	Font sanbold14=new Font("SansSerif",Font.BOLD,40); // font for the mancala labels
    final int Width=1680, Height=780;
    final int Height_topPanel=80;
    final int Height_northPanel=50;
    final int Width_westPanel=100;
	
    /**
     * Creates a main panel that holds the mancala board
     * @param d - data model
     */
	public StyleOne(Model d){
        dataModel= d;
        data = dataModel.getcurBoard();
        player = 0;
        Game game = new Game(data);
        for(int i = 0; i < data.length; i++)	copy[i] = data[i];
        //main layout
        setPreferredSize(new Dimension(Width, Height));
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEtchedBorder());
        JPanel mainPanel = new JPanel();

        topPanel.setPreferredSize(new Dimension(Width, Height_topPanel));
        mainPanel.setPreferredSize(new Dimension(Width, Height-Height_topPanel));
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setPreferredSize(new Dimension(Width, Height_northPanel));

        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        westPanel.setPreferredSize(new Dimension(Width_westPanel, (Height-Height_topPanel-2*Height_northPanel)));

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        eastPanel.setPreferredSize(new Dimension(Width_westPanel, (Height-Height_topPanel-2*Height_northPanel)));

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.setPreferredSize(new Dimension(Width, Height_northPanel));

        JPanel centerPanel = new JPanel();
       
        //Label pits and mancalas
        JLabel labelBPits = new JLabel("                                     B6                B5                B4                 B3                B2                B1");
		labelBPits.setFont(new Font("Times New Roman", Font.BOLD, 30));
		JLabel labelAPits = new JLabel("                                     A1                A2                A3                A4                A5                A6");
		labelAPits.setFont(new Font("Times New Roman", Font.BOLD, 30));
		JLabel mancalaALabel = new JLabel(" A ");
		mancalaALabel.setFont(new Font("Times New Roman", Font.BOLD, 55));
		JLabel mancalaBLabel = new JLabel(" B ");
		mancalaBLabel.setFont(new Font("Times New Roman", Font.BOLD, 55));
		
		//Center panel with labels
		JPanel centerPanelFinal = new JPanel();
		centerPanelFinal.setBorder(BorderFactory.createEtchedBorder());
		centerPanelFinal.setLayout(new BorderLayout());
		centerPanelFinal.add(labelBPits, BorderLayout.NORTH);
		centerPanelFinal.add(mancalaBLabel, BorderLayout.WEST);
		centerPanelFinal.add(mancalaALabel, BorderLayout.EAST);
		centerPanelFinal.add(centerPanel, BorderLayout.CENTER);
		centerPanelFinal.add(labelAPits, BorderLayout.SOUTH);
        
		//Final panel that goes into frame
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(westPanel, BorderLayout.WEST);
        mainPanel.add(eastPanel, BorderLayout.EAST);
        mainPanel.add(centerPanelFinal, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        //top layout
        undo = 3;
        JLabel lblBlank1=new JLabel();
        lblBlank1.setPreferredSize(new Dimension(Width_westPanel, Height_topPanel));
        JLabel lblTurn=new JLabel("Player blue's turn");
        lblTurn.setFont(new Font("SansSerif",Font.BOLD,20));
        lblTurn.setPreferredSize(new Dimension(200, Height_topPanel));
        JLabel lblUndoLeft=new JLabel("Undo Left: " + undo);
        lblUndoLeft.setFont(new Font("SansSerif",Font.BOLD,20));
        lblTurn.setPreferredSize(new Dimension(200, Height_topPanel));
        JLabel lblBlank2=new JLabel();
        lblBlank2.setPreferredSize(new Dimension(200, Height_topPanel));

        ArrayList<JLabel> labels =new ArrayList<JLabel>();

        JButton btnUndo = new JButton("Undo");
        btnUndo.addActionListener((e) -> {
        	if(undo > 0) {
	        	for(int i = 0; i < data.length; i++) {
	        		data[i] = copy[i];
	        	}
	        	if(player == 1) {
	    			player = 0;
	    			lblTurn.setText("Player blue's turn");
	        	}else {
	    			player = 1;
	    			lblTurn.setText("Player red's turn");
	        	}
	    		undo -= 1;
	    		lblUndoLeft.setText("Undo Left: " + undo);
	        	updateLabel(labels, data);
        	} else {
        		JFrame frame = new JFrame();
        		JOptionPane.showMessageDialog(frame, "No more undo left!");
        	}
        });
        btnUndo.setPreferredSize(new Dimension(100, 40));

        JLabel lblBlank3=new JLabel();
        JLabel lblBlank4=new JLabel();
        JLabel lblBlank5=new JLabel();
        JButton btnRefillUndo = new JButton("Confirm");
        btnRefillUndo.setPreferredSize(new Dimension(100, 40));
        btnRefillUndo.addActionListener((e) -> {
        	undo = 3;
        	lblUndoLeft.setText("Undo Left: " + undo);
        });
        lblBlank5.setPreferredSize(new Dimension(30, Height_topPanel));
        lblBlank3.setPreferredSize(new Dimension(30, Height_topPanel));
        lblBlank4.setPreferredSize(new Dimension(30, Height_topPanel));
            
        JButton btnQuit = new JButton("Quit");
        btnQuit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });
        btnQuit.setPreferredSize(new Dimension(100, 40));
        topPanel.add(lblBlank1);
        topPanel.add(lblTurn);
        topPanel.add(lblUndoLeft);
        topPanel.add(lblBlank2);
        topPanel.add(btnRefillUndo);
        topPanel.add(lblBlank5);
        topPanel.add(btnUndo);
        topPanel.add(lblBlank3);
        topPanel.add(btnQuit);
        topPanel.add(lblBlank4);
        
        for (int i=0; i<24; i++) {
        	int temp;
            JLabel g;
            if (i >= 1 && i <= 6){
                g = new JLabel("" + data[13-i], JLabel.CENTER);
                temp = 13 - i - 6;
                // these are for player B (top, 1, red)
                g.addMouseListener(new MouseAdapter() {
                	public void mousePressed(MouseEvent e) {
                		if (player == 1) {
                			for(int i = 0; i < data.length; i++)	copy[i] = data[i];
                			if(!game.move(data, player, temp )) {
                    			player = 0;
                    			lblTurn.setText("Player blue's turn");
                    		}
                    		updateLabel(labels, data);
                		}
                	}
                });
                g.setForeground(Color.red);
            } else if (i == 8) {
            	g = new JLabel("" + data[13], JLabel.CENTER);
            	g.setForeground(Color.red);
        	}else if (i >= 17 && i <= 22){
        		// these are for player A (bottom, 0, blue)
        		temp = i - 16;
                g = new JLabel("" + data[i - 17], JLabel.CENTER);
                g.addMouseListener(new MouseAdapter() {
                	public void mousePressed(MouseEvent e) {
                		if (player == 0) {
                			for(int i = 0; i < data.length; i++)	copy[i] = data[i];
                			if(!game.move(data, player, temp )) {
                				player = 1;
                				lblTurn.setText("Player red's turn");
                			}
                		}
                		updateLabel(labels, data);
                	}
                });
                g.setForeground(Color.blue);
            } else if (i == 15){
            	g = new JLabel("" + data[6], JLabel.CENTER);
            	g.setForeground(Color.blue);
            } else {
            	g = new JLabel("");
            }
            g.setFont(sanbold14);
            labels.add(g);
        }

        centerPanel.setLayout(new GridLayout(3, 8));
        for(int i=0;i<24; i++){
            centerPanel.add(labels.get(i));
        }
        
        
        setVisible(true);
        setResizable(false);
        pack();
    }
    
	/**
	 * Update labels after each move
	 * @param labels - mancala pits
	 * @param arr - number of stones in each pit
	 */
    public void updateLabel(ArrayList<JLabel> labels, int arr[]) {
    	for (int i=0; i<24; i++) {
    		if (i >= 1 && i <= 6){
                labels.get(i).setText("" + arr[13-i]);
            } else if (i == 8) {
            	labels.get(i).setText("" + arr[13]);
        	} else if (i >= 17 && i <= 22){
        		labels.get(i).setText("" + arr[i - 17]);
            } else if (i == 15){
            	labels.get(i).setText("" + arr[6]);
            } 
        }
    }
    
    /**
     * Update view after every move
     */
    public void stateChanged(ChangeEvent e) {
		dataModel.getcurBoard();
		repaint();
	}
}
