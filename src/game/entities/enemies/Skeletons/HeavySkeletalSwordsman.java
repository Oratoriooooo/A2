package game.entities.enemies.Skeletons;

import game.entities.enemies.EnemyCharacter;
import game.items.weapons.Grossmesser;
import game.utils.RandomNumberGenerator;

/**
 * BEHOLD, HEAVY SKELETAL SWORDSMAN!
 *
 *
 * @author Vicky Huang
 * Modified by:
 * @author Natalie Chan
 *
 */
public class HeavySkeletalSwordsman extends SkeletonEnemy {
    private final int MAX_RUNES = 892;
    private final int MIN_RUNES = 35;

    public HeavySkeletalSwordsman(){
        super ("Heavy Skeletal Swordsman",'q',153);
        this.addWeaponToInventory(new Grossmesser());
    }

    /**
     * generates a random value of runes upon death.
     * The range is from 35 to 892 runes.
     * @return random amount of runes
     */
    public int generateRunes(){
        return RandomNumberGenerator.getRandomInt(MIN_RUNES, MAX_RUNES);
    }

    /**
     * The following method is called when the enemy is reset and the enemy actor can be reset so the
     * parent class canReset boolean is set to true.
     *
     */
    @Override
    public void reset() {
        super.setCanReset(true);
    }

}
