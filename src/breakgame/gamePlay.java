/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author kk
 */
public class gamePlay extends JPanel implements KeyListener,ActionListener {
    
    private boolean play = false;
    private int score =0;
    
    private int totalBricks =21;
    private  Time time;
    private int delay=8;
    private int playerX =310;
    
    private int ballposX=120;
    private int ballposY =350;
    private int ballXdri=-1;
    private int ballYdri=-2;
    private final Timer timer;
    private MapGenerator map;
    public gamePlay(){
        map=new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        timer = new Timer(delay,this);
        timer.start();
    }
    public void paint(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        
        //drawing map
        map.draw((Graphics2D)g);
        //border
        g.setColor(Color.yellow);
        g.fillRect(0, 0,3,592);
        g.fillRect(0, 0,692,3);
        g.fillRect(691, 0,3,592);
        
        
        //score
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+score, 590, 30);
        
        
        //the paddle
        g.setColor(Color.green);
        g.fillRect(playerX,560,100,8);
        if(totalBricks<=0){
             play=false;
            ballXdri=0;
            ballYdri=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("You Wine", 250, 300);
            
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to Restart Game", 230, 350);
        }
        
        if(ballposY>570){
            play=false;
            ballXdri=0;
            ballYdri=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game Over", 250, 300);
            
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to Restart Game", 230, 350);
        }
        
        
        // the ball
        g.setColor(Color.red);
        g.fillOval(ballposX, ballposY ,20,20);
        
        
        
        
        
        g.dispose();
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        
       
        
        if(play){
             if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,560,100,8))){
                 ballYdri=-ballYdri;
             }
            
             A: for(int i=0;i<map.map.length;i++){
                 for(int j=0;j<map.map[0].length;j++){
                     if(map.map[i][j]>0){
                         int brickX=j*map.brickWidth+80;
                         int brickY=i*map.brickHeight+50;
                         int brickWidth=map.brickWidth;
                         int brickHeight=map.brickHeight;
                         
                         Rectangle rect= new Rectangle(brickX, brickY, brickWidth, brickHeight);
                         Rectangle ballRect=new Rectangle(ballposX, ballposY,20,20);
                         Rectangle brickRect=rect;
                         
                         
                         if(ballRect.intersects(brickRect)){
                             map.setBrickValue(0, i, j);
                             totalBricks--;
                             score+=5;
                             
                             if(ballposX+19<=brickRect.x || ballposX+1>=brickRect.width){
                                  ballXdri=-ballXdri;
                             }else{
                                 ballYdri=-ballYdri;
                             }
                             break A;
                         }
                     }
                 }
             }
             
             
            ballposX+=ballXdri;
            ballposY+=ballYdri;
            if(ballposX<0){
                ballXdri=-ballXdri;
            }
            if(ballposY<0){
                ballYdri=-ballYdri;
            }
            if(ballposX>670){
                ballXdri=-ballXdri;
            }
        }
        
           repaint();
    }
     @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(playerX>=600){
                playerX=600;
            }else{
                moveRight();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
             if(playerX<20){
                playerX=1;
            }else{
                moveLeft();
            }
        }
        
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                play=true;
                playerX =310;
    
                ballposX=120;
                ballposY =350;
                ballXdri=-1;
                ballYdri=-2;
                score=0;
                totalBricks=21;
                map=new MapGenerator(3,7);
                
                repaint();
            }
        }
        
    }
    
    
    public void moveRight(){
        play=true;
        playerX+=19;
        }
     public void moveLeft(){
        play=true;
        playerX-=19;
        }
     
     
     
     
}
