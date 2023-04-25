package game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.archetypes.*;
import game.entities.Traders.Merchant;
import game.entities.enemies.LoneWolf;
import game.entities.Player;
import game.environments.Dirt;
import game.environments.Floor;
import game.environments.Wall;
import game.items.FlaskOfCrimsonTears;
import game.items.Runes;
import game.utils.RunesManager;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display()); // new

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor());

		List<String> map = Arrays.asList(
				"...........................................................................",
				"......................#####....######......................................",
				"......................#..___....____#......................................",
				"..................................__#......................................",
				"......................._____........#......................................",
				"......................#............_#......................................",
				"......................#...........###......................................",
				"...........................................................................",
				"...........................................................................",
				"..................................###___###................................",
				"..................................________#................................",
				"..................................#________................................",
				"..................................#_______#................................",
				"..................................###___###................................",
				"....................................#___#..................................",
				"...........................................................................",
				"...........................................................................",
				"...........................................................................",
				"..####__##....................................................######..##...",
				"..#.....__....................................................#....____....",
				"..#___..........................................................__.....#...",
				"..####__###..................................................._.....__.#...",
				"..............................................................###..__###...",
				"...........................................................................");
		GameMap gameMap = new GameMap(groundFactory, map); // controller of all actors
		world.addGameMap(gameMap);

		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		gameMap.at(23, 17).addActor(new LoneWolf());

		// ask for starting archetype of the tarnished
		Scanner startingClass = new Scanner(System.in);
		System.out.println("Select your role:\ns. Samurai\nb. Bandit\nw. Wretch");
		String selection = startingClass.nextLine();
		StartingArchetype archetype = new Bandit();
		switch (selection){
			case "b":
				archetype = new Bandit();
				break;
			case "s":
				archetype = new Samurai();
				break;
			case "w":
				archetype = new Wretch();
				break;
			default:
				System.out.println("Please choose a valid starting class.\nShutting game down...");
				System.exit(0);
		}

		Runes runes = new Runes(); //instantiate runes
		FlaskOfCrimsonTears flask = new FlaskOfCrimsonTears();
		RunesManager runesManager = RunesManager.getInstance();

		// HINT: what does it mean to prefer composition to inheritance?
		Player player = new Player("Tarnished", '@', archetype, runes);
		player.addItemToInventory(flask);
		world.addPlayer(player, gameMap.at(36, 10));

		//test application ------------
		//Runes run = new Runes();
		//run.setRunesValue(100);
		//run.togglePortability();
		//Merchant trader = new Merchant("Merchant Kale", 'K', 1);
		//gameMap.at(37, 11).addActor(trader);
		//gameMap.at(39,11).addItem(run);


		world.run();
	}
}
