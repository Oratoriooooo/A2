package game.events;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.FancyMessage;

public class ChangeWorldAction extends Action {

    public String execute(Actor actor, GameMap map){
        String message = FancyMessage.LOST_GRACE;


        return menuDescription(actor);
    }

    public String menuDescription(Actor actor){
        return actor + " travels to Site of Lost Grace";
    }


}
