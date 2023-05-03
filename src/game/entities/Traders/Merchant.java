package game.entities.Traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.behaviours.Behaviour;
import game.behaviours.Status;
import game.events.BuyAction;
import game.events.SellAction;
import game.items.weapons.Club;
import game.items.weapons.GreatKnife;
import game.items.weapons.Scimitar;
import game.items.weapons.Uchigatana;


import java.util.*;

/**
 * @author Natalie Chan
 * Represents a trader or Merchant character.
 */

public class Merchant extends Actor {
    /**
     * A Hashmap of Merchant behaviors with varying prioritisation
     */
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    /**
     * Hashmap of selling prices according to type of weapon based on the names
     */
    private Map<String, Integer> sellingPrices = new HashMap<>();
    /**
     * Hashmap of prices to buy for each type of weapon
     */
    private Map<WeaponItem, Integer> buyingPrices = new HashMap<>();

    /**
     * Constructor.
     * @param name name of the merchant
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints the merchant's starting number of hitpoints, forever at one
     */
    public Merchant(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        createSellingPrices();
        this.addCapability(Status.IMMORTAL);
    }

    /**
     *
     * @param actions    collection of possible Actions for this merchant
     * @param lastAction The Action this merchant took last turn
     * @param map        the map containing the merchant
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display){
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }

        return new DoNothingAction();
    }

    /**
     * The trader can trade with any actor that has the capability CAN_TRADE
     * @param otherActor the Actor that might want to trade
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of actions a actor may pick to use on Merchant
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.CAN_TRADE)){
            for (WeaponItem weapon : otherActor.getWeaponInventory()){
                    actions.add(new SellAction(weapon, sellingPrices.get(weapon.toString())));
                }
            }
            createNewWares();
            for (Map.Entry<WeaponItem, Integer> weapon: buyingPrices.entrySet()){
                actions.add( new BuyAction(weapon.getKey(),weapon.getValue()) );
            }
        //}
        return actions;
    }

    /**
     * creates the selling prices for weapons a actor can sell to the trader
     */
    public void createSellingPrices(){
        this.sellingPrices.put("Uchigatana", 500);
        this.sellingPrices.put("Great Knife", 350);
        this.sellingPrices.put("Club", 100);
        this.sellingPrices.put("Grossmesser", 100);
        this.sellingPrices.put("Scimitar", 100);
    }

    /**
     * Creates new weapons to be sold per new game turn and their corresponding prices.
     */
    public void createNewWares(){
        this.buyingPrices.clear();
        this.buyingPrices.put(new Club(), 1000);
        this.buyingPrices.put(new GreatKnife(), 3500);
        this.buyingPrices.put(new Uchigatana(), 5000);
        this.buyingPrices.put(new Scimitar(), 600);
    }

}
