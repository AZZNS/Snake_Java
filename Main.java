package Snake;

import javax.swing.JFrame;

public class Main {
 
 public static void main(String[] args) {
  
	 JFrame frame = new JFrame();
	  Panel panel = new Panel();
	  
	  frame.add(panel);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.setTitle("SNAKECODING");
	  frame.pack();
	  frame.setVisible(true);
	  frame.setLocationRelativeTo(null);
  
 }

}
