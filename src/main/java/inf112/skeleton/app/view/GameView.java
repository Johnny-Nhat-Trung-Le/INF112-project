package inf112.skeleton.app.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.AssetsManager;
import inf112.skeleton.app.model.GameModel;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.model.event.EventDispose;
import inf112.skeleton.app.view.screen.GameOverScreen;
import inf112.skeleton.app.view.screen.GameScreen;

import inf112.skeleton.app.view.screen.MenuScreen;
import inf112.skeleton.app.view.screen.PauseScreen;


public class GameView extends Game {
    public static final float VIEWPORT_WIDTH = 20;
    public static final float VIEWPORT_HEIGHT = 20;
    public static final float ASPECT_RATIO = 2;
    private final GameModel model;
    private final EventBus bus;
    private final InputProcessor processor;
    private GameState gameState;
    private boolean resetGame = false;
    private AssetsManager assetsManager;

    public GameView(GameModel model, EventBus bus, InputProcessor processor) {
        this.model = model;
        this.bus = bus;
        this.processor = processor;
        assetsManager = model.getMusicManager();
        this.gameState = GameState.MAIN_MENU;
    }

    @Override
    public void create() {
        setScreen(new MenuScreen(processor));
        assetsManager.playMusic("MAIN");
    }

    public void render() {
        if (model.getState() != gameState) {
            gameState = model.getState();
            switch (gameState) {
                case ACTIVE -> {
                    if (!resetGame) {
                        setScreen(new GameScreen(model, bus, processor));
                    } else {
                        //TODO fix reset av game
                        resetGame = false;
                        setScreen(new GameScreen(new GameModel(bus), bus, processor));
                    }
                }
                case MAIN_MENU -> setScreen(new MenuScreen(processor));
                case PAUSE -> setScreen(new PauseScreen(processor));
                case GAME_OVER -> {
                    resetGame = true;
                    setScreen(new GameOverScreen(processor));
                    assetsManager.stopMusic();
                    assetsManager.playMusic("DEAD");
                }
            }
        }
        super.render();
    }
}
