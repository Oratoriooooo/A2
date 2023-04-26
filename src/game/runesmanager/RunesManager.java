package game.runesmanager;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.Runes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * A runes manager that manages all items or actors that can manipulate runes
 * Created by:
 * @author Natalie Chan
 */
public class RunesManager{
    /**
     *
     */
    private HashMap<Actor, GenerateRunes> canGenerate;
    /**
     *
     */
    private HashMap<Actor,Runes> runes;
    /**
     *
     */
    private static RunesManager instance;

    private final int MAX_RUNE_OBJECTS = 2;

    private List<GenerateRunes> lis = new ArrayList<>();

    /**
     * Constructor
     */
    private RunesManager(){
        this.canGenerate = new HashMap<>();
        this.runes = new HashMap<>();
    }

    /**
     * Gets an actor and generates runes from it
     * @param actor to generate runes from
     * @return value of the generated runes
     */
    public int generateRunes(Actor actor){
        return this.canGenerate.get(actor).generateRunes();
    }

    /**
     * Gets only one instance of RuneManager from instance attribute.
     * If there is no instance, create new instance and assign it to the attribute
     * @return one instance of RuneManager
     */
    static public RunesManager getInstance(){
        if (RunesManager.instance == null){
            RunesManager.instance = new RunesManager();
        }
        return RunesManager.instance;
    }

    /**
     * Add actor as a GenerateRunes-Actor key set into the canGenerate runes HashMap
     * @param creator an actor that can generate runes
     * @param actor
     */
    public void registerActor(GenerateRunes creator, Actor actor) {
        this.instance.canGenerate.put(actor, creator);

    }

    /**
     * add Runes object to be managed into key sets with any possible owners.
     *
     * If there are more runes objects on the map than allowed, remove the already dropped Runes.
     * @param actor Owner of Runes, can be no-one if dropped
     * @param runes runes object
     */
    public void registerRunes(Actor actor,Runes runes){
        // if player dies and there's already runes on the ground
        if (this.runes.size() >=this.MAX_RUNE_OBJECTS) {
            this.runes.remove(null);

        }
        this.runes.put(actor, runes);
    }

    /**
     * removes a Actor-Runes key value set from the runes Hashmap
     * @param runes identifier for actor-runes key value set
     */
    public void removeRunes(Runes runes){
        this.runes.remove(runes);
    }

    /**
     * Gets the collection of runes that belongs to an actor
     * @param actor an actor that
     * @return the runes of an actor
     */
    public Runes getRunes(Actor actor) {
        Runes runes = this.runes.get(actor);
        if (runes == null){
            System.out.println(actor  + " does not own any runes");
        }

        return runes;
    }

    /**
     * removes Actor-GenerateRunes key value set from canGenerate Hashmap
     * @param actor actor to be removed
     */
    public void removeActor(Actor actor) {
        this.canGenerate.remove(actor);
    }


}
