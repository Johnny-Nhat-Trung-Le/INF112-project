package inf112.skeleton.app.view;

import com.badlogic.gdx.Game;

import inf112.skeleton.app.view.screen.GameScreen;
public class GameView extends Game{
    private ViewableGameModel model;
    private ViewablePlayerModel player;
    public GameView(ViewableGameModel model, ViewablePlayerModel player){
        this.model = model;
        this.player = player;
    }
    @Override
    public void create() {
        setScreen(new GameScreen(model,player));
    }
    public void render(){
        super.render();
    }

}
