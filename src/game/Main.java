package game;

import map.Map;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Map map = new Map(40,60);

        map.roomGen(13,6);
        System.out.println(map.toString());

        map.roomFill(2,10);
        System.out.println(map.toString());
    }
}
