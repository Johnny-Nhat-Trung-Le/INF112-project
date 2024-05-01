package inf112.lilBro.app.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.lilBro.app.event.Event;
import inf112.lilBro.app.event.EventBus;
import inf112.lilBro.app.model.event.EventDeath;
import inf112.lilBro.app.model.event.EventItemPickedUp;
import inf112.lilBro.app.model.item.ItemEnergy;
import inf112.lilBro.app.model.item.ItemHP;
import inf112.lilBro.app.model.item.ItemModel;
import inf112.lilBro.app.model.item.ItemMushroom;
import inf112.lilBro.app.model.tiles.TileModel;
import inf112.lilBro.app.model.tiles.contactableTiles.Saw;
import inf112.lilBro.app.model.tiles.contactableTiles.Spike;
import inf112.lilBro.app.utils.ContactListeners;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayerModel {
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;
    private static final int NUM_ITERATIONS = 60;
    private static final float DT = 1 / 60f;
    private static final float INIT_X = 0;
    private static final float INIT_Y = 30;
    private static final float WIDTH = TileModel.TILE_WIDTH;
    private static final float HEIGHT = TileModel.TILE_HEIGHT;
    private static final float GRAVITY_X = 0;
    private static final float GRAVITY_Y = 0;
    private World world;
    private EventBus bus;
    private PlayerModel player;
    private ContactListeners contacts;
    private List<Event> events;

    private void step() {
        player.step(DT);
        world.step(DT, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }

    private void handleEvent(Event e) {
        events.add(e);
    }

    /**
     * @param x left-most
     * @param y bottom-most
     * @param width full
     * @param height full
     */
    private Body createStaticBody(float x, float y, float width, float height) {
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(x + width / 2, y + height / 2);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

        FixtureDef fd = new FixtureDef();
        fd.density = 1;
        fd.friction = 1;
        fd.shape = shape;

        Body b = world.createBody(bd);
        Fixture f = b.createFixture(fd);

        return b;
    }

    @BeforeEach
    public void reset() {
        world = new World(new Vector2(GRAVITY_X, GRAVITY_Y), true);
        bus = new EventBus();
        player = new PlayerModel(bus, world, INIT_X, INIT_Y);
        contacts = new ContactListeners();
        contacts.add(player);
        world.setContactListener(contacts);
        events = new ArrayList<>();
        bus.addEventHandler(this::handleEvent);
    }

    @Test
    public void testInitialState() {
        assertEquals(INIT_X, player.getX(), "Player does not start at the horizontal position specified in the initialization! expected: " + INIT_X + ", got: " + player.getX());
        assertEquals(INIT_Y, player.getY(), "Player does not start at the vertical position specified in the initialization! expected: " + INIT_Y + ", got: " + player.getY());
    }

    @Test
    public void testStandStill() {
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
            assertEquals(INIT_X, player.getX(), "Player horizontal position changes on step, without call to the move-methods!");
            assertEquals(INIT_Y, player.getY(), "Player vertical position changes on step, without call to the move-methods!");
        }
    }

    @Test
    public void testMoveRight() {
        float lastX = INIT_X;

        player.moveRight(true);

        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
            assertTrue(lastX < player.getX(), "Player is not moving after moveRight(true) has been called!");
            assertEquals(INIT_Y, player.getY(), "Player is moving in the vertical axis when moveRight(true) has been called!");
            lastX = player.getX();
        }
    }

    @Test
    public void testMoveLeft() {
        float lastX = INIT_X;

        player.moveLeft(true);

        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
            assertTrue(lastX > player.getX(), "Player is not moving after moveRight(true) has been called!");
            assertEquals(INIT_Y, player.getY(), "Player is moving in the vertical axis when moveRight(true) has been called!");
            lastX = player.getX();
        }
    }

    @Test
    public void testMoveDown() {
        float lastY = INIT_Y;

        player.moveDown(true);

        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
            assertTrue(lastY > player.getY(), "Player is not moving downwards after moveDown(true) has been called!");
            assertEquals(INIT_X, player.getX(), "Player is moving in the horizontal axis when moveDown(true) has been called!");
            lastY = player.getY();
        }
    }

    @Test
    public void testMoveUp() {
        // Create Static Body under Player
        float width = 10;
        float height = 2;
        BodyDef ground = new BodyDef();
        ground.type = BodyDef.BodyType.StaticBody;
        float groundY = INIT_Y - 10;
        ground.position.set(0, groundY);
        Body groundBody = world.createBody(ground);
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(width / 2, height / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0;
        fixtureDef.shape = groundShape;
        groundBody.createFixture(fixtureDef);

        world.setGravity(new Vector2(GRAVITY_X, -20));

        for (int i = 0; i < NUM_ITERATIONS * 2; i++) {
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

        float topY = player.getY();
        assertTrue(topY > initialY);

        for (int i = 0; i < NUM_ITERATIONS * 2; i++) {
            step();
        }
        float finalY = player.getY();
        assertTrue(finalY < topY);
        assertEquals(finalY, initialY);
        // Reset the gravity
        world.setGravity(new Vector2(GRAVITY_X, GRAVITY_Y));
    }

    @Test
    public void testSpike() {
        Spike spike = new Spike(world, bus, INIT_X, player.getY(), WIDTH, HEIGHT);
        int init_Hp = player.getHp();
        contacts.add(spike);
        player.moveRight(true);
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        assertEquals(init_Hp - 1, player.getHp(), "Player is suppose to lose 1 hp");
    }

    @Test
    public void testImmunityDoubleSaw() {
        Saw saw = new Saw(world, bus, player.getX() - player.getWidth(), player.getY(), WIDTH);
        Saw saw1 = new Saw(world, bus, saw.getX() - saw.getWidth(), saw.getY(), WIDTH);
        contacts.add(saw);
        contacts.add(saw1);
        int init_Hp = player.getHp();
        player.moveLeft(true);
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
        }
        assertEquals(init_Hp - 1, player.getHp(), "Player is suppose to lose 1 hp");
    }

    @Test
    public void testGetHP() {
        int playerHp = player.getHp();
        assertEquals(3, playerHp, "Player HP should be 3");
    }

    private void initTestDeathBySpike() {
        float pl = player.getX();
        float pb = player.getY();
        float pw = player.getWidth();
        float ph = player.getHeight();
        float pt = pb + ph;
        float pr = pl + pw;

        createStaticBody(pl - pw * 2, pt, pw * 5, 1);
        Spike spikeL = new Spike(world, bus, pl - pw * 3 / 2, pb, pw, 1);
        Spike spikeR = new Spike(world, bus, pr + pw * 3 / 2, pb, pw, 1);
        contacts.add(spikeL);
        contacts.add(spikeR);
    }

    @Test
    public void testDeathBySpike() {
        initTestDeathBySpike();
        boolean r = true;

        while (player.getHp() > 0) {
            if (r) player.moveRight(true);
            else player.moveLeft(true);
            for (int i = 0; i < NUM_ITERATIONS; i++) step();
            if (r) player.moveRight(false);
            else player.moveLeft(false);
            r = !r;
        }

        List<EventDeath> deaths = events.stream().filter((e) -> e instanceof EventDeath).map((e) -> (EventDeath) e).toList();
        assertEquals(1, deaths.size(), "Player did not post a single " + EventDeath.class + " when HP got to zero!");
        assertEquals(player, deaths.get(0).owner(), "The " + EventDeath.class + " did not have the owner as the Player!");
    }

    @Test
    public void testItemPickup() {
        ItemModel item = new ItemEnergy(bus, world, INIT_X, INIT_Y);
        contacts.add(item);
        step();

        assertEquals(item, player.getItem(), "Item that player got was not the item picked up");
    }

    @Test
    public void testMultipleItemPickup() {
        ItemModel item1 = new ItemEnergy(bus, world, INIT_X, INIT_Y);
        ItemModel item2 = new ItemEnergy(bus, world, INIT_X, INIT_Y);
        contacts.add(item1);
        contacts.add(item2);

        step();

        List<EventItemPickedUp> pickUp = events.stream().filter((e) -> e instanceof EventItemPickedUp).map((e) -> (EventItemPickedUp) e).toList();
        assertEquals(1, pickUp.size(), "Not only one " + EventItemPickedUp.class + " was posted on two items on Player");
        assertEquals(item1, pickUp.get(0).item(), "The item picked up was not correct");
    }

    @Test
    public void testUseItem() {
        ItemModel item = new ItemEnergy(bus, world, INIT_X, INIT_Y);
        contacts.add(item);

        step();

        player.useItem();
        assertEquals(1, player.getEffects().size(), "There is not exactly one effect applied");

        player.useItem();
        player.useItem();
        player.useItem();

        assertEquals(1, player.getEffects().size(), "There is not exactly one effect applied after multiple of the same item used!");
    }

    @Test
    public void testMultipleItemsWithEffects() {
        ItemModel item1 = new ItemEnergy(bus, world, INIT_X, INIT_Y);
        ItemModel item2 = new ItemMushroom(bus, world, INIT_X + WIDTH, INIT_Y);

        contacts.add(item1);
        contacts.add(item2);

        step();
        while (player.getItem() != null) player.useItem();
        assertNull(player.getItem(), "Item was not used up");

        player.moveRight(true);
        while (player.getItem() == null) step();
        player.useItem();
        assertEquals(2, player.getEffects().size(), "There are not two effects applied");
        assertEquals(item1.use().getClass(), player.getEffects().get(0).getClass(), "The first effect has the wrong class");
        assertEquals(item2.use().getClass(), player.getEffects().get(1).getClass(), "The second effect has the wrong class");
    }

    @Test
    public void testEffectAppliedSpeed() {
        ItemModel item = new ItemEnergy(bus, world, INIT_X, INIT_Y);
        contacts.add(item);

        player.moveRight(true);
        for (int i = 0; i < NUM_ITERATIONS; i++) step();

        float lastX = player.getX();

        float noEffectDistance = 0;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
            noEffectDistance += player.getX() - lastX;
            lastX = player.getX();
        };

        player.useItem();
        float effectDistance = 0;
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            step();
            effectDistance += player.getX() - lastX;
            lastX = player.getX();
        }

        assertTrue(noEffectDistance < effectDistance, "Traveled further without speed-boost");
    }

    @Test
    public void testEffectHp() {
        ItemModel item = new ItemHP(bus, world, INIT_X, INIT_Y);
        contacts.add(item);

        player.moveRight(true);
        for (int i = 0; i < NUM_ITERATIONS; i++) step();
        int hp = player.getHp();

        player.useItem();

        int effectedHp = player.getHp();
        assertTrue(hp < effectedHp);
    }
}
