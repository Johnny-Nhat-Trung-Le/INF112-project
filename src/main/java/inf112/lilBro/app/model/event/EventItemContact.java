package inf112.lilBro.app.model.event;

import com.badlogic.gdx.physics.box2d.Contact;
import inf112.lilBro.app.event.Event;
import inf112.lilBro.app.model.item.ItemModel;

public record EventItemContact(Contact contact, ItemModel item) implements Event {
}
