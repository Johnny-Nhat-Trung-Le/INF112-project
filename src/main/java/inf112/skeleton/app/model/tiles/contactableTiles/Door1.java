package inf112.skeleton.app.model.tiles.contactableTiles;

import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.model.PlayerModel;
import inf112.skeleton.app.model.TileFactory;
import inf112.skeleton.app.model.event.EventDamage;
import inf112.skeleton.app.model.event.EventGameState;
import inf112.skeleton.app.model.tiles.TileModel;

public class Door1 extends TileModel implements ContactableTiles{
    private final EventBus eventBus;
    private static final float DOORWIDTH = TILE_WIDTH/2;
    private static final float DOORHEIGHT = TILE_HEIGHT/2;
    private final String USERDATA = "Door1";
    public static void loadStatic(){}

    static {
        TileFactory.register('9', (world, eventBus, x, y) -> {
            return new Door1(world, eventBus,x + TILE_WIDTH / 2, y - TILE_HEIGHT / 4, DOORWIDTH, DOORHEIGHT);
        });
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
        fDef.shape = super.createShape(DOORWIDTH,DOORHEIGHT);

        Body b = world.createBody(bDef);
        Fixture doorFixture = b.createFixture(fDef);
        doorFixture.setUserData(USERDATA);
        return b;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fA = contact.getFixtureA();
        Fixture fB = contact.getFixtureB();

        if (fB.getUserData() != null && fB.getUserData().equals(USERDATA) && PlayerModel.isContacted(fA)) {
            eventBus.post(new EventGameState(GameState.VICTORY));
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
