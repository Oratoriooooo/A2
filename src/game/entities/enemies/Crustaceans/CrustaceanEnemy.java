package game.entities.enemies.Crustaceans;

import game.entities.enemies.EnemyType;
import game.behaviours.SlamAreaAttackBehaviour;
import game.entities.enemies.EnemyCharacter;

/**
 * Abstract class for all crustacean enemy types
 *
 *
 * @author Vicky Huang
 * Modified by:
 *
 */
public abstract class CrustaceanEnemy extends EnemyCharacter {
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public CrustaceanEnemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        // add the behaviour of slamming creatures nearby
        this.addBehaviour(1, new SlamAreaAttackBehaviour());
        this.addCapability(EnemyType.CRUSTACEAN);
    }
}


