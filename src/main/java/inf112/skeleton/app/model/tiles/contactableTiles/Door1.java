package inf112.skeleton.app.model.tiles.contactableTiles;

import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.event.EventReachedDoor;
import inf112.skeleton.app.model.tiles.TileModel;

public class Door1 extends TileModel implements ContactableTiles {
    public static final char KEY = '9';
    private static final float DOOR_WIDTH = TILE_WIDTH / 2;
    private static final float DOOR_HEIGHT = TILE_HEIGHT / 2;
    private final EventBus eventBus;
    private final String USERDATA = "Door1";

    /**
     * Creates a {@link TileModel} with default width and height.
     * <p>
     * Used for {@link inf112.skeleton.app.model.TileFactory}.
     *
     * @param world that the body is added to
     * @param bus   that is used for handling and posting {@link inf112.skeleton.app.event.Event}s
     * @param x     left-most position in the horizontal axis
     * @param y     bottom-most position in the vertical axis
     */
    public Door1(World world, EventBus bus, float x, float y) {
        this(world, bus, x + TILE_WIDTH / 2, y - TILE_HEIGHT / 4, TILE_WIDTH / 2, TILE_HEIGHT / 2);
    }

    /**
     * Creates a {@link TileModel} and places its body in
     * the specified {@link World}.
     *
     * @param world that the body is added to
     * @param x     center position in the horizontal axis
     * @param y     center position in the vertical axis
     * @param w     width of body
     * @param h     height of body
     */
    public Door1(World world, EventBus bus, float x, float y, float w, float h) {
        super(world, x, y, w, h);
        eventBus = bus;
        eventBus.addEventHandler(this);
    }

    @Override
    protected Body createBody(float x, float y) {
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.position.set(x, y);

        FixtureDef fDef = new FixtureDef();
        fDef.density = 1;
        fDef.friction = 0.5f;
        fDef.restitution = 0;
        fDef.shape = super.createShape(DOOR_WIDTH, DOOR_HEIGHT);

        Body b = world.createBody(bDef);
        Fixture doorFixture = b.createFixture(fDef);
        doorFixture.setUserData(USERDATA);
        return b;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fB = contact.getFixtureB();

        if (USERDATA.equals(fB.getUserData())) {
            eventBus.post(new EventReachedDoor());
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
