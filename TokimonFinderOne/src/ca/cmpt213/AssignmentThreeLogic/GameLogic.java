package ca.cmpt213.AssignmentThreeLogic;
import ca.cmpt213.AssignmentThreeUI.UI;

//logic base of the game, will work regardless of UI
public class GameLogic {
    final private Map map;

    public GameLogic(Map m){
        map = m;
        initialPos();
    }

    private void initialPos(){
        UI.startScreen(map);
        int[] num = UI.choosePos();
        movePos(num[0], num[1]);
    }


    //moves the player based on the input coordinate
    public void movePos(int y, int x){
        if(map.getCurrent() != null){
            if(map.getLayout()[map.getCurrent().getY()][map.getCurrent().getX()].getVal() == 0){
                map.getLayout()[map.getCurrent().getY()][map.getCurrent().getX()].setVal(1);
            }
        }
        map.getLayout()[x][y].setVisited(true);
        map.getLayout()[x][y].setShow(true);
        map.setCurrent(map.getLayout()[x][y]);

        if(map.getLayout()[x][y].getVal() == 0){
            //visit empty
            map.getEmptyVisited().add(map.getLayout()[x][y]); //add the new update coordinate to emptyvisited array
        }
        else if(map.getLayout()[x][y].getVal() == 1){
            //already visited
        }
        else if(map.getLayout()[x][y].getVal() == 2){
            //visit toki
            for(int a=0; a<map.getTokimon().size(); a++){
                if(map.getTokimon().get(a) == map.getLayout()[x][y]){
                    map.getTokimon().remove(a);
                }
            }
            UI.gotToki(map);
            return;
        }
        else if(map.getLayout()[x][y].getVal() == 3){
            //got Fokimon
            UI.gotFoki(map);
            System.exit(-1);
        }
        UI.printMap(map);
    }

    public Map getMap(){
        return map;
    }

}
