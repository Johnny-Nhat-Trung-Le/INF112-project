package inf112.skeleton.app.event;

public interface EventHandler {
    /**
     * Handles an {@link Event} posted in the {@link EventBus}.
     *
     * @param event event posted in {@link EventBus}
     */
    void handleEvent(Event event);
}
