package inf112.skeleton.app.TileMapTesting.objects.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import static inf112.skeleton.app.TileMapTesting.helper.Constants.PPM;

public class Player extends GameEntity {
    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 10f;
    }

    @Override
    public void update() {
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
    }

    @Override
    public void render(SpriteBatch batch) {

    }

    private void checkUserInput() {
        velX = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            velX = 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            velX = -1;
        }
        body.setLinearVelocity(velX * speed, -25f);}

}
