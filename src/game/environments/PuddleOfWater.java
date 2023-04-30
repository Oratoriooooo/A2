package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.Status;
import game.entities.enemies.GiantCrab;

/**
 * A special type of Ground representing a puddle of water.
 * Created by: Vicky Huang
 * @author Vicky Huang
 * Modified by:
 *
 */
public class PuddleOfWater extends SpawnableGround {
    /**
     * Constructor.
     */
    public PuddleOfWater() {
        super('~');
        this.setChanceToSpawn(2);
        this.addCapability(Status.SPAWN_GIANT_CRAB);
    }
    /**
     * Ground can also experience the joy of time.
     * At each game iteration, check and see if we can spawn an enemy.
     *
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        super.tick(location);
        String result = "";
        if (this.hasCapability(Status.SPAWN_GIANT_CRAB)) {
            result += super.spawnEnemy(new GiantCrab());
        }
        System.out.println(result);
    }
}


