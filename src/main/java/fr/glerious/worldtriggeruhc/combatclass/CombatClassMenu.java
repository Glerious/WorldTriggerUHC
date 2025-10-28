package fr.glerious.worldtriggeruhc.combatclass;

import fr.glerious.uhcmanagerapi.utils.BetterItems;
import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.uhcmanagerapi.utils.Menu;
import fr.glerious.uhcmanagerapi.utils.Methods;
import fr.glerious.worldtriggeruhc.Main;
import fr.glerious.worldtriggeruhc.combatclass.classes.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class CombatClassMenu extends Menu implements Listener {

    public CombatClassMenu() {
        super("§l§6Ω - Choix des classes");
        List<Integer> slots = Methods.concatList(Methods.rangedList(1, 3), Methods.rangedList(5, 7));
        List<BetterItems> betterItems = Arrays.asList(
                new BetterItems(Material.BLAZE_ROD, "§l§4Classe Attacker", true),
                new BetterItems(Material.BOW, "§l§8Classe Gunner", true),
                new BetterItems(Material.MAGMA_CREAM, "§l§eClasse Shooter", true),
                new BetterItems(Material.ARROW, "§l§9Classe Sniper", true),
                new BetterItems(Material.SNOW_BALL, "§l§2Classe all-rounder", true),
                new BetterItems(Material.EMERALD, "§l§2Classe Operator", true)
        );
        modifyBasePage(1, Methods.list2Hash(slots, betterItems));
    }

    @EventHandler
    public void onClassItemClick(InventoryClickEvent event) {
        if (!event.getInventory().getName().equals(actualPage.getInventory().getName())) return;
        if (event.getCursor() == null) return;

        Player player = (Player) event.getWhoClicked();
        GamePlayer gamePlayer = fr.glerious.uhcmanagerapi.Main.getGamePlayer(player.getUniqueId());
        if (gamePlayer == null) return;

        ItemStack item = event.getCurrentItem();
        if (item == null) return;
        event.setCancelled(true);
        switch (item.getType()) {
            case BLAZE_ROD : addCombatClass(gamePlayer, Attacker.class); break;
            case BOW : addCombatClass(gamePlayer, Gunner.class); break;
            case MAGMA_CREAM : addCombatClass(gamePlayer, Shooter.class); break;
            case ARROW : addCombatClass(gamePlayer, Sniper.class); break;
            case SNOW_BALL : addCombatClass(gamePlayer, AllRounder.class); break;
            case EMERALD : addCombatClass(gamePlayer, Operator.class); break;
        }
        player.closeInventory();
    }

    public static void addCombatClass(GamePlayer gamePlayer, Class<? extends CombatClass> cCombatClass) {
        Main.getCombatClasses().removeIf(otherCombatClass
                -> otherCombatClass.getGamePlayer().equals(gamePlayer));
        CombatClass combatClass = null;
        try {
            combatClass = cCombatClass.getDeclaredConstructor(GamePlayer.class).newInstance(gamePlayer);
        } catch (InstantiationException | IllegalAccessException
                 | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        combatClass.updateRoleLine();
        Main.getCombatClasses().add(combatClass);
    }
}