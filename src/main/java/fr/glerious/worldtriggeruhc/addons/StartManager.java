package fr.glerious.worldtriggeruhc.addons;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class StartManager implements Listener {

    @EventHandler
    public void onPerformCommand(PlayerCommandPreprocessEvent event) {
        Bukkit.broadcastMessage(event.getMessage());
        event.setCancelled(true);
    }


}
