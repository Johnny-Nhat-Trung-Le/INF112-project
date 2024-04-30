package inf112.skeleton.app.model;

import inf112.skeleton.app.controller.ControllableGameModel;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.event.EventGameState;
import inf112.skeleton.app.model.event.EventLevelChanged;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class TestGameModel {
    List<Event> events;
    private ControllableGameModel gameModel;
    private EventBus bus;

    @BeforeEach
    public void setup() {
        events = new CopyOnWriteArrayList<>();
        bus = new EventBus();
        bus.addEventHandler(this::handleEvent);
        gameModel = new GameModel(bus);
    }

    private void handleEvent(Event event) {
        events.add(event);
    }

    @Test
    public void SetMainToActive() {
        GameState initGameState = gameModel.getState();
        gameModel.setState(GameState.ACTIVE);
        assertEquals(1, events.size(), "Should have been one event called");
        assertEquals(new EventGameState(GameState.ACTIVE), events.get(0), "The only event called should have been an EventGameState");
        assertNotEquals(initGameState, gameModel.getState());
        assertEquals(GameState.ACTIVE, gameModel.getState(), "The GameState of the gamemodel should have been switched from main menu to active");
    }

    @Test
    public void setActiveToPause() {
        gameModel.setState(GameState.ACTIVE);
        assertEquals(GameState.ACTIVE, gameModel.getState(), "The GameState of the gamemodel should have been switched from main menu to active");
        gameModel.setState(GameState.PAUSE);
        assertEquals(2, events.size(), "There should have been called 2 events");
        assertEquals(new EventGameState(GameState.PAUSE), events.get(1), "The final event should have been a pause event");
        assertEquals(GameState.PAUSE, gameModel.getState());

    }

    @Test
    public void SetGameOver() {
        gameModel.setState(GameState.GAME_OVER);
        assertEquals(2, events.size(), "Should have been two event calls");
        assertEquals(new EventLevelChanged(), events.get(0), "The first event should have been a EventLevelChanged event");
        assertEquals(GameState.GAME_OVER, gameModel.getState(), "The state of GameModel should have switched to GameState.Game_Over");
    }
}

