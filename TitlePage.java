import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TitlePage extends JFrame{

    // Constructor for TitlePage
    public TitlePage(){
        setTitle("Counting Sort");
		setSize(500, 200);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        // Adding a JLabel to display the title text
        JLabel lblTitle = new JLabel("Counting Sort Animation", JLabel.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		add(lblTitle);

        // Creating a JPanel to hold two JButtons for "Start" and "Exit"
        JPanel pnlButton = new JPanel(new GridLayout(1, 2));
		
        // Creating a "Exit" button and adding an ActionListener to terminate the program when clicked
		JButton btnExit	 = new JButton("Exit");

        // Creating a "Start" button and adding an ActionListener to create a new JFrame for the animation when clicked
		JButton btnStart = new JButton("Start");
		btnExit .addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }});

		btnStart.addActionListener(new ActionListener() {
                        	public void actionPerformed(ActionEvent e) {
                                JFrame CountingSortAnimationJFrame = new CountingSortAnimationJFrame();
                                CountingSortAnimationJFrame.setVisible(true);
                        	}});

        // Adding the "Exit" and "Start" buttons to the JPanel
		pnlButton.add(btnExit);
		pnlButton.add(btnStart);
		
        // Adding the JPanel to the bottom of the window
		add(pnlButton, BorderLayout.SOUTH);
    }

    // Main method to create an instance of the TitlePage class and launch the window
    public static void main(String[] args) {
        new TitlePage();
    }
}
