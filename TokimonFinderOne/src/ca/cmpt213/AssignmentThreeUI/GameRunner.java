package ca.cmpt213.AssignmentThreeUI;
import ca.cmpt213.AssignmentThreeLogic.GameLogic;
import ca.cmpt213.AssignmentThreeLogic.Map;

import java.util.Scanner;
import java.lang.*;

//runs the game on the basis of systen.in and sout
//is the class that runs the game using the UI and gamelogic
public class GameRunner {

    private GameLogic gameLogic;

    public GameRunner(String[] args){
        startGame(args);
        while(true){
            midGame();
        }
    }

    //makes sure the arguments recieved from main are valid
    private void startGame(String[] args){
        int toki = -99999;
        int foki = -99999;
        boolean cheat = false;

        if(args.length>3){
            System.out.println("Too many arguments");
            System.exit(-1);
        }

        if(args.length == 0){
            System.out.println("Default mode");
            System.out.println("Toki: " + 10 + " Foki: " + 5 + " Cheat: " + false);
            gameLogic = new GameLogic(new Map(10, 5, false));
            return;
        }
        else{
            for(int a=0; a<args.length; a++){
                if(args[a].equalsIgnoreCase("--cheat")){
                    cheat = true;
                }
                else if(args[a].substring(0,9).equalsIgnoreCase("--numtoki")){
                    if(toki != -99999){
                        System.out.println("Repeated tokimon value");
                        System.exit(-1);
                    }
                    toki = Integer.parseInt(args[a].substring(10));
                }
                else if(args[a].substring(0,9).equalsIgnoreCase("--numfoki")){
                    if(foki != -99999){
                        System.out.println("Repeated fokimon value");
                        System.exit(-1);
                    }
                    foki = Integer.parseInt(args[a].substring(10));
                }
                else{
                    System.out.println("Input error");
                    System.exit(-1);
                }
            }
        }

        if(toki == -99999){
            toki = 10;
        }
        if(foki == -99999){
            foki = 5;
        }

        if(toki<5 || foki<5 || (toki + foki)>100){
            System.out.println("Error with input values");
            System.exit(-1);
        }

        System.out.println("Toki: " + toki + " Foki: " + foki + " Cheat: " + cheat);
        gameLogic = new GameLogic(new Map(toki, foki, cheat));
    }

    //makes sure you the player can't walk past game grid, makes sure only correct inputs are input
    private void midGameMove(){
        boolean check = false;
        Scanner in = new Scanner(System.in);
        System.out.println("enter WASD to move character");
        char move = move = in.nextLine().charAt(0);
        if(move == 'W' || move == 'w' || move == 'A' || move == 'a' || move == 'S' || move == 's' || move == 'D' || move == 'd'){
            check = true;
        }

        while(!check){
            if(move == 'W' || move == 'w' || move == 'A' || move == 'a' || move == 'S' || move == 's' || move == 'D' || move == 'd'){
                check = true;
            }
            System.out.println("Please enter WASD only");
            move = in.next().charAt(0);
        }

        while(true){
            if(move == 'W' || move == 'w'){
                if(gameLogic.getMap().getCurrent().getY() != 0){
                    gameLogic.movePos(gameLogic.getMap().getCurrent().getX(), gameLogic.getMap().getCurrent().getY()-1);
                    return;
                }
                if(gameLogic.getMap().getCurrent().getY() == 0){
                    while(true){
                        System.out.println("Cant move up, move another direction");
                        move = in.nextLine().charAt(0);
                        if(move != 'W' && move != 'w'){
                            break;
                        }
                        if(move != 'w'){
                            break;
                        }
                    }
                }
            }

            if(move == 'A' || move == 'a'){
                if(gameLogic.getMap().getCurrent().getX() != 0){
                    gameLogic.movePos(gameLogic.getMap().getCurrent().getX()-1, gameLogic.getMap().getCurrent().getY());
                    return;
                }
                if(gameLogic.getMap().getCurrent().getX() == 0){
                    while(true){
                        System.out.println("Cant move left, move another direction");
                        move = in.nextLine().charAt(0);
                        if(move != 'A' && move != 'a'){
                            break;
                        }
                        if(move != 'a'){
                            break;
                        }
                    }
                }
            }

            if(move == 'S' || move == 's'){
                if(gameLogic.getMap().getCurrent().getY() != 9){
                    gameLogic.movePos(gameLogic.getMap().getCurrent().getX(), gameLogic.getMap().getCurrent().getY()+1);
                    return;
                }
                if(gameLogic.getMap().getCurrent().getY() == 9){
                    while(true){
                        System.out.println("Cant move down, move another direction");
                        move = in.nextLine().charAt(0);
                        if(move != 'S' && move != 's'){
                            break;
                        }
                        if(move != 's'){
                            break;
                        }
                    }
                }
            }

            if(move == 'D' || move == 'd'){
                if(gameLogic.getMap().getCurrent().getX() != 9){
                    gameLogic.movePos(gameLogic.getMap().getCurrent().getX()+1, gameLogic.getMap().getCurrent().getY());
                    return;
                }
                if(gameLogic.getMap().getCurrent().getX() == 9){
                    while(true){
                        System.out.println("Cant move right, move another direction");
                        move = in.nextLine().charAt(0);
                        if(move != 'D' && move != 'd'){
                            break;
                        }
                        if(move != 'd'){
                            break;
                        }
                    }
                }
            }
        }
    }

    //lets user use spells, lets them teleport, shows tokimon, ans whos fokimon
    public void midGameSpell(){
        if(gameLogic.getMap().getSpells() == 0 ){
            System.out.println("You have used all your spells");
            return;
        }
        System.out.println("Enter option number");
        System.out.println("1. Jump to another grid location");
        System.out.println("2. Randomly reveal one Tokimon (you still have to collect)");
        System.out.println("3. Randomly reveal one Fokimon (can still kill you)");
        Scanner in = new Scanner(System.in);
        int option = in.nextInt();
        while(option<1 || option>3){
            System.out.println("Enter options 1, 2, or 3");
            option = in.nextInt();
        }
        if(option == 1){
            int[] num = UI.choosePos();
            gameLogic.getMap().decSpells();
            gameLogic.movePos(num[0],num[1]);
        }
        int count = 0;
        if(option == 2){
            //gotta make another boolean show in coordinate, if show is enabled then while printing normal map show it
            for(int a=0; a<gameLogic.getMap().getTokimon().size(); a++){
                if(!gameLogic.getMap().getTokimon().get(a).getShow()){
                    gameLogic.getMap().getTokimon().get(a).setShow(true);
                    break;
                }
                count++;
            }
            if(count == gameLogic.getMap().getTokimon().size()){
                System.out.println("All tokimon already revealed");
                return;
            }
            gameLogic.getMap().decSpells();
            UI.printMap(gameLogic.getMap());
        }
        count = 0;
        if(option == 3){
            for(int a=0; a<gameLogic.getMap().getFokimon().size(); a++){
                if(!gameLogic.getMap().getFokimon().get(a).getShow()){
                    gameLogic.getMap().getFokimon().get(a).setShow(true);
                    break;
                }
                count++;
            }
            if(count == gameLogic.getMap().getFokimon().size()){
                System.out.println("All fokimon already revealed");
                return;
            }
            gameLogic.getMap().decSpells();
            UI.printMap(gameLogic.getMap());
        }
    }

    //runs the midgame options
    private void midGame(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter option number");
        System.out.println("1. Move (WASD)");
        System.out.println("2. Use spell");
        int option = in.nextInt();
        while(option < 1 || option > 2){
            System.out.println("Enter option 1 or 2");
            option = in.nextInt();
        }
        if(option == 1){
            midGameMove();
        }
        if(option == 2){
           midGameSpell();
        }
    }
}
