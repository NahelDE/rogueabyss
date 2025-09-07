package controller;

import model.GameModel;
import model.entities.Ennemy;
import view.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController extends KeyAdapter {
    private GameModel model;
    private GamePanel view;
    public GameController(GameModel model, GamePanel view) {
        this.model = model;
        this.view = view;
    }
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_Z: model.movePlayerUp(); break;
            case KeyEvent.VK_S: model.movePlayerDown(); break;
            case KeyEvent.VK_Q: model.movePlayerLeft(); break;
            case KeyEvent.VK_D: model.movePlayerRight(); break;
            case KeyEvent.VK_SPACE: model.attack(); break;
        }
        view.updateHpLabel();
        model.updateEnnemies();
        view.repaint();
    }
}