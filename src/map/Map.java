package map;

import java.util.ArrayList;
import java.util.Random;

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

    public void floodFill(int x , int y){
        //but : faire une fonction recursive qui s'appelle sur les autres autour , comme une inondation qui se repend
        // on change le tab visited en fonction des cases qu'on visite , celle qui sont pas visité sont inacessible
        if (map[x][y] == '.' || map[x][y] == '+') {
            visited[x][y] = true;
            floodFill(x-1,y);
            floodFill(x,y-1);
            floodFill(x+1,y);
            floodFill(x,y+1);
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                str += map[i][j];
                if (visited[i][j]) {
                    map[i][j]='V';
                }

            }
            str += "\n";
        }
        return str;
    }
}
