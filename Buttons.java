import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
        //declaring vars...
        public int total_clicks = 0;
        public static int btn_index= 0;

        //color used to as action event 
        public Color col = null;

        public StopWatch clock = new StopWatch();        
        public static double[][] captureData = new double[15][2];

        //button array of width,distance
        public static int[][] buttons = 
        {
          {24,96},{24,192},{24,384},
          {24,768},{16, 1024}, {48,192},
          {48,384},{48,768},{30,930},
          {15,960}, {96,288},{96,672},
          {60, 900},{30,930}, {15, 960}
        };
        
        
        public Button() {
            col = Color.green;
        }
}
