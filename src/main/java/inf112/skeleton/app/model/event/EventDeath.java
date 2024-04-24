package inf112.skeleton.app.model.event;

import inf112.skeleton.app.event.Event;

public record EventDeath(Object owner) implements Event {
}
