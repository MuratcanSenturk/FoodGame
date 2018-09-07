
package yılanoyunu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements KeyListener, ActionListener{
    Timer timer = new Timer(5, this);
    Random rnd = new Random();
    FoodGame foodgame = new FoodGame();
    public int score = 0;
    private double time = 35.000;
    private double headX = 200;
    private double headY = 200;
    private double headVelocityX = 0;
    private double headVelocityY = 0;
    private double velocityValue = 1.0;
    private int headWidth = 60;
    private int headHeight = 60;
    private int foodX = 340;
    private int foodY = 240;
    private BufferedImage headImg;
    private BufferedImage kokorecImg;
            
   
    public Game() {
        
        
        try {
            headImg = ImageIO.read(new FileImageInputStream(new File("ben.png")));
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        try {
                kokorecImg = ImageIO.read(new FileImageInputStream(new File("kokoreç.png")));
        } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        setBackground(Color.white);
        
        timer.start();
        
    }

    
    
    public boolean kontrol(){
        
        if(new Rectangle(foodX, foodY, kokorecImg.getWidth()/14, kokorecImg.getHeight()/14).intersects(headX,headY,headWidth,headHeight)){
            
           
            return true;
        }
        
        return false;
    }
    
    

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        
        
        g.drawImage(kokorecImg,foodX, foodY, kokorecImg.getWidth()/14, kokorecImg.getHeight()/14,this);
        g.setColor(Color.red);
        g.drawImage(headImg,(int)headX, (int)headY, headWidth,headHeight,this);
        
        if(kontrol()){
            
            foodX = rnd.nextInt(460);
            foodY = rnd.nextInt(460);
            
            headWidth += 1;
            headHeight += 1;
            velocityValue += 0.15;
            score ++;
            System.out.println("Speed: " + velocityValue);
            
            foodgame.setSkor(score);
            
                        
        }
        
        if((int)time <= 0){
            
            timer.stop();
            String mesaj2 = "Game Over! Your score is: " + score;
            JOptionPane.showMessageDialog(this, mesaj2);
            System.exit(0);
            
            
        }
        
    }

    

    @Override
    public void repaint() {
        super.repaint();
    }

    

    
    
    

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int k = e.getKeyCode();
        
        if(k == KeyEvent.VK_LEFT && headVelocityX != (double)velocityValue){
            if(headX >= 0){
            headVelocityX = -velocityValue;
            headVelocityY = 0;
            }else{
                headX = 0;
            }
            
        }else if(k == KeyEvent.VK_RIGHT && headVelocityX != -(double)velocityValue){
            if(headX <= 500){
                headVelocityX = velocityValue;
                headVelocityY = 0;
            }else{
                headX = 500;
            }
        }else if(k == KeyEvent.VK_UP && headVelocityY != -(double)velocityValue){
            if(headY >= 0){
                headVelocityY = velocityValue;
                headVelocityX = 0;
            }else{
                headY = 0;
            }
            
        }else if(k == KeyEvent.VK_DOWN && headVelocityY != (double)velocityValue){
            if(headY <=500){
                headVelocityY = -velocityValue;
                headVelocityX = 0;
            }else{
                headY = 500;
            }
        }
        
        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(headX < -headWidth){
            
            headX = 500;
            
            
        }
        if(headX > 500){
            
            headX = 0;
            
        }
        if(headY < -headHeight){
            
            headY = 500;
            
        }
        if(headY > 500){
            
            headY = 0;
            
        }
        
        headX += headVelocityX;
        headY -= headVelocityY;
        time -= 0.005;
        foodgame.setRemainingTime((int)(time));
        repaint();
        
     }
    
}
