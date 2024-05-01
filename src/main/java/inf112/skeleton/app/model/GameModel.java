package inf112.skeleton.app.model;

import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.controller.ControllableGameModel;
import inf112.skeleton.app.controller.ControllableLevel;
import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.event.EventHandler;
import inf112.skeleton.app.model.event.EventDeath;
import inf112.skeleton.app.model.event.EventGameState;
import inf112.skeleton.app.model.event.EventLevelChanged;
import inf112.skeleton.app.model.event.EventReachedDoor;
import inf112.skeleton.app.model.item.ItemEnergy;
import inf112.skeleton.app.model.item.ItemHP;
import inf112.skeleton.app.model.item.ItemModel;
import inf112.skeleton.app.model.item.ItemMushroom;
import inf112.skeleton.app.view.ViewableGameModel;
import inf112.skeleton.app.view.ViewableLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class GameModel implements ViewableGameModel, ControllableGameModel, EventHandler {
    private final EventBus bus;
    private final Map<String, ILevel> levels;
    private String level;
    private GameState state;

    public GameModel(EventBus bus) {
        this.bus = bus;
        state = GameState.MAIN_MENU;
        levels = new HashMap<>();

        bus.addEventHandler(this);
        fillLevels();
    }

    private void fillLevels() {
        TileFactory.initialize();

        // LEVEL 2
        List<Function<World, ItemModel>> i2 = new ArrayList<>();
        i2.add((w) -> new ItemMushroom(bus, w, 24, 20));
        i2.add((w) -> new ItemEnergy(bus, w, 26, 20));
        i2.add((w) -> new ItemHP(bus, w, 28, 20));
        ILevel l2 = new Level(
                bus,
                -20,
                -20,
                0,
                20f,
                20f,
                """
                        ---------------------------------------------------------9
                        ---------------------------------------------------------8
                        ----------------------------------------------LGGGGGGGGGGR
                        LGGGGGR--------------------------------------qxhhhhhhhbbbn
                        vbbbbbn------------------------------------B----------vbbn
                        vbbbbbn------------------------------------i----------vbbn
                        vbbbbbn----------------------------------------LR-----vbbn
                        vbbbbbn----------------------------------------fzgr---vbbn
                        vbbbbbn----|------lr---------------------------------Bvbbn
                        vbbbbbme-----LR------------------------------------LGGabbn
                        vbbbbbn---G--vn-------G------------------------S-LGabbbbbn
                        vbbbbbn--qx--vn-------b-------------------s---LGGabbbbbbbn
                        vbbbbbnB-----vn-------ze-------------s---lgr--fhhhhhhhhhhj
                        vbbhhhzwe----vn---------------SS----lgr-------------------
                        vbn-------|--vn-----------I--lgggr------------------------
                        vbn---------Bvn-----------c-------------------------------
                        vbn---s----qwanSSSSSSSSS--c-------------------------------
                        vbmGGGGGR----vbbbbbbbbbR--c-------------------------------
                        vbbbbbbbn----vbbbbbbbbbn--c-------------------------------
                        vbbbbbbbn----vbbbbbbbbbn--d-------------------------------
                        fhhhhhhhj----fhhhhhhhhhj----------------------------------
                        """,
                "",
                i2
        );
        levels.put("2", l2);

        // Level 1
        List<Function<World, ItemModel>> i1 = new ArrayList<>();
        i1.add((w) -> new ItemMushroom(bus, w, 40f, 5f));
        ILevel l1 = new Level(
                bus,
                -20,
                -20,
                0,
                3f,
                5f,
                """
                        --------------SS------------9-
                        -----------LGGGGGR---s---B--8-
                        lgggr–lggr–GGGGGGGgggggggggggr
                        """,
                "",
                i1
        );
        levels.put("1",l1);

        // SET DEFAULT
        if (!levels.isEmpty()) {
            level = levels.keySet().stream().toList().get(0);
        }
    }

    @Override
    public GameState getState() {
        return state;
    }

    @Override
    public void setState(GameState state) {
        if (this.state == GameState.ACTIVE && !state.equals(this.state)) {
            if (levels.containsKey(level)) {
                levels.get(level).disable();
            }
        }
        if (state == GameState.ACTIVE && !state.equals(this.state)) {
            if (levels.containsKey(level)) {
                levels.get(level).activate();
            }
        }
        if (state == GameState.GAME_OVER) {
            if (levels.containsKey(level)) {
                levels.get(level).reset();
                bus.post(new EventLevelChanged());
            }
        }

        bus.post(new EventGameState(state));
        this.state = state;
    }

    @Override
    public ControllableLevel getControllableLevel() {
        return levels.get(level);
    }

    @Override
    public void setLevel(String key) {
        /*if (levels.containsKey(level) ) {
            levels.get(level).disable();
        }*/
        level = key;
        if (levels.containsKey(level) && state == GameState.ACTIVE) {
            levels.get(level).activate();
        }
        bus.post(new EventLevelChanged());
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof EventReachedDoor) {
            setState(GameState.VICTORY);
        } else if (event instanceof EventDeath e) {
            if (e.owner() != null && e.owner().equals(levels.get(level).getControllablePlayer())) {
                setState(GameState.GAME_OVER);
            }
        }
    }

    @Override
    public ViewableLevel getViewableLevel() {
        return levels.get(level);
    }
}

