package game.entities;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.items.Runes;
import game.items.weapons.Club;
import game.resettables.Resettable;
import game.behaviours.Status;
import game.utils.RunesManager;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Player extends Actor implements Resettable{

	private final Menu menu = new Menu();
	private Runes runes;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints, Runes runes) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.CAN_TRADE);
		this.addCapability(Status.CAN_RECIEVE_RUNES);
		this.addWeaponToInventory(new Club());
		this.addItemToInventory(runes);
		this.setRunes(runes);
		RunesManager.getInstance().registerRunes(this, runes);

	}

	public String displayPlayer(){
		int runeValue = this.getRunes().getRunesValue();
		int maxHp = this.getMaxHp();
		int currentHp = this.getHitPoints();
		return "Tarnished (" +currentHp+ "/"+maxHp+"), runes: " + runeValue;
	}

	public int getHitPoints(){
		return this.hitPoints;
	}

	public Runes getRunes(){

		return this.runes;
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		System.out.println(displayPlayer());
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	public void setRunes(Runes runes){
		this.runes = runes;
		this.addItemToInventory(runes);
	}


	@Override
	public void reset() {}
}
