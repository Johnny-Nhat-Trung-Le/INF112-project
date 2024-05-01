package inf112.lilBro.app.model;

import com.badlogic.gdx.physics.box2d.ContactListener;
import inf112.lilBro.app.controller.ControllableLevel;
import inf112.lilBro.app.event.EventHandler;
import inf112.lilBro.app.view.ViewableLevel;

public interface ILevel extends ViewableLevel, ControllableLevel, EventHandler, ContactListener {
}
