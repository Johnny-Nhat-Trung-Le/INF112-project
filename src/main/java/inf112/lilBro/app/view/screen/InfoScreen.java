package inf112.lilBro.app.view.screen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FillViewport;
import inf112.lilBro.app.event.EventBus;
import inf112.lilBro.app.model.item.ItemEnergy;
import inf112.lilBro.app.model.item.ItemHP;
import inf112.lilBro.app.model.item.ItemMushroom;
import inf112.lilBro.app.view.ViewableItem;
import inf112.lilBro.app.view.texturepack.ITexturePack;
import inf112.lilBro.app.view.texturepack.TexturePack;


public class InfoScreen extends AbstractScreen {

    private static final Texture texture = new Texture("Backgrounds/openBook.png");
    private final Table tableController;
    private final Table tableItem;
    private final Stage stage;
    private final Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.BLACK);
    private final ITexturePack texturePack = new TexturePack();
    private final ItemMushroom itemMushroom = new ItemMushroom(new EventBus(), new World(new Vector2(0, 0), true), 0, 0);
    private final ItemEnergy itemEnergy = new ItemEnergy(new EventBus(), new World(new Vector2(0, 0), true), 0, 0);
    private final Image mushroomImg = new Image(new TextureRegion(texturePack.getItemTexture(itemMushroom)));
    private final Image energyImg = new Image(new TextureRegion(texturePack.getItemTexture(itemEnergy)));

    private final ItemHP itemHp = new ItemHP(new EventBus(), new World(new Vector2(0, 0), true), 0, 0);
    private final Image hpImg = new Image(new TextureRegion(texturePack.getItemTexture(itemHp)));

    /**
     * Creates an infoScreen for all the information about the game
     *
     * @param processor The input processor
     */
    public InfoScreen(InputProcessor processor) {
        super(processor);
        stage = new Stage(new FillViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, gameCam));

        tableController = new Table();
        tableController.setFillParent(true);
        createLeftTable();
        stage.addActor(tableController);

        tableItem = new Table();
        tableItem.setFillParent(true);
        createRightTable();
        stage.addActor(tableItem);
    }

    private void createLeftTable() {
        tableController.left().padLeft(30);
        tableController.add(new Label("Controls", labelStyle));
        tableController.row();
        tableController.add(new Label("W - Jump", labelStyle));
        tableController.row();
        tableController.add(new Label("S - Down", labelStyle));
        tableController.row();
        tableController.add(new Label("A - Left", labelStyle));
        tableController.row();
        tableController.add(new Label("D - Right", labelStyle));
        tableController.row();
        tableController.add(new Label("Space - Use Item", labelStyle));
        tableController.row();
        tableController.add(new Label("P - Pause/Continue", labelStyle));
        tableController.row();
        tableController.add(new Label("H - Help", labelStyle));
        tableController.row();
        tableController.add(new Label("B - Back", labelStyle));
    }

    private void createRightTable() {
        tableItem.right().padRight(30);
        createItemGroup(mushroomImg, itemMushroom);
        createDescriptionContainer(itemMushroom);
        createItemGroup(energyImg, itemEnergy);
        createDescriptionContainer(itemEnergy);
        createItemGroup(hpImg, itemHp);
        createDescriptionContainer(itemHp);
    }

    /**
     * Creates a horizontalGroup for a ViewableItem and its image
     *
     * @param itemImg the Image of the ViewableItem
     * @param item    the ViewableItem
     */
    private void createItemGroup(Image itemImg, ViewableItem item) {
        HorizontalGroup grp = new HorizontalGroup();
        grp.right();
        grp.addActor(itemImg);
        grp.addActor(new Label(" - " + item.getName(), labelStyle));

        tableItem.add(grp);
        tableItem.row();
    }

    /**
     * Creates a Container<Label> which has the description of the ViewableItem
     *
     * @param item ViewableItem
     */
    private void createDescriptionContainer(ViewableItem item) {
        Label description = new Label(item.getDescription(), labelStyle);
        description.setAlignment(Align.center);
        description.setWrap(true);

        Container<Label> descriptionContainer = new Container<>();
        descriptionContainer.setActor(description);
        descriptionContainer.width(VIEWPORT_WIDTH / 3);

        tableItem.add(descriptionContainer);
        tableItem.row();
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
