package inf112.skeleton.app.model.tiles.contactableTiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.event.EventDamage;
import inf112.skeleton.app.model.tiles.TileModel;


public class Spike extends TileModel implements ContactableTiles {
    public static final char KEY = 'S';
    private static final int DAMAGE = 1;
    private static final String USERDATA = "spikeData";

    private final EventBus eventBus;

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
    public Spike(World world, EventBus bus, float x, float y) {
        this(world, bus, x + TILE_WIDTH / 2, y + TILE_HEIGHT / 4, TILE_WIDTH / 2, TILE_HEIGHT / 2);
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
    public Spike(World world, EventBus bus, float x, float y, float w, float h) {
        super(world, x, y, w, h);
        eventBus = bus;
        eventBus.addEventHandler(this);

    }

    @Override
    protected Shape createShape(float w, float h) {
        Vector2[] vecBottom = new Vector2[3];
        vecBottom[0] = new Vector2(-TILE_WIDTH / 4, -TILE_HEIGHT / 4); // BL
        vecBottom[1] = new Vector2(TILE_WIDTH / 4, -TILE_HEIGHT / 4); // BR
        vecBottom[2] = new Vector2(0, TILE_HEIGHT / 8 + TILE_HEIGHT / 14); // C
        PolygonShape shape = new PolygonShape();
        shape.set(vecBottom);
        return shape;
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
        fDef.shape = createShape(this.getWidth(), this.getHeight());

        Body b = world.createBody(bDef);
        Fixture spikeFixture = b.createFixture(fDef);
        spikeFixture.setUserData(USERDATA);
        return b;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fA = contact.getFixtureA();
        Fixture fB = contact.getFixtureB();
        if (USERDATA.equals(fB.getUserData())) {
            eventBus.post(new EventDamage(DAMAGE, fA));
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