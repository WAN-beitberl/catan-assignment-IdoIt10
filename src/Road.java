import java.util.ArrayList;

public class Road {
    private int start;
    private int end;

    private static ArrayList<Road> allRoads;
    public Road(int start, int end){
        this.start = start;
        this.end = end;
        ArrayList<Road> road = new ArrayList<Road>();
        addRoad();
    }

    protected void addRoad(){
        Road road = new Road(this.start, this.end);
        allRoads.add(road);
    }
}
