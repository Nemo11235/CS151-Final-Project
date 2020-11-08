import javax.swing.*;
import java.awt.*;

public class Tester {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        Dimension frameDimension = new Dimension(1000, 600);
        frame.setSize(frameDimension);
        //frame.setLayout(new FlowLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  // Get the dimension of the scree
        frame.setLocation((screenSize.width-frameDimension.width)/2, (screenSize.height-frameDimension.height)/2);
        //Make the frame in the center of the screen
        int[] array={0,1,2,3,4,5,6,7,8,9,10,11,12,13};
        ViewPanel viewPanel = new ViewPanel(array);
        frame.add(viewPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
