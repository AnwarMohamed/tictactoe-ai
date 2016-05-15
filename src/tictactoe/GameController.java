package tictactoe;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
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
    private int mDepthLimit = 5;
    private final List<Entry<Integer, Integer>> mChildrenScore;
    
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
        
        mChildrenScore = new ArrayList<Entry<Integer, Integer>>();
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
        
        doAlphaBetaMinimax(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, TURN_STATE_X);
        
        int optimumPlay = getOptimumPlay();
        
        mPlays[optimumPlay] = PLAY_STATE_X;
        
        mMoveButtons[optimumPlay].setEnabled(false);
        mMoveButtons[optimumPlay].setSelected(true);
        mMoveButtons[optimumPlay].setIcon(ICON_STATE_X);
        
        if (hasWinner(PLAY_STATE_X)) {
            mGameFrame.notifyLoss();
        } else if (hasWinner(PLAY_STATE_O)) {
            mGameFrame.notifyWin();
        } else if (isFinished()) {
            mGameFrame.notifyDraw();
        }
        
        mTurn = TURN_STATE_O;
    }    
    
    private int getOptimumPlay() {
        int MAX = -100000;
        int optimum = -1;
        
        for (int i = 0; i < mChildrenScore.size(); ++i) {
            if (MAX < mChildrenScore.get(i).getKey()) {
                MAX = mChildrenScore.get(i).getKey();
                optimum = i;
            }
        }
        
        return mChildrenScore.get(optimum).getValue();        
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
            X = O = 0;                        
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
            X = O = 0;
            for (int i = 0; i < 3; ++i) {
                if (mPlays[(i*3)+j] == PLAY_STATE_X) {
                    X++;
                } else if (mPlays[(i*3)+j] == PLAY_STATE_O) {
                    O++;
                }
            }
            score += updateScore(X, O);
        }

        X = O = 0;        
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
    
    private int doAlphaBetaMinimax(int alpha, int beta, int depth, int turn) {
        
        if (beta <= alpha) {             
            if(turn == TURN_STATE_X) {
                return Integer.MAX_VALUE; 
            } else {
                return Integer.MIN_VALUE; 
            }
        }
        
        if (depth == mDepthLimit) {
            return evaluatePlays();
        }                                 
        
        if (depth == 0) {
            mChildrenScore.clear();
        } 
        
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        
        List<Integer> emptyPlays = getEmptyPlays();
        for (Integer emptyPlay : emptyPlays) {
            int score = 0;
            
            if (turn == TURN_STATE_X) {
                mPlays[emptyPlay] = PLAY_STATE_X;
                
                score = doAlphaBetaMinimax(alpha, beta, depth+1, TURN_STATE_O);
                maxValue = Math.max(maxValue, score);
                alpha = Math.max(score, alpha);
                
                if (depth == 0) {
                    mChildrenScore.add(new AbstractMap.SimpleEntry(score, emptyPlay));
                }
            } else if (turn == TURN_STATE_O) {
                mPlays[emptyPlay] = PLAY_STATE_O;
                
                score = doAlphaBetaMinimax(alpha, beta, depth+1, TURN_STATE_X); 
                minValue = Math.min(minValue, score);
                beta = Math.min(score, beta);
            }
            
            mPlays[emptyPlay] = PLAY_STATE_DEFAULT;
            
            if(score == Integer.MAX_VALUE || score == Integer.MIN_VALUE) {
                break;
            }
        }
        
        return turn == TURN_STATE_X ? maxValue : minValue;                
    }
}
