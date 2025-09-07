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
        map = new Map(80, 100);
        map.generateMap(16, 10, 4, 10);
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
                    if(!isOccupied(ennemies[i].getX()+1, ennemies[i].getY())) {
                        ennemies[i].moveRight(currentMap);
                    }
                    break;
                case 1:
                    if(!isOccupied(ennemies[i].getX()-1, ennemies[i].getY())) {
                        ennemies[i].moveLeft(currentMap);
                    }
                    break;
                case 2:
                    if(!isOccupied(ennemies[i].getX(), ennemies[i].getY()+1)) {
                        ennemies[i].moveDown(currentMap);
                    }
                    break;
                case 3:
                    if(!isOccupied(ennemies[i].getX(), ennemies[i].getY()-1)) {
                        ennemies[i].moveUp(currentMap);
                    }
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
        if(!isOccupied(player.getX(), player.getY()-1)) {
            player.moveUp(currentMap);
        }
    }
    public void movePlayerDown() {
        if(!isOccupied(player.getX(), player.getY()+1)) {
            player.moveDown(currentMap);
        }
    }

    public void movePlayerLeft() {
        if(!isOccupied(player.getX()-1, player.getY())) {
            player.moveLeft(currentMap);
        }
    }
    public void movePlayerRight() {
        if(!isOccupied(player.getX()+1, player.getY())) {
            player.moveRight(currentMap);
        }
    }

    public void attack() {
        Ennemy target = getAdjacentEnnemy();
        if (target != null) {player.attack(target);}
    }

    public boolean isOccupied(int x , int y) {
        for (Ennemy e : ennemies) {
            if (e.getX() == x && e.getY() == y && e.isAlive()) {
                return true;
            }
        }
        if (player.getX() == x && player.getY() == y && player.isAlive()) {
            return true;
        }

        return false;
    }
}
