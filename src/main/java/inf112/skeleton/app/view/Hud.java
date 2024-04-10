package inf112.skeleton.app.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Hud extends Stage {
    public Hud(SpriteBatch batch) {
        super(new ScreenViewport(new OrthographicCamera()), batch);
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);

        addLabels(table);
        addActor(table);
    }

    private void addLabels(Table table) {

    }
}
