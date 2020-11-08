import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class gridLabel extends JLabel {
    public gridLabel(int gridNumber){      // gridNumber= 0, 7;   9~14;  16,23.
        this.gridNumber=gridNumber;
        //setBackground(Color.BLUE);
    }

    public gridLabel(int gridNumber, int[] mancalaArray){ //gridNumber= 1~6;   8,15;  17~22.
        this.gridNumber=gridNumber;
        this.mancalaArray=mancalaArray;
        //setBackground(Color.LIGHT_GRAY);
        if (gridNumber>=1 && gridNumber<=6)         //B6~B1: [7]~[12]
            orderInArray=13-gridNumber;
        if (gridNumber==8)                          //Mancala B: [13]
            orderInArray=13;
        if (gridNumber==15)                        //Mancala A: [6]
            orderInArray=6;
        if (gridNumber>=17 && gridNumber<=22)      //A1~A6: [0]~[5]
            orderInArray=gridNumber-17;

        setIcon(new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                  Graphics2D g2 = (Graphics2D) g;
                Ellipse2D.Double circle = new Ellipse2D.Double(1, 0, getParent().getWidth()/8-3, getParent().getHeight()/3-3);
                g2.setColor(Color.ORANGE);
                g2.setStroke(new BasicStroke(2));
                g2.draw(circle);

                Font sanbold14=new Font("SansSerif",Font.BOLD,40);
                g2.setFont(sanbold14);
                String text=""+getMancalaValue();
                if (orderInArray>6)
                    g2.setColor(Color.RED);
                else
                    g2.setColor(Color.BLUE);
                int xx;
                if (getMancalaValue()>9)
                    xx=getParent().getWidth()/16-22;
                else
                    xx=getParent().getWidth()/16-14;
                g2.drawString(text, xx, getParent().getHeight()/6+15);
            }

            @Override
            public int getIconWidth() {
                return 0;
            }

            @Override
            public int getIconHeight() {
                return 0;
            }
        });

    }

    public int getGridNumber() {
        return gridNumber;
    }

    public int getOrderInArray(){
        return orderInArray;
    }

    public int getMancalaValue(){
        return mancalaArray[orderInArray];
    }

    public int[] getMancalaArray(){
        return mancalaArray;
    }

    private int gridNumber;
    private int orderInArray;
    private int[] mancalaArray=new int[14];
}
