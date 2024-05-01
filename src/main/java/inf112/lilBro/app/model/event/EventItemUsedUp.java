package inf112.lilBro.app.model.event;

import inf112.lilBro.app.event.Event;
import inf112.lilBro.app.model.item.ItemModel;

public record EventItemUsedUp(ItemModel item) implements Event {
}
