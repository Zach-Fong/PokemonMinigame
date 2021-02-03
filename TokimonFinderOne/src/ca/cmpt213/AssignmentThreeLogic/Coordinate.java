package ca.cmpt213.AssignmentThreeLogic;

//contains all the values of a coordinate: its position, if its been visisted, if it should be shown, its value
public class Coordinate {
    private boolean visited;
    private boolean show;
    private int x;
    private int y;
    private int val;

    public Coordinate(int x, int y, int val, boolean s){
        visited = false;
        show = s;
        this.x = x;
        this.y = y;
        this.val = val;
    }

    public boolean getVisited(){
        return visited;
    }

    public boolean getShow(){
        return show;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getVal(){
        return val;
    }

    public void setVal(int val){
        this.val = val;
    }

    public void setVisited(boolean visited){
        this.visited = visited;
    }

    public void setShow(boolean show){
        this.show = show;
    }
}