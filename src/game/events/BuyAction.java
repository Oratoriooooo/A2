package game.events;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.Runes;
import game.runesmanager.RunesManager;

/**
 * Creates an action to buy a weapon from an actor.
 * Created By:
 * @author Natalie Chan
 */
public class BuyAction extends Action {
    /**
     * price of weapon
     */
    private int buyingPrice;
    /**
     * weapon to buy
     */
    WeaponItem weaponToBuy;

    /**
     * Constructor.
     * @param weapon weapon to buy
     * @param price price of weapon
     */
    public BuyAction(WeaponItem weapon, int price){
        super();
        this.buyingPrice = price;
        this.weaponToBuy = weapon;
    }

    /**
     * When executed, the buyer's runes are depleted according to the weapon price and the weapon is added to it's inventory.
     * This only occurs if there is sufficient funds in the buyer's possession
     * @param buyer buyer of weapon
     * @param map The map the buyer is on.
     * @return the result of the transaction
     */
    public String execute(Actor buyer, GameMap map){
        Runes runes = RunesManager.getInstance().getRunes(buyer);
        if(runes.getRunesValue()-this.buyingPrice >0){
            runes.depleteRunes(this.buyingPrice);
            buyer.addWeaponToInventory(this.weaponToBuy);
        }
        else{
            return buyer + " does not have enough runes to purchase "+ this.weaponToBuy;
        }
        return buyer + " bought a " + this.weaponToBuy;
    }

    /**
     * Describes which weapon the actor wants to buy and for how many runes.
     * @param buyer buyer of weapon
     * @return a description used for the menu UI
     */
    public String menuDescription(Actor buyer){
        return buyer + " purchases " +this.weaponToBuy +" for "+this.buyingPrice;
    }
}
