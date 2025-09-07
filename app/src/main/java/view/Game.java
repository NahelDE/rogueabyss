package view;

import model.entities.Ennemy;
import model.entities.Player;
import model.map.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Game extends JPanel {

    public Game() {

        setFocusable(true); // permet de recevoir le clavier
        requestFocusInWindow(); // demande le focus
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch(keyCode) {
                    case KeyEvent.VK_Z: // haut
                        player.moveUp(currentMap);
                        break;
                    case KeyEvent.VK_S: // bas
                        player.moveDown(currentMap);
                        break;
                    case KeyEvent.VK_Q: // gauche
                        player.moveLeft(currentMap);
                        break;
                    case KeyEvent.VK_D: // droite
                        player.moveRight(currentMap);
                        break;
                }
                updateEnnemies();
                repaint();
            }
        });


        setPreferredSize(new Dimension(currentMap[0].length * tileSize, currentMap.length * tileSize));
    }

    public void paintComponent(Graphics g) {
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

    public void updateEnnemies(){
        for(int i = 0; i < ennemies.length; i++) {
            Random r = new Random();
            int x = r.nextInt(0,4);

            switch (x){
                case 0:
                    ennemies[i].moveRight(currentMap);
                    break;
                case 1:
                    ennemies[i].moveLeft(currentMap);
                    break;
                case 2:
                    ennemies[i].moveDown(currentMap);
                    break;
                case 3:
                    ennemies[i].moveUp(currentMap);
                    break;
            }
        }
    }
}
