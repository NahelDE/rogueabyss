package view;

import controller.GameController;
import model.GameModel;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args){

        JFrame frame = new JFrame("RogueAbyss");
        JLabel hpLabel = new JLabel("HP:");
        GameModel model = new GameModel();
        GamePanel gamePanel = new GamePanel(model,hpLabel);
        GameController gameController = new GameController(model, gamePanel);
        gamePanel.addKeyListener(gameController);



        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(hpLabel, BorderLayout.NORTH);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
