/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakgame;
import javax.swing.JFrame;
/**
 *
 * @author kk
 */
public class BreakGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame obj=new JFrame();
        gamePlay game=new gamePlay();
        obj.setBounds(10, 10, 700, 600);
        obj.setTitle("kk-Breakgame");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(game);
    }
    
}
