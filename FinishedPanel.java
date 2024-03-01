import javax.swing.*;
import java.awt.*;


public class FinishedPanel extends JPanel {
    
    private JLabel finished = new JLabel("Done!");
    private String text = "Click anywhere to see results";
    private JLabel cont = new JLabel("<html><div style=\"text-align: center;\">" + text + "</html>");
    
    public FinishedPanel() {   
        setSize(980,650);
        setLayout(null);
        setBackground(Color.lightGray);
        
        finished.setSize(400,50);
        finished.setFont(finished.getFont().deriveFont(50.0f));
        finished.setForeground(Color.BLACK);        
        finished.setLocation(425,20);
        add(finished);
        
        cont.setForeground(Color.gray);
        cont.setFont(cont.getFont().deriveFont(16.0f));
        cont.setLocation(410,100);
        cont.setSize(200,50);
        add(cont);        
    }    
}