package game.resettables;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class ResetManager {
    private List<Resettable> resettables;
    private static ResetManager instance;

    /**
     * HINT 1: where have we seen a private constructor before?
     * HINT 2: see the instance attribute above.
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
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

    public void run() {
        for(Resettable resettable: this.resettables){
            resettable.reset();
        }
    }

    public void registerResettable(Resettable resettable) {
        this.resettables.add(resettable);
    }

    public void removeResettable(Resettable resettable) {
        this.resettables.remove(resettable);
    }
}
