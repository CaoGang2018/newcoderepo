package com.cao.bird;

import javax.swing.*;
import java.io.IOException;

/**
 * @author admin_cg
 * @data 2020/9/21 20:36
 */
public class StartGames {
    public static void main(String[] args) throws Exception{
        JFrame frame = new JFrame();

        BirdPanel birdPanel = new BirdPanel();
        frame.add(birdPanel);

        frame.setSize(440, 670);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        birdPanel.action();
    }
}
