package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.entities.enemies.EnemyType;
import game.events.SlamAreaAttackAction;

import java.util.Random;

/**
 * A class that checks if the actor is close to another type of actor it can attack and returns a
 * SlamAreaAttackAction if the chance is more than 50%.
 *
 * Created by: Vicky Huang
 * @author Vicky Huang
 * Modified by:
 *
 */
public class SlamAreaAttackBehaviour implements Behaviour {
    /**
     * Random number generator
     */
    private Random rand = new Random();
    /**
     * Integer representing the chance that the actor does a slam area attack
     */
    private int chanceToHit = 50;

    /**
     * Returns a SlamAreaAttackAction if the actor can attack another actor in its vicinity while
     * considering a 50% chance of attack.
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return SlamAreaAttackAction if the actor can conduct a slam area attack, null otherwise
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!(rand.nextInt(100) < chanceToHit)) {
            // the actor will not do a slam area attack so return null
            return null;
        }
        // if the random number is greater than the chance of attacking, we will check the surrounding
        // actors and if there is an actor that can be attacked, we return a SlamAreaAttackAction
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            Actor otherActor = destination.getActor();
            Boolean canAttack = false;
            if (otherActor != null) {
                // Check if the actor can attack the other actor by checking to see if their capability list
                // for the EnemyType enums class is different
                if (!actor.findCapabilitiesByType(EnemyType.class).equals(otherActor.findCapabilitiesByType(EnemyType.class))) {
                    canAttack = true;
                }
                if (canAttack) {
                    return new SlamAreaAttackAction(otherActor, exit.getName());
                }
            }
        }
        return null;
    }
}

