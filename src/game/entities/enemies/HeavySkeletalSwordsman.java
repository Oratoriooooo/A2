package game.entities.enemies;

import game.utils.RandomNumberGenerator;

public class HeavySkeletalSwordsman extends EnemyCharacter {
    private final int MAX_RUNES = 892;
    private final int MIN_RUNES = 35;

    public HeavySkeletalSwordsman(){
        super ("Heavy Skeletal Swordsman",'q',153);
        //this.addWeaponToInventory();
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
