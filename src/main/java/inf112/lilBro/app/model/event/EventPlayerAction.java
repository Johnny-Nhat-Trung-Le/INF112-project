package inf112.lilBro.app.model.event;

import inf112.lilBro.app.event.Event;
import inf112.lilBro.app.model.PlayerAction;

public record EventPlayerAction(PlayerAction action) implements Event {
}
