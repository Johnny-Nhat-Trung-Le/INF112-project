package inf112.skeleton.app.model.item;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.Durability;
import inf112.skeleton.app.model.effect.Effect;
import inf112.skeleton.app.model.effect.EffectHpUp;
import inf112.skeleton.app.model.tiles.TileModel;
import inf112.skeleton.app.view.ViewableItem;

import java.time.Duration;

public class ItemHp extends ItemModel implements ViewableItem{
    private static final int DURABILITY = 1;
    private static final int DURATION = 30;

    public ItemHp(EventBus bus, World world, float x, float y) {
        super(bus, world, x, y);
         createEffect =() -> new EffectHpUp(DURATION);
         durability = new Durability(DURABILITY,DURABILITY);

    }
    @Override
    public String getDescription() {
        return "This is the players health point.\n Going to 0 means the player is dead";
    }

    @Override
    public String getName() {
        return "Hp";
    }


}
