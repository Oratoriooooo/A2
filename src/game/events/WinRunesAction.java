package game.events;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Runes;
import game.runesmanager.RunesManager;

public class WinRunesAction extends Action {
    Actor winner;
    int runesWon;
    public WinRunesAction(Actor actor){
        super();
        this.winner = actor;
    }

    public String execute(Actor loser, GameMap game){
        this.runesWon = RunesManager.getInstance().generateRunes(loser);
        // code to add value to runes
        Runes actorRunes = RunesManager.getInstance().getRunes(winner);
        actorRunes.addRunes(this.runesWon);
        RunesManager.getInstance().removeActor(loser); //removes generate runes actor
        return System.lineSeparator() + menuDescription(winner);
    }

    public String menuDescription(Actor actor){
        return actor + " received " + runesWon + " runes";
    }
}
