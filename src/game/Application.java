package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.*;
import game.entities.Player;
import game.entities.Traders.Merchant;
import game.entities.enemies.Canines.*;
import game.entities.enemies.Crustaceans.*;
import game.entities.enemies.Skeletons.*;
import game.environments.*;
import game.items.FlaskOfCrimsonTears;
import game.items.Runes;
import game.runesmanager.RunesManager;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Graveyard(), new GustOfWind(), new PuddleOfWater(), new SiteOfLostGrace());

		List<String> map = Arrays.asList(
				"...........................................................................",
				"......................#####....######......................................",
				"......&...............#..___....____#......................................",
				"..................................__#......................................",
				"......~................_____........#.........................n............",
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
		GameMap gameMap = new GameMap(groundFactory, map);
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
		RunesManager runesManager = RunesManager.getInstance();
		Runes runes = new Runes(); //instantiate runes
		FlaskOfCrimsonTears flask = new FlaskOfCrimsonTears();


		Merchant kale = new Merchant("Merchant Kale", 'K', 1);
		gameMap.at(37, 12).addActor(kale);



		// HINT: what does it mean to prefer composition to inheritance?
		Player player = new Player("Tarnished", '@', 300, runes);
		player.addItemToInventory(flask);
		world.addPlayer(player, gameMap.at(36, 10));

		// Add the site of lost grace
		// public void add(char groundChar, NumberRange xs, NumberRange ys)
		int x = 28;
		int y = 10;
		NumberRange lostGraceX = new NumberRange(x, 1);
		NumberRange lostGraceY = new NumberRange(y, 1);
		gameMap.add('U', lostGraceX, lostGraceY);

		// Add the location of the site of lost grace to the player class

		Location siteOfLostGrace = gameMap.at(x, y);
		player.setSiteOfLostGrace(siteOfLostGrace);

		world.run();
	}
}
