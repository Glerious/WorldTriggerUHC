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

public class AllRounder extends CombatClass {

    public AllRounder(GamePlayer gamePlayer) {
        super(gamePlayer);
    }

    @Override
    public List<ItemStack> items() {
        BetterItems item = new BetterItems(Material.BOW, "§2§lPistolet", true);
        return Collections.singletonList(item.getItemStack());
    }

    @Override
    public String name()
    {
        return "AllRounder";
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
            getGamePlayer().getPlayer().launchProjectile(Arrow.class, playerDirection.multiply(2));
            setCooldown(Methods.seconds2ticks(1.5));
            return;
        }
        super.power();
    }
}
