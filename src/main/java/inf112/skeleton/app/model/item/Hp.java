package inf112.skeleton.app.model.item;

import com.badlogic.gdx.physics.box2d.Body;
import inf112.skeleton.app.model.Durability;
import inf112.skeleton.app.model.Effect;
import inf112.skeleton.app.model.Item;
import inf112.skeleton.app.model.tiles.TileModel;
import inf112.skeleton.app.view.ViewableItem;

public class Hp implements ViewableItem, Item {
    private int x;
    private int y;
    private final float WIDTH=TileModel.TILE_WIDTH;
    private final float HEIGHT = TileModel.TILE_HEIGHT;
    public Hp(){
        x = 0;
        y = 0;
    }
    @Override
    public Effect use() {
        return null;
    }

    @Override
    public Body getBody() {
        return null;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getWidth() {
        return WIDTH;
    }

    @Override
    public float getHeight() {
        return HEIGHT;
    }

    @Override
    public String getDescription() {
        return "This is the players health point.\n Going to 0 means the player is dead";
    }

    @Override
    public String getName() {
        return "Hp";
    }

    @Override
    public Durability getDurability() {
        return null;
    }
}
