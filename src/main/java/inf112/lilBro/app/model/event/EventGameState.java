package inf112.lilBro.app.model.event;

import inf112.lilBro.app.event.Event;
import inf112.lilBro.app.model.GameState;

public record EventGameState(GameState gameState) implements Event {
}
