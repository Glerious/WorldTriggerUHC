package fr.glerious.worldtriggeruhc.combatclass.classes;

import fr.glerious.uhcmanagerapi.gameplayer.BetterItems;
import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.uhcmanagerapi.utils.Methods;
import fr.glerious.worldtriggeruhc.combatclass.CombatClass;
import fr.glerious.worldtriggeruhc.utils.ConfigAPI;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Collections;
import java.util.List;

public class Gunner extends CombatClass {

    public Gunner(GamePlayer gamePlayer)
    {
        super(gamePlayer);
    }

    @Override
    public String name()
    {
        return "Gunner";
    }

    @Override
    public List<ItemStack> items() {
        BetterItems item = new BetterItems(Material.BOW, "§7§lFusil", true);
        return Collections.singletonList(item.getItemStack());
    }

    @Override
    public void annonce() {

    }

    @Override
    public void ambient() {

    }

    @Override
    public void power() {
        if (getCooldown() == 0) {
            Vector playerDirection = getGamePlayer().getPlayer().getLocation().getDirection();
            getGamePlayer().getPlayer().launchProjectile(Arrow.class, playerDirection.multiply(1.2));
            setCooldown(Methods.seconds2ticks(0.5));
            return;
        }
        getGamePlayer().getPlayer().sendMessage(ConfigAPI.getInformation("cooldown") + getCooldown());
    }
}
