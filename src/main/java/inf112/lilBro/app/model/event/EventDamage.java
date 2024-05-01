package inf112.lilBro.app.model.event;

import com.badlogic.gdx.physics.box2d.Fixture;
import inf112.lilBro.app.event.Event;

public record EventDamage(int damage, Fixture fixture) implements Event {
}

