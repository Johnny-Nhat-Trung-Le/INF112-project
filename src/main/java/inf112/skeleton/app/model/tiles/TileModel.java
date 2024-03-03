package inf112.skeleton.app.model.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventHandler;
import inf112.skeleton.app.model.Physicable;
import inf112.skeleton.app.model.Stepable;
import inf112.skeleton.app.model.TileFactory;
import inf112.skeleton.app.model.event.EventDispose;
import inf112.skeleton.app.view.ViewableTile;
import inf112.skeleton.app.view.texturepack.TexturePack;

import static inf112.skeleton.app.view.texturepack.TexturePack.TILE_0;

public class TileModel implements ViewableTile, Physicable, Stepable, EventHandler {
    public static final float TILE_WIDTH = 5;
    public static final float TILE_HEIGHT = 5;
    private final World world;
    private final Body body;
    private final Shape shape;
    private final float width;
    private final float height;
    //Testing
    private final String textureKey;


    public static void loadStatic() {
    }

    static {
        TileFactory.register('0', (world, eventBus, x, y) -> {
            return new TileModel(world, x + TILE_WIDTH / 2, y + TILE_HEIGHT / 2, TILE_WIDTH, TILE_HEIGHT, TILE_0);
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
    public TileModel(World world, float x, float y, float w, float h, String textureKey) {
        this.world = world;
        width = w;
        height = h;
        shape = createShape(w, h);
        body = createBody(x, y);
        this.textureKey = textureKey;
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
        fDef.density = 0.5f;
        fDef.friction = 0.5f;
        fDef.shape = shape;

        Body b = world.createBody(bDef);
        b.createFixture(fDef);

        return b;
    }

    protected Shape createShape(float w, float h) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(w / 2, h / 2);
        return shape;
    }

    @Override
    public Body getBody() {
        return body;
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


    // MEWO MEWO INNOCENT METHOD
    @Override
    public String getTextureKey() {
        return textureKey;
    }
}