package view;

import controller.GameController;
import model.GameModel;
import model.entities.Ennemy;
import model.entities.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    GameModel model;
    private final int TILE_SIZE;

    public GamePanel(GameModel model) {
        this.model = model;
        this.TILE_SIZE = model.getTileSize();

        setFocusable(true); // permet de recevoir le clavier
        requestFocusInWindow(); // demande le focus
        addKeyListener(new GameController(model, this));

        setPreferredSize(new Dimension(model.getCurrentMap()[0].length * TILE_SIZE, model.getCurrentMap().length * TILE_SIZE));
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
                g.fillRect(j*TILE_SIZE, i*TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
        g.setColor(Color.GREEN);
        g.fillRect(player.getX()*TILE_SIZE, player.getY()*TILE_SIZE, TILE_SIZE, TILE_SIZE);

        g.setColor(Color.RED);
        for(int i = 0; i < ennemies.length; i++) {
            g.fillRect(ennemies[i].getX()*TILE_SIZE, ennemies[i].getY()*TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }
}
