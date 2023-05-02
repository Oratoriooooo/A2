package game.events;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An action executed if the actor can be despawned.
 * Created by:
 * @author Vicky Huang
 * Modified by:
 *
 */
public class DespawnAction extends Action {
    /**
     * Removes the actor from the map, effectively 'despawning' it.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the despawn action to be displayed in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // remove the actor from the map
        map.removeActor(actor);
        // return the result
        return actor + " is despawned.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}

