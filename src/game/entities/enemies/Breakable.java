package game.entities.enemies;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A breakable interface
 * Created by:
 * @author Vicky Huang
 * Modified by:
 *
 */
public interface Breakable {
    String broken(Actor by, Location at, GameMap map);
}
