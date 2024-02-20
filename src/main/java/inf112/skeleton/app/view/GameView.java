package inf112.skeleton.app.view;

import com.badlogic.gdx.Game;

import inf112.skeleton.app.view.screen.GameScreen;
public class GameView extends Game{
    private ViewableGameModel model;
    private ViewablePlayerModel player;
    public GameView(ViewableGameModel model){
        this.model = model;
        this.player = model.getPlayer();
    }
    @Override
    public void create() {
        setScreen(new GameScreen(model));
    }
    public void render(){
        super.render();
    }

}
