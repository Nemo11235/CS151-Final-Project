import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

//CS 151 Final Project, the View of MVC pattern, Written by Lifan Zeng. 11/6/2020
public class ViewPanel extends JPanel {
    public ViewPanel(int[] arrayMancala){
        this.arrayMancala=arrayMancala;
        //main layout
        setPreferredSize(new Dimension(Width, Height));
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEtchedBorder());
        //topPanel.setBackground(Color.LIGHT_GRAY);
        JPanel mainPanel = new JPanel();

        topPanel.setPreferredSize(new Dimension(Width, Height_topPanel));
        //mainPanel.setBackground(Color.lightGray);
        mainPanel.setPreferredSize(new Dimension(Width, Height-Height_topPanel));
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        //northPanel.setBackground(Color.LIGHT_GRAY);//
        northPanel.setPreferredSize(new Dimension(Width, Height_northPanel));

        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        //westPanel.setBackground(Color.LIGHT_GRAY);//
        westPanel.setPreferredSize(new Dimension(Width_westPanel, (Height-Height_topPanel-2*Height_northPanel)));

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        //eastPanel.setBackground(Color.LIGHT_GRAY);//
        eastPanel.setPreferredSize(new Dimension(Width_westPanel, (Height-Height_topPanel-2*Height_northPanel)));

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        //southPanel.setBackground(Color.LIGHT_GRAY);//
        southPanel.setPreferredSize(new Dimension(Width, Height_northPanel));

        JPanel centerPanel = new JPanel();
        //centerPanel.setBackground(Color.lightGray);//
        //centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3, true));
        centerPanel.setBorder(BorderFactory.createEtchedBorder());

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(westPanel, BorderLayout.WEST);
        mainPanel.add(eastPanel, BorderLayout.EAST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        //top layout
        JLabel lblBlank1=new JLabel();
        lblBlank1.setPreferredSize(new Dimension(Width_westPanel, Height_topPanel));
        JLabel lblTurn=new JLabel("Play A's turn");
        lblTurn.setFont(new Font("SansSerif",Font.BOLD,20));
        lblTurn.setPreferredSize(new Dimension(200, Height_topPanel));
        JLabel lblUndoLeft=new JLabel("Undo Left: 3");
        lblUndoLeft.setFont(new Font("SansSerif",Font.BOLD,20));
        lblTurn.setPreferredSize(new Dimension(200, Height_topPanel));
        JLabel lblBlank2=new JLabel();
        lblBlank2.setPreferredSize(new Dimension(200, Height_topPanel));

        JButton btnUndo = new JButton("Undo");
        btnUndo.setPreferredSize(new Dimension(70, 40));
        //btnUndo.setFont(new Font("SansSerif",Font.BOLD,14));

        JLabel lblBlank3=new JLabel();
        lblBlank3.setPreferredSize(new Dimension(30, Height_topPanel));
        JButton btnQuit = new JButton("Quit");
        //btnQuit.setFont(new Font("SansSerif",Font.BOLD,14));
        btnQuit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });
        btnQuit.setPreferredSize(new Dimension(70, 40));
        JLabel lblBlank4=new JLabel();
        lblBlank4.setPreferredSize(new Dimension(130, Height_topPanel));
        topPanel.add(lblBlank1);
        topPanel.add(lblTurn);
        topPanel.add(lblUndoLeft);
        topPanel.add(lblBlank2);
        topPanel.add(btnUndo);
        topPanel.add(lblBlank3);
        topPanel.add(btnQuit);
        topPanel.add(lblBlank4);



//        int[] array={0,1,2,3,4,5,6,7,8,9,10,11,12,13};
        ArrayList<gridLabel> alt_Lable=new ArrayList<gridLabel>();

        for (int i=0; i<24; i++) {
            gridLabel g;
            if ((i >= 1 && i <= 6) || (i == 8) || (i == 15)|| (i >= 17 && i <= 22)) {
                g = new gridLabel(i, arrayMancala);
            } else
                g = new gridLabel(i);
            alt_Lable.add(g);
        }

        centerPanel.setLayout(new GridLayout(3, 8));
        for(int i=0;i<24; i++){
            centerPanel.add(alt_Lable.get(i));
        }
    }




    final int Width=1000, Height=600;
    final int Height_topPanel=80;
    final int Height_northPanel=50;
    final int Width_westPanel=100;
    private int[] arrayMancala=new int[14];
}
