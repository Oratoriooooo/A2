package game.items.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

public class GreatKnife extends WeaponItem {
    /**
     * Constructor
     */
    public GreatKnife() {
        super("Great Knife", '!', 103, "stabs", 80); //change hitrate & damage, displaychar
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}
}

