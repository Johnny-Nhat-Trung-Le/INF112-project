package inf112.skeleton.app.model.item;

import com.badlogic.gdx.physics.box2d.World;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.Durability;
import inf112.skeleton.app.model.effect.Effect;
import inf112.skeleton.app.model.effect.EffectHpUp;
import inf112.skeleton.app.model.event.EventRegenerate;
import inf112.skeleton.app.view.ViewableItem;

public class ItemHP extends ItemModel implements ViewableItem {
    private static final int DURABILITY = 1;
    private static final int DURATION = 30;
    private static final int AMOUNT = 1;

    public ItemHP(EventBus bus, World world, float x, float y) {
        super(bus, world, x, y);
        createEffect = () -> new EffectHpUp(DURATION);
        durability = new Durability(DURABILITY, DURABILITY);
    }

    @Override
    public Effect use() {
        bus.post(new EventRegenerate(AMOUNT));
        return super.use();
    }

    @Override
    public String getDescription() {
        return "Restores one HP for each use";
    }

    @Override
    public String getName() {
        return "HP";
    }


}
