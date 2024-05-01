package inf112.lilBro.app.model.item;

import com.badlogic.gdx.physics.box2d.World;
import inf112.lilBro.app.event.EventBus;
import inf112.lilBro.app.model.Durability;
import inf112.lilBro.app.model.effect.EffectHpUp;
import inf112.lilBro.app.view.ViewableItem;

public class ItemHP extends ItemModel implements ViewableItem {
    private static final int DURABILITY = 1;
    private static final int DURATION = 30;

    public ItemHP(EventBus bus, World world, float x, float y) {
        super(bus, world, x, y);
        createEffect = () -> new EffectHpUp(DURATION);
        durability = new Durability(DURABILITY, DURABILITY);
    }

    @Override
    public String getDescription() {
        return "Restores HP";
    }

    @Override
    public String getName() {
        return "HP";
    }


}
