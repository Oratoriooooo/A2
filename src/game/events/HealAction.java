package game.events;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utils.Healable;

/**
 * An action to heal a actor.
 * Created By:
 * @author Natalie Chan
 */
public class HealAction extends Action {

    private Healable healingItem;

    /**
     * Constructor
     * @param item healing item to use to heal
     */
    public HealAction(Healable item){
        super();
        this.healingItem = item;

    }

    /**
     * Heals an actor of a given amount of HP.
     *
     * if the item has no more uses, the actor does not heal.
     *
     * @param actor The actor healing.
     * @param map The map the actor is on.
     * @return result of healing, or no healing
     */
    public String execute(Actor actor, GameMap map){
        if(this.healingItem.heal(actor)){
            return actor + " healed " + this.healingItem.getHp() + " hp";
        }
        return actor + " cannot heal with " + this.healingItem + " anymore";
    }

    public String menuDescription(Actor actor){
        return actor + " consumes " + this.healingItem +" "+ this.healingItem.displayUses();
    }
}
