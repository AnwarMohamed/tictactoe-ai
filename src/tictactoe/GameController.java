package tictactoe;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class GameController {
        
    private final JToggleButton[] mMoveButtons;
    private final ActionListener mMoveListener;
    private final ActionListener mLevelListener;
    private final ActionListener mRestartListener;
    
    private final GameFrame mGameFrame;
    
    private static final int PLAY_STATE_DEFAULT = 0;
    private static final int PLAY_STATE_O = -1;
    private static final int PLAY_STATE_X = 1;
    
    private static final int TURN_STATE_X = 1;
    private static final int TURN_STATE_O = -1;
    
    private static final int LEVEL_STATE_EASY = 0;
    private static final int LEVEL_STATE_MEDIUM = 1;
    private static final int LEVEL_STATE_HARD = 2;
    
    private int mLevel = LEVEL_STATE_EASY;
    private int mTurn = TURN_STATE_O;
    private final int[] mPlays = new int[9];
    
    private static ImageIcon ICON_STATE_O;
    private static ImageIcon ICON_STATE_X;
    
    public GameController(final GameFrame gameFrame) {
        mMoveListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JToggleButton button = (JToggleButton) e.getSource();                
                String buttonLocation = button.getToolTipText();
                
                mPlays[Integer.parseInt(buttonLocation.split(":")[0]) * 3
                        + Integer.parseInt(buttonLocation.split(":")[1])] = PLAY_STATE_O;
                
                button.setEnabled(false);
                button.setIcon(ICON_STATE_O);
                
                mTurn = TURN_STATE_X;
                
                if (hasWinner(PLAY_STATE_X)) {
                    mGameFrame.notifyLoss();
                } else if (hasWinner(PLAY_STATE_O)) {
                    mGameFrame.notifyWin();
                } else if (isFinished()) {
                    mGameFrame.notifyDraw();
                } else {                
                    play();
                }
            }
        };
        
        mMoveButtons = gameFrame.getButtons();
                
        for (JToggleButton button: mMoveButtons) {
            button.addActionListener(mMoveListener);
        }                
                        
        mLevelListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mLevel = gameFrame.getLevel();
            }
        };
        
        mRestartListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restart();
            }
        };
        
        gameFrame.setLevelListener(mLevelListener);
        gameFrame.setRestartListener(mRestartListener);
        
        ICON_STATE_O = new ImageIcon("circle.png");
        ICON_STATE_O = new ImageIcon(
                ICON_STATE_O.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        
        ICON_STATE_X = new ImageIcon("cross.png");        
        ICON_STATE_X = new ImageIcon(
                ICON_STATE_X.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        
        mGameFrame = gameFrame;
    }
    
    private void restart() {
        for (JToggleButton button: mMoveButtons) {
            button.setEnabled(true);
            button.setSelected(false);
            button.setIcon(null);
        }
        
        for (int i=0; i<mPlays.length; i++) {
            mPlays[i] = PLAY_STATE_DEFAULT;
        }
        
        mTurn = TURN_STATE_O;               
    }
    
    private void play() {
        
        
        mTurn = TURN_STATE_X;
        
        
        if (hasWinner(PLAY_STATE_X)) {
            mGameFrame.notifyLoss();
        } else if (hasWinner(PLAY_STATE_O)) {
            mGameFrame.notifyWin();
        } else if (isFinished()) {
            mGameFrame.notifyDraw();
        }
    }    
    
    private boolean isFinished() {
        for (int play: mPlays) {
            if (play == PLAY_STATE_DEFAULT) {
                return false;
            }
        }        
        return true;
    }
    
    private boolean hasWinner(int player) {
        if ((mPlays[0] == mPlays[4] 
                && mPlays[4] == mPlays[8] 
                && mPlays[8] == player) 
                || 
                (mPlays[2] == mPlays[4] 
                && mPlays[4] == mPlays[6] 
                && mPlays[6] == player)) {            
            return true;
        }
        
        for (int i = 0; i < 3; i++) {
            if ((mPlays[i] == mPlays[3+i] 
                    && mPlays[3+i] == mPlays[6+i] 
                    && mPlays[6+i] == player) 
                   || 
                    (mPlays[3*(2-i)] == mPlays[3*(2-i)+1] 
                    && mPlays[3*(2-i)+1] == mPlays[3*(2-i)+2] 
                    && mPlays[3*(2-i)+2] == player)) {            
               return true;
            }
        }
        return false;
    }    
}
