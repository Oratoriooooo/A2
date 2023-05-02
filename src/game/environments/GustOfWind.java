package game.environments;

import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.Status;
import game.entities.enemies.Canines.GiantDog;
import game.entities.enemies.Canines.LoneWolf;

/**
 * A special type of Ground representing a gust of wind.
 * Created by: Vicky Huang
 * @author Vicky Huang
 * Modified by:
 *
 */
public class GustOfWind extends SpawnableGround {
    /**
     * Constructor.
     */
    public GustOfWind() {
        super('&');
        this.setChanceToSpawn(33);

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
//        if (this.hasCapability(Status.SPAWN_LONE_WOLF)) {
//            result += super.spawnEnemy(new LoneWolf());
//        }
        if (this.isEast()) {
            result += super.spawnEnemy(new GiantDog());
        }
        else {
            result += super.spawnEnemy(new LoneWolf());
        }
        System.out.println(result);
    }

}

