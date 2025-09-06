package map;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.min;

public class Map {
    private int height, width;

    //stocke le centre de chaque salle
    ArrayList<int[]> roomCenters = new ArrayList<>();
    boolean [][] visited;
    private char[][] map;

    public Map(int height, int width) {
        map = new char[height][width];
        visited = new boolean[height][width];
        //visited sert pour le floodFill il represente les cases de la carte et verifie si elles ont été visitées

        this.height = height;
        this.width = width;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = '#';
                visited[i][j] = false;
            }
        }
    }

    public ArrayList<int[]> getRoomCenters() {
        return roomCenters;
    }

    public void roomGen(int roomNumber , int radius){
        // le but de cette fonction est de generer $roomNumber sols dans une map a des endroits random
        // séparé de radius cases

        final int maxIterationNumber = 10;

        Random rand = new Random();

        boolean possibleToPlace = true;
        int roomPlacedNumber = 0;
        int iterationNumber = 0;

        while(roomPlacedNumber!=roomNumber && iterationNumber <= maxIterationNumber) {
            int possibleRoomX = rand.nextInt(1,height-1 );
            int possibleRoomY = rand.nextInt(1 ,width-1 );

            //verifier le radius pour voir si il y a une salle autour
            for (int x = possibleRoomX-radius; x <= possibleRoomX+radius; x++) {
                for (int y = possibleRoomY-radius; y <= possibleRoomY+radius; y++) {
                    //on verifie que la pos est dans les bounds
                    if (x >= 0 && x < height && y >= 0 && y < width ) {
                        //si on trouve une salle , on annule et on retourne au debut de la boucle
                        if (map[x][y] == '+' || map[x][y] == '.') {
                            possibleToPlace = false;
                        }
                    }
                }
            }

            if (possibleToPlace) {
                map[possibleRoomX][possibleRoomY] = '+';
                roomCenters.add(new int[]{possibleRoomX, possibleRoomY});
                roomPlacedNumber++;
            }

            possibleToPlace = true;
            iterationNumber ++;
        }
    }

    public void roomFill(int radius ,int percentage){
        //sert a agrandir des salles d'un radius en plus , a utiliser avev roomGen pour gen des salles
        // percentage = pourcentage de pas generer de sol , plus c'est gros plus les salles seront etranges

        // but : parcourir la map et enregistrer les co des sols pour ensuite repasser
        // dessus et les agrandir

        Random rand = new Random();
        //pour faire des salles qui ne se ressemble pas

        for (int i = 0; i < roomCenters.size(); i++) {
            for (int x = roomCenters.get(i)[0]- radius; x <= roomCenters.get(i)[0]+radius; x++) {
                for (int y = roomCenters.get(i)[1]-radius; y <= roomCenters.get(i)[1]+radius; y++) {
                    //on verifie que la pos est dans les bounds
                    if (x > 0 && x < height - 1 && y > 0 && y < width -1 && !(x == roomCenters.get(i)[0] && y == roomCenters.get(i)[1])) {
                        if (rand.nextInt(0,100) >= percentage) {
                            map[x][y] = '.';
                        }
                    }
                }
            }
        }
    }

    private void floodVerify(int x , int y){
        //but : faire une fonction recursive qui s'appelle sur les autres autour , comme une inondation qui se repend
        // on change le tab visited en fonction des cases qu'on visite , celle qui sont pas visité sont inacessible
        if ((map[x][y] == '.' || map[x][y] == '+') && !visited[x][y] ) {
            visited[x][y] = true;
            floodVerify(x-1,y);
            floodVerify(x,y-1);
            floodVerify(x+1,y);
            floodVerify(x,y+1);
        }
    }

    public void floodFill(int x , int y){
        //fonction qui utilise la fct floodVerify pour enlever les sols inacessible
        floodVerify(x,y);

        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (!visited[i][j]) {
                    map[i][j] = '#';
                }
            }
        }

    }

    public void makeCorridor(){
        for(int i = 0 ; i < roomCenters.size() -1; i++){
            int x1 = roomCenters.get(i)[0];
            int y1 = roomCenters.get(i)[1];
            int x2 = roomCenters.get(i+1)[0];
            int y2 = roomCenters.get(i+1)[1];

            for(int x = Math.min(x1,x2); x < Math.max(x1,x2); x++){
                map[x][y1]= '.';
            }

            for(int y = Math.min(y1,y2); y < Math.max(y1,y2); y++){
                map[x2][y]= '.';
            }

        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if (visited[i][j]) {
                    str += '~';
                }
                else {
                    str += map[i][j];
                }

            }
            str += "\n";
        }
        return str;
    }


}
