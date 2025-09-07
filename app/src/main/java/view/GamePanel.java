package view;

import controller.GameController;
import model.GameModel;
import model.entities.Ennemy;
import model.entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    GameModel model;


    public GamePanel(GameModel model) {
        this.model = model;

        setFocusable(true); // permet de recevoir le clavier
        requestFocusInWindow(); // demande le focus
        addKeyListener(new GameController(model, this));

        setPreferredSize(new Dimension(model.getCurrentMap()[0].length * tileSize, model.getCurrentMap().length * tileSize));
    }

    public void paintComponent(Graphics g) {
        char [][]currentMap = model.getCurrentMap();
        Player player = model.getPlayer();
        Ennemy[] ennemies = model.getEnnemies();

        for(int i = 0; i < currentMap.length; i++) {
            for(int j = 0; j < currentMap[i].length; j++) {
                if(currentMap[i][j] == '#') {
                    g.setColor(Color.BLACK);
                }
                else if (currentMap[i][j] == '.') {
                    g.setColor(Color.GRAY);
                }
                else if (currentMap[i][j] == '+') {
                    g.setColor(Color.DARK_GRAY);
                }
                g.fillRect(j*tileSize, i*tileSize, tileSize, tileSize);
            }
        }
        g.setColor(Color.GREEN);
        g.fillRect(player.getX()*tileSize, player.getY()*tileSize, tileSize, tileSize);

        g.setColor(Color.RED);
        for(int i = 0; i < ennemies.length; i++) {
            g.fillRect(ennemies[i].getX()*tileSize, ennemies[i].getY()*tileSize, tileSize, tileSize);
        }
    }
}
