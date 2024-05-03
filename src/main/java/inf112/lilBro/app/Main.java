package inf112.lilBro.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.lilBro.app.controller.Controller;
import inf112.lilBro.app.event.EventBus;
import inf112.lilBro.app.model.GameModel;
import inf112.lilBro.app.view.GameView;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Lil-Bros-Adventure-Back-Home");
        cfg.setWindowedMode(1400, 1024);

        EventBus bus = new EventBus();

        GameModel model = new GameModel(bus);

        Controller controller = new Controller(model);
        bus.addEventHandler(controller);

        GameView view = new GameView(model, bus, controller);
        new Lwjgl3Application(view, cfg);

    }
}