package game.entities.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.events.AttackAction;
import game.behaviours.Behaviour;
import game.behaviours.Status;
import game.behaviours.WanderBehaviour;
import game.utils.GenerateRunes;
import game.utils.RandomNumberGenerator;
import game.utils.RunesManager;

import java.util.HashMap;
import java.util.Map;

/**
 * BEHOLD, DOG!
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Natalie Chan
 *
 */
public class LoneWolf extends Enemy{
    /**
     * max amount of runes a Lone Wolf may generate
     */
    private final int MAX_RUNES = 1470;
    /**
     * min amount of runes a Lone Wolf may generate
     */
    private final int MIN_RUNES = 55;

    /**
     * Constructor.
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102);
        RunesManager.getInstance().registerActor(this, this);


    }

    /**
     * generates a random value of runes upon death.
     * The range is from 55 to 1470 runes.
     * @return random amount of runes
     */
    public int generateRunes(){
        return RandomNumberGenerator.getRandomInt(MIN_RUNES, MAX_RUNES);
    }

    /**
     *
     * @return
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }
}
