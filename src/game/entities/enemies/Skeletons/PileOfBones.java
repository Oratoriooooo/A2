package game.entities.enemies.Skeletons;

import edu.monash.fit2099.demo.conwayslife.Status;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.FollowBehaviour;
import game.entities.enemies.EnemyType;
import game.entities.enemies.Breakable;
import game.events.AttackAction;
import game.events.BreakAction;
import game.events.DespawnAction;
import game.events.RespawnPileOfBonesAction;
import game.items.weapons.Grossmesser;
import game.resettables.Resettable;


/**
 * BEHOLD, PILE OF BONES!
 *
 *
 * @author Vicky Huang
 * Modified by:
 *
 */
public class PileOfBones extends Actor implements Breakable, Resettable {
    /**
     * Integer representing the number of game iterations that have passed
     */
    int ticks = 0;

    /**
     * Boolean representing if the enemy gets reset or not
     */
    private boolean canReset = false;
    /**
     * Actor representing the enemy it will respawn into
     */
    private Actor enemyActor;


    /**
     * Constructor.
     */
    public PileOfBones() {
        super("Pile Of Bones", 'X', 0);
        // add Grossmesser to its inventory
        this.addWeaponToInventory(new Grossmesser());
        // add the skeleton capability because it cannot be attacked by other skeletons
        this.addCapability(EnemyType.SKELETON);
        this.addCapability(Status.BREAKABLE);
    }

    /**
     * Tracks the number of game turns/ticks and if three turns have passed, spawn an enemy in its
     * place.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action that spawns an enemy in place of the pile of bones if 3 game turns have passed, otherwise
     *         return a DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // if the actor can be reset, it will be removed from the map
        if (this.canReset) {
            return new DespawnAction();
        }

        // return null;
        // increment the counter for ticks
        ticks += 1;

        // if 3 game turns have passed, despawn the pile of bones then return an action for spawning
        // the enemy
        if (ticks == 3) {
            Action despawnAction = new DespawnAction();
            Location location = map.locationOf(this);
            // print out the results of despawning the pile of bones
            System.out.println(despawnAction.execute(this, map));
            return new RespawnPileOfBonesAction(location.x(), location.y(), this.enemyActor);
        }

        return new DoNothingAction();
    }

    /**
     * Executes the logic when the pile of bones is broken i.e. remove it from the map.
     * Don't need to drop weapon because it has already been dropped at that location by the
     * previous actor and the pile of bones just sits on top of it.
     *
     * @param by the Actor that breaks the pile of bones
     * @param at the location that the pile of bones is at
     * @param map the map that the Actor is on
     * @return destruction of pile of bones
     */
    @Override
    public String broken(Actor by, Location at, GameMap map) {
        Action despawnAction = new DespawnAction();
        String result = by + " breaks the " + this + ".\n";
        despawnAction.execute(this, map);
        return result;
    }

    /**
     * The following method is called when the enemy is reset and the enemy actor can be reset so the
     * parent class canReset boolean is set to true.
     *
     */
    @Override
    public void reset() {
        this.canReset = true;
    }

    /**
     * This method sets the enemy actor attribute to the provided actor.
     *
     * @param enemyActor the actor to be set
     */
    public void setEnemyActor(Actor enemyActor) {
        this.enemyActor = enemyActor;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(game.behaviours.Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));

            // Go through the list of weapons of the other actor to get their actions
            for (int i = 0; i < otherActor.getWeaponInventory().size(); i++) {
                actions.add(otherActor.getWeaponInventory().get(i).getSkill(this, direction));
            }
        }
        return actions;

    }
}

