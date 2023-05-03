package game.events;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Runes;
import game.resettables.ResetManager;
import game.runesmanager.RunesManager;

/**
 * Action for recieving runes from an enenmy death
 * @author Natalie Chan
 */
public class WinRunesAction extends Action {
    Actor winner;
    int runesWon;

    /**
     *  Constructor.
     * @param actor winner of clash that recieves runes
     */
    public WinRunesAction(Actor actor){
        super();
        this.winner = actor;
    }

    /**
     * passes a generated value of runes from dead enemy to Actor that has Runes i it's possession
     * @param loser generates Runes from
     * @param game map of game
     * @return result of action
     */
    public String execute(Actor loser, GameMap game){
        this.runesWon = RunesManager.getInstance().generateRunes(loser);
        // code to add value to runes
        Runes actorRunes = RunesManager.getInstance().getRunes(winner);
        actorRunes.addRunes(this.runesWon);
        RunesManager.getInstance().removeActor(loser); //removes generate runes actor
        return System.lineSeparator() + menuDescription(winner);
    }

    @Override
    public String menuDescription(Actor actor){
        return actor + " received " + runesWon + " runes";
    }
}
