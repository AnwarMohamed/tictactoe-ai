package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;

/**
 *
 * @author anwarelmakrahy
 */
public class GameFrame extends javax.swing.JFrame {
       
    JRadioButtonMenuItem easyAction;
    JRadioButtonMenuItem mediumAction; 
    JRadioButtonMenuItem hardAction;
    
    JMenuItem restartAction;
    
    /**
     * Creates new form GameFrame
     */
    public GameFrame() {
        initComponents();
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu gameMenu = new JMenu("Game");        
        restartAction = new JMenuItem("Restart Game");
        JMenuItem exitAction = new JMenuItem("Exit");
                
        gameMenu.add(restartAction);
        gameMenu.add(new JSeparator());
        gameMenu.add(exitAction);

        restartAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent action) {
                easyAction.setEnabled(true);
                mediumAction.setEnabled(true);
                hardAction.setEnabled(true);
            }
        });
        
        exitAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent action) {
                System.exit(0);
            }
        });
        
        JMenu levelMenu = new JMenu("Level");
        easyAction = new JRadioButtonMenuItem("Easy");
        mediumAction = new JRadioButtonMenuItem("Medium");
        hardAction = new JRadioButtonMenuItem("Hard");
                
        ButtonGroup levelGroup = new ButtonGroup();
        levelGroup.add(easyAction);
        levelGroup.add(mediumAction);
        levelGroup.add(hardAction);                
  
        levelMenu.add(easyAction);
        levelMenu.add(mediumAction);
        levelMenu.add(hardAction);                 
        
        easyAction.setSelected(true);
        
        menuBar.add(gameMenu);
        menuBar.add(levelMenu);
        
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easyAction.setEnabled(false);
                mediumAction.setEnabled(false);
                hardAction.setEnabled(false);                
            }
        };
        
        button0_0.addActionListener(buttonListener);
        button0_1.addActionListener(buttonListener);
        button0_2.addActionListener(buttonListener);
        button1_0.addActionListener(buttonListener);
        button1_1.addActionListener(buttonListener);
        button1_2.addActionListener(buttonListener);
        button2_0.addActionListener(buttonListener);
        button2_1.addActionListener(buttonListener);
        button2_2.addActionListener(buttonListener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioGroupDifficulty = new javax.swing.ButtonGroup();
        button0_1 = new javax.swing.JToggleButton();
        button0_0 = new javax.swing.JToggleButton();
        button0_2 = new javax.swing.JToggleButton();
        button1_1 = new javax.swing.JToggleButton();
        button1_2 = new javax.swing.JToggleButton();
        button1_0 = new javax.swing.JToggleButton();
        button2_1 = new javax.swing.JToggleButton();
        button2_2 = new javax.swing.JToggleButton();
        button2_0 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(310, 360));
        setResizable(false);

        button0_1.setToolTipText("0:1");

        button0_0.setToolTipText("0:0");

        button0_2.setToolTipText("0:2");

        button1_1.setToolTipText("1:1");

        button1_2.setToolTipText("1:2");

        button1_0.setToolTipText("1:0");

        button2_1.setToolTipText("2:1");

        button2_2.setToolTipText("2:2");

        button2_0.setToolTipText("2:0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button2_0, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(button1_0, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(button0_0, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button0_1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(button0_2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button0_2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button0_0, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button0_1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1_0, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button2_0, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }

    public int getLevel() {
        if (easyAction.isSelected()) {
            return 0;
        } else if (mediumAction.isSelected()) {
            return 1;
        } else {
            return 2;
        }
    }
    
    public void setRestartListener(ActionListener listener) {
        restartAction.addActionListener(listener);
    }
    
    public void setLevelListener(ActionListener listener) {
        easyAction.addActionListener(listener);
        mediumAction.addActionListener(listener);
        hardAction.addActionListener(listener);
    }
    
    public javax.swing.JToggleButton[] getButtons() {
        return new javax.swing.JToggleButton[]{
            button0_0, button0_1, button0_2 ,
            button1_0, button1_1, button1_2 ,
            button2_0, button2_1, button2_2 
        };        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton button0_0;
    private javax.swing.JToggleButton button0_1;
    private javax.swing.JToggleButton button0_2;
    private javax.swing.JToggleButton button1_0;
    private javax.swing.JToggleButton button1_1;
    private javax.swing.JToggleButton button1_2;
    private javax.swing.JToggleButton button2_0;
    private javax.swing.JToggleButton button2_1;
    private javax.swing.JToggleButton button2_2;
    private javax.swing.ButtonGroup radioGroupDifficulty;
    // End of variables declaration//GEN-END:variables
}