package fr.glerious.worldtriggeruhc.combatclass.classes;

import fr.glerious.uhcmanagerapi.utils.BetterItems;
import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.uhcmanagerapi.utils.Methods;
import fr.glerious.worldtriggeruhc.combatclass.CombatClass;
import net.minecraft.server.v1_8_R3.Tuple;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Collections;
import java.util.List;


public class Shooter extends CombatClass {

    private Boolean mode = false;

    public Shooter(GamePlayer gamePlayer) {
        super(gamePlayer);
    }

    @Override
    public String name() {
        return "Shooter";
    }

    public Boolean getMode() {
        return mode;
    }

    public void setMode(Boolean mode) {
        this.mode = mode;
    }

    @Override
    public List<ItemStack> items() {
        BetterItems item = new BetterItems(Material.MAGMA_CREAM, "§e§lTrionShoot§r", true);
        return Collections.singletonList(item.getItemStack());
    }

    @Override
    public void annonce() {
        super.annonce();
        ambient();
    }

    @Override
    public void ambient() {
        if (mode) getGamePlayer().sendActionBar("§l§7Carré - 3x3");
        else getGamePlayer().sendActionBar("§l§7Horizontal - 1x7");
        setMode(!mode);
    }

    @Override
    public void power() {
        Player player = gamePlayer.getPlayer();
        if(cooldown != 0) {
            super.power();
            return;
        }
        Vector playerDirection = player.getLocation().getDirection()
                .multiply(2).add(new Vector(0, 0, 0));
        Tuple<Integer, Integer> arrowPattern;
        if (mode) arrowPattern = new Tuple<>(7, 1);
        else arrowPattern = new Tuple<>(3, 3);
        for (Integer x : Methods.rangedList(0, arrowPattern.a() - 1)) {
            for (Integer y : Methods.rangedList(0, arrowPattern.b() - 1)) {
                int middleX = (arrowPattern.a() - 1) / 2;
                int middleY = (arrowPattern.b() - 1) / 2;
                player.getWorld().spawnArrow(
                        player.getLocation().add(playerDirection)
                                .add(
                                playerDirection.getZ() * (x - middleX),
                                (middleY - y),
                                playerDirection.getX() * (middleX - x)),
                        playerDirection,
                        (float) 1.4,
                        0
                );
            } setCooldown(Methods.seconds2ticks(5));
        }
    }
}
