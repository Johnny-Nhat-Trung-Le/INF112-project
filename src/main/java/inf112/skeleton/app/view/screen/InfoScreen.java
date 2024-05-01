package inf112.skeleton.app.view.screen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import inf112.skeleton.app.event.EventBus;
import inf112.skeleton.app.model.item.ItemEnergy;
import inf112.skeleton.app.model.item.ItemHP;
import inf112.skeleton.app.model.item.ItemMushroom;
import inf112.skeleton.app.view.ViewableItem;
import inf112.skeleton.app.view.texturepack.ITexturePack;
import inf112.skeleton.app.view.texturepack.TexturePack;


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


    public InfoScreen(InputProcessor processor) {
        super(processor);

        stage = new Stage(new ExtendViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT / VIEWPORT_WIDTH));
        tableController = new Table();
        tableController.setFillParent(true);
        tableItem = new Table();
        tableItem.setFillParent(true);
        tableItem.setFillParent(true);
        createLeftTable();
        createRightTable();
        stage.addActor(tableController);
        stage.addActor(tableItem);

    }

    private void createLeftTable() {
        // W, A, S, D, SPACE, P, H, B
        tableController.top().left().padLeft(40).padTop(40);
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
        //TODO fix b-back
        tableController.row().left();
        tableController.add(new Label("B - Back", labelStyle));
    }

    private void createRightTable() {
        tableItem.top().right().padRight(10).padTop(15);
        createItemGroup(mushroomImg, itemMushroom);
        createDescriptionContainer(itemMushroom);
        createItemGroup(energyImg, itemEnergy);
        createDescriptionContainer(itemEnergy);
        createItemGroup(hpImg,itemHp);
        createDescriptionContainer(itemHp);
        //TODO the itemImg and itemDesc aint aligned on the left side

    }

    /**
     * Creates a horizontalGroup for a ViewableItem and its image
     *
     * @param itemImg the Image of the ViewableItem
     * @param item    the ViewableItem
     */
    private void createItemGroup(Image itemImg, ViewableItem item) {
        HorizontalGroup grp = new HorizontalGroup();
        grp.top().right().padRight(30);
        grp.addActor(itemImg);
        grp.addActor(new Label("- " + item.getName(), labelStyle));
        tableItem.add(grp);
        tableItem.row();
        tableItem.padLeft(20);
    }

    /**
     * Creates a Container<Label> which has the description of the ViewableItem
     *
     * @param item ViewableItem
     */
    private void createDescriptionContainer(ViewableItem item) {
        Container<Label> descriptionContainer = new Container<>();
        Label description = new Label(item.getDescription(), labelStyle);
        descriptionContainer.right().top();
        descriptionContainer.padLeft(40);
        description.setWrap(true);
        descriptionContainer.setActor(description);
        descriptionContainer.width(item.getDescription().length() * 6);
        descriptionContainer.fillX(); // Fill the container horizontally
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
