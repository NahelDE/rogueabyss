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

        int camX = player.getX() * TILE_SIZE - getWidth() / 2;
        int camY = player.getY() * TILE_SIZE - getHeight() / 2;

        camX = Math.max(0, Math.min(camX, currentMap[0].length * TILE_SIZE - getWidth()));
        camY = Math.max(0, Math.min(camY, currentMap.length * TILE_SIZE - getHeight()));

        camX = (camX / TILE_SIZE) * TILE_SIZE;
        camY = (camY / TILE_SIZE) * TILE_SIZE;

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
                g.fillRect(j*TILE_SIZE-camX, i*TILE_SIZE-camY, TILE_SIZE, TILE_SIZE);
            }
        }
        g.setColor(Color.GREEN);
        g.fillRect(player.getX()*TILE_SIZE-camX, player.getY()*TILE_SIZE-camY, TILE_SIZE, TILE_SIZE);

        g.setColor(Color.RED);
        for(int i = 0; i < ennemies.length; i++) {
            if(ennemies[i].isAlive()){
                g.fillRect(ennemies[i].getX()*TILE_SIZE-camX, ennemies[i].getY()*TILE_SIZE-camY, TILE_SIZE, TILE_SIZE);
            }

        }

        for (Ennemy e : model.getEnnemies()) {
            if(e.isAlive()){
                int x = e.getX() * TILE_SIZE;
                int y = e.getY() * TILE_SIZE;

                g.setColor(Color.RED);
                g.fillRect(x-camX, y-camY, TILE_SIZE, TILE_SIZE);

                int hpMax = e.getHpMax();
                int hp = e.getHp();
                int barWidth = (int)((double)hp / hpMax * TILE_SIZE);
                int barHeight = 5;

                g.setColor(Color.GREEN);
                g.fillRect(x-camX, y-camY - barHeight, barWidth, barHeight);

                g.setColor(Color.RED);
                g.fillRect(x-camX + barWidth, y-camY - barHeight, TILE_SIZE - barWidth, barHeight);

            }

        }
    }
}
