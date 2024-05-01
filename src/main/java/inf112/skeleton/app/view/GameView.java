package inf112.skeleton.app.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.event.EventHandler;
import inf112.skeleton.app.model.GameState;
import inf112.skeleton.app.model.event.EventGameState;
import inf112.skeleton.app.model.event.EventLevelChanged;
import inf112.skeleton.app.model.event.EventPlayerAction;
import inf112.skeleton.app.view.screen.*;

public class GameView extends Game implements EventHandler {
    public static final float VIEWPORT_WIDTH = 20;
    public static final float VIEWPORT_HEIGHT = 20;
    public static final float ASPECT_RATIO = 2;
    private final EventBus bus;
    private final InputProcessor processor;
    private final IAssetsManager assetsManager;
    private final ViewableGameModel model;

    /**
     * Creates {@link Game}
     *
     * @param model     The {@link ViewableGameModel}
     * @param bus       The {@link EventBus}
     * @param processor The {@link InputProcessor}
     */
    public GameView(ViewableGameModel model, EventBus bus, InputProcessor processor) {
        this.model = model;
        this.bus = bus;
        this.processor = processor;
        assetsManager = new AssetsManager();
        bus.addEventHandler(this);
    }

    @Override
    public void create() {
        setScreen(new MenuScreen(processor));
        assetsManager.playMusic("MAIN");
    }

    private void updateScreenAndMusic(GameState state) {
        switch (state) {
            case ACTIVE -> {
                if (model.getViewableLevel() == null) {
                    throw new NullPointerException("No level selected");
                }
                setScreen(new GameScreen(model.getViewableLevel(), bus, processor));
                assetsManager.playMusic("BACKGROUND");
            }
            case MAIN_MENU -> {
                setScreen(new MenuScreen(processor));
                assetsManager.playMusic("MAIN");
            }
            case GAME_OVER -> {
                setScreen(new GameOverScreen(processor));
                assetsManager.playMusic("DEAD");
            }
            case PAUSE -> {
                setScreen(new PauseScreen(processor));
                assetsManager.pauseMusic();
            }
            case VICTORY -> {
                setScreen(new VictoryScreen(processor));
                assetsManager.playMusic("VICTORY");
            }
            case INFO -> {
                setScreen(new InfoScreen(processor));
                assetsManager.pauseMusic();
            }
            case LEVEL -> {
                setScreen(new LevelScreen(processor));
                assetsManager.pauseMusic();
            }
        }
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof EventLevelChanged) {
            updateScreenAndMusic(model.getState());
        } else if (event instanceof EventGameState e) {
            GameState gameState = e.gameState();
            updateScreenAndMusic(gameState);
        } else if (event instanceof EventPlayerAction e) {
            assetsManager.playSoundEffect(e.action());
        }
    }
}

