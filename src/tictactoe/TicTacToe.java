package tictactoe;

import com.alee.laf.WebLookAndFeel;
import javax.swing.SwingUtilities;


public class TicTacToe {    
    public static void main(String[] args) {        
        SwingUtilities.invokeLater(new Runnable() {                 
            @Override
            public void run() {                
                WebLookAndFeel.install();
                WebLookAndFeel.setDecorateFrames(true);
                WebLookAndFeel.setDecorateDialogs(true);
                
                GameFrame gameFrame = new GameFrame();                        
                GameController gameController = new GameController(gameFrame);
                
                gameFrame.setTitle("Tic-Tac-Toe with Artificial Intelligence");
                gameFrame.setLocationRelativeTo(null);
                gameFrame.setVisible(true);
            }
        });
        
    }    
}
