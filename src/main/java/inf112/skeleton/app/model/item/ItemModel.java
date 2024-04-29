package inf112.skeleton.app.model.item;

import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.event.EventHandler;
import inf112.skeleton.app.model.Durability;
import inf112.skeleton.app.model.effect.Effect;
import inf112.skeleton.app.model.event.EventDispose;
import inf112.skeleton.app.model.event.EventItemContact;
import inf112.skeleton.app.model.event.EventItemPickedUp;
import inf112.skeleton.app.model.event.EventItemUsedUp;
import inf112.skeleton.app.view.ViewableItem;

import java.util.Objects;
import java.util.function.Supplier;

public abstract class ItemModel implements ViewableItem, EventHandler, ContactListener {
    private static final float WIDTH = 2;
    private static final float HEIGHT = 2;
    protected Durability durability;
    protected Supplier<Effect> createEffect;
    private final Body body;
    private final World world;
    private Shape shape;
    private final EventBus bus;

    /**
     * {@code durability} and {@code createEffect} must be set manually.
     *
     * @param bus   the {@link EventBus} that is used to call {@link EventItemUsedUp}
     * @param world that the {@link Body} is created in
     * @param x     left-most position of {@link ItemModel}
     * @param y     bottom-most position of {@link ItemModel}
     */
    public ItemModel(EventBus bus, World world, float x, float y) {
        this.bus = bus;
        this.world = world;
        this.body = createBody(world, x, y);

        bus.addEventHandler(this);
    }

    /**
     * @param world that the {@link Body} is created in
     * @param x     left-most position of {@link Body}
     * @param y     bottom-most position of {@link Body}
     * @return {@link Body} of item
     */
    private Body createBody(World world, float x, float y) {
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.position.set(x + WIDTH / 2, y + HEIGHT / 2);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH / 2, HEIGHT / 2);

        FixtureDef fDef = new FixtureDef();
        fDef.isSensor = true;
        fDef.shape = shape;

        Body body = world.createBody(bDef);
        body.createFixture(fDef).setUserData(this);

        this.shape = shape;
        return body;
    }

    /**
     * Uses the item and decreases its durability. If
     * the item is used up an {@link EventItemUsedUp} will
     * be posted.
     *
     * @return a new {@link Effect} of the item
     */
    public Effect use() {
        reduceDurability();
        if (durability.remaining() <= 0) {
            bus.post(new EventItemUsedUp(this));
        }
        return createEffect.get();
    }

    private void reduceDurability() {
        durability = new Durability(Math.max(durability.remaining() - 1,0), durability.maximum());
    }

    /**
     * Destroys the body from the world.
     */
    public void destroyBody() {
        world.destroyBody(body);
    }

    @Override
    public float getWidth() {
        return WIDTH;
    }

    @Override
    public float getHeight() {
        return HEIGHT;
    }

    @Override
    public float getX() {
        return body.getPosition().x - WIDTH / 2;
    }

    @Override
    public float getY() {
        return body.getPosition().y - HEIGHT / 2;
    }

    @Override
    public Durability getDurability() {
        return new Durability(durability.remaining(), durability.maximum());
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof EventDispose) {
            shape.dispose();
        }
    }

    @Override
    public void beginContact(Contact contact) {
        if (!isContacted(contact)) return;
        bus.post(new EventItemContact(contact, this));
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

    private boolean isContacted(Contact contact) {
        return this.equals(contact.getFixtureA().getUserData())
                || this.equals(contact.getFixtureB().getUserData());
    }
}
