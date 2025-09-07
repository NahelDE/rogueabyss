package model.entities;

public abstract class Entity {
    private int x;
    private int y;
    private int hp;

    public Entity(int x , int y , int hp){
        this.x = x;
        this.y = y;
        this.hp = hp;
    }

    public int getHp() {return hp;}
    public void setHp(int hp) {this.hp = hp;}
    public int getX() {return x;}
    public int getY() {return y;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}

    public abstract void moveUp(char[][] map);
    public abstract void moveDown(char[][] map);
    public abstract void moveLeft(char[][] map);
    public abstract void moveRight(char[][] map);
}
