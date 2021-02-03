package ca.cmpt213.AssignmentThreeUI;
import ca.cmpt213.AssignmentThreeLogic.Coordinate;
import ca.cmpt213.AssignmentThreeLogic.Map;
import java.util.Scanner;

//the main UI for the game
public class UI {

    //startScreen of the game
    public static void startScreen(Map map){
        System.out.println("*********Tokimon Finder*********");
        UI.printMap(map);
    }

    //prints the main UI map
    public static void printMap(Map map){
        System.out.printf(" ");
        for(int a=1; a<11; a++){
            System.out.printf( "  " + a);
        }
        System.out.println();

        int i = 0;
        for(int a=0; a<10; a++){
            System.out.printf((char)('A' + i) + "");
            i += 'B' - 'A';
            for(int b=0; b<10; b++){
                System.out.printf(coordinateStringVal(map.getLayout()[a][b], map.getCurrent()));
            }
            System.out.println();
        }
        System.out.println("You have caught: " + (map.getTotalTokimon() - map.getTokimon().size()) + "/" + map.getTotalTokimon() + " Tokimon");
        System.out.println("You have " + map.getSpells() + " remaining spells");
    }

    //sets up the map so when the game ends, the player can see all tokimon and fokimon positions
    public static void finalMap(Map map){
        map.setCurrent(new Coordinate(-1,-1,-1, false));
        for(int a=0; a<10; a++){
            for(int b=0; b<10; b++){
                map.getLayout()[a][b].setShow(true);
            }
        }
        printMap(map);
    }

    //returns the coordinate symbol based on whats assigned to it
    public static String coordinateStringVal(Coordinate coordinate, Coordinate current){
        if(coordinate == current){
            return "  @";
        }
        if(coordinate.getShow() == false){
            if(coordinate.getVisited() == false){
                return "  ~";
            }
            else {
                return "   ";
            }
        }
        if(coordinate.getShow() == true){
            if(coordinate.getVal() == 0){
                return "  ~";
            }
            if(coordinate.getVal() == 1){
                return "   ";
            }
            if(coordinate.getVal() == 2){
                return "  $";
            }
            if(coordinate.getVal() == 3){
                return "  X";
            }
        }
        return "";
    }


    //checker to make sure the user input when choosing a coordinate is valid
    public static int[] choosePos(){
        Scanner in = new Scanner(System.in);

        System.out.println("Enter position coordinate (number)");
        int x = in.nextInt();
        in.nextLine();
        while(x<1 || x>10){
            System.out.println("Enter position coordinate between 1 and 10");
            x = in.nextInt();
        }

        System.out.println("Enter position coordinate (capital letter)");
        char ytemp = in.next().charAt(0);
        int y = (int)(ytemp - 'A');
        while(y<0 || y>9){
            System.out.println("Enter position coordinate between A and Z");
            ytemp = in.next().charAt(0);
            y = (int)(ytemp - 'A');
        }
        int[] val = new int [2];
        val[0] = x-1;
        val[1] = y;
        return val;
    }

    //menu for when a tokimon is captured
    public static void gotToki(Map map){
        if(map.getTokimon().size() == 0){
            UI.finalMap(map);
            System.out.println("Congratulations you caught all the Tokimon");
            System.out.println("Game Over - you won");
            System.exit(1);
        }
        UI.printMap(map);
        System.out.println("Congratulations you caught a Tokimon");
    }

    //menu for when a fokimon is captured
    public static void gotFoki(Map map){
        UI.finalMap(map);
        System.out.println("You landed on a Fokimon");
        System.out.println("Game Over - you lost");
        System.exit(1);
    }
}
