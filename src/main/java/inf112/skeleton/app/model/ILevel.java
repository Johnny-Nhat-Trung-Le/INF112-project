package inf112.skeleton.app.model;

import com.badlogic.gdx.physics.box2d.ContactListener;
import inf112.skeleton.app.controller.ControllableLevel;
import inf112.skeleton.app.event.EventHandler;
import inf112.skeleton.app.view.ViewableLevel;

public interface ILevel extends ViewableLevel, ControllableLevel, EventHandler, ContactListener {

}
