package entities;

public class Player {
    private int x;
    private int y;
    private int hp;

    public Player(int x , int y , int hp){
        this.x = x;
        this.y = y;
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
