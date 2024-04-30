package inf112.skeleton.app.model.event;

import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.model.PlayerAction;

public record EventPlayerAction(PlayerAction action) implements Event {
}
