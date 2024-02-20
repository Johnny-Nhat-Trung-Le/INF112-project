package inf112.skeleton.app.model;

import inf112.skeleton.app.view.ViewableGameModel;

public class GameModel implements ViewableGameModel{
    private int width = 600;
    private int height = 750;
    @Override
    public int getWidth() {
       return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }
    
}
