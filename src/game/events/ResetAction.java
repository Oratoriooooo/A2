package game.events;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.resettables.ResetManager;

/**
 * An Action to reset the whole game.
 * Created by:
 * @author Vicky Huang
 *
 */
public class ResetAction extends Action {
    /**
     * This method resets the game by using the ResetManager.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String describing the result of the action to be displayed to the UI
     * @see game.resettables.ResetManager
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " resets the game";
    }
}
