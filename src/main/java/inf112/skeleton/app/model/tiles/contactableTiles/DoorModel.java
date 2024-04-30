package inf112.skeleton.app.model.tiles.contactableTiles;

import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.event.EventReachedDoor;
import inf112.skeleton.app.model.tiles.TileModel;

public abstract class DoorModel extends TileModel implements ContactableTiles {
    private final EventBus eventBus;

    /**
     * Creates a {@link DoorModel} and places its body in
     * the specified {@link World}.
     *
     * @param world that the body is added to
     * @param x     center position in the horizontal axis
     * @param y     center position in the vertical axis
     * @param w     width of body
     * @param h     height of body
     */
    protected DoorModel(World world, EventBus bus, float x, float y, float w, float h) {
        super(world, x, y, w, h);
        eventBus = bus;
        body.getFixtureList().get(0).setUserData(this);
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fA = contact.getFixtureA();
        Fixture fB = contact.getFixtureB();

        if (this.equals(fB.getUserData()) || this.equals(fA.getUserData())) {
            eventBus.post(new EventReachedDoor());
        }
    }

    @Override
    public void endContact(Contact contact) {}

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}
}
