package inf112.skeleton.app.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {

    private final List<EventHandler> handlers;

    public EventBus() {
        handlers = new CopyOnWriteArrayList<>();
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

    /**
     * Removes an {@link EventHandler} so it doesn't receive
     * {@code EventHandler::handle}
     *
     * @param handler to be removed
     */
    public void removeEventHandler(EventHandler handler) {
        handlers.remove(handler);
    }
}
