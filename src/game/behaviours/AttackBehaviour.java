package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.entities.enemies.EnemyType;
import game.events.AttackAction;

/**
 * A class that figures out if the actor is close to another type of actor it can attack.
 * If it is, it will return an AttackAction.
 *
 * Created by:
 * @author Vicky Huang
 * Modified by:
 *
 */

public class AttackBehaviour implements Behaviour {
    /**
     * Returns an AttackAction if the actor is able to attack the other actor, null if not possible
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an Action or null if no AttackAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        // get exits and get location of exits and then check if the destination contains an
        // actor and if it does, then let it attack it
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
                    // if the actor can attack and has a weapon, we will get the weapon's special skill which is an action
                    if (actor.getWeaponInventory().size() != 0) {
                        return actor.getWeaponInventory().get(0).getSkill(otherActor, exit.getName());
                    }
                    else {
                        return new AttackAction(otherActor, exit.getName());
                    }
                }

            }
        }

        // if there are no actors that can be attacked surrounding the actor, return null //weapon
        return null;
    }
}

