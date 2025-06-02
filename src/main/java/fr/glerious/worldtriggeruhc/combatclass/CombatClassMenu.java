package fr.glerious.worldtriggeruhc.combatclass;

import fr.glerious.uhcmanagerapi.gameplayer.BetterItems;
import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.uhcmanagerapi.utils.Menu;
import fr.glerious.uhcmanagerapi.utils.Methods;
import fr.glerious.uhcmanagerapi.utils.menu.Page;
import fr.glerious.worldtriggeruhc.Main;
import fr.glerious.worldtriggeruhc.combatclass.classes.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

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
        Page page = new Page(name, 1, slots, betterItems);
        addPage("0", page, true);
    }

    @EventHandler
    public void onClassItemClick(InventoryClickEvent event){
        if (!event.getInventory().getName().equals(actualPage.getInventory().getName())) return;
        if (event.getCursor() == null) return;

        Player player = (Player) event.getWhoClicked();
        GamePlayer gamePlayer = fr.glerious.uhcmanagerapi.Main.getGamePlayer(player.getUniqueId());
        if (gamePlayer == null) return;

        ItemStack item = event.getCurrentItem();
        if (item == null) return;
        event.setCancelled(true);
        switch (item.getType()) {
            case BLAZE_ROD : Main.getCombatClasses().add(new Attacker(gamePlayer)); break;
            case BOW : Main.getCombatClasses().add(new Gunner(gamePlayer)); break;
            case MAGMA_CREAM : Main.getCombatClasses().add(new Shooter(gamePlayer)); break;
            case ARROW : Main.getCombatClasses().add(new Sniper(gamePlayer)); break;
            case SNOW_BALL : Main.getCombatClasses().add(new AllRounder(gamePlayer)); break;
            case EMERALD :Main.getCombatClasses().add(new Operator(gamePlayer)); break;
        }
        CombatClass combatClass = Main.getCombatClass(player.getUniqueId());
        assert combatClass != null;
        combatClass.updateRoleLine();
        player.closeInventory();
    }
}