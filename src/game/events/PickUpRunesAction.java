package game.events;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.runesmanager.RunesManager;

/**
 * Action to pick up Runes from the ground.
 * @author Natalie Chan
 */
public class PickUpRunesAction extends PickUpAction {
    private int runesValue;
    private Item item;

    /**
     * Constructor.
     * @param runes object to be picked up
     * @param value value of runes
     */
    public PickUpRunesAction(Item runes, int value){
        super(runes);
        this.runesValue = value;
        this.item = runes;
    }

    /**
     * Adds runes value to player runes then removes it from map.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result of action
     */
    public String execute(Actor actor, GameMap map){
        RunesManager.getInstance().getRunes(actor).addRunes(this.runesValue);
        super.execute(actor, map);
        return actor + " retrieves Runes (value: "+ this.runesValue+")";
    }



}
