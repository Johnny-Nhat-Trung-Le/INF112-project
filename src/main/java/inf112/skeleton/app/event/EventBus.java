package inf112.skeleton.app.event;

import java.util.ArrayList;
import java.util.List;

public class EventBus {

    private final List<EventHandler> handlers;

    public EventBus() {
        handlers = new ArrayList<>();
    }

    /**
     * Posts a new event that is handled by all the
     * {@link EventHandler}s in the {@link EventBus}.
     *
     * @param e the event posted
     */
    public void post(Event e) {
        for (EventHandler handler : handlers) {
            handler.handleEvent(e);
        }
    }

    /**
     * Adds an {@link EventHandler} to the {@link EventBus}
     * that now handles events called by EventBus::post.
     *
     * @param handler the {@link EventHandler} that is added to
     *                the {@link EventBus}
     */
    public void addEventHandler(EventHandler handler) {
        handlers.add(handler);
    }
}
