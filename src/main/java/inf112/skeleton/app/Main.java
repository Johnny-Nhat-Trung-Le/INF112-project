package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import inf112.skeleton.app.controller.Controller;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.GameModel;
import inf112.skeleton.app.model.tiles.*;
import inf112.skeleton.app.view.GameView;

public class Main {
    public static void main(String[] args) {
        loadClasses();

        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("game");
        cfg.setWindowedMode(480, 320);

        EventBus bus = new EventBus();

        GameModel model = new GameModel(bus);

        Controller controller = new Controller(model);
        bus.addEventHandler(controller);

        GameView view = new GameView(model, bus, controller);

        new Lwjgl3Application(view, cfg);

    }

    private static void loadClasses() {
        TileModel.loadStatic();

        TileGroundSingle.loadStatic();
        TileGround.loadStatic();
        TileGroundLeft.loadStatic();
        TileGroundRight.loadStatic();

        TileFloatingGroundSingle.loadStatic();
        TileFloatingGround.loadStatic();
        TileFloatingGroundLeft.loadStatic();
        TileFloatingGroundRight.loadStatic();

        TileFloatingGroundSingleSlim.loadStatic();
        TileFloatingGroundSlim.loadStatic();
        TileFloatingGroundLeftSlim.loadStatic();
        TileFloatingGroundRightSlim.loadStatic();

        Spike.loadStatic();
        Barrel.loadStatic();
    }
}