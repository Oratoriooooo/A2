package game.archetypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;

public abstract class StartingArchetype {
    private int startingHitPoints;
    private WeaponItem startingWeapon;

    public StartingArchetype(int hitPoints, WeaponItem weapon){
        setStartingHitPoints(hitPoints);
        setStartingWeapon(weapon);
    }

    public void setStartingHitPoints(int startingHitPoints) {
        this.startingHitPoints = startingHitPoints;
    }

    public void setStartingWeapon(WeaponItem startingWeapon) {
        this.startingWeapon = startingWeapon;
    }

    public int getStartingHitPoints(){
        return this.startingHitPoints;
    }

    public WeaponItem getStartingWeapon(){
        return this.startingWeapon;
    }
}
