package game.utils;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A Heal-able interface.
 * Created By:
 * @author Natalie Chan
 *
 */
public interface Healable {
    /**
     * Heals a given actor a amount of hp
     * @param actor actor to be healed
     * @return true if actor is healed, false if not
     */
    boolean heal(Actor actor);

    /**
     * Displays the current uses of the item against its max uses
     * @return current uses of the item against its max uses
     */
    String displayUses();

    /**
     * generate an amount of hp to heal an actor
     * @return value of hp
     */
    int getHp();
}
