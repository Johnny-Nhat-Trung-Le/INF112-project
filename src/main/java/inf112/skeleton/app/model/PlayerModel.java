package inf112.skeleton.app.model;


import com.badlogic.gdx.physics.box2d.*;
import inf112.skeleton.app.controller.ControllablePlayerModel;
import inf112.skeleton.app.view.ViewablePlayerModel;


public class PlayerModel implements ControllablePlayerModel, ViewablePlayerModel, Physicable {
    private float x=0;
    private float y = 0;
    private float width = 40;

    private float height = 40;


    private World world;
    private final Body body;
    private PolygonShape shape;

    private PlayerState playerState;
    public PlayerModel(World world){
        //TODO
        this.world = world;
        this.playerState = PlayerState.IDLE;
        this.body = this.world.createBody(createBodyDef(this.x,this.y));
        body.createFixture(createFixtureDef(this.width,this.height));
        this.shape.dispose();

        
    }

    @Override
    public void moveUp(boolean value) {
        System.out.println("PlayerModel.moveUp");
        this.playerState = PlayerState.JUMP;
    }

    @Override
    public void moveDown(boolean value) {
        System.out.println("PlayerModel.moveDown");
        //TODO HVORDAN TENKE VI DOWN
        this.playerState = PlayerState.DOWN;
    }

    @Override
    public void moveLeft(boolean value) {
        System.out.println("PlayerModel.moveLeft");
        this.playerState = PlayerState.LEFT;


    }

    @Override
    public void moveRight(boolean value) {
        System.out.println("PlayerModel.moveRight");
        this.playerState = PlayerState.RIGHT;
    }
    @Override
    public void moveIdle(boolean value){
        this.playerState = PlayerState.IDLE;
    }

    @Override
    public void useItem() {

    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public float getWidth() {
        return this.width;
    }

    @Override
    public float getHeight() {
        return this.height;
    }

    @Override
    public Body getBody() {
        return this.body;
    }

    @Override
    public void step() {

    }

    protected BodyDef createBodyDef(float x, float y) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(x, y);
        return def;
    }

    protected FixtureDef createFixtureDef(float w, float h) {
        FixtureDef def = new FixtureDef();
        this.shape = new PolygonShape();
        shape.setAsBox(w, h);
        def.density = 0.5f;
        def.friction = 0.5f;
        def.shape = shape;
        return def;
    }


    @Override
    public PlayerState getPlayerState() {
        return this.playerState;
    }
}
