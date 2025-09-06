package game;

import javax.swing.*;

public class Main {
    public static void main(String[] args){

        JFrame frame = new JFrame("RogueAbyss");
        Game game = new Game();

        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
