package fr.glerious.worldtriggeruhc;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;


public class DamageManager implements Listener {

    @EventHandler
    public void onArrowDamage(EntityDamageByEntityEvent event) {
        if (!event.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) return;
        Player attacker = (Player) event.getDamager();
        if (attacker == null) return;
        Entity victim = event.getEntity();
        if (attacker.getInventory().contains(Material.BOW)) {
            for (ItemStack itemStack:
                 attacker.getInventory()) {
                if (itemStack.getItemMeta().getDisplayName().equals("§7§lFusil")) {

                }
            }
        }

    }

}
