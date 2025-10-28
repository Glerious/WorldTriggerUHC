package fr.glerious.worldtriggeruhc;

import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.uhcmanagerapi.timeline.gamestates.Waiting;
import fr.glerious.worldtriggeruhc.utils.Methods;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinAndQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                GamePlayer gamePlayer = fr.glerious.uhcmanagerapi.Main.getGamePlayer(event.getPlayer().getUniqueId());
                gamePlayer.getSideBar().setMoreLine(0, "§7» §6Classe: §7 Aucun");
                gamePlayer.getSideBar().changeNames("§6◈ §lWorld§7§lTrigger");
                gamePlayer.getSideBar().showScoreboard();
            }
        }.runTaskLater(Main.getMain(), Methods.seconds2ticks(1));

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

    }

    @EventHandler
    public void onItemNoGameClick(PlayerInteractEvent event) {
        Action action = event.getAction();
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if (!(fr.glerious.uhcmanagerapi.Main.getGameState() instanceof Waiting)) return;
        if (item == null) return;
        if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (item.getItemMeta().getDisplayName().equals("§cClasse")) {
                player.performCommand("wt choose");
            }
        } event.setCancelled(true);
    }
}