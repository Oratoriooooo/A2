package game.entities.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.RandomNumberGenerator;

/**
 * BEHOLD, CRAYFISH!
 *
 * Created by: Vicky Huang
 * @author Vicky Huang
 * Modified by:
 *
 */
public class GiantCrayfish extends CrustaceanEnemy {
    private final int MAX_RUNES = 2374;
    private final int MIN_RUNES = 500;
    /**
     * Constructor.
     *
     */
    public GiantCrayfish() {
        super("Giant Crayfish", 'R', 4803);
    }

    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(527, "slams", 100);
    }

    /**
     * generates a random value of runes upon death.
     * The range is from 500 to 2374 runes.
     * @return random amount of runes
     */
    public int generateRunes(){
        return RandomNumberGenerator.getRandomInt(MIN_RUNES, MAX_RUNES);
    }
}
