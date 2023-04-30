package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;


/**
 * A special type of Ground representing a ground that can spawn an enemy.
 * Created by: Vicky Huang
 * @author Vicky Huang
 * Modified by:
 *
 */
public abstract class SpawnableGround extends Ground {
    /**
     * Random number generator
     */
    private Random rand = new Random();
    /**
     * Integer representing the chance that the ground can spawn an enemy
     */
    private int chanceToSpawn;
    /**
     * Boolean representing if the enemy can be spawned
     */
    private boolean canSpawn;
    /**
     * Integer representing the x coordinate of the ground
     */
    private int x;
    /**
     * Integer representing the y coordinate of the ground
     */
    private int y;
    /**
     * GameMap representing the map of the location of the ground
     */
    private GameMap map;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public SpawnableGround(char displayChar) {
        super(displayChar);
    }

    /**
     * Setter for the chance of spawning the enemy
     *
     * @param chanceToSpawn integer representing the percentage chance of spawning
     */
    public void setChanceToSpawn(int chanceToSpawn) {
        this.chanceToSpawn = chanceToSpawn;
    }

    /**
     * Getter for the boolean representing if an enemy can be spawned
     *
     * @return boolean that represents if an enemy can be spawned
     */

    public boolean canSpawn() {
        return this.canSpawn;
    }

    /**
     * Setter for canSpawn boolean
     * @param canSpawn the new boolean value
     */
    public void setCanSpawn(boolean canSpawn) {
        this.canSpawn = canSpawn;
    }

    /**
     * Getter for x
     *
     * @return Integer representing the x coordinate of the ground's location
     */
    public int x() {
        return this.x;
    }

    /**
     * Getter for y
     *
     * @return Integer representing the y coordinate of the ground's location
     */
    public int y() {
        return this.y;
    }

    public GameMap map() {
        return this.map;
    }


    /**
     * Ground can also experience the joy of time.
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        // we can get location's map
        this.map = location.map();
        // get the location x and y coordinates
        this.x = location.x();
        this.y = location.y();

        // randomly generate a number and check if the chance allows us to spawn an enemy and check if there
        // is an actor at the location
        if (rand.nextInt(100) < chanceToSpawn && !this.map().isAnActorAt(location)) {
            canSpawn = true;
        }
    }

    /**
     * Spawns an enemy at the ground's location.
     *
     * @param enemyActor the enemy Actor to be placed at the location
     * @return the result of the spawning action
     */
    public String spawnEnemy(Actor enemyActor) {
        String result = "";
        // if the boolean is true, then spawn an enemy
        if (this.canSpawn()) {
            // add the actor to the location
            this.map().at(this.x(), this.y()).addActor(enemyActor);
            // print a message to console
            result += enemyActor + " is spawned.";
            // reset the boolean because we should use the chance calculated in the next tick
            this.setCanSpawn(false);
        }
        return result;
    }
}
