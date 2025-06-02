package fr.glerious.worldtriggeruhc;

import fr.glerious.uhcmanagerapi.gameplayer.BetterItems;
import fr.glerious.uhcmanagerapi.timeline.gamestates.Waiting;
import fr.glerious.worldtriggeruhc.addons.Cooldown;
import fr.glerious.worldtriggeruhc.addons.RoleEffects;
import fr.glerious.worldtriggeruhc.combatclass.CombatClass;
import fr.glerious.worldtriggeruhc.combatclass.CombatClassListener;
import fr.glerious.worldtriggeruhc.combatclass.CombatClassMenu;
import fr.glerious.worldtriggeruhc.faction.Faction;
import fr.glerious.worldtriggeruhc.faction.FactionEvent;
import fr.glerious.worldtriggeruhc.faction.FactionListener;
import fr.glerious.worldtriggeruhc.neighbors.Neighbor;
import fr.glerious.worldtriggeruhc.neighbors.NeighborEvent;
import fr.glerious.worldtriggeruhc.utils.Methods;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Main extends JavaPlugin {

    private static Main main;

    public static final Commands commands = new Commands();

    private static final List<CombatClass> combatClasses = new ArrayList<>();

    private static final List<Neighbor> neighbors = new ArrayList<>();

    private static final List<Faction> factionList = new ArrayList<>();

    public static Main getMain()
    {
        return main;
    }

    public static List<CombatClass> getCombatClasses() {
        return combatClasses;
    }

    public static CombatClass getCombatClass(UUID uuid) {
        return Methods.getByUUID(combatClasses, uuid);
    }

    public static List<Neighbor> getNeighbors() {
        return neighbors;
    }

    public static Neighbor getNeighbor(UUID uuid) {
        return Methods.getByUUID(neighbors, uuid);
    }

    public static List<Faction> getFactionList() {
        return factionList;
    }

    @Override
    public void onEnable() {
        main = this;
        saveDefaultConfig();

        addCommand("wt", commands);

        addListener(new JoinAndQuitListener());
        addListener(new FactionListener());
        addListener(new CombatClassListener());
        addListener(new CombatClassMenu());

        Bukkit.getScheduler().runTask(this, () -> {
            fr.glerious.uhcmanagerapi.Main.getEvents().add(new NeighborEvent());
            fr.glerious.uhcmanagerapi.Main.getEvents().add(new FactionEvent());
            fr.glerious.uhcmanagerapi.Main.getRunnables().add(new Cooldown());
            fr.glerious.uhcmanagerapi.Main.getRunnables().add(new RoleEffects());

            Waiting waiting = (Waiting) fr.glerious.uhcmanagerapi.Main.getGameState();
            waiting.getBetterItems().add(new BetterItems(Material.BANNER, "§cTeam", false));
            waiting.getBetterItems().add(new BetterItems(Material.GOLDEN_CARROT, "§cClasse", false));
        });
    }

    private void addListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    private void addCommand(String command, CommandExecutor commandExecutor) {
        getCommand(command).setExecutor(commandExecutor);
    }
}
