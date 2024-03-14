package inf112.skeleton.app.model;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.controller.ControllablePlayerModel;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.event.EventHandler;
import inf112.skeleton.app.model.event.EventDispose;
import inf112.skeleton.app.model.event.EventItemContact;
import inf112.skeleton.app.model.event.EventItemPickedUp;
import inf112.skeleton.app.model.event.EventItemUsedUp;
import inf112.skeleton.app.model.item.ItemModel;
import inf112.skeleton.app.view.ViewablePlayerModel;

import java.util.HashSet;

public class PlayerModel implements ControllablePlayerModel, ViewablePlayerModel, Physicable, EventHandler, ContactListener {
    public static final String USER_DATA_BOTTOM = "PlayerBottom";
    public static final String USER_DATA_LEFT = "PlayerLeft";
    public static final String USER_DATA_RIGHT = "PlayerRight";
    public static final String USER_DATA_TOP = "PlayerTop";
    private static final String USER_DATA_SENSOR = "PlayerSensor";
    private static final float WIDTH = 3;
    private static final float HEIGHT = 3;
    private static final float DX = 15;
    private static final float DY = 40;
    private static final float AIR_CONTROL = 0.5f;
    private static final float MAX_DX = 10;
    private static final float MAX_DY = 20;
    private static final float DENSITY = 0.5f;
    private static final float FRICTION = 0;
    private static final float FRICTION_BOTTOM = 10;
    private static final float RESTITUTION = 0;
    private final EventBus bus;
    private final World world;
    private final Body body;
    private Shape shapeTop, shapeBottom, shapeLeft, shapeRight;
    private Shape shapeSensor;
    private PlayerState state;
    private boolean moveUp, moveDown, moveLeft, moveRight;
    private int contactCountSensor = 0;
    private ItemModel item;
    private Effect effect;

    public static boolean isContacted(Fixture fixture) {
        HashSet<Object> set = new HashSet<>();
        set.add(USER_DATA_BOTTOM);
        set.add(USER_DATA_LEFT);
        set.add(USER_DATA_RIGHT);
        set.add(USER_DATA_TOP);

        return set.contains(fixture.getUserData());
    }

    /**
     * @param world which the player-{@link Body} is created in
     * @param x     left-most position of player
     * @param y     bottom-most position of player
     */
    public PlayerModel(EventBus bus, World world, float x, float y) {
        this.bus = bus;
        this.world = world;
        body = createBody(x + WIDTH / 2, y + HEIGHT / 2);
        state = PlayerState.IDLE_RIGHT;
        moveUp = false;
        moveDown = false;
        moveLeft = false;
        moveRight = false;

        bus.addEventHandler(this);
    }

    @Override
    public void moveUp(boolean value) {
        moveUp = value;
    }

    @Override
    public void moveDown(boolean value) {
        moveDown = value;
    }

    @Override
    public void moveLeft(boolean value) {
        moveLeft = value;
    }

    @Override
    public void moveRight(boolean value) {
        moveRight = value;
    }

    @Override
    public void useItem() {
        if (item == null) return;
        this.effect = item.use();
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
    public float getWidth() {
        return WIDTH;
    }

    @Override
    public float getHeight() {
        return HEIGHT;
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public void step(float timeStep) {
        // EFFECT
        if (effect != null) {
            if (effect.hasEnded()) effect = null;
            else effect.step();
        }

        // MOVEMENT
        float dx = DX;
        dx *= effect == null ? 1 : effect.getSpeedBoost();
        dx *= isGrounded() ? 1 : AIR_CONTROL;
        float dy = DY;
        dy *= effect == null ? 1 : effect.getJumpBoost();

        if (moveUp && !moveDown && isGrounded()) move(0, dy);
        if (moveDown && !moveUp && !isGrounded()) move(0, -DY);
        if (moveRight && !moveLeft) move(dx, 0);
        if (moveLeft && !moveRight) move(-dx, 0);

        updateState();
    }

    private boolean isGrounded() {
        return contactCountSensor > 0;
    }

    private void move(float dx, float dy) {
        Vector2 d = body.getLinearVelocity();
        float x = body.getPosition().x;
        float y = body.getPosition().y;

        float maxDx = MAX_DX;
        maxDx *= effect == null ? 1 : effect.getSpeedBoost();
        float maxDy = MAX_DY;
        maxDy *= effect == null ? 1 : effect.getJumpBoost();

        if (dx > 0 && d.x < maxDx || dx < 0 && d.x > -maxDx) {
            body.applyLinearImpulse(dx, 0, getX(), getY(), true);
        } else if (dx != 0) {
            body.setLinearVelocity((dx > 0 ? 1 : -1) * maxDx, d.y);
        }
        if (dy > 0 && d.y < maxDy || dy < 0 && d.y > -maxDy) {
            body.applyLinearImpulse(0, dy, getX(), getY(), true);
        } else if (dy != 0) {
            body.setLinearVelocity(d.x, (dy > 0 ? 1 : -1) * maxDy);
        }
    }

    /**
     * Initializes the {@code shape}-variable and creates
     * a {@link Body} with its fixture.
     *
     * @param x horizontal center-position of player
     * @param y vertical center-position of player
     * @return the newly created {@link Body}
     */
    private Body createBody(float x, float y) {
        createShapes();

        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(x, y);
        bDef.fixedRotation = true;

        Body body = world.createBody(bDef);
        createFixtures(body);

        return body;
    }

    /**
     * Creates and fills the {@link Shape}-variables.
     */
    private void createShapes() {
        float e = 0.02f;

        // SENSOR
        PolygonShape shapeSensor = new PolygonShape();
        shapeSensor.setAsBox(WIDTH / 2 - e, e, new Vector2(0, -HEIGHT / 2), 0);
        this.shapeSensor = shapeSensor;

        // BOTTOM
        Vector2[] vecBottom = new Vector2[3];
        vecBottom[0] = new Vector2(-WIDTH / 2 + e, -HEIGHT / 2); // BL
        vecBottom[1] = new Vector2(WIDTH / 2 - e, -HEIGHT / 2); // BR
        vecBottom[2] = new Vector2(0, 0); // C
        PolygonShape shapeBottom = new PolygonShape();
        shapeBottom.set(vecBottom);
        this.shapeBottom = shapeBottom;

        // TOP
        Vector2[] vecTop = new Vector2[3];
        vecTop[0] = new Vector2(-WIDTH / 2, HEIGHT / 2); // TL
        vecTop[1] = new Vector2(WIDTH / 2, HEIGHT / 2); // TR
        vecTop[2] = new Vector2(0, 0); // C
        PolygonShape shapeTop = new PolygonShape();
        shapeTop.set(vecTop);
        this.shapeTop = shapeTop;

        // LEFT
        Vector2[] vecLeft = new Vector2[3];
        vecLeft[0] = new Vector2(-WIDTH / 2, HEIGHT / 2 - e); // TL
        vecLeft[1] = new Vector2(0, 0); // C
        vecLeft[2] = new Vector2(-WIDTH / 2, -HEIGHT / 2 + e); // BL
        PolygonShape shapeLeft = new PolygonShape();
        shapeLeft.set(vecLeft);
        this.shapeLeft = shapeLeft;

        // RIGHT
        Vector2[] vecRight = new Vector2[3];
        vecRight[0] = new Vector2(WIDTH / 2, HEIGHT / 2 - e); // TR
        vecRight[1] = new Vector2(WIDTH / 2, -HEIGHT / 2 + e); // BR
        vecRight[2] = new Vector2(0, 0); // C
        PolygonShape shapeRight = new PolygonShape();
        shapeRight.set(vecRight);
        this.shapeRight = shapeRight;
    }

    /**
     * Creates fixtures and applies them to the {@link Body}.
     */
    private void createFixtures(Body body) {
        FixtureDef fDefSensor = new FixtureDef();
        fDefSensor.isSensor = true;
        fDefSensor.shape = shapeSensor;

        FixtureDef fDefBottom = new FixtureDef();
        fDefBottom.density = DENSITY;
        fDefBottom.friction = FRICTION_BOTTOM;
        fDefBottom.restitution = RESTITUTION;
        fDefBottom.shape = shapeBottom;

        FixtureDef fDefTop = new FixtureDef();
        fDefTop.density = DENSITY;
        fDefTop.friction = FRICTION;
        fDefTop.restitution = RESTITUTION;
        fDefTop.shape = shapeTop;

        FixtureDef fDefLeft = new FixtureDef();
        fDefLeft.density = DENSITY;
        fDefLeft.friction = FRICTION;
        fDefLeft.restitution = RESTITUTION;
        fDefLeft.shape = shapeLeft;

        FixtureDef fDefRight = new FixtureDef();
        fDefRight.density = DENSITY;
        fDefRight.friction = FRICTION;
        fDefRight.restitution = RESTITUTION;
        fDefRight.shape = shapeRight;

        body.createFixture(fDefSensor).setUserData(USER_DATA_SENSOR);
        body.createFixture(fDefBottom).setUserData(USER_DATA_BOTTOM);
        body.createFixture(fDefTop).setUserData(USER_DATA_TOP);
        body.createFixture(fDefLeft).setUserData(USER_DATA_LEFT);
        body.createFixture(fDefRight).setUserData(USER_DATA_RIGHT);
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof EventDispose) {
            shapeSensor.dispose();
            shapeBottom.dispose();
            shapeTop.dispose();
            shapeLeft.dispose();
            shapeRight.dispose();
        } else if (event instanceof EventItemContact e) {
            if (!isContacted(e.contact().getFixtureA()) && !isContacted(e.contact().getFixtureB())) return;
            if (item != null) return;
            item = e.item();
            bus.post(new EventItemPickedUp(item));
        } else if (event instanceof EventItemUsedUp e) {
            if (!e.item().equals(item)) return;
            item = null;
        }
    }

    @Override
    public PlayerState getState() {
        return this.state;
    }

    private void updateState() {
        if (isGrounded()) {
            if (moveRight && !moveLeft) {
                state = PlayerState.RIGHT;
            } else if (moveLeft && !moveRight) {
                state = PlayerState.LEFT;
            } else {
                if (state.equals(PlayerState.LEFT) || state.equals(PlayerState.JUMP_LEFT)) {
                    state = PlayerState.IDLE_LEFT;
                } else if (state.equals(PlayerState.RIGHT) || state.equals(PlayerState.JUMP_RIGHT)) {
                    state = PlayerState.IDLE_RIGHT;
                }
            }
        } else {
            if (moveLeft && !moveRight) {
                state = PlayerState.JUMP_LEFT;
            } else if (moveRight && !moveLeft) {
                state = PlayerState.JUMP_RIGHT;
            } else {
                if (state.equals(PlayerState.IDLE_LEFT) || state.equals(PlayerState.LEFT) || state.equals(PlayerState.JUMP_LEFT)) {
                    state = PlayerState.JUMP_LEFT;
                } else {
                    state = PlayerState.JUMP_RIGHT;
                }
            }
        }
    }

    @Override
    public void beginContact(Contact contact) {
        if (isSensorToGroundContact(contact)) contactCountSensor++;
        if (isBottomToGroundContact(contact)) body.setLinearVelocity(body.getLinearVelocity().x, 0);
    }

    @Override
    public void endContact(Contact contact) {
        if (isSensorToGroundContact(contact)) contactCountSensor--;
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {
    }

    private boolean isSensorToGroundContact(Contact contact) {
        Fixture fA = contact.getFixtureA();
        Fixture fB = contact.getFixtureB();

        return (USER_DATA_SENSOR.equals(fA.getUserData()) && !fB.isSensor())
                || (USER_DATA_SENSOR.equals(fB.getUserData()) && !fA.isSensor());
    }

    private boolean isBottomToGroundContact(Contact contact) {
        Fixture fA = contact.getFixtureA();
        Fixture fB = contact.getFixtureB();

        return (USER_DATA_BOTTOM.equals(fA.getUserData()) && !fB.isSensor())
                || (USER_DATA_BOTTOM.equals(fB.getUserData()) && !fA.isSensor());
    }
}
