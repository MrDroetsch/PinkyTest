package me.bunnykick.pinkytest;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Timer implements Runnable {

    private final List<TrackableItem> items;

    public Timer() {
        items = Collections.synchronizedList(new ArrayList<>());
    }

    public void addItem(TrackableItem item) {
        items.add(item);
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        if(items.isEmpty()) return;

        for(Iterator<TrackableItem> iterator = items.iterator(); iterator.hasNext();) {
            TrackableItem item = iterator.next();
            if(item.getItem() == null || !item.getItem().isValid()) {
                iterator.remove();
                continue;
            }
            if(item.isOnGround()) {
                Bukkit.getPluginManager().callEvent(new ItemLandedOnGroundEvent(item.getItem(), item.getThrower()));
                iterator.remove();
            }
        }
    }

}
