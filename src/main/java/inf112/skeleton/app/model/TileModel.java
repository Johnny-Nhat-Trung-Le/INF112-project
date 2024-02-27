package inf112.skeleton.app.model;

import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.view.ViewableTile;

public class TileModel implements ViewableTile, Physicable, Stepable {
    private final World world;
    private final Body body;
    private float x;
    private float y;
    private float width;
    private float height;

    public TileModel(World world, float x, float y, float w, float h) {
        this.world = world;
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        this.body = world.createBody(createBodyDef(x,y));
        body.createFixture(createFixtureDef(w, h));
    }

    protected BodyDef createBodyDef(float x, float y) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(x, y);
        return def;
    }

    protected FixtureDef createFixtureDef(float w, float h) {
        FixtureDef def = new FixtureDef();
        def.density = 0.5f;
        def.friction = 0.5f;
        def.shape = createShape(w, h);
        return def;
    }

    protected Shape createShape(float w, float h) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(w, h);
        return shape;
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
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
    public void step(float timeStep) {}
}
