package inf112.skeleton.app.model.item;

import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.Durability;
import inf112.skeleton.app.model.effect.EffectJumpBoost;

public class ItemMushroom extends ItemModel {
    private static final int DURABILITY = 5;
    private static final int DURATION = 600;

    /**
     * @param bus   the {@link EventBus} that is used to call {@link inf112.skeleton.app.model.event.EventItemUsedUp}
     * @param world that the {@link com.badlogic.gdx.physics.box2d.Body} is created in
     * @param x     left-most position of {@link ItemModel}
     * @param y     bottom-most position of {@link ItemModel}
     */
    public ItemMushroom(EventBus bus, World world, float x, float y) {
        super(bus, world, x, y);
        durability = new Durability(DURABILITY, DURABILITY);
        createEffect = () -> new EffectJumpBoost(DURATION);
    }

    @Override
    public String getDescription() {
        return "Increases the jump-power by a power of two";
    }

    @Override
    public String getName() {
        return "Mushroom";
    }
}
