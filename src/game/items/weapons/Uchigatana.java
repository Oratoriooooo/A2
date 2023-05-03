package game.items.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * Represents an Uchigatana weapon.
 * Created By:
 * @author Natalie Chan
 */
public class Uchigatana extends WeaponItem {
    /**
     * Constructor.
     */
    public Uchigatana(){
        super("Uchigatana", '!', 103, "stabs", 80); //change hitrate & damage, displaychar
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}


}


