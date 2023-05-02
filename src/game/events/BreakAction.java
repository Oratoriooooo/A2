package game.events;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.entities.enemies.Breakable;

/**
 * An Action to break a Breakable.
 * Created by:
 * @author Vicky Huang
 * Modified by:
 *
 */
public class BreakAction extends Action {
    /**
     * Breakable representing the breakable to be broken
     */
    private Breakable breakable;
    /**
     * Location where the breakable is being broken
     */
    private Location breakableLocation;
    /**
     * The direction that the breakable is in
     */
    private String direction;

    /**
     * Constructor.
     *
     * @param breakable Breakable representing the breakable to be broken
     * @param breakableLocation Location where the breakable is being broken
     * @param direction The direction that the breakable is in
     */
    public BreakAction(Breakable breakable, Location breakableLocation, String direction) {
        this.breakable = breakable;
        this.breakableLocation = breakableLocation;
        this.direction = direction;
    }
    /**
     * Calls the broken method of the breakable and passes it some parameters.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String describing the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return breakable.broken(actor, breakableLocation, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}

