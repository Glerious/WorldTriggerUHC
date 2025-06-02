package fr.glerious.worldtriggeruhc.combatclass;

import fr.glerious.uhcmanagerapi.timeline.gamestates.InGame;
import fr.glerious.worldtriggeruhc.Main;
import fr.glerious.worldtriggeruhc.combatclass.classes.*;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class CombatClassListener implements Listener {

    @EventHandler
    public void OnTake(PlayerPickupItemEvent event) {
        if (fr.glerious.uhcmanagerapi.Main.getGameState() instanceof InGame)
            if(event.getItem().getItemStack().getType().equals(Material.ARROW))
                event.setCancelled(true);
    }

    @EventHandler
    public void onPowerUse(PlayerInteractEvent event) {
        if (!(fr.glerious.uhcmanagerapi.Main.getGameState() instanceof InGame)) return;

        ItemStack item = event.getItem();
        if (item == null) return;
        if (!item.getItemMeta().hasDisplayName()) return;

        CombatClass combatClass = Main.getCombatClass(event.getPlayer().getUniqueId());
        Action action = event.getAction();
        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            switch (item.getItemMeta().getDisplayName()) {
                case "§c§lDash":
                    if (combatClass instanceof Attacker) combatClass.power();
                    break;
                case "§7§lFusil":
                    if (combatClass instanceof Gunner) combatClass.power();
                    break;
                case "§9§lSniper":
                    if (combatClass instanceof Sniper) combatClass.power();
                    break;
                case "§2§lPistolet":
                    if (combatClass instanceof AllRounder) combatClass.power();
                    break;
                case "§e§lTrionShoot§r":
                    if (combatClass instanceof Shooter) combatClass.power();
                    break;
            }
        }
        else if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
            switch (item.getItemMeta().getDisplayName()) {
                case "§9§lSniper":
                    if (combatClass instanceof Sniper) combatClass.ambient();
                    break;
                case "§e§lTrionShoot§r":
                    if (combatClass instanceof Shooter) combatClass.ambient();
                    break;
            }
        }
    }

    @EventHandler
    public void onFallInCooldown(EntityDamageEvent event) {
        if (!(fr.glerious.uhcmanagerapi.Main.getGameState() instanceof InGame)) return;
        if (!event.getEntityType().equals(EntityType.PLAYER)) return;

        event.setCancelled(true);

        CombatClass combatClass = Main.getCombatClass(event.getEntity().getUniqueId());
        Player player = combatClass.getGamePlayer().getPlayer();
        Integer cooldown = combatClass.getCooldown();

        if (!event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) return;
        if (player.isDead()) return;
        if (!cooldown.equals(0))
            if (combatClass instanceof Attacker) event.setCancelled(true);
    }

    @EventHandler
    public void onRoleItemDrop(PlayerDropItemEvent event) {
        List<String> items = Arrays.asList("§c§lLame", "§c§lDash", "§7§lFusil", "§e§lTrionShoot§r", "§9§lSniper", "§2§lPistolet");
        if (items.contains(event.getItemDrop().getName())) event.setCancelled(true);
    }
}
