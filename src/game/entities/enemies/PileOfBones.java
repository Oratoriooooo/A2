package game.entities.enemies;

import edu.monash.fit2099.demo.conwayslife.Status;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.EnemyType;
import game.events.DespawnAction;
import game.events.SpawnHeavySkeletalSwordsmanAction;
import game.items.weapons.Grossmesser;


/**
 * BEHOLD, PILE OF BONES!
 *
 * Created by: Vicky Huang
 * @author Vicky Huang
 * Modified by:
 *
 */
public class PileOfBones extends Actor implements Breakable {
    /**
     * Integer representing the number of game iterations that have passed
     */
    int ticks = 0;

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
            return new SpawnHeavySkeletalSwordsmanAction(location.x(), location.y());
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
     * @return
     */
    @Override
    public String broken(Actor by, Location at, GameMap map) {
        Action despawnAction = new DespawnAction();
        String result = by + " breaks the " + this + ".\n";
        despawnAction.execute(this, map);
        return result;
    }
}

