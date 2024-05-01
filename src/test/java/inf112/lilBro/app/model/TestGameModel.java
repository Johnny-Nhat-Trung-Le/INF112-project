package inf112.lilBro.app.model;

import inf112.lilBro.app.controller.ControllablePlayerModel;
import inf112.lilBro.app.event.Event;
import inf112.lilBro.app.event.EventBus;
import inf112.lilBro.app.model.event.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class TestGameModel {
    private static final float DT = 1 / 60f;
    private List<Event> events;
    private GameModel model;
    private EventBus bus;

    @BeforeEach
    public void setup() {
        events = new CopyOnWriteArrayList<>();
        bus = new EventBus();
        bus.addEventHandler(this::handleEvent);
        model = new GameModel(bus);

    }

    private void step() {
        bus.post(new EventStep(DT));
    }

    private void handleEvent(Event event) {
        events.add(event);
    }

    @Test
    public void SetMainToActive() {
        GameState initGameState = model.getState();
        model.setState(GameState.ACTIVE);
        assertEquals(1, events.size(), "Should have been one event called");
        assertEquals(new EventGameState(GameState.ACTIVE), events.get(0), "The only event called should have been an EventGameState");
        assertNotEquals(initGameState, model.getState());
        assertEquals(GameState.ACTIVE, model.getState(), "The GameState of the gamemodel should have been switched from main menu to active");
    }

    @Test
    public void setActiveToPause() {
        model.setState(GameState.ACTIVE);
        assertEquals(GameState.ACTIVE, model.getState(), "The GameState of the gamemodel should have been switched from main menu to active");
        model.setState(GameState.PAUSE);
        assertEquals(2, events.size(), "There should have been called 2 events");
        assertEquals(new EventGameState(GameState.PAUSE), events.get(1), "The final event should have been a pause event");
        assertEquals(GameState.PAUSE, model.getState());

    }

    @Test
    public void SetGameOver() {
        model.setState(GameState.GAME_OVER);
        assertEquals(2, events.size(), "Should have been two event calls");
        assertEquals(new EventLevelChanged(), events.get(0), "The first event should have been a EventLevelChanged event");
        assertEquals(GameState.GAME_OVER, model.getState(), "The state of GameModel should have switched to GameState.Game_Over");
    }

    @Test
    public void handleEventReachedDoor() {
        GameState initGameState = model.getState();
        assertEquals(GameState.MAIN_MENU, initGameState, "The gameState should start as Main_Menu");
        bus.post(new EventReachedDoor());
        assertEquals(2, events.size(), "There should have been two events posted");
        assertEquals(new EventReachedDoor(), events.get(0), "The first event should be the event posted");
        assertEquals(new EventGameState(GameState.VICTORY), events.get(1), "The second event should be of EventGameState");
        assertEquals(GameState.VICTORY, model.getState());
    }

    @Test
    public void handleEventDeath() {
        GameState initGameState = model.getState();
        ControllablePlayerModel playerModel = model.getControllableLevel().getControllablePlayer();
        assertEquals(GameState.MAIN_MENU, initGameState, "The gameState should start as Main_Menu");
        bus.post(new EventDeath(playerModel));
        assertEquals(3, events.size(), "There should have been three events posted");
        assertEquals(new EventDeath(playerModel), events.get(0), "The first event should be the event posted");
        assertEquals(new EventLevelChanged(), events.get(1), "The second should be of EventLevelChanged");
        assertEquals(new EventGameState(GameState.GAME_OVER), events.get(2), "The third event should be of EventGameState");
        assertEquals(GameState.GAME_OVER, model.getState());
    }

    @Test
    public void testSetNonExistingLevel() {
        assertEquals(GameState.MAIN_MENU, model.getState());
        model.setLevel("test");
        assertNull(model.getControllableLevel());
        assertNull(model.getViewableLevel());
        assertEquals(new EventLevelChanged(), events.get(0));
    }

    @Test
    public void testExistingLevel() {
        assertEquals(GameState.MAIN_MENU, model.getState());
        model.setLevel("1");
        assertNotNull(model.getControllableLevel());
        assertNotNull(model.getViewableLevel());
        assertEquals(new EventLevelChanged(), events.get(0));
    }

    @Test
    public void testSetLevel() {
        String levelKey = "1";
        model.setLevel(levelKey);
        model.setState(GameState.ACTIVE);

        assertEquals(GameState.ACTIVE, model.getState(), "The game-state has not been set to " + GameState.ACTIVE);
        assertNotNull(model.getViewableLevel(), "The level does not exist, key: " + levelKey);

        model.setLevel(levelKey);
        model.setLevel(levelKey);
        model.setLevel(levelKey);
        model.setLevel(levelKey);

        model.getControllableLevel().getControllablePlayer().moveRight(true);

        events.clear();
        bus.post(new EventReachedDoor());

        assertEquals(2, events.size(), "There should only be two events posted!");
        assertInstanceOf(EventReachedDoor.class, events.get(0), "The first event should be " + EventReachedDoor.class);
        assertInstanceOf(EventGameState.class, events.get(1), "The first event should be " + EventLevelChanged.class);
        assertEquals(GameState.VICTORY, ((EventGameState) events.get(1)).gameState(), "The game-state should be set to " + GameState.VICTORY);

        System.out.println(events);
    }

}

