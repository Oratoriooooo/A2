package game.items;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.*;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.Status;
import game.events.DropRunesAction;
import game.events.PickUpRunesAction;
import game.resettables.ResetManager;
import game.resettables.Resettable;
import game.runesmanager.RunesManager;


/**
 * @author Natalie Chan
 * Represents a collection of the Runes currency.
 */
public class Runes extends Item {
    /**
     * number of runes
     */
    private int runesValue;

    /**
     * Number of game iterations
     */
    private int gameNumber;
    /**
     * maximum resets of Runes when dropped on map ground
     */
    private final int MAX_GAMES = 2;
    /**
     * location of runes dropped
     */
    private Location droppedLocation;


    /**
     * Constructor.
     */
    public Runes() {
        super("Runes", '$', false);
        this.runesValue = 0;
        this.addCapability(Status.CAN_TRADE);
        this.addCapability(Status.CAN_RECIEVE_RUNES);
        this.gameNumber = 0;

    }

    /**
     * Adds more runes to the number of runes in Rune.
     *
     * @param value value to be added
     */
    public void addRunes(int value) {
        setRunesValue(value + this.getRunesValue());
    }

    /**
     * Depletes runes by a given value only if the result is still positive
     *
     * @param value potential number of runes to be taken
     * @return true if result is positive, false if not
     */
    public boolean depleteRunes(int value) {
        if (this.getRunesValue() - value >= 0) {
            this.setRunesValue(this.getRunesValue() - value);
            return true;
        }
        return false;
    }

    /**
     * Gets the number of runes
     *
     * @return value of runes
     */
    public int getRunesValue() {
        return this.runesValue;
    }

    /**
     * Create and return an action to pick the runes up from the ground.
     * The runes value of the Runes picked up wll be added to the runes in an actor's possession.
     *
     * @return a new PickUpItemAction if this Item is portable, null otherwise.
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        if (portable) {
            int newRunesValue = this.getRunesValue();
            return new PickUpRunesAction(this, newRunesValue);
        }
        return null;
    }

    /**
     * Runes will only be dropped when an actor holding onto them is killed.
     * Runes dropped will now belong to nobody(null).
     * <p>
     * It will return null unless actor has died.
     *
     * @param actor actor that has possession of the runes object
     * @return a new DropItemAction if this Item is portable, null otherwise.
     */
    @Override
    public DropAction getDropAction(Actor actor) {
        //initially false, when player dies = runes can be dropped
        if (!actor.isConscious()) {
            this.togglePortability();
            RunesManager.getInstance().removeRunes(this); //change owner
            RunesManager.getInstance().registerRunes(null, this); //now runes does not belong to anyone
        }
        if (portable) {
            return new DropRunesAction(this);
        }
        return null;
    }

    /**
     * Sets new number of runes, which must be positive.
     *
     * @param value new number of runes
     * @return true if new value is greater than 0, false if not
     */
    public boolean setRunesValue(int value) {
        if (value >= 0) {
            this.runesValue = value;
            return true;
        }
        return false;
    }

    /**
     * Sets the location where a dead actor dropped its runes.
     * @param location location of dropped runes
     */
    public void setLocation(Location location){
        this.droppedLocation = location;

    }

    /**
     * Returns the location on the game map where runes was dropped.
     * @return location of runes
     */
    public Location getDroppedLocation(){
        return this.droppedLocation;
    }


}











