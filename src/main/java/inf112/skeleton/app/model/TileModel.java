package inf112.skeleton.app.model;

import com.badlogic.gdx.physics.box2d.Body;
import inf112.skeleton.app.view.ViewableTile;

public class TileModel implements ViewableTile, Physicable, Stepable {
    @Override
    public Body getBody() {
        return null;
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }

    @Override
    public float getWidth() {
        return 0;
    }

    @Override
    public float getHeight() {
        return 0;
    }

    @Override
    public void step() {

    }
}
