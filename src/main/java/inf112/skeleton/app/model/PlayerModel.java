package inf112.skeleton.app.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.controller.ControllablePlayerModel;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventHandler;
import inf112.skeleton.app.model.event.EventDispose;
import inf112.skeleton.app.view.ViewablePlayerModel;

public class PlayerModel implements ControllablePlayerModel, ViewablePlayerModel, Physicable, EventHandler {
    private static final float WIDTH = 3;
    private static final float HEIGHT = 3;
    private static final float DX = 4f;
    private static final float DY = 4f;
    private static final float MAX_DX = 8;
    private static final float MAX_DY = 8;
    // Testing
    private float velX;
    private final World world;
    private Shape shape;
    private final Body body;
    private PlayerState playerState;
    private boolean moveUp, moveDown, moveLeft, moveRight;
    // Testing
    private int jumpCounter;

    /**
     * @param world which the {@link Body} is created
     * @param x left-most position of player
     * @param y bottom-most position of player
     */
    public PlayerModel(World world, float x, float y) {
        this.world = world;
        body = createBody(x + WIDTH / 2, y + HEIGHT / 2);
        playerState = PlayerState.IDLE;
        moveUp = false;
        moveDown = false;
        moveLeft = false;
        moveRight = false;
        jumpCounter = 0;
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
    public void useItem() {}

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
        if (moveUp && !moveDown) {
            move(0,DY);
        }
        if (moveDown && !moveUp) {
            move(0,-DY);
        }
        if (moveRight && !moveLeft) {
            move(DX,0);
        }
        if (moveLeft && !moveRight) {
            move(-DX,0);
        }
        updateState();
    }

    private void move(float dx, float dy) {
        Vector2 d = body.getLinearVelocity();
        float x = body.getPosition().x;
        float y = body.getPosition().y;
        velX = 0;

//        if (dx > 0 && d.x < MAX_DX || dx < 0 && d.x > -MAX_DX){
//            body.applyLinearImpulse(dx, 0, x, y, true);
//        }
        if (dx > 0) {
            velX = DX;
        }
        if (dx < 0) {
            velX = -DX;
        }
        if(dy != 0 && jumpCounter < 2) {
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyLinearImpulse(new Vector2(0, 50), body.getPosition(), true);
            jumpCounter++;
        }

        if(body.getLinearVelocity().y == 0) {
            jumpCounter = 0;
        }
//        if (dy > 0 && d.y < MAX_DY || dy < 0 && d.y > -MAX_DY){
//            body.applyLinearImpulse(0, dy, x, y, true);
//        }
        body.setLinearVelocity(velX, body.getLinearVelocity().y < 25 ? body.getLinearVelocity().y : 25);
    }

    /**
     * Initializes the {@code shape}-variable and creates
     * a {@link Body} with its fixture.
     *
     * @param x left-most position of player
     * @param y bottom-most position of player
     * @return the newly created {@link Body}
     */
    private Body createBody(float x, float y) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH / 2, HEIGHT / 2);
        this.shape = shape;

        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(x, y);
        bDef.fixedRotation = true;

        FixtureDef fDef = new FixtureDef();
        fDef.density = 0.5f;
        fDef.friction = 0.5f;
        fDef.shape = shape;

        Body b = world.createBody(bDef);
        b.createFixture(fDef);

        return b;
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof EventDispose) {
            shape.dispose();
        }
    }

    @Override
    public PlayerState getPlayerState() {
        return this.playerState;
    }

    private void updateState() {
        Vector2 velocity = body.getLinearVelocity();

        if (isPlayerOnGround()) {
            if (velocity.x > 0.1f) {
                playerState = PlayerState.LEFT;
            } else if (velocity.x < -0.1f) {
                playerState = PlayerState.RIGHT;
            } else {
                playerState = PlayerState.IDLE;
            }
        } else if (velocity.x < 0.1f) {
            playerState = PlayerState.JUMPLEFT;
        } else {
            playerState = PlayerState.JUMPRIGHT;
        }
    }

    private boolean isPlayerOnGround() {
        return Math.abs(body.getLinearVelocity().y) < 0.1f;
    }
}
