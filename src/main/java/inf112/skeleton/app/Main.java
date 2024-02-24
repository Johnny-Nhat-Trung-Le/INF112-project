package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import inf112.skeleton.app.model.GameModel;
import inf112.skeleton.app.model.PlayerModel;
import inf112.skeleton.app.view.AnimationTest;
import inf112.skeleton.app.view.GameView;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("game");
        cfg.setWindowedMode(480, 320);
        GameModel model = new GameModel();
        //new Lwjgl3Application(new AnimationTest(),cfg);
        new Lwjgl3Application(new GameView(model), cfg);
    }
}