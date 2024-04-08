package inf112.skeleton.app.model.tiles.contactableTiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.PlayerModel;
import inf112.skeleton.app.model.TileFactory;
import inf112.skeleton.app.model.event.EventDamage;
import inf112.skeleton.app.model.tiles.TileModel;


public class Saw extends TileModel implements ContactableTiles {
    private final EventBus eventBus;
    private final int DAMAGE = 1;
    private final String USERDATA = "SawData";

    public static void loadStatic() {
    }

    static {
        TileFactory.register('s', (world, eventBus, x, y) -> {
            return new Saw(world, eventBus, x + TILE_WIDTH / 2, y + TILE_HEIGHT / 4, TILE_WIDTH / 2, TILE_HEIGHT / 2);
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
    public Saw(World world, EventBus bus, float x, float y, float w, float h) {
        super(world, x, y, w, h);
        eventBus = bus;
        eventBus.addEventHandler(this);

    }

    @Override
    protected Shape createShape(float w, float h) {
        PolygonShape halfCircleShape = new PolygonShape();
        float radius = w / 8; //
        float[] vertices = new float[12];
        for (int i = 0; i < 6; i++) {
            float angle = (i / 6.0f) * 90f; // Angle ranges from 0 to 90 degrees
            vertices[i * 2] = (float) Math.cos(Math.toRadians(angle)) * radius; // x-coordinate
            vertices[i * 2 + 1] = (float) Math.sin(Math.toRadians(angle)) * radius; // y-coordinate
        }

        halfCircleShape.set(vertices);
        return halfCircleShape;
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
        if (fB.getUserData() != null && fB.getUserData().equals(USERDATA) && PlayerModel.userDataSet.contains((String) fA.getUserData())) {
            eventBus.post(new EventDamage(DAMAGE));
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