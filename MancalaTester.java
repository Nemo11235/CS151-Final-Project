import javax.swing.*;

import java.awt.*;
import java.util.*;

public class MancalaTester {
	public static void main(String args[]) {
		// use JOptionPane to ask the user for game board style and initial pebble, and setup the int array
		String[] styles = {"Style 1", "Style 2"};
		String[] pebbles = {"3", "4"};
		int data[] = new int[14];
		String style = (String)JOptionPane.showInputDialog(null, "Choose a board style", "Game board selection", JOptionPane.QUESTION_MESSAGE, null, styles, "Style 1");
		int pebble = Integer.parseInt((String)JOptionPane.showInputDialog(null, "Choose the initial pebble", "Pebble selection", JOptionPane.QUESTION_MESSAGE, null, pebbles, "3"));
		// setup the int array
		for(int i = 0; i < data.length; i++) {
			if(i != 6 && i != 13)
				data[i] = pebble;
			else
				data[i] = 0;
		}
		
		// 
		Model model = new Model(data);
		StyleOne a = new StyleOne(model);
		
	}
}
