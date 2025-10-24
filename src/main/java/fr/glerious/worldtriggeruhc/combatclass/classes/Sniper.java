package fr.glerious.worldtriggeruhc.combatclass.classes;

import fr.glerious.javautils.BetterItems;
import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.javautils.Methods;
import fr.glerious.worldtriggeruhc.combatclass.CombatClass;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sniper extends CombatClass {

    private Boolean mode = Boolean.FALSE;

    public Sniper(GamePlayer gamePlayer)
    {
        super(gamePlayer);
    }

    @Override
    public String name()
    {
        return "Sniper";
    }

    public Boolean getMode()
    {
        return mode;
    }

    public void setMode(Boolean mode)
    {
        this.mode = mode;
    }

    @Override
    public List<ItemStack> items() {
        List<String> lore = Arrays.asList("§7» §f§lClique gauche: Modes", "§7» §f§lClique droit: Tirer");
        BetterItems item1 = new BetterItems(Material.BOW, "§9§lSniper", lore, true);
        return Collections.singletonList(item1.getItemStack());
    }

    @Override
    public void annonce() {
        super.annonce();
        mode = true;
        ambient();
    }

    @Override
    public void ambient() {
        Player player = gamePlayer.getPlayer();
        if (cooldown == 0) {
            if (mode) {
                PotionEffectType removed = PotionEffectType.INVISIBILITY;
                if (player.hasPotionEffect(removed))
                    player.removePotionEffect(removed);
                gamePlayer.sendActionBar("§7» §f§lMode Visible");
            } else {
                player.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(Methods.seconds2ticks(1000000), 0), true);
                gamePlayer.sendActionBar("§7» §f§lMode Furtif");
            }
            setMode(!mode);
        }
    }

    @Override
    public void power() {
        Player player = gamePlayer.getPlayer();
        if(cooldown == 0) {
            Vector playerDirection = player.getLocation().getDirection();
            player.launchProjectile(Arrow.class, playerDirection.multiply(5.5));
            player.addPotionEffect(PotionEffectType.SPEED.createEffect(Methods.seconds2ticks(3), 2), true);
            setCooldown(Methods.seconds2ticks(30));
            if(getMode()) ambient();
            return;
        }
        super.power();
    }
}
