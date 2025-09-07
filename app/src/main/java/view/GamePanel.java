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
    JLabel hpLabel;

    public GamePanel(GameModel model, JLabel hpLabel) {
        this.model = model;
        this.hpLabel = hpLabel;
        hpLabel.setText("HP:"+model.getPlayer().getHp());
        this.TILE_SIZE = model.getTileSize();

        setFocusable(true); // permet de recevoir le clavier
        requestFocusInWindow(); // demande le focus
        setPreferredSize(new Dimension(model.getCurrentMap()[0].length * TILE_SIZE, model.getCurrentMap().length * TILE_SIZE));
    }

    public void updateHpLabel() {
        hpLabel.setText("HP: " + model.getPlayer().getHp());
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
            if(ennemies[i].isAlive()){
                g.fillRect(ennemies[i].getX()*TILE_SIZE, ennemies[i].getY()*TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }

        }

        for (Ennemy e : model.getEnnemies()) {
            if(e.isAlive()){
                int x = e.getX() * TILE_SIZE;
                int y = e.getY() * TILE_SIZE;

                g.setColor(Color.RED);
                g.fillRect(x, y, TILE_SIZE, TILE_SIZE);

                int hpMax = e.getHpMax();
                int hp = e.getHp();
                int barWidth = (int)((double)hp / hpMax * TILE_SIZE);
                int barHeight = 5;

                g.setColor(Color.GREEN);
                g.fillRect(x, y - barHeight, barWidth, barHeight);

                g.setColor(Color.RED);
                g.fillRect(x + barWidth, y - barHeight, TILE_SIZE - barWidth, barHeight);

                // (Optionnel) Affiche le nombre de HP
                g.setColor(Color.BLACK);
                g.drawString(hp + "/" + hpMax, x, y - 2);
            }

        }
    }
}
