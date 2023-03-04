import java.awt.Color;
import java.util.*;

public class Player {
    private String name;
    private Color color;
    private int victoryPoints;
    private int settlements;
    private int cities;
    public HashMap<String, Integer> resources;
    //private ArrayList<DevCard> hand;
    private ArrayList<Road> roads;
    private int numbSettlements;
    private static final int victoryPointsForWinner = 2;
    private int numbRoads;
    private int numbCities;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.victoryPoints = 0;
        this.settlements = 0;
        this.cities = 0;
        this.numbCities = 0;
        this.numbRoads = 2;
        this.numbSettlements = 2;
        roads = new ArrayList<Road>();
        resources = new HashMap<String, Integer>(5);
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public int getVictoryPoints() {
        return this.victoryPoints;
    }

    public int getSettlements() {
        return this.settlements;
    }

    public void setSettlements(int settlements) {
        this.settlements = settlements;
    }

    public int getCities() {
        return this.cities;
    }

    public void setCities(int cities) {
        this.cities = cities;
    }

    public HashMap<String, Integer> getResources() {
        return this.resources;
    }

    public void setResources(HashMap<String, Integer> resources) {
        this.resources = resources;
    }

    public void addResource(String s) {
        this.resources.put(s, this.resources.get(s));
    }

    public String getName() {
        return this.name;
    }

    public void addRode(int start, int end){
        Road road = new Road(start, end);
        roads.add(road);
    }
}