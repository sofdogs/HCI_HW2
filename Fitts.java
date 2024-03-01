import javax.swing.*;
import java.awt.event.*;
import static Fitts.Button.buttons;
import javax.swing.table.DefaultTableModel;

public class Fitts extends JFrame {
    
    public static void main(String[] args) throws Exception {
        //set frame...
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final int[][] buttonArr = buttons;

        /*
        for (int i = 0; i < buttonArr.length; i++) {
            for (int j = 0; j < buttonArr[i].length; j++) {
                System.out.print(buttonArr[i][j] + " ");
            }
            System.out.println(); // Move to the next line after printing each row
        }
        */

        double[][] results = new double[15][2];

        final Button button1 = new Button();
        final Button button2 = new Button();        

        final IntroPanel intro = new IntroPanel();
        intro.setLocation(100,100);
        
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                frame.remove(intro);
                frame.repaint();
                frame.removeMouseListener(this);
                fittFrame(frame, button1, button2, buttonArr, 0, results);                
            }            
        });
        
        frame.setLayout(null);
        frame.add(intro);
        frame.setBounds(20, 20, 2000, 900);
        frame.setVisible(true);
    }
    
    public static void fittFrame(final JFrame frame, final Button button1, final Button button2, final int[][] buttonArr, int btn_index, double [][] results) {
        //getting difficulty index of each button/square
        double[] id = difficultyIndex(buttonArr);

        //double[][] results = new double[15][2];
        
        /*
        for (int i = 0; i < buttonArr.length; i++) {
            for (int j = 0; j < buttonArr[i].length; j++) {
                System.out.print(buttonArr[i][j] + " ");
            }
            System.out.println(); // Move to the next line after printing each row
        }
        */
        System.out.println(btn_index);
        int horizontalPos1 = (1200 - (buttonArr[btn_index][0] + buttonArr[btn_index][1]))/2;
        int horizontalPos2 = horizontalPos1 + buttonArr[btn_index][1];
        
        final Button nextButton1 = new Button();
        final Button nextButton2 = new Button();
        
        //for button 1...
        button1.setSize(buttonArr[btn_index][0], 550); //width,height
        button1.setLocation(horizontalPos1, 150); //x,y   
        button1.requestFocusInWindow();
        ActionListener button1Listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Button 1 is clicked!");
                // Start clock
                if (button1.total_clicks == 0) {
                    button1.clock.start();
                }
                
                // Increment total clicks
                button1.total_clicks++;
            }
        };
        
        //for button 2...
        button2.setSize(buttonArr[btn_index][0], 550);
        button2.setLocation(horizontalPos2, 150);  //x,y   
        button2.requestFocusInWindow();  
        
        ActionListener button2Listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Button 2 is clicked!");
                if(button2.total_clicks == 4)
                { 
                    //System.out.println("Hello, world!");
                    button1.clock.stop();
                    button2.removeAll();
                    frame.remove(button1);
                    frame.remove(button2);
                    frame.repaint();

                    //results [difficulty index][time it took]
                    results[button2.btn_index][0] = id[button2.btn_index];
                    results[button2.btn_index][1] = (button1.clock.getElapsedTime());
                    System.out.print(results[button2.btn_index][0] + " ");
                    System.out.print(results[button2.btn_index][1] + " ");



                    //captureData[button2.btn_index][1] = (button1.clock.getElapsedTime())/7;
                    //captureData[button2.btn_index][0] = id[button2.btn_index];
                    
                    //increment index
                    button2.btn_index++;

                    //check if reached all combos...
                    if (button2.btn_index < buttonArr.length) 
                    {
                        final EndofTrial trialPanel = new EndofTrial();
                        trialPanel.setLocation(100,100);
                        frame.add(trialPanel);
                        frame.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                frame.remove(trialPanel);
                                frame.repaint();
                                frame.removeMouseListener(this);
                                fittFrame(frame, nextButton1, nextButton2, buttonArr, button2.btn_index, results);
                            }

                        });
                    } else { //if so, go to finish panel and display results...
                        for (int i = 0; i < results.length; i++) {
                            for (int j = 0; j < results[i].length; j++) {
                                System.out.print(results[i][j] + " ");
                            }
                            System.out.println(); // Move to the next line after printing each row
                        }

                        
                        final FinishedPanel finishedPanel = new FinishedPanel();
                        finishedPanel.setLocation(100,100);
                        frame.add(finishedPanel);

                        String[] columns = {"ID", "Time(Miliseconds)"};

                        //double [][] yo = results(captureData);
                        frame.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                frame.remove(finishedPanel);
                                frame.removeMouseListener(this);
                               
                                DefaultTableModel model = new DefaultTableModel(0, 2); // Assuming 2 columns
                                for (double[] result : results) {
                                    model.addRow(new Object[]{result[0], result[1]}); // Assuming each result is a double array with 2 values
                                }
                                

                                // Create a JTable with the model
                                JTable table = new JTable(model);

                                // Create a scroll pane for the table
                                JScrollPane scrollPane = new JScrollPane(table);

                                // Create a JFrame to display the table
                                JFrame frame = new JFrame();
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.getContentPane().add(scrollPane);
                                frame.pack();
                                frame.setVisible(true);
                                frame.pack();
                                frame.setVisible(true);
                            }

                        });
                    } 
                }
                
                // Increment total clicks
                button2.total_clicks++;
            }
        };  
        frame.add(button1);
        frame.add(button2);
        button1.addActionListener(button1Listener);
        button2.addActionListener(button2Listener);
    }
    
    // Calculates the index of difficulty, using Fitts's model for ID.
    public static double[] difficultyIndex(int[][] inputArray) {
        double[] indexOfDifficulty = new double[inputArray.length];

        //System.out.print(indexOfDifficulty[0] + " ");

        for (int i = 0; i < indexOfDifficulty.length; i++) {
            double dividend = inputArray[i][1]; // Ensure inputArray[i][1] is a double
            double divisor = inputArray[i][0];  // Ensure inputArray[i][0] is a double and not zero

            if (divisor != 0) {
                double result = (dividend / divisor) + 1; // Perform division after ensuring divisor is not zero
                indexOfDifficulty[i] = Math.log(result) / Math.log(2);
            }

            System.out.print(indexOfDifficulty[i] + " ");
            //System.out.print(inputArray[i][1] + " ");
        }        
        

        return indexOfDifficulty;
    }

}

