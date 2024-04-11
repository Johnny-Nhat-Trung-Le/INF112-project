package inf112.skeleton.app.model;

import com.badlogic.gdx.physics.box2d.Body;

public interface Physicable {
    /**
     * @return the body of the Object
     */
    Body getBody();
}
