package game.entities.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.SlamAreaAttackBehaviour;
import game.utils.RandomNumberGenerator;

/**
 * BEHOLD, GIANT DOG!
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class GiantDog extends CanineEnemy {
    /**
     * max amount of runes a Giant Dog may generate
     */
    private final int MAX_RUNES = 1808;
    /**
     * min amount of runes a Giant Dog may generate
     */
    private final int MIN_RUNES = 313;
    /**
     * Constructor.
     *
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693);
        // Giant dog slams other creatures so we add a slam attack behaviour
        this.addBehaviour(1, new SlamAreaAttackBehaviour());
    }

    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(314, "slams with head", 90);
    }

    /**
     * generates a random value of runes upon death.
     * The range is from 313 to 1808 runes.
     * @return random amount of runes
     */
    public int generateRunes(){
        return RandomNumberGenerator.getRandomInt(MIN_RUNES, MAX_RUNES);
    }
}
