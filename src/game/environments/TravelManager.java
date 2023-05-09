package game.environments;

import edu.monash.fit2099.engine.positions.Location;

import java.util.HashMap;

public class TravelManager {
    HashMap<String, Location> lostGraces;

    private static TravelManager instance;

    private TravelManager(){
        lostGraces = new HashMap<>();
    }

    public static TravelManager getInstance(){
        if (instance == null){
            TravelManager.instance = new TravelManager();
        }
        return TravelManager.instance;
    }


    public void addLostGrace(Location location, String name){
        this.lostGraces.put(name, location);

    }
}
