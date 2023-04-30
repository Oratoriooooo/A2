package game.entities.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.RandomNumberGenerator;

public class GiantCrab extends EnemyCharacter {
    private final int MAX_RUNES = 4961;
    private final int MIN_RUNES = 318;

    public GiantCrab(){
        super("Giant Crab", 'C', 407);

    }

    /**
     * generates a random value of runes upon death.
     * The range is from 318 to 4961 runes.
     * @return random amount of runes
     */
    public int generateRunes(){
        return RandomNumberGenerator.getRandomInt(MIN_RUNES, MAX_RUNES);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(208, "slams", 90);
    }

}
