package game.entities.enemies.Skeletons;


import game.items.weapons.Scimitar;
import game.utils.RandomNumberGenerator;

/**
 * BEHOLD, SKELETAL BANDIT!
 *
 * Created by: Vicky Huang
 * @author Vicky Huang
 * Modified by:
 *
 */
public class SkeletalBandit extends SkeletonEnemy {

    private final int MAX_RUNES = 892;
    private final int MIN_RUNES = 35;
    /**
     * Constructor.
     *
     */
    public SkeletalBandit() {
//        super("Heavy Skeletal Swordsman", 'q', 153);
//        this.addWeaponToInventory(new Grossmesser());
        super("Skeletal Bandit", 'b', 184);
        // add scimitar
        this.addWeaponToInventory(new Scimitar());
    }

    /**
     * generates a random value of runes upon death.
     * The range is from 35 to 892 runes.
     * @return random amount of runes
     */
    public int generateRunes(){
        return RandomNumberGenerator.getRandomInt(MIN_RUNES, MAX_RUNES);
    }
}

