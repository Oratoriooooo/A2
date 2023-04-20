package game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.archetypes.StartingArchetype;
import game.entities.Traders.Merchant;
import game.entities.enemies.LoneWolf;
import game.entities.Player;
import game.environments.Dirt;
import game.environments.Floor;
import game.environments.Wall;
import game.items.Runes;

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
/*
		Scanner startingClass = new Scanner(System.in);
		System.out.println("Choose the Tarnished's class:\n1. Samurai\n2. Bandit\n3. Wretch");
		String selection = startingClass.nextLine();
		StartingArchetype archetype;
		switch (selection){
			case "1":
				archetype = new Bandit();
				break;

				case "2":
				break;
				case "3":
				break;
		}
*/

		Runes runes = new Runes(); //instantiate runes
		Merchant trader = new Merchant();

		// HINT: what does it mean to prefer composition to inheritance?
		Player player = new Player("Tarnished", '@', 300, runes);
		world.addPlayer(player, gameMap.at(36, 10));
		gameMap.at(37, 11).addActor(trader);
		world.run();
	}
}
