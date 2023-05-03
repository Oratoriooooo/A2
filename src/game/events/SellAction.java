package game.events;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.Runes;
import game.runesmanager.RunesManager;

/**
 *
 * @author Natalie Chan
 * An action to sell a weapon from an actor's inventory.
 */

public class SellAction extends Action {
    /**
     * A weapon to sell from a actor's weapon inventory
     */
    WeaponItem weaponToSell;

    /**
     * selling price of the specific weapon type
     */
    int sellingPrice;

    /**
     * Constructor
     * @param weapon A weapon to be sold
     * @param price price of the weapon
     */
    public SellAction(WeaponItem weapon, int price){
        super();
        this.weaponToSell = weapon;
        this.sellingPrice = price;

    }

    /**
     * When executed, the weapon to be sold is removed from the actor's inventory and given runes which are added
     * to its corresponding Runes object.
     * @param actor The actor selling the weapon.
     * @param game The map the actor is on.
     * @return the result of the transaction eg. actor gains runes
     */
    @Override
    public String execute(Actor actor, GameMap game) {
        Runes runes = RunesManager.getInstance().getRunes(actor);
        runes.addRunes(this.sellingPrice);
        actor.removeWeaponFromInventory(this.weaponToSell);
        return actor + " sold " + this.weaponToSell;
    }

    /**
     * Describes which weapon the actor wants to sell and for how many runes.
     * @param actor The actor selling the weapon.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + " sells " + this.weaponToSell + " for " + this.sellingPrice;
    }
}
