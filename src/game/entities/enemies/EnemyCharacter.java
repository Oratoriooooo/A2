package game.entities.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.*;
import game.events.AttackAction;
import game.events.DespawnAction;
import game.resettables.*;
import game.runesmanager.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Represents a generic enemy entity in the game.
 * Created by:
 * @author Natalie Chan
 * @author Vicky Huang
 */
public abstract class EnemyCharacter extends Actor implements GenerateRunes, Resettable {
    /**
     * Tree map of behaviours with priority considerations
     */

    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    /**
     * Random number generator
     */
    private Random rand = new Random();
    /**
     * Integer representing the chance that the actor despawns
     */
    private int chanceToDespawn = 10;
    /**
     * Boolean representing if the enemy gets reset or not
     */
    private boolean canReset = false;


    /**
     * Constructor.
     * @param name name of enemy entity
     * @param displayChar game map ground representation of enemy
     * @param hitPoints max initial hitpoints of enemy entity
     */
    public EnemyCharacter(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        this.addCapability(Status.CAN_GENERATE_RUNES);
        this.behaviours.put(999, new WanderBehaviour());
        this.addBehaviour(50, new AttackBehaviour());
        ResetManager.getInstance().registerResettable(this, this);
        RunesManager.getInstance().registerActor(this, this);


    }
    /**
     * At each turn, select a valid action to perform for an enemy.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // if the actor can be reset, it will be removed from the map
        if (this.canReset) {
            return new DespawnAction();
        }

        // if the randomly generated number is less than the chance of despawning, return a despawn action
        if (rand.nextInt(100) < chanceToDespawn) {
            return new DespawnAction();
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }

        return new DoNothingAction();
    }


    /**
     * The enemy can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of possible actions an actor may use on a enemy
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
            // Go through the list of weapons of the other actor to get their actions
            for (int i = 0; i < otherActor.getWeaponInventory().size(); i++) {
                actions.add(otherActor.getWeaponInventory().get(i).getSkill(this, direction));
            }

            // the enemy character is next to the player, they will follow them
            this.addBehaviour(998, new FollowBehaviour(otherActor));
        }
        return actions;

    }

    /**
     * Add the behaviour to the tree map of behaviours
     *
     * @param priority int representing the priority of the behaviour in the tree map
     * @param behaviour the Behaviour to be added to the tree map
     */
    public void addBehaviour(int priority, Behaviour behaviour) {
        this.behaviours.put(priority, behaviour);
    }


    public void reset(){
    }

    public void setCanReset(boolean childReset) {
        this.canReset = childReset;
    }

}
