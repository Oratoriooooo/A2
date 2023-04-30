package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.Status;
import game.behaviours.Status;
import game.entities.enemies.HeavySkeletalSwordsman;

/**
 * A special type of Ground representing a graveyard.
 * Created by: Vicky Huang
 * @author Vicky Huang
 * Modified by:
 *
 */
public class Graveyard extends SpawnableGround {
    /**
     * Constructor.
     */
    public Graveyard() {
        super('n');
        this.setChanceToSpawn(27);
        this.addCapability(Status.SPAWN_HEAVY_SKELETAL_SWORDSMAN);
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
        if (this.hasCapability(Status.SPAWN_HEAVY_SKELETAL_SWORDSMAN)) {
            result += super.spawnEnemy(new HeavySkeletalSwordsman());
        }
        System.out.println(result);
    }
}

