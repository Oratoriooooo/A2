package game.environments;


import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.FancyMessage;
import game.behaviours.Status;
import game.events.ChangeWorldAction;
import game.events.ResetAction;

public class SiteOfLostGrace extends Ground {

    /**
     * site is discovered by player
     */
    private boolean discovered; // new

    private String name;
    /**
     * Constructor.
     *
     */
    public SiteOfLostGrace(String name) {
        super('U');
        this.name = name;
        discovered = false;
    }

    /**
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return allowable actions actor can choose
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        // Prints the first discovery message of the site - new
        if (!discovered) {
            String message = FancyMessage.LOST_GRACE;
            FancyMessage.printMessage(message);
            TravelManager.getInstance().addLostGrace(location, this.name);
            discovered = true;
        }

        // if the actor is able to rest on the site, they can reset so return a reset action
        if(actor.hasCapability(Status.RESTING)){
            actions.add(new ResetAction());
            //change world
        }

        return actions;
    }
}

