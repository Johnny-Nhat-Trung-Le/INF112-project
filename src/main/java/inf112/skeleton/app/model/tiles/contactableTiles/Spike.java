package inf112.skeleton.app.model.tiles.contactableTiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.model.GameModel;
import inf112.skeleton.app.model.PlayerModel;
import inf112.skeleton.app.model.TileFactory;
import inf112.skeleton.app.model.tiles.TileModel;

import java.util.HashSet;

public class Spike extends TileModel implements ContactableTiles {

    public static void loadStatic(){}

    static {
        TileFactory.register('S', (world, eventBus, x, y) -> {
            return new Spike(world, x + TILE_WIDTH / 2, y + TILE_HEIGHT / 4, TILE_WIDTH/2, TILE_HEIGHT/2);
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
    public Spike(World world, float x, float y, float w, float h) {
        super(world, x, y, w, h);
    }

    @Override
    protected Shape createShape(float w, float h) {
        Vector2[] vecBottom = new Vector2[3];
        vecBottom[0] = new Vector2(-TILE_WIDTH / 2, -TILE_HEIGHT / 2); // BL
        vecBottom[1] = new Vector2(TILE_WIDTH / 2, -TILE_HEIGHT / 2); // BR
        vecBottom[2] = new Vector2(0, TILE_HEIGHT/4+TILE_HEIGHT/14); // C
        PolygonShape shape= new PolygonShape();
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
        fDef.shape = createShape(this.getWidth(),this.getHeight());

        Body b = world.createBody(bDef);
        Fixture spikeFixture = b.createFixture(fDef);
        spikeFixture.setUserData("spikeData");
        return b;
    }
    @Override
    public void beginContact(Contact contact) {
        Fixture fA = contact.getFixtureA();
        Fixture fB = contact.getFixtureB();
        if(fB.getUserData()!= null  && fB.getUserData().equals("spikeData")) {
            //TODO trenger jeg egentlig if statementet, tross alt trenger jeg ikke å vite hvilke pos karakter skader seg
            if (fA.getUserData().equals(PlayerModel.userDataTop)) {
                System.out.println("Spike.Top");
            } else if (fA.getUserData().equals(PlayerModel.userDataBottom) && fB.getUserData() != null && fB.getUserData().equals("spikeData")) {
                System.out.println("Bottom");
            } else if (fA.getUserData().equals(PlayerModel.userDataLeft)) {
                System.out.println("Left");
            } else if (fA.getUserData().equals(PlayerModel.userDataRight)) {
                System.out.println("Right");
            }
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