package fr.glerious.worldtriggeruhc.faction;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class FactionListener implements Listener {

    @EventHandler
    public void onNeighborKilledByKido(PlayerDeathEvent event)
    {
        Player killer = event.getEntity().getKiller();
    }

    public void onShinodaDeath(PlayerDeathEvent event)
    {
        Player killed = event.getEntity();
    }
}
