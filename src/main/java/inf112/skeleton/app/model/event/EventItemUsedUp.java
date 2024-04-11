package inf112.skeleton.app.model.event;

import inf112.skeleton.app.event.Event;
import inf112.skeleton.app.model.item.ItemModel;

public record EventItemUsedUp(ItemModel item) implements Event {
}
