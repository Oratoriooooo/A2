package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.behaviours.Status;
import game.events.HealAction;
import game.resettables.ResetManager;
import game.resettables.Resettable;
import game.utils.Healable;

/**
 * Represents the healing item, flask of Crimson Tears
 */
public class FlaskOfCrimsonTears extends Item implements Healable, Resettable {
    private final int MAX_USES = 2;
    private int currentUses;
    private final int MAX_HP = 250;

    /**
     * Constructor.
     */
    public FlaskOfCrimsonTears(){
        super("Flask of Crimson Tears", 'f', false);
        this.addCapability(Status.CAN_HEAL);
        this.reset();
        this.addAction(new HealAction(this));
        ResetManager.getInstance().registerResettableItem(this);

    }

    @Override
    public String displayUses(){
        return "(" + this.currentUses + "/" + this.MAX_USES + ")";
    }

    /**
     * Heals an actor that has possession of the flask by a max 250 hp to current hp
     * @param actor actor to be healed
     * @return true if the flask is used to heal, false if there are no more uses left
     */
    @Override
    public boolean heal(Actor actor){
        if (this.currentUses != 0){
            this.currentUses-=1;
            actor.heal(this.getHp());
            return true;
        }
        return false;
    }

    /**
     * Gets the amount of healthpoints this item will heal
     * @return amount of HP
     */
    @Override
    public int getHp(){
        return this.MAX_HP;
    }

    /**
     * resets number of uses
     */
    public void reset(){
        this.currentUses = this.MAX_USES;
    }
}
