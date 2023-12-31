package me.bunnykick.pinkytest;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class TrackableItem {
    private Item item;
    private Player thrower;

    public TrackableItem(Item item, Player thrower) {
        setItem(item);
        setThrower(thrower);
    }

    public Player getThrower() {
        return thrower;
    }

    public Item getItem() {
        return item;
    }

    private void setThrower(Player thrower) {
        this.thrower = thrower;
    }

    private void setItem(Item item) {
        this.item = item;
    }

    public boolean isOnGround() {
        return item.isOnGround();
    }

}
