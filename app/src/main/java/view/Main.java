package view;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args){

        JFrame frame = new JFrame("RogueAbyss");
        Game game = new Game();
        JLabel hpLabel = new JLabel("HP:");

        frame.add(game, BorderLayout.CENTER);
        frame.add(hpLabel, BorderLayout.NORTH);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
