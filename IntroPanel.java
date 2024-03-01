import javax.swing.*;
import java.awt.*;
public class IntroPanel extends JPanel {
    
    private JLabel title = new JLabel("Fitts Law Experiment");
    private String text = "This is for my HCI class to demonstrate Fitt's Law. "
            + "To start the experiment, please press the start button below. "
            + "Once pressed, you'll see the experiment page and the timer"
            + "will start once you press the button on the lefthand side first.";
    private JLabel intro = new JLabel("<html><div style=\"text-align: center;\">" + text + "</html>");
    private JButton startButton = new JButton("Start");
    
    public IntroPanel() {
        
        // Set up the panel, components, and add them to the panel.
        
        setSize(980,650);
        setLayout(null);
        setBackground(Color.WHITE);
        title.setFont(title.getFont().deriveFont(35.0f));
        title.setForeground(Color.BLACK);
        title.setSize(400, 100);
        title.setLocation(300,0);


        intro.setFont(intro.getFont().deriveFont(15.0f));
        intro.setForeground(Color.BLACK);
        intro.setSize(350, 300);
        intro.setLocation(325,100);
        
        startButton.setSize(75, 40);
        startButton.setLocation(465,380);
        startButton.setBackground(Color.green);
        
        add(title);
        add(intro);
        add(startButton);
    }
}
