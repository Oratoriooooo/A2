package game.entities.enemies.Canines;

import game.EnemyType;
import game.entities.enemies.EnemyCharacter;

/**
 * Abstract class for all canine enemy types
 *
 * Created by: Vicky Huang
 * @author Vicky Huang
 * Modified by:
 *
 */
public abstract class CanineEnemy extends EnemyCharacter {
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public CanineEnemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(EnemyType.CANINE);
    }
}

