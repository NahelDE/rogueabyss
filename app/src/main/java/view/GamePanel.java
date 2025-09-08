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

    //Thread thread fluide

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

        paintHealthBar(g, camX, camY);

    }
    public void paintHealthBar(Graphics g,int camX,int camY){
        int barHeight = 5;

        for (Ennemy e : model.getEnnemies()) {
            if(e.isAlive()){
                int x = e.getX() * TILE_SIZE;
                int y = e.getY() * TILE_SIZE;

                //paint ennemies
                g.setColor(Color.RED);
                g.fillRect(x-camX, y-camY, TILE_SIZE, TILE_SIZE);

                int hpMax = e.getHpMax();
                int hp = e.getHp();
                int barWidth = (int)((double)hp / hpMax * TILE_SIZE);


                g.setColor(Color.GREEN);
                g.fillRect(x-camX, y-camY - barHeight, barWidth , barHeight);

                g.setColor(Color.RED);
                g.fillRect(x-camX + barWidth, y-camY - barHeight , TILE_SIZE - barWidth, barHeight);
            }
        }
        if(model.getPlayer().isAlive()){
            Player p = model.getPlayer();
            int x = p.getX() * TILE_SIZE;
            int y = p.getY() * TILE_SIZE;

            //paint player
            g.setColor(Color.BLUE);
            g.fillRect(x-camX, y-camY, TILE_SIZE, TILE_SIZE);

            int hpMax = p.getHpMax();
            int hp = p.getHp();
            int barWidth = (int)((double)hp / hpMax *TILE_SIZE);

            g.setColor(Color.GREEN);
            g.fillRect(x-camX, y-camY - barHeight , barWidth, barHeight);


            g.setColor(Color.RED);
            g.fillRect(x-camX + barWidth, y-camY - barHeight , TILE_SIZE - barWidth, barHeight);
        }
    }
}
