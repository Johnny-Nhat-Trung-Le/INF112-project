package inf112.skeleton.app.model;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.item.ItemEnergy;
import inf112.skeleton.app.model.item.ItemMushroom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestItem{
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;
    private static final int NUM_ITERATIONS = 60;
    private static final float DT = 1/60f;
    private static final float INIT_X = 0;
    private static final float INIT_Y = 30;
    private static final float GRAVITY_X = 0;
    private static final float GRAVITY_Y = 0;
    private World world;
    private PlayerModel player;
    private EventBus bus;

    private void step(){
        player.step(DT);
        world.step(DT, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }

    @BeforeEach
    public void reset(){
        world = new World(new Vector2(GRAVITY_X, GRAVITY_Y),true);
        bus= new EventBus();
        player= new PlayerModel(bus,world, INIT_X, INIT_Y);
        world.setContactListener(player);
    }

    @Test
    public void pickUpEnergy(){
        player.moveRight(true);
        assertNull(player.getItem(),"player should not start with an item");
        ItemEnergy energy = new ItemEnergy(bus, world,player.getX(), player.getY());
        world.setContactListener(energy);
        step();
        assertNotNull(player.getItem(),"Suppose to have an item in the player inventory");

    }

    @Test
    public void pickUpMushroom(){
        player.moveLeft(true);
        assertNull(player.getItem(),"player should not start with an item");
        ItemMushroom mushroom = new ItemMushroom(bus, world, player.getX(), player.getY());
        world.setContactListener(mushroom);
        step();
        assertNotNull(player.getItem(),"Suppose to have an item in the player inventory");
    }

    @Test
    public void pickUpOnlyOneItem(){
        player.moveRight(true);
        //TODO FIX Has mushroom instead of energy
        ItemMushroom mushroom = new ItemMushroom(bus, world,INIT_X + player.getWidth(), player.getY());
        ItemEnergy energy = new ItemEnergy(bus, world,INIT_X + (2 * player.getWidth()), player.getY());
        world.setContactListener(mushroom);
        world.setContactListener(energy);
        System.out.println(mushroom.getX() + " mushroomX");
        System.out.println(energy.getX() + " energyX");
        for(int i = 0; i < NUM_ITERATIONS; i++){
            step();
        }
        System.out.println(player.getX());
        System.out.println(player.getItem());
        assertNotNull(player.getItem(),"Suppose to have an item in the player inventory");
        assertEquals(mushroom.toString(),player.getItem().toString(),"Should be the first item which is mushroom");

    }
    @Test
    public void pickUpItemAfterUsed(){
        player.moveLeft(true);
        assertNull(player.getItem(),"player should not start with an item");
        ItemMushroom mushroom= new ItemMushroom(bus, world, player.getX(), player.getY());
        world.setContactListener(mushroom);
        step();
        assertEquals(mushroom.toString(), player.getItem().toString(),"The item should be a mushroom");
        for(int i = 0; i < mushroom.getDurability().maximum(); i++){
            player.useItem();
            step();
        }
        ItemEnergy energy = new ItemEnergy(bus,world, player.getX(), player.getY());
        world.setContactListener(energy);
        step();
        assertEquals(energy.toString(),player.getItem().toString(),"The item should now been energy");

    }
    @Test
    public void testUseEnergyDistance(){
        player.moveRight(true);
        for(int i = 0; i < NUM_ITERATIONS; i++){
            step();
        }
        float diffX = player.getX() - INIT_X;
        //Add energy Item to the world and let the player use it
        ItemEnergy energy = new ItemEnergy(bus, world, player.getX(), player.getY());
        world.setContactListener(energy);
        step();
        float startXWithItem = player.getX();
        player.useItem();
        for(int i = 0; i < NUM_ITERATIONS; i++){
            step();
        }
        float diffXWithItem = player.getX() - startXWithItem;
        assertTrue(diffXWithItem > diffX,"The distance used with the energy item should be larger " +
                "than distance without using energy item");
    }


    @Test
    public void testUseMushroomJump(){
        float maxHeight = jumpWithoutItem();
        reset();
        world.setGravity(new Vector2(GRAVITY_X,-20));
        ItemMushroom mushroom = new ItemMushroom(bus, world,player.getX(), player.getY());
        world.setContactListener(mushroom);
        step();
        world.setContactListener(player);
        createStaticBody();
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        assertNotNull(player.getItem(),"Player should have gotten ItemMushroom");
        float previousY = player.getY();
        player.useItem();
        player.moveUp(true);
        float maxHeightItem = 0;
        while(true){
            step();
            float currentY = player.getY();
            if(currentY < previousY){
                maxHeightItem = previousY;
                break;
            }
            previousY = currentY;
        }
        assertTrue(maxHeightItem > maxHeight,"using mushroom should make you jump higher");
    }

    private float jumpWithoutItem(){
        createStaticBody();
        world.setGravity(new Vector2(GRAVITY_X,-20));
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        float initialY = player.getY();
        float previousY = initialY;
        player.moveUp(true);
        while (true) {
            step();

            float currentY = player.getY();
            if (currentY < previousY) {
                player.moveUp(false);
                break;
            }

            previousY = currentY;
        }
        return previousY;
    }
    private void createStaticBody(){
        float width = 10;
        float height = 2;
        BodyDef ground = new BodyDef();
        ground.type = BodyDef.BodyType.StaticBody;
        float groundY = INIT_Y - 10;
        ground.position.set(0, groundY);
        Body groundBody = world.createBody(ground);
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(width/2,height/2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density=1;
        fixtureDef.friction=0.5f;
        fixtureDef.restitution=0;
        fixtureDef.shape=groundShape;
        groundBody.createFixture(fixtureDef);
    }


    @Test
    public void testNoDuplicateUseEnergy(){
        player.moveRight(true);
        ItemEnergy energy = new ItemEnergy(bus,world,player.getX(),player.getY());
        world.setContactListener(energy);
        step();
        float startX=player.getX();
        player.useItem();
        for(int i = 0; i < NUM_ITERATIONS; i++){
            step();
        }
        float diffX = player.getX() - startX;
        //Using energy item  twice or more should not give more speed.
        player.useItem();
        player.useItem();
        startX = player.getX();
        for(int i = 0; i < NUM_ITERATIONS; i++){
            step();
        }
        //Approximating the differences, we are working with floating points
        float tolerance=1f;
        float doubleEnergyDiff = player.getX() - startX;
        assertTrue(diffX - tolerance < doubleEnergyDiff && doubleEnergyDiff < diffX + tolerance);
    }
    @Test
    public void testNoDuplicateUseMushroom() {
        //TODO
    }
}
