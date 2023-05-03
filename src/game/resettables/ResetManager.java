package game.resettables;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class ResetManager {
    private Map<Actor, Resettable> resettables = new HashMap<>();

    private List<Resettable> items = new ArrayList<>();

    private static ResetManager instance;

    /**
     * HINT 1: where have we seen a private constructor before?
     * HINT 2: see the instance attribute above.
     */
    private ResetManager() {

    }

    /**
     * Gets only one instance of ResetManager from the instance attribute.
     * If there is no instance, create new instance and assign it to the attribute
     * @return one instance of ResetManager
     */
    public static ResetManager getInstance(){
        if(instance == null){
            instance = new ResetManager();
        }
        return instance;

    }


    /**
     * Calls the reset method of every resettable.
     */
    public void run() {
        for(Map.Entry<Actor, Resettable> entry: this.resettables.entrySet()){
            this.resettables.get(entry.getKey()).reset();
        }
        for (Resettable item: this.items){
            item.reset();
        }

    }

    /**
     * Registers resettable entity
     * @param actor actor key
     * @param resettable resettable actor value
     */
    public void registerResettable(Actor actor,Resettable resettable) {
        this.resettables.put(actor, resettable);
    }

    /**
     * Register resettable item
     * @param resettable item that can be reset
     */
    public void registerResettableItem(Resettable resettable) {
        this.items.add(resettable);
    }

    /**
     * removes an actor and its corresponding  resettable value
     * @param actor entity to be removed
     */
    public void removeResettable(Actor actor) {
        this.resettables.remove(actor);
    }

    /**
     * removes resettable item from list
     * @param item item to be removed
     */
    public void removeItem(Resettable item){
        this.items.remove(item);
    }


}
