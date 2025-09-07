package model.entities;

public class Player extends Entity{
    public Player(int x , int y , int hpMax, int attack){
        super(x,y,hpMax,attack);
    }

    public void moveUp(char[][] map) {
        if (map[y - 1][x] != '#') {
            y--;
        }
    }

    public void moveDown(char[][] map) {
        if (map[y + 1][x] != '#') {
            y++;
        }
    }

    public void moveLeft(char[][] map) {
        if (map[y][x - 1] != '#') {
            x--;
        }
    }

    public void moveRight(char[][] map) {
        if (map[y][x + 1] != '#') {
            x++;
        }
    }
}
