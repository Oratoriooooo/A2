package game.events.death;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.behaviours.Status;
import game.events.ResetAction;
import game.resettables.ResetManager;
import game.runesmanager.RunesManager;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Natalie Chan
 *
 */
public class DeathAction extends Action {
    private Actor attacker;

    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the items and weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";
        String reset = "";
        String dropMessage = "";

        ActionList dropActions = new ActionList();
        // drop all items
        if (!target.hasCapability(Status.RETAIN_ITEMS_AND_WEAPONS)) {
            for (Item item : target.getItemInventory())
                dropActions.add(item.getDropAction(target));
            for (WeaponItem weapon : target.getWeaponInventory())
                dropActions.add(weapon.getDropAction(target));
            for (Action drop : dropActions)
                drop.execute(target, map);

        }else {
            Action drop = RunesManager.getInstance().getRunes(target).getDropAction(target);
            dropMessage = drop.execute(target, map);
            ResetAction resetAction = new ResetAction();
            reset += "\n" + resetAction.execute(target, map);
        }


        if (!target.hasCapability(Status.RESPAWNABLE)) {
            map.removeActor(target);
        }


        result += System.lineSeparator() + menuDescription(target);
        result += reset;
        result += dropMessage;
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
}
