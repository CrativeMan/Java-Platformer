package main;

import javax.swing.*;

public class GameFrame {
    private JFrame jFrame;
    public GameFrame(GamePanel gamePanel){
        jFrame = new JFrame();

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.add(gamePanel);
        jFrame.pack();
        //! You shall not pass
        jFrame.setVisible(true);
    }
}
