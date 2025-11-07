package connectFour;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestGUI implements ActionListener{

	//Have the J stuff up here so all methods can use them
	private int count = 0;
	private JLabel label;
	private JFrame frame;
	private JButton button;
	private JPanel panel;
	
	//Setting up the UI
	public TestGUI() {
		frame = new JFrame();
		
		button = new JButton("Click Me!"); //Creates a button that the user can press and name
		button.addActionListener(this);
		
		label = new JLabel("Number of Clicks: 0"); //creates a label
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(button);
		panel.add(label);
		
		frame.add(panel, BorderLayout.CENTER); //adds panel to frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //what happens when frame is closed
		frame.setTitle("Our GUI"); //set title of the window
		frame.pack(); //set window to match a certain size
		frame.setVisible(true); //set window to be visivle and in focus
		
		
	}
	public static void main(String[] args) {
		new TestGUI();
	}
	
	//Method for ActionListener
	//Method that happens when said button is pressed
	@Override
	public void actionPerformed(ActionEvent e) {
		count++;
		label.setText("Number of Clicks: " + count);
	}

}
