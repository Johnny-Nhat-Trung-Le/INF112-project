package inf112.lilBro.app.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import inf112.lilBro.app.model.effect.Effect;
import inf112.lilBro.app.model.item.ItemHP;
import inf112.lilBro.app.model.item.ItemModel;
import inf112.lilBro.app.view.screen.GameScreen;
import inf112.lilBro.app.view.texturepack.ITexturePack;

import java.util.*;

public class Hud extends Stage {
    private static final int IMG_SIZE = 16;
    private static final int TEXT_WIDTH = 10;
    private static final int TEXT_HEIGHT = 20;
    private final ViewableLevel level;
    private final ITexturePack texturePack;
    private final Image itemIcon;
    private final Label itemDurability;
    private final HorizontalGroup effectIcons;
    private final Map<ViewableEffect, Image> effectIconImages;
    private final HorizontalGroup effectDurations;
    private final Map<ViewableEffect, Image> effectDurationImages;
    private final Table table;
    private final HorizontalGroup hpGroup;

    private final LinkedList<Image> hpIcons;

    /**
     * A HUD which overlaps the {@link GameScreen} and displays
     * {@link ItemHP}, {@link Effect}, and {@link ItemModel}
     *
     * @param batch         The Batch from {@link GameScreen}
     * @param viewableLevel The {@link ViewableLevel}
     * @param texturePack   The {@link ITexturePack}
     */
    public Hud(SpriteBatch batch, ViewableLevel viewableLevel, ITexturePack texturePack) {
        super(new ExtendViewport(GameView.VIEWPORT_WIDTH * 20, GameView.VIEWPORT_HEIGHT / GameView.VIEWPORT_WIDTH * 20, new OrthographicCamera()), batch);
        level = viewableLevel;
        this.texturePack = texturePack;
        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        itemIcon = new Image(new BaseDrawable());
        itemDurability = new Label("", labelStyle);
        effectIcons = new HorizontalGroup();
        effectIconImages = new HashMap<>();
        effectDurations = new HorizontalGroup();
        effectDurationImages = new HashMap<>();

        hpIcons = new LinkedList<>();
        for (int i = 0; i < level.getViewablePlayer().getHp(); i++) {
            hpIcons.add(new Image());
        }

        table = new Table();
        hpGroup = new HorizontalGroup();
        table.setFillParent(true);
        hpGroup.setFillParent(true);
        fillTable();
        addActor(table);
        addActor(hpGroup);

    }

    private void fillTable() {
        Table inventory = new Table();
        inventory.setBackground(new SpriteDrawable(new Sprite(texturePack.getInventorySlot())));
        inventory.add(itemIcon);
        inventory.add(itemDurability);


        Table effects = new Table();
        effects.left();
        effects.add(effectIcons);
        effects.row().left();
        effects.add(effectDurations);

        table.top().left();
        table.padLeft(10).padTop(10);
        table.add(inventory).width(IMG_SIZE + TEXT_WIDTH * 2).height(TEXT_HEIGHT);
        table.row().left();
        table.add(effects);

        hpGroup.top().right();
        hpGroup.padTop(10);
        drawHp();
    }

    private void updateHp() {
        if (hpIcons.size() == level.getViewablePlayer().getHp()) return;
        //Checks whether the player lost or gained hp.
        //clears the table when hp has been lost.
        if (hpIcons.size() > level.getViewablePlayer().getHp()) {
            hpIcons.remove();
            hpGroup.clear();
        } else {
            hpIcons.add(new Image());
        }
        drawHp();

    }

    private void drawHp() {
        for (Image hpIcon : hpIcons) {
            hpIcon.setDrawable(new SpriteDrawable(new Sprite(texturePack.getHpTexture())));
            hpGroup.addActor(hpIcon);
        }
    }

    private void updateItemActors() {
        ViewableItem item = level.getViewablePlayer().getItem();
        boolean isFilled = itemIcon.getWidth() != 0;

        if (item != null) {
            if (!isFilled) {
                itemIcon.setDrawable(new SpriteDrawable(new Sprite(texturePack.getItemTexture(item))));
            }
            itemDurability.setText(item.getDurability().remaining());
        } else {
            if (isFilled) {
                itemIcon.setDrawable(new BaseDrawable());
                itemDurability.setText("");
            }
        }
    }

    private void updateEffectActors() {
        Set<ViewableEffect> valid = new HashSet<>();
        for (ViewableEffect effect : level.getViewablePlayer().getEffects()) {
            valid.add(effect);
            // ICON
            if (!effectIconImages.containsKey(effect)) {
                Image icon = new Image(texturePack.getEffectTexture(effect));
                effectIconImages.put(effect, icon);
                effectIcons.addActor(icon);
            }
            // DURATION
            Image duration = new Image(getEffectDuration(effect));
            if (effectDurationImages.containsKey(effect)) {
                Image old = effectDurationImages.get(effect);
                effectDurations.removeActor(old);
            }
            effectDurationImages.put(effect, duration);
            effectDurations.addActor(duration);
        }
        // Remove expired effects
        for (ViewableEffect effect : effectIconImages.keySet().stream().filter((e) -> !valid.contains(e)).toList()) {
            Image icon = effectIconImages.get(effect);
            effectIcons.removeActor(icon);
            effectIconImages.remove(effect);

            Image duration = effectDurationImages.get(effect);
            effectDurations.removeActor(duration);
            effectDurationImages.remove(effect);
        }
    }

    private Sprite getEffectDuration(ViewableEffect effect) {
        float wp = effect.getDuration().remaining() / (float) effect.getDuration().maximum();
        Pixmap pm = new Pixmap(IMG_SIZE, IMG_SIZE / 4, Pixmap.Format.RGBA8888);
        pm.setColor(Color.GREEN);
        pm.fillRectangle(0, 0, (int) (IMG_SIZE * wp), IMG_SIZE / 4);
        return new Sprite(new Texture(pm));
    }

    private void update() {
        updateHp();
        updateItemActors();
        updateEffectActors();
    }


    @Override
    public void draw() {
        update();
        super.draw();
    }

}
