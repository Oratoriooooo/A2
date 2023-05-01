package game.entities.enemies.Skeletons;

import game.entities.enemies.EnemyType;
import game.behaviours.Status;
import game.entities.enemies.EnemyCharacter;

/**
 * Abstract class for all skeleton enemy types
 *
 * Created by: Vicky Huang
 * @author Vicky Huang
 * Modified by:
 *
 */
public abstract class SkeletonEnemy extends EnemyCharacter {
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public SkeletonEnemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        // add capabilities
        this.addCapability(Status.BECOME_PILE_OF_BONES);
        this.addCapability(EnemyType.SKELETON);
    }
}


