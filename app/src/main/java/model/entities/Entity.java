package model.entities;

public abstract class Entity {
    protected int x;
    protected int y;
    protected int hpMax;
    protected int hp;
    protected int attack;
    protected boolean isAlive;

    public Entity(int x , int y , int hp,int attack){
        this.x = x;
        this.y = y;
        this.hpMax = hp;
        this.hp = hp;
        this.attack=attack;
        isAlive=true;
    }

    public int getHp() {return hp;}
    public void setHp(int hp) {this.hp = hp;}
    public int getHpMax() {return hpMax;}
    public void setHpMax(int hpMax) {this.hpMax = hpMax;}
    public int getX() {return x;}
    public int getY() {return y;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public int getAttack(){return attack;}
    public void setAttack(int attack){this.attack = attack;}
    public boolean isAlive(){return isAlive;}
    public void setAlive(boolean alive){isAlive = alive;}

    public abstract void moveUp(char[][] map);
    public abstract void moveDown(char[][] map);
    public abstract void moveLeft(char[][] map);
    public abstract void moveRight(char[][] map);

    public void attack(Entity target){
        target.setHp(target.getHp()-this.attack);

        if (target.getHp() <= 0){
            target.setAlive(false);
        }
    }
}
