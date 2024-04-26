package inf112.skeleton.app.model.tiles;

import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.event.EventHandler;
import inf112.skeleton.app.model.Stepable;
import inf112.skeleton.app.model.event.EventDispose;
import inf112.skeleton.app.view.ViewableTile;

import java.util.Objects;


public abstract class TileModel implements ViewableTile, Stepable, EventHandler {
    public static final float TILE_WIDTH = 5;
    public static final float TILE_HEIGHT = 5;
    protected final World world;
    protected final Body body;
    private final Shape shape;
    private final float width;
    private final float height;

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
    public TileModel(World world, EventBus bus, float x, float y) {
        this(world, x + TILE_WIDTH / 2, y + TILE_HEIGHT / 2, TILE_WIDTH, TILE_HEIGHT);
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
    public TileModel(World world, float x, float y, float w, float h) {
        this.world = world;
        width = w;
        height = h;
        shape = createShape(w, h);
        body = createBody(x, y);
    }

    /**
     * Must be called after {@link Shape} has been initialized
     * to set the body's shape.
     */
    protected Body createBody(float x, float y) {
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.position.set(x, y);

        FixtureDef fDef = new FixtureDef();
        fDef.density = 1;
        fDef.friction = 0.5f;
        fDef.restitution = 0;
        fDef.shape = shape;

        Body b = world.createBody(bDef);
        b.createFixture(fDef);

        return b;
    }

    /**
     * @param w width
     * @param h height
     * @return {@link PolygonShape} with the specified width and height
     */
    protected Shape createShape(float w, float h) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(w / 2, h / 2);
        return shape;
    }

    @Override
    public float getX() {
        return body.getPosition().x - width / 2;
    }

    @Override
    public float getY() {
        return body.getPosition().y - height / 2;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void step(float timeStep) {
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof EventDispose) {
            shape.dispose();
        }
    }
}