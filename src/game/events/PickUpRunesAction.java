package game.events;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utils.RunesManager;

public class PickUpRunesAction extends PickUpAction {
    private int runesValue;
    private Item item;
    public PickUpRunesAction(Item runes, int value){
        super(runes);
        this.runesValue = value;
        this.item = runes;
    }

    public String execute(Actor actor, GameMap map){
        RunesManager.getInstance().getRunes(actor).addRunes(this.runesValue);
        super.execute(actor, map);
        return actor + "retrieves Runes (value: "+ this.runesValue+")";
    }



}
