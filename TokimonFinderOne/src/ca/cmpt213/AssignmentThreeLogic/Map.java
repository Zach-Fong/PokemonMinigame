package ca.cmpt213.AssignmentThreeLogic;
import java.util.ArrayList;
import java.util.List;

//keeps track of the tokimon grid values
public class Map {

    final private List<Coordinate> emptyVisited; //1
    final private List<Coordinate> tokimon; //2
    final private List<Coordinate> fokimon; //3
    private Coordinate current; //4
    final private Coordinate[][] layout;
    private int totalTokimon;
    private int spells;

    //sets up the map with the amount of tokimon, fokimon, and if cheat is enabled
    public Map(int toki, int foki, boolean cheat){
        emptyVisited = new ArrayList<Coordinate>();
        tokimon = new ArrayList<Coordinate>();
        fokimon = new ArrayList<Coordinate>();
        current = null;
        layout = new Coordinate[10][10];
        totalTokimon = toki;
        spells = 20;

        for(int a=0; a<10; a++){
            for(int b=0; b<10; b++){
                layout[a][b] = new Coordinate(b,a,0, cheat);
            }
        }

        int count=0;
        while(count<toki){
            int x = (int) (Math.random()*10);
            int y = (int) (Math.random()*10);
            if(layout[x][y].getVal() == 0){
                layout[x][y].setVal(2);
                tokimon.add(layout[x][y]);
                count++;
            }
        }
        count = 0;

        while(count<foki){
            int x = (int) (Math.random()*10);
            int y = (int) (Math.random()*10);
            if(layout[x][y].getVal() == 0){
                layout[x][y].setVal(3);
                fokimon.add(layout[x][y]);
                count++;
            }
        }
    }

    public Coordinate[][] getLayout(){
        return layout;
    }

    public List<Coordinate> getEmptyVisited(){
        return emptyVisited;
    }

    public List<Coordinate> getTokimon(){
        return tokimon;
    }

    public List<Coordinate> getFokimon(){
        return fokimon;
    }

    public Coordinate getCurrent(){
        return current;
    }

    public void setCurrent(Coordinate current){
        this.current = current;
    }

    public int getTotalTokimon(){
        return totalTokimon;
    }

    public int getSpells(){
        return spells;
    }

    public void decSpells(){
        spells--;
    }

}
