package inf112.skeleton.app.model.item;

import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.Durability;
import inf112.skeleton.app.model.Effect;
import inf112.skeleton.app.model.event.EventItemUsedUp;

public class ItemEnergy extends ItemModel {
    private static final int DURABILITY = 5;
    private static final int DURATION = 600;
    private static final float SPEED_BOOST = 4;
    private static final float JUMP_BOOST = 1.5f;
    private Durability durability;

    /**
     * @param bus   the {@link EventBus} that is used to call {@link inf112.skeleton.app.model.event.EventItemUsedUp}
     * @param world that the {@link com.badlogic.gdx.physics.box2d.Body} is created in
     * @param x     left-most position of {@link ItemModel}
     * @param y     bottom-most position of {@link ItemModel}
     */
    public ItemEnergy(EventBus bus, World world, float x, float y) {
        super(bus, world, x, y);
        durability = new Durability(DURABILITY,DURABILITY);
    }

    private void reduceDurability() {
        durability = new Durability(durability.remaining() - 1, durability.maximum());
    }

    @Override
    public Effect use() {
        reduceDurability();
        if (durability.remaining() <= 0) {
            bus.post(new EventItemUsedUp(this));
        }
        return new Effect(DURATION,SPEED_BOOST,JUMP_BOOST);
    }

    @Override
    public String getDescription() {
        return "Increases movement speed and jump strength by a multiplier of 2";
    }

    @Override
    public String getName() {
        return "Energy";
    }

    @Override
    public Durability getDurability() {
        return new Durability(durability.remaining(), durability.maximum());
    }
}
