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
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import inf112.skeleton.app.model.item.Hp;
import inf112.skeleton.app.view.texturepack.ITexturePack;
import java.util.LinkedList;

public class Hud extends Stage {
    private final ViewableGameModel model;
    private final ITexturePack texturePack;
    private final Table table;
    private final Hp hpObject = new Hp();
    private int hpCounter;
    private final LinkedList<Image> hpIcons;

    public Hud(SpriteBatch batch, ViewableGameModel viewableGameModel, ITexturePack texturePack) {
        super(new ExtendViewport(GameView.VIEWPORT_WIDTH*20,GameView.VIEWPORT_HEIGHT/GameView.VIEWPORT_WIDTH*20,new OrthographicCamera()),batch);
        model = viewableGameModel;
        this.texturePack = texturePack;

        hpCounter = model.getViewablePlayer().getHp();
        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        hpIcons = new LinkedList<>();
        for (int i = 0; i < hpCounter; i++) {
            hpIcons.add(new Image());
        }
        table = new Table();
        table.setFillParent(true);
        fillTable();
        addActor(table);
    }

    private void fillTable() {
        table.top().left();
        table.padTop(10); // Add padding to the top and left edges
        drawHp();
    }

    private void updateHp() {
        //Checks whether the player lost or gained hp.
        //clears the table when removing hp, have to redraw
        if (hpCounter > model.getViewablePlayer().getHp()) {
            hpIcons.remove();
            table.clear();
        } else {
            hpIcons.add(new Image());
        }
        //updates the hpCounter
        hpCounter = model.getViewablePlayer().getHp();
        drawHp();

    }

    private void drawHp() {
        for (Image hpIcon : hpIcons) {
            hpIcon.setDrawable(new SpriteDrawable(new Sprite(texturePack.getItemTexture(hpObject))));
            table.add(hpIcon).padLeft(5);
        }
    }

    private void update() {
        if (hpCounter != model.getViewablePlayer().getHp()) updateHp();
    }

    @Override
    public void draw() {
        update();
        super.draw();
    }

}
