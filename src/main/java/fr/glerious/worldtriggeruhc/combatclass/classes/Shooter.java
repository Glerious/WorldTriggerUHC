package fr.glerious.worldtriggeruhc.combatclass.classes;

import fr.glerious.uhcmanagerapi.gameplayer.BetterItems;
import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.uhcmanagerapi.utils.Methods;
import fr.glerious.worldtriggeruhc.combatclass.CombatClass;
import fr.glerious.worldtriggeruhc.utils.ConfigAPI;
import net.minecraft.server.v1_8_R3.Tuple;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Collections;
import java.util.List;


public class Shooter extends CombatClass {

    private Boolean mode;

    public Shooter(GamePlayer gamePlayer)
    {
        super(gamePlayer);
    }

    @Override
    public String name() {
        return "Shooter";
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
        BetterItems item = new BetterItems(Material.MAGMA_CREAM, "§e§lTrionShoot§r", true);
        return Collections.singletonList(item.getItemStack());
    }

    @Override
    public void annonce() {
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
        if(cooldown == 0) {
            Vector playerDirection = player.getLocation().getDirection();
            double arg = Math.atan(playerDirection.getX() / playerDirection.getZ()) + Math.PI / 2;
            Tuple<Integer, Integer> arrowPattern;
            if (mode) arrowPattern = new Tuple<>(0, 7);
            else arrowPattern = new Tuple<>(3, 3);
            for (Integer integer : Methods.rangedList(0, arrowPattern.a()))
                for (Integer otherInteger : Methods.rangedList(0, arrowPattern.b())) {
                    Integer spliter = (arrowPattern.a() - 1) / 2;
                    int otherSpliter = (arrowPattern.b() - 1) / 2;
                    player.getWorld().spawnArrow(
                            player.getLocation().add(Math.sin(arg) * (integer - spliter), 2 + otherSpliter - otherInteger, Math.cos(arg) * (integer - spliter)),
                            playerDirection,
                            (float) 1.4,
                            0
                    );
                }
            setCooldown(Methods.seconds2ticks(5));
            return;
        }
        player.sendMessage(ConfigAPI.getInformation("cooldown") + cooldown);
    }
}
