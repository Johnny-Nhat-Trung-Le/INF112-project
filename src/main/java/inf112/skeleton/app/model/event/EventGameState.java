package inf112.skeleton.app.model.event;

import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.model.GameState;

public record EventGameState(GameState gameState) implements Event {
}
