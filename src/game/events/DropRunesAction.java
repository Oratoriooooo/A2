package game.events;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Runes;

/**
 * Created by:
 * @author Natalie Chan
 * Special action that allows actor to drop Runes.
 */
public class DropRunesAction extends DropAction {
    /**
     * Runes being dropped
     */
    Runes runes;

    /**
     * Constructor.
     * @param runes Runes being dropped
     */
    public DropRunesAction(Runes runes){
        super(runes);
        this.runes = runes;

    }

    /**
     * Drops Runes onto the game map after saving the location where the actor died
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return dropped action result message
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location droppedLocation;
        droppedLocation = map.locationOf(actor);
        this.runes.setLocation(droppedLocation);
        actor.removeItemFromInventory(this.runes);
        super.execute(actor, map);
        return actor + " dropped " + this.runes.getRunesValue() + " runes";
    }
}
