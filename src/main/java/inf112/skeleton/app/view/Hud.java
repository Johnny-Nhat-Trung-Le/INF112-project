package inf112.skeleton.app.view;

import com.badlogic.gdx.Game;
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
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.model.item.Hp;
import inf112.skeleton.app.view.texturepack.ITexturePack;
import java.util.LinkedList;

public class Hud extends Stage {
    private final ViewableGameModel model;
    private final ITexturePack texturePack;
    private final Table table;
    private final Hp hpObject = new Hp();
    private final Label hpCounterL;
    private int hpCounter;
    private final LinkedList<Image> hpIcons;

    public Hud(SpriteBatch batch, ViewableGameModel viewableGameModel, ITexturePack texturePack) {
        super( new ScreenViewport(new OrthographicCamera()), batch);
        //TODO FIks hud viewports, den er scuffed når man resize
        model = viewableGameModel;
        this.texturePack = texturePack;

        hpCounter = model.getViewablePlayer().getHp();
        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        hpCounterL = new Label("" + hpCounter, style);

        hpIcons = new LinkedList<>();
        for (int i = 0; i < hpCounter; i++) {
            hpIcons.add(new Image());
        }
        table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        fillTable();
        addActor(table);
    }

    private void fillTable() {
        table.top().left();
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
            table.add(hpIcon);
        }
        hpCounterL.setText(hpCounter + "");
        table.add(hpCounterL);
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
