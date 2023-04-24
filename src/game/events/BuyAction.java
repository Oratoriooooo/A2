package game.events;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.Runes;
import game.utils.RunesManager;

public class BuyAction extends Action {
    private int buyingPrice;
    WeaponItem weaponToBuy;

    public BuyAction(WeaponItem weapon, int price){
        super();
        this.buyingPrice = price;
        this.weaponToBuy = weapon;
    }

    public String execute(Actor actor, GameMap map){
        Runes runes = RunesManager.getInstance().getRunes(actor);
        if(runes.getRunesValue()-this.buyingPrice >0){
            runes.depleteRunes(this.buyingPrice);
            actor.addWeaponToInventory(this.weaponToBuy);
        }
        else{
            return actor + " does not have enough runes to purchase "+ this.weaponToBuy;
        }
        return actor + " bought a " + this.weaponToBuy;
    }

    public String menuDescription(Actor actor){
        return actor + " purchases " +this.weaponToBuy +" for "+this.buyingPrice;
    }
}
