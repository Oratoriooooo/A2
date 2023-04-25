package game.entities;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.archetypes.StartingArchetype;
import game.items.Runes;
import game.items.weapons.Club;
import game.resettables.Resettable;
import game.behaviours.Status;
import game.utils.RunesManager;

/**
 * Class representing the Player. It implements the Resettable interface.
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Natalie Chan
 *
 */
public class Player extends Actor implements Resettable{
	/**
	 * A menu GUI to display current allowable player actions
	 */
	private final Menu menu = new Menu();
	/**
	 * Holds a player's runes
	 */
	private Runes runes;

	/**
	 * Constructor of a Player. Registers itself to be keyed to one Runes object.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param startClass   contains player's starting number of hitpoints and weapon
	 */
	public Player(String name, char displayChar, StartingArchetype startClass, Runes runes) {
		super(name, displayChar, startClass.getStartingHitPoints());
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addWeaponToInventory(startClass.getStartingWeapon());
		this.setRunes(runes);
		RunesManager.getInstance().registerRunes(this, runes);

	}


	/**
	 * Displays its current health at the start of the turn
	 * @return current hitpoints against it's max hitpoints eg. 100/150
	 */
	public String displayCurrentHitPoints(){
		int maxHp = this.getMaxHp();
		int currentHp = this.getHitPoints();
		return "(" +currentHp+ "/"+maxHp+")";
	}

	/**
	 * Gets current hitpoints of a player
	 * @return value of a player's hitpoints
	 */
	public int getHitPoints(){
		return this.hitPoints;
	}

	/**
	 * gets the Runes of a player
	 * @return runes of a player
	 */
	public Runes getRunes(){
		return this.runes;
	}

	/**
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		System.out.println(this + displayCurrentHitPoints() + ", runes: " + getRunes().getRunesValue());
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Sets Runes to the player's inventory and attribute for easy access
	 * @param runes A collection of runes
	 */
	public void setRunes(Runes runes){
		this.runes = runes;
		this.addItemToInventory(runes);
	}


	@Override
	public void reset() {
		Runes newRunes = new Runes();
		setRunes(newRunes);
		this.resetMaxHp(this.getMaxHp());

	}
}
