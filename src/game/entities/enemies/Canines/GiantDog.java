package game.entities.enemies.Canines;

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

    private final int MIN_RUNES = 313;
    private final int MAX_RUNES = 1808;

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

    @Override
    public int generateRunes(){
        return RandomNumberGenerator.getRandomInt(MIN_RUNES,MAX_RUNES);
    }
}

