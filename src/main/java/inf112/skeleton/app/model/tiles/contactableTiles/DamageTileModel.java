package inf112.skeleton.app.model.tiles.contactableTiles;

import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.event.EventDamage;
import inf112.skeleton.app.model.tiles.TileModel;

public abstract class DamageTileModel extends TileModel implements ContactableTiles {
    private final int DAMAGE;
    private final EventBus eventBus;

    /**
     * @param world  that the {@link Body} is created in
     * @param bus    that {@link EventDamage} is posted in
     * @param x      center in x-axis
     * @param y      center in y-axis
     * @param w      full width
     * @param h      full width
     * @param damage amount of damage on contact
     */
    protected DamageTileModel(World world, EventBus bus, float x, float y, float w, float h, int damage) {
        super(world, x, y, w, h);
        DAMAGE = damage;
        eventBus = bus;

        eventBus.addEventHandler(this);
        body.getFixtureList().get(0).setUserData(this);
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fA = contact.getFixtureA();
        Fixture fB = contact.getFixtureB();

        if (this.equals(fB.getUserData())) {
            eventBus.post(new EventDamage(DAMAGE, fA));
        } else if (this.equals(fA.getUserData())) {
            eventBus.post(new EventDamage(DAMAGE, fB));
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
