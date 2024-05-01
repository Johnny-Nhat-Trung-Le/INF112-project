package inf112.skeleton.app.model;

public interface Stepable {
    /**
     * Steps through the program.
     *
     * @param timeStep the time between the last step
     */
    void step(float timeStep);
}
