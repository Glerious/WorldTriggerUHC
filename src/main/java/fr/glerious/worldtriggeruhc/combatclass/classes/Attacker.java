package fr.glerious.worldtriggeruhc.combatclass.classes;

import fr.glerious.uhcmanagerapi.gameplayer.BetterItems;
import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.uhcmanagerapi.utils.Methods;
import fr.glerious.worldtriggeruhc.combatclass.CombatClass;
import fr.glerious.worldtriggeruhc.utils.ConfigAPI;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;

public class Attacker extends CombatClass {

    public Attacker(GamePlayer gamePlayer)
    {
        super(gamePlayer);
    }

    @Override
    public String name()
    {
        return "Attacker";
    }

    @Override
    public List<ItemStack> items() {
        BetterItems item1 = new BetterItems(Material.DIAMOND_SWORD, "§c§lLame", Enchantment.DAMAGE_ALL, 4, false);
        BetterItems item2 = new BetterItems(Material.BLAZE_ROD, "§c§lDash", true);
        return Arrays.asList(item1.getItemStack(), item2.getItemStack());
    }

    @Override
    public void annonce() {

    }

    @Override
    public void ambient() {
        getGamePlayer().dropItem(Material.BOW);
    }

    @Override
    public void power() {
        if(getCooldown() == 0) {
            Vector look = getGamePlayer().getPlayer().getLocation().getDirection().normalize();
            getGamePlayer().getPlayer().setVelocity(look.multiply(1.7));
            setCooldown(Methods.seconds2ticks(10));
        }
        getGamePlayer().getPlayer().sendMessage(ConfigAPI.getInformation("cooldown") + getCooldown());
    }
}
