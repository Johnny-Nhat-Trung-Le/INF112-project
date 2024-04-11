package inf112.skeleton.app.model.event;

import com.badlogic.gdx.physics.box2d.Fixture;
import inf112.skeleton.app.event.Event;

public record EventDamage(int damage, Fixture fixture) implements Event {
}

