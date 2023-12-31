package me.bunnykick.pinkytest;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public final class PinkyTest extends JavaPlugin implements Listener {

    private Timer timer;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        timer = new Timer();
        Bukkit.getScheduler().runTaskTimer(this, timer, 5, 5);
    }

    @EventHandler
    public void onItemThrow(PlayerDropItemEvent event) {
        Item item = event.getItemDrop();    // NotNull
        Player player = event.getPlayer();  // NotNull

        switch(item.getItemStack().getType()) {
            case GOLD_BLOCK, GOLD_INGOT, GOLD_NUGGET -> {
                ItemStack itemStack = item.getItemStack();
                ItemMeta itemMeta = itemStack.getItemMeta();
                PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
                dataContainer.set(
                        new NamespacedKey(this, "NBT_Dummy_NoStack"),
                        PersistentDataType.STRING,
                        String.valueOf(System.currentTimeMillis())
                );
                itemStack.setItemMeta(itemMeta);
                item.setItemStack(itemStack);
                timer.addItem(new TrackableItem(item, player));
            }
        }

    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        Item item = event.getItem();

        switch(item.getItemStack().getType()) {
            case GOLD_BLOCK, GOLD_INGOT, GOLD_NUGGET -> {
                ItemStack itemStack = item.getItemStack();
                ItemMeta itemMeta = itemStack.getItemMeta();
                PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();
                dataContainer.remove(new NamespacedKey(this, "NBT_Dummy_NoStack"));
                itemStack.setItemMeta(itemMeta);
                item.setItemStack(itemStack);
            }
        }
    }

    @EventHandler
    public void onItemLand(ItemLandedOnGroundEvent event) {
        Item item = event.getItem();
        Player player = event.getPlayer();

        Location itemLocation = item.getLocation();
        Block block = itemLocation.getWorld().getBlockAt(itemLocation.add(0, -1, 0));

        if(block.getType() == Material.GOLD_BLOCK) {
            int amount = item.getItemStack().getAmount() * switch(item.getItemStack().getType()) {
                case GOLD_BLOCK -> 81;
                case GOLD_INGOT -> 9;
                default -> 1;
            };
            player.sendMessage("Du hast " + amount + " Gold hingeworfen!");
            item.remove();
        } else {
            player.sendMessage("Du hast keinen Goldblock getroffen!");
        }
    }

}
