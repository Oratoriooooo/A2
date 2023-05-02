package game.environments;


import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.FollowBehaviour;
import game.behaviours.Status;
import game.events.AttackAction;
import game.events.ResetAction;

public class SiteOfLostGrace extends Ground {
    /**
     * Constructor.
     *
     */
    public SiteOfLostGrace() {
        super('U');
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        // if the actor is able to rest on the site, they can reset so return a reset action
        if(actor.hasCapability(Status.RESTING)){
            actions.add(new ResetAction());
        }
        return actions;
    }
}

