package game.events.attacking;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.Status;
import game.events.death.DespawnAction;

/**
 * An action executed if the actor can conduct a slam area attack.
 *
 * @author Vicky Huang
 * Modified by:
 *
 */
public class SlamAreaAttackAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Constructor with intrinsic weapon as default
     *
     * @param target the actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public SlamAreaAttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * 	 When executed, the actor's surrounding locations are checked for other actors. If there
     * 	 are other actors, create an AttackAction for it and execute it.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the action to be displayed in the UI.
     * @see AttackAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // for each surrounding actor, create an attack action and execute it
        String result = "---------------------------------------------\n";
        result += actor + " conducts a slam area attack where: \n";
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            Actor otherActor = destination.getActor();
            // if there is another actor, attack them with the attack action
            if (otherActor != null) {
                // check if the other actor is breakable and if it is, then break them
                if (otherActor.hasCapability(Status.BREAKABLE)) {
                    Action despawnAction = new DespawnAction();
                    result = actor + " breaks the " + target + ".\n";
                    despawnAction.execute(target, map);
                }
                // if the actor is not breakable, then do an AttackAction
                else {
                    Action attack = new AttackAction(otherActor, exit.getName());
                    // execute the attack
                    result += attack.execute(actor, map) + "\n";
                }
            }
        }
        result += "---------------------------------------------";
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}

