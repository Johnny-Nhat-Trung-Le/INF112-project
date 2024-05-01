package inf112.lilBro.app.model.event;

import inf112.lilBro.app.event.Event;

public record EventDeath(Object owner) implements Event {
}
