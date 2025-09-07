package model;

import model.entities.Ennemy;
import model.entities.Player;
import model.map.Map;

import java.util.Random;

public class GameModel {
    private char[][] currentMap;
    private final int TILE_SIZE = 20;

    Map map;
    Player player;
    Ennemy[] ennemies = new Ennemy[2];

    public GameModel() {
        map = new Map(40, 60);
        map.generateMap(10, 5, 2, 5);
        currentMap = map.getMap();
        player = new Player(map.getRoomCenters().get(0)[1], map.getRoomCenters().get(0)[0], 10,5);

        System.out.println(map.toString());

        ennemies[0] = new Ennemy(map.getRoomCenters().get(1)[1], map.getRoomCenters().get(1)[0], 10,1);
        ennemies[1] = new Ennemy(map.getRoomCenters().get(2)[1], map.getRoomCenters().get(2)[0], 10,1);

    }

    public void updateEnnemies() {
        for (int i = 0; i < ennemies.length; i++) {
            Random r = new Random();
            int x = r.nextInt(0, 4);

            switch (x) {
                case 0:
                    if(player.isAlive() && (player.getX() == ennemies[i].getX()+1) && (player.getY() == ennemies[i].getY())){
                            break;
                    }
                    ennemies[i].moveRight(currentMap);
                    break;
                case 1:
                    if(player.isAlive() && (player.getX() == ennemies[i].getX()-1) && (player.getY() == ennemies[i].getY())){
                        break;
                    }
                    ennemies[i].moveLeft(currentMap);
                    break;
                case 2:
                    if(player.isAlive() && (player.getX() == ennemies[i].getX()) && (player.getY() == ennemies[i].getY()-1)){
                        break;
                    }
                    ennemies[i].moveDown(currentMap);
                    break;
                case 3:
                    if(player.isAlive() && (player.getX() == ennemies[i].getX()) && (player.getY() == ennemies[i].getY()+1)){
                        break;
                    }
                    ennemies[i].moveUp(currentMap);
                    break;
            }
        }
    }

    public Ennemy getAdjacentEnnemy() {
        int px = player.getX();
        int py = player.getY();
        for (Ennemy e : ennemies) {
            int ex = e.getX();
            int ey = e.getY();
            if ((Math.abs(px-ex) == 1 && py == ey) || (Math.abs(py-ey) == 1 && px == ex)) {
                return e;
            }
        }
        return null;
    }

    public char[][] getCurrentMap() { return currentMap; }
    public Player getPlayer() { return player; }
    public Ennemy[] getEnnemies() { return ennemies; }
    public int getTileSize() { return TILE_SIZE; }

    public void movePlayerUp() {
        for (Ennemy e : ennemies) {
            if(e.isAlive() && (player.getX() == e.getX()) && (player.getY()+1 == e.getY())){
                break;
            }
        }
        player.moveUp(currentMap);
    }
    public void movePlayerDown() {
        for (Ennemy e : ennemies) {
            if(e.isAlive() && (player.getX() == e.getX()) && (player.getY()-1 == e.getY())){
                break;
            }
        }
        player.moveDown(currentMap);
    }

    public void movePlayerLeft() {
        for (Ennemy e : ennemies) {
            if(e.isAlive() && (player.getX()-1 == e.getX()) && (player.getY() == e.getY())){
                break;
            }
        }
        player.moveLeft(currentMap);
    }
    public void movePlayerRight() {
        for (Ennemy e : ennemies) {
            if(e.isAlive() && (player.getX()+1 == e.getX()) && (player.getY() == e.getY())){
                break;
            }
        }
        player.moveRight(currentMap);
    }

    public void attack() {
        Ennemy target = getAdjacentEnnemy();
        if (target != null) {player.attack(target);}
    }
}
