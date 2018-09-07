package yılanoyunu;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class FoodGame extends JFrame {
    
        public static int skor = 0;
        public static int remainingTime = 100;

    public static int getRemainingTime() {
        return remainingTime;
    }

    public static void setRemainingTime(int remainingTime) {
        FoodGame.remainingTime = remainingTime;
    }
    public static void main(String[] args) {
        
        FoodGame foodgame = new FoodGame();
        
        foodgame.setSize(500, 500);
        foodgame.setFocusable(false);
        foodgame.setResizable(false);
        foodgame.setVisible(true);
        foodgame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Game oyun = new Game();
        
        JLabel skorTablosu = new JLabel();
        oyun.add(skorTablosu);
        skorTablosu.setVisible(true);
        skorTablosu.setBackground(Color.red);
        
        JLabel zaman = new JLabel();
        oyun.add(zaman);
        zaman.setVisible(true);
        
        
        oyun.requestFocus();
        oyun.addKeyListener(oyun);
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false);
        
        foodgame.add(oyun);
        foodgame.setVisible(true);
        while(true){
        skorTablosu.setText("Score: " + getSkor() + " |||| ");
        zaman.setText("Remaining Time: " + (double)(getRemainingTime()));
        }
        
        
    }

    public static int getSkor() {
        return skor;
    }

    public static void setSkor(int skor) {
        FoodGame.skor = skor;
    }
    
}
