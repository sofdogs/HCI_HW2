import javax.swing.*;
import java.awt.*;

public class EndofTrial extends JPanel{
    private JLabel finished = new JLabel("Trial Done");
    private String text = "Click to continue to the next!"; 
    private JLabel intro = new JLabel("<html><div style=\"text-align: center;\">" + text + "</html>");
    
    public EndofTrial() {   
        setSize(980,650);
        setLayout(null);
        setBackground(Color.lightGray);
        
        //finished.setSize(400,50);
        finished.setFont(finished.getFont().deriveFont(50.0f));
        finished.setForeground(Color.BLACK);        
        finished.setSize(400, 100);
        finished.setLocation(350,10);
        add(finished);

        intro.setFont(intro.getFont().deriveFont(20.0f));
        intro.setForeground(Color.BLACK);
        intro.setSize(350, 300);
        intro.setLocation(350,100);
        add(intro);        
    }
}