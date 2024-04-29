package inf112.skeleton.app.event;

import inf112.skeleton.app.model.GameModel;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.model.event.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEventBus {
    private EventBus eventBus;
    private List<Event> eventList;
    private EventDeath eventDeath;
    private EventReachedDoor eventReachedDoor;
    private EventGameState eventGameState;

    @BeforeEach
    public void setUp() {
        eventBus = new EventBus();
        eventList = new ArrayList<>();
        eventBus.addEventHandler(this::handleEvent);
        eventDeath = new EventDeath(this);
        eventReachedDoor = new EventReachedDoor();
        eventGameState = new EventGameState(GameState.ACTIVE);
    }

    @Test
    public void testEventDeath() {
        eventBus.post(eventDeath);
        assertEquals(1, eventList.size(), "EventList size should be 1");
        assertTrue(eventList.contains(eventDeath));
        assertEquals(eventDeath, eventList.get(0), "Event at index 0 should be EventDeath");
    }

    @Test
    public void testEventReachedDoor() {
        eventBus.post(eventReachedDoor);
        assertEquals(1, eventList.size(), "EventList size should be 1");
        assertTrue(eventList.contains(eventReachedDoor));
        assertEquals(eventReachedDoor, eventList.get(0), "Event at index 0 should be EventReachedDoor");
    }

    @Test
    public void testEventGameState() {
        eventBus.post(eventGameState);
        assertEquals(1, eventList.size(), "EventList size should be 1");
        assertTrue(eventList.contains(eventGameState));
        assertEquals(eventGameState, eventList.get(0), "Event at index 0 should be EventGameState");
        assertEquals(GameState.ACTIVE, eventGameState.gameState(), "GameState should be ACTIVE");
    }


    @Test
    public void testMultipleEvents() {
        eventBus.post(eventGameState);
        eventBus.post(eventDeath);
        eventBus.post(eventReachedDoor);
        assertEquals(3, eventList.size(), "EventList size should be 3");
        assertEquals(eventGameState, eventList.get(0), "Event at index 0 should be EventGameState");
        assertEquals(eventDeath, eventList.get(1), "Event at index 1 should be EventDeath");
        assertEquals(eventReachedDoor, eventList.get(2), "EVent at index 2 should be EventReachedDoor");

    }

    /**
     * Adds event to eventList
     * @param event
     */
    private void handleEvent(Event event) {
       eventList.add(event);
    }

}


