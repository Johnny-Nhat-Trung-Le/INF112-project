package inf112.skeleton.app.model;

public interface Stepable {
    /**
     * Is called for each step (frame) in the program.
     *
     * @param timeStep the time between the last step
     */
    void step(float timeStep);
}
