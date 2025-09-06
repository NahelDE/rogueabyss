package game;

import map.Map;

public class Main {
    public static void main(String[] args) {

        Map map = new Map(40,60);

        map.roomGen(10,5);
        map.roomFill(2,10);
        map.makeCorridor();

        System.out.println(map.toString());

        map.floodFill(map.getRoomCenters().get(0)[0],map.getRoomCenters().get(0)[1]);

        System.out.println(map.toString());
    }
}
