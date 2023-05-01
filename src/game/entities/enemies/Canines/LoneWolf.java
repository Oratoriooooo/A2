package game.entities.enemies.Canines;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.entities.enemies.EnemyCharacter;
import game.utils.RandomNumberGenerator;
import game.runesmanager.RunesManager;

/**
 * BEHOLD, DOG!
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Natalie Chan
 *
 */
public class LoneWolf extends EnemyCharacter {
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
     * Creates and returns an Lone Wolf intrinsic weapon.
     *
     * 'bites' for 97 damage at a hit rate of 95.
     *
     * @return a freshly-instantiated IntrinsicWeapon for Lone Wolf
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }
}
