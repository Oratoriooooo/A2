package game.items.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.events.AttackAction;
import game.events.SpinningAreaAttackAction;

import java.util.Random;
import java.util.Scanner;

public class Scimitar extends WeaponItem {
    /**
     * Random number generator
     */
    private Random rand = new Random();
    /**
     * Integer representing the chance that the weapon returns the special skill
     */
    private int chanceToUseSpecialSkill = 50;
    /**
     * Constructor.
     *
     */
    public Scimitar() {
        super("Scimitar", 's', 118, "slices", 88);
    }
    public Action getSkill(Actor target, String direction) {
        // there is a 50% chance of an attack where a single enemy is attacked or a spinning attack
        if (!(rand.nextInt(100) < chanceToUseSpecialSkill)) {
            return new AttackAction(target, direction, this);
        }
        else {
            // return the spinning attack action
            return new SpinningAreaAttackAction(target, direction, this);
        }
    }
}
