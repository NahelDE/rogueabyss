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
        map.generateMap(10, 5, 2, 10);
        currentMap = map.getMap();
        player = new Player(map.getRoomCenters().get(0)[1], map.getRoomCenters().get(0)[0], 10);

        System.out.println(map.toString());

        ennemies[0] = new Ennemy(map.getRoomCenters().get(1)[1], map.getRoomCenters().get(1)[0], 10);
        ennemies[1] = new Ennemy(map.getRoomCenters().get(2)[1], map.getRoomCenters().get(2)[0], 10);

    }

    public void updateEnnemies() {
        for (int i = 0; i < ennemies.length; i++) {
            Random r = new Random();
            int x = r.nextInt(0, 4);

            switch (x) {
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


    public char[][] getCurrentMap() { return currentMap; }
    public Player getPlayer() { return player; }
    public Ennemy[] getEnnemies() { return ennemies; }
    public int getTileSize() { return TILE_SIZE; }

    public void movePlayerUp() { player.moveUp(currentMap); }
    public void movePlayerDown() { player.moveDown(currentMap); }
    public void movePlayerLeft() { player.moveLeft(currentMap); }
    public void movePlayerRight() { player.moveRight(currentMap); }

}
