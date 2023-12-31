package me.bunnykick.pinkytest;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ItemLandedOnGroundEvent extends Event {

    private Item item;
    private Player player;
    private static final HandlerList HANDLER_LIST = new HandlerList();

    public ItemLandedOnGroundEvent(Item item, Player player) {
        this.item = item;
        this.player = player;
    }

    public Item getItem() {
        return item;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
