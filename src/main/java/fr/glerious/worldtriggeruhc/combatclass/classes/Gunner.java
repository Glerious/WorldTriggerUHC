package fr.glerious.worldtriggeruhc.combatclass.classes;

import fr.glerious.uhcmanagerapi.utils.BetterItems;
import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.uhcmanagerapi.utils.Methods;
import fr.glerious.worldtriggeruhc.combatclass.CombatClass;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
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
        super.annonce();
    }

    @Override
    public void ambient() {

    }

    @Override
    public void power() {
        Player player = gamePlayer.getPlayer();
        if (getCooldown() == 0) {
            Vector playerDirection = player.getLocation().getDirection();
            player.launchProjectile(Arrow.class, playerDirection.multiply(2));
            setCooldown(Methods.seconds2ticks(0.5));
            return;
        }
        super.power();
    }
}
