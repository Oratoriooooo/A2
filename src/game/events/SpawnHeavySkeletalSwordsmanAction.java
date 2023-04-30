package game.events;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.entities.enemies.HeavySkeletalSwordsman;

/**
 * An action executed to spawn a heavy skeletal swordsman.
 * Created by:
 * @author Vicky Huang
 * Modified by:
 *
 */
public class SpawnHeavySkeletalSwordsmanAction extends Action {
    /**
     * Integer representing the x coordinate to spawn the enemy
     */
    int x;
    /**
     * Integer representing the y coordinate to spawn the enemy
     */
    int y;

    /**
     * Constructor
     *
     * @param x the x coordinate to spawn the enemy
     * @param y the y coordinate to spawn the enemy
     */

    public SpawnHeavySkeletalSwordsmanAction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Adds a HeavySkeletalSwordsman to the map
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the action to be displayed in the UI
     * @see HeavySkeletalSwordsman
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Actor otherActor = new HeavySkeletalSwordsman();
        map.at(x, y).addActor(otherActor);
        String result = otherActor + " has been spawned.";
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}

