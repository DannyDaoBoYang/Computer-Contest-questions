/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package car;
import java.awt.*;


/**
 *
 * @author Nanthivarman
 */
public class Car {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DrawingPanel panel = new DrawingPanel(1000, 1000);
        panel.setBackground(Color.LIGHT_GRAY);
        Graphics g = panel.getGraphics();
        for ( int i = 0; i < 1000; i++){
        g.setColor(Color.BLACK);
        g.fillRect(10+i, 30, 100, 50);
        
        g.setColor(Color.RED);
        g.fillOval(20+i, 70, 20, 20);
        g.fillOval(80+i, 70, 20, 20);
        
        g.setColor(Color.CYAN);
        g.fillRect(80+i, 40, 30, 20);
        panel.clear();
        panel.sleep(100);
        //panel.clear();
        }
    }
}

    
    

