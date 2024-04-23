package inf112.skeleton.app.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.item.ItemEnergy;
import inf112.skeleton.app.model.item.ItemMushroom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestItem {
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;
    private static final int NUM_ITERATIONS = 60;
    private static final float DT = 1 / 60f;
    private static final float INIT_X = 0;
    private static final float INIT_Y = 30;
    private static final float GRAVITY_X = 0;
    private static final float GRAVITY_Y = 0;
    private World world;
    private PlayerModel player;
    private EventBus bus;

    private void step() {
        player.step(DT);
        world.step(DT, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }

    @BeforeEach
    public void reset() {
        world = new World(new Vector2(GRAVITY_X, GRAVITY_Y), true);
        bus = new EventBus();
        player = new PlayerModel(bus, world, INIT_X, INIT_Y);
        world.setContactListener(player);
    }

    @Test
    public void pickUpEnergy() {
        player.moveRight(true);
        assertNull(player.getItem(), "player should not start with an item");
        ItemEnergy energy = new ItemEnergy(bus, world, player.getX(), player.getY());
        world.setContactListener(energy);
        step();
        assertNotNull(player.getItem(), "Suppose to have an item in the player inventory");

    }

    @Test
    public void pickUpMushroom() {
        player.moveLeft(true);
        assertNull(player.getItem(), "player should not start with an item");
        ItemMushroom mushroom = new ItemMushroom(bus, world, player.getX(), player.getY());
        world.setContactListener(mushroom);
        step();
        assertNotNull(player.getItem(), "Suppose to have an item in the player inventory");
    }

    @Test
    public void pickUpItemAfterUsed(){
        player.moveLeft(true);
        assertNull(player.getItem(), "player should not start with an item");
        ItemMushroom mushroom = new ItemMushroom(bus, world, player.getX(), player.getY());
        world.setContactListener(mushroom);
        step();
        assertEquals( mushroom.toString(),player.getItem().toString(), "The item should be a mushroom");
        for(int i=0; i<mushroom.getDurability().maximum(); i++){
            player.useItem();
            step();
        }
        ItemEnergy energy = new ItemEnergy(bus,world, player.getX(), player.getY());
        world.setContactListener(energy);
        step();
        assertEquals( energy.toString(),player.getItem().toString(),"The item should now be energy");

    }
    @Test
    public void testUseEnergyDistance() {
        player.moveRight(true);

        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        float diffX = player.getX() - INIT_X;
        //Add energyItem to the world and let the player use it
        ItemEnergy energy = new ItemEnergy(bus, world, player.getX(), player.getY());
        world.setContactListener(energy);
        step();
        float startXWithItem = player.getX();
        player.useItem();
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        float diffXWithItem = player.getX() - startXWithItem;
        assertTrue(diffXWithItem > diffX, "The distance used with the energy item should be larger than distance without using energy item");
    }

    @Test
    public void testNoDuplicateUseEnergy(){
        player.moveRight(true);
        ItemEnergy energy = new ItemEnergy(bus, world, player.getX(), player.getY());
        world.setContactListener(energy);
        step();
        float startX = player.getX();
        player.useItem();
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        float diffX= player.getX()-startX;
        //Using energy item twice or more should not give more speed.
        player.useItem();
        player.useItem();
        startX = player.getX();
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        //Approximating the difference since we are working with floating points
        float tolerance = 1f;
        float doubleEnergyDiff = player.getX()-startX;
        assertTrue(diffX-tolerance< doubleEnergyDiff && doubleEnergyDiff< diffX+tolerance);
    }

}
