package me.bunnykick.pinkytest;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class TrackableItem {
    private Item item;
    private Player threw;

    public TrackableItem(Item item, Player threw) {
        setItem(item);
        setThrew(threw);
    }

    public Player getThrew() {
        return threw;
    }

    public Item getItem() {
        return item;
    }

    private void setThrew(Player threw) {
        this.threw = threw;
    }

    private void setItem(Item item) {
        this.item = item;
    }

    public boolean isOnGround() {
        return item.isOnGround();
    }

}
