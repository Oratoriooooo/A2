package game.events;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.entities.enemies.Skeletons.HeavySkeletalSwordsman;

/**
 * An action executed to spawn a heavy skeletal swordsman.
 * Created by:
 * @author Vicky Huang
 * Modified by:
 *
 */
public class RespawnPileOfBonesAction extends Action {
    /**
     * Integer representing the x coordinate to spawn the enemy
     */
    int x;
    /**
     * Integer representing the y coordinate to spawn the enemy
     */
    int y;
    /**
     * Actor representing the actor to be respawned.
     */
    Actor respawnActor;

    /**
     * Constructor
     *
     * @param x the x coordinate to spawn the enemy
     * @param y the y coordinate to spawn the enemy
     */

    public RespawnPileOfBonesAction(int x, int y, Actor respawnActor) {
        this.x = x;
        this.y = y;
        this.respawnActor = respawnActor;
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
//        Actor otherActor = new HeavySkeletalSwordsman();
        map.at(x, y).addActor(this.respawnActor);
        this.respawnActor.heal(1000);
        String result = this.respawnActor + " has been spawned.";
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}

