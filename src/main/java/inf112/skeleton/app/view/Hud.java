package inf112.skeleton.app.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.view.texturepack.ITexturePack;

public class Hud extends Stage {
    private final ViewableGameModel model;
    private final ITexturePack texturePack;
    private final Table table;
    private final Image itemIcon;
    private final Label itemName;
    private final Label itemDurability;
    private final Image effectIcon;
    private final Label effectDuration;

    public Hud(SpriteBatch batch, ViewableGameModel model, ITexturePack texturePack) {
        super(new ScreenViewport(new OrthographicCamera()), batch);
        this.model = model;
        this.texturePack = texturePack;

        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        itemIcon = new Image(new BaseDrawable());
        itemName = new Label("", style);
        itemDurability = new Label("", style);
        effectIcon = new Image(new BaseDrawable());
        effectDuration = new Label("", style);

        table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        fillTable();
        addActor(table);
    }

    private void fillTable() {
        table.top().left();
        table.add(itemIcon);
        table.add(itemName);
        table.add(itemDurability);
        table.row();
        table.add(effectIcon);
        table.add(effectDuration);
    }

    private void updateItemActors() {
        ViewableItem item = model.getViewablePlayer().getItem();
        boolean isFilled = itemIcon.getWidth() != 0;

        if (item != null) {
            if (!isFilled) {
                itemIcon.setDrawable(new SpriteDrawable(new Sprite(texturePack.getItemTexture(item))));
                itemName.setText(item.getName());
            }
            itemDurability.setText(item.getDurability().toString());
        } else {
            if (isFilled) {
                itemIcon.setDrawable(new BaseDrawable());
                itemName.setText("");
                itemDurability.setText("");
            }
        }
    }

    private void updateEffectActors() {
        ViewableEffect effect = model.getViewablePlayer().getEffect();
        boolean isFilled = effectIcon.getWidth() != 0;

        if (effect != null) {
            if (!isFilled) {
                effectIcon.setDrawable(new SpriteDrawable(new Sprite(texturePack.getItemTexture(effect.getItem()))));
            }
            effectDuration.setText(effect.getDuration().toString());
        } else {
            if (isFilled) {
                effectIcon.setDrawable(new BaseDrawable());
            }
            effectDuration.setText("");
        }
    }

    private void update() {
        updateItemActors();
        updateEffectActors();
    }

    @Override
    public void draw() {
        update();
        super.draw();
    }
}
