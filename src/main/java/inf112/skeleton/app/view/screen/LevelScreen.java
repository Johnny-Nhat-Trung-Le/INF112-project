package inf112.skeleton.app.view.screen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;

public class LevelScreen extends AbstractScreen {
    private final Texture texture;
    private final Table table;
    private final Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.BLACK);
    private final String title;
    private final Stage stage;

    /**
     * Creates a Screen for when a user selects what level to play
     *
     * @param processor The input processor
     */
    public LevelScreen(InputProcessor processor) {
        super(processor);
        stage = new Stage(new FillViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, gameCam));
        texture = new Texture("Backgrounds/openBook.png");
        table = new Table();
        title = "Level";
        table.setFillParent(true);
        createLevelTable();
        stage.addActor(table);

    }

    private void createLevelTable() {
        table.center().top();
        table.add(new Label(title, new Label.LabelStyle(font, Color.GREEN))).padBottom(100);
        table.center().row();
        table.add(new Label("1", labelStyle)).right().padRight(150);
        table.add(new Label("2", labelStyle));

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.setProjectionMatrix(stage.getViewport().getCamera().combined);
        batch.begin();
        batch.draw(texture, 0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        batch.end();
        stage.draw();
    }
}
