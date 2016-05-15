package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JToggleButton;

public class GameController {
            
    private final ActionListener mMoveListener;
    private final ActionListener mLevelListener;    
    
    private GameFrame mGameFrame;
    
    private static final int PLAY_STATE_DEFAULT = 0;
    private static final int PLAY_STATE_O = -1;
    private static final int PLAY_STATE_X = 1;
    
    private static final int TURN_STATE_X = 1;
    private static final int TURN_STATE_O = 2;
    
    private static final int LEVEL_STATE_EASY = 0;
    private static final int LEVEL_STATE_MEDIUM = 1;
    private static final int LEVEL_STATE_HARD = 2;
    
    private int mLevel = LEVEL_STATE_EASY;    
    private int[] mPlays = new int[9];
    
    private int mDepthLimit;       
    
    public GameController(final GameFrame gameFrame) {
        mMoveListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JToggleButton button = (JToggleButton) e.getSource();                
                String buttonLocation = button.getToolTipText();
                
                mPlays[Integer.parseInt(buttonLocation.split(":")[0]) * 3
                        + Integer.parseInt(buttonLocation.split(":")[1])] = PLAY_STATE_O;               
                                                
                if (hasWinner(PLAY_STATE_X)) {                    
                    mGameFrame.notifyLoss();                   
                } else if (hasWinner(PLAY_STATE_O)) {                    
                    mGameFrame.notifyWin();                    
                } else if (getEmptyPlays().isEmpty()) {                    
                    mGameFrame.notifyDraw();                    
                } else {                            
                    play();
                }
            }
        };
                                            
        mLevelListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mLevel = gameFrame.getLevel();
            }
        };
        
        gameFrame.setLevelListener(mLevelListener);
                                
        for (JToggleButton button: gameFrame.getButtons()) {
            button.addActionListener(mMoveListener);
        }   
        
        mGameFrame = gameFrame; 
        mGameFrame.setGameController(this);
    }
    
    public void restart() {                
        for (int i=0; i<mPlays.length; i++) {
            mPlays[i] = PLAY_STATE_DEFAULT;
        }
    }
    
    private void play() {        
        if (mLevel == LEVEL_STATE_EASY) {
            mDepthLimit = 1;
        } else if (mLevel == LEVEL_STATE_MEDIUM) {
            mDepthLimit = (getEmptyPlays().size()/2) + 1;
        } else if (mLevel == LEVEL_STATE_HARD) {
            mDepthLimit = -1;
        }
        
        int bestPlay = doAlphaBetaMinimax(
                Integer.MIN_VALUE, Integer.MAX_VALUE, mDepthLimit, TURN_STATE_X)[1];                
        
        mPlays[bestPlay] = PLAY_STATE_X;
        
        printPlays();
        
        mGameFrame.updateButtons(mPlays);
        
        if (hasWinner(PLAY_STATE_X)) {
            mGameFrame.notifyLoss();
        } else if (hasWinner(PLAY_STATE_O)) {
            mGameFrame.notifyWin();
        } else if (getEmptyPlays().isEmpty()) {
            mGameFrame.notifyDraw();
        }                
    }    
    
    private void printPlays() {
        for (int i = 0; i < 3; ++i) {                        
            for (int j = 0; j < 3; ++j) {
                System.out.print(mPlays[(i*3)+j] + "\t");                    
            }             
            System.out.println();
        }   
        System.out.println();
    }
    
    private int[] doAlphaBetaMinimax(int alpha, int beta, int depth, int turn) {           
        List<Integer> emptyPlays = getEmptyPlays();
     
        int score;
        int bestPlay = -1;
      
        if (depth == 0 
                || hasWinner(PLAY_STATE_X) 
                || hasWinner(PLAY_STATE_O)
                || getEmptyPlays().isEmpty()) {
            return new int[] { evaluatePlays(), bestPlay};
        } else {
            for (Integer emptyPlay: emptyPlays) {
                if (turn == TURN_STATE_X) {
                    mPlays[emptyPlay] = PLAY_STATE_X;

                    score = doAlphaBetaMinimax(alpha, beta, depth-1, TURN_STATE_O)[0];
                    if (score > alpha) {
                       alpha = score;
                       bestPlay = emptyPlay;
                    }
                } else if (turn == TURN_STATE_O) {
                    mPlays[emptyPlay] = PLAY_STATE_O;

                    score = doAlphaBetaMinimax(alpha, beta, depth-1, TURN_STATE_X)[0]; 
                    if (score < beta) {
                        beta = score;
                        bestPlay = emptyPlay;
                    }
                }  
                
                mPlays[emptyPlay] = PLAY_STATE_DEFAULT;
                
                if (alpha >= beta) {
                    break;
                }                                
            }            
            return new int[] {(turn == TURN_STATE_X) ? alpha : beta, bestPlay};
        }
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
    
    private List<Integer> getEmptyPlays() {
        List<Integer> empty = new ArrayList<Integer>();        
        for (int i=0; i<mPlays.length; i++) {
            if (mPlays[i] == PLAY_STATE_DEFAULT) {
                empty.add(i);
            }
        }        
        return empty;
    }        
    
    public int evaluatePlays() {
        int X, O, score = 0;
        
        for (int i = 0; i < 3; ++i) {            
            X = (O = 0);
            for (int j = 0; j < 3; ++j) {
                if (mPlays[(i*3)+j] == PLAY_STATE_X) {
                    X++;
                } else if (mPlays[(i*3)+j] == PLAY_STATE_O) {
                    O++;
                }
            }             
            score += updateScore(X, O);
        }
        
        for (int j = 0; j < 3; ++j) {
            X = (O = 0);
            for (int i = 0; i < 3; ++i) {
                if (mPlays[(i*3)+j] == PLAY_STATE_X) {
                    X++;
                } else if (mPlays[(i*3)+j] == PLAY_STATE_O) {
                    O++;
                }
            }
            score += updateScore(X, O);
        }

        X = (O = 0);    
        for (int i = 0, j = 0; i < 3; ++i, ++j) {
            if (mPlays[(i*3)+j] == PLAY_STATE_X) {
                X++;
            } else if (mPlays[(i*3)+j] == PLAY_STATE_O) {
                O++;
            }
        }
        score += updateScore(X, O);

        X = O = 0;        
        for (int i = 2, j = 0; i > -1; --i, ++j) {
            if (mPlays[(i*3)+j] == PLAY_STATE_X) {
                X++;
            } else if (mPlays[(i*3)+j] == PLAY_STATE_O) {
                O++;
            }
        }
        return (score += updateScore(X, O));
    }    
    
    private int updateScore(int X, int O){
        int change;
        if (X == 3) {
            change = 100;
        } else if (X == 2 && O == 0) {
            change = 10;
        } else if (X == 1 && O == 0) {
            change = 1;
        } else if (O == 3) {
            change = -100;
        } else if (O == 2 && X == 0) {
            change = -10;
        } else if (O == 1 && X == 0) {
            change = -1;
        } else {
            change = 0;
        } 
        return change;
    }    
}
