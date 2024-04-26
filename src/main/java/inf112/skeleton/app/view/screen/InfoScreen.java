package inf112.skeleton.app.view.screen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.item.ItemEnergy;
import inf112.skeleton.app.model.item.ItemMushroom;
import inf112.skeleton.app.view.texturepack.ITexturePack;
import inf112.skeleton.app.view.texturepack.TexturePack;


public class InfoScreen extends AbstractScreen {

    private final Table tableController;
    private final Table tableItem;
    private final Stage stage;
    private final Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.BLACK);
    private static final Texture texture = new Texture("Backgrounds/openBook.png");
    private final ITexturePack texturePack = new TexturePack();
    private final ItemMushroom itemMushroom = new ItemMushroom(new EventBus(), new World(new Vector2(0,0),true),0,0);
    private final ItemEnergy itemEnergy = new ItemEnergy(new EventBus(), new World(new Vector2(0,0),true),0,0);
    private final Image mushroomImg = new Image(new TextureRegion(texturePack.getItemTexture(itemMushroom)));
    private final Image energyImg = new Image(new TextureRegion(texturePack.getItemTexture(itemEnergy)));


    public InfoScreen(InputProcessor processor) {
        super(processor);

        stage = new Stage(new ExtendViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT / VIEWPORT_WIDTH));
        tableController = new Table();
        tableController.setFillParent(true);
        tableItem = new Table();
        tableItem.setFillParent(true);
        tableController.setDebug(true);
        tableItem.setDebug(true);
        createLeftTable();
        createRightTable();
        stage.addActor(tableController);
        stage.addActor(tableItem);

    }

    private void createLeftTable() {
        // W, A, S, D, SPACE, P, H, B
        tableController.top().left().padLeft(55).padTop(10);
        tableController.add(new Label("W - Jump", labelStyle));
        tableController.row();
        tableController.add(new Label("meow p", labelStyle));
    }

    private void createRightTable() {
        tableItem.top().right().padRight(40).padTop(10);
        tableItem.add(mushroomImg).padRight(5);
        tableItem.add(new Label("- " + itemMushroom.getName(), labelStyle));
        tableItem.row();
        tableItem.add(new Label("meow p", labelStyle));
        //tableItem.add(new Label(itemMushroom.getDescription(), labelStyle));
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();
        batch.draw(texture, 0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        batch.end();

        stage.act();
        stage.draw();

    }
}
