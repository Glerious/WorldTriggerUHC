package fr.glerious.worldtriggeruhc;

import fr.glerious.uhcmanagerapi.ConfigUHC;
import fr.glerious.javautils.BetterItems;
import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.javautils.Grade;
import fr.glerious.uhcmanagerapi.timeline.GameState;
import fr.glerious.uhcmanagerapi.timeline.gamestates.Waiting;
import fr.glerious.worldtriggeruhc.addons.AttributeEvent;
import fr.glerious.worldtriggeruhc.addons.Cooldown;
import fr.glerious.worldtriggeruhc.addons.RoleEffects;
import fr.glerious.worldtriggeruhc.addons.StartRunnable;
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

import java.util.*;


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
            fr.glerious.uhcmanagerapi.Main.getEvents().add(new AttributeEvent());
            fr.glerious.uhcmanagerapi.Main.getStartEvents().add(new StartRunnable());
            fr.glerious.uhcmanagerapi.Main.getRunnables().add(new Cooldown());
            fr.glerious.uhcmanagerapi.Main.getRunnables().add(new RoleEffects());

            Waiting waiting = (Waiting) fr.glerious.uhcmanagerapi.Main.getGameState();
            waiting.getBetterItems().add(new BetterItems(Material.GOLDEN_CARROT, "§cClasse", false));
        });
    }

    private void addListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    private void addCommand(String command, CommandExecutor commandExecutor) {
        getCommand(command).setExecutor(commandExecutor);
    }

    private static String hasNotNumberArgumentRequire(int arguments, int argumentsRequire) {
        if (arguments < argumentsRequire) return  "arg_require";
        return "";
    }

    private static String hasNotGradeRequire(GamePlayer gamePlayer, Grade grade) {
        if (!gamePlayer.hasGarde(grade)) return "grade_require";
        return "";
    }

    private static String hasNotGameStateRequire(Class<? extends GameState> gameStateRequire) {
        if (gameStateRequire != fr.glerious.uhcmanagerapi.Main.getGameState().getClass()) return "game_state_require";
        return "";
    }

    private static String isNotPlayerOnline(String entry) {
        if (Bukkit.getPlayer(entry) == null) return "player_require";
        return "";
    }

    public static boolean commandChecker(GamePlayer gamePlayer,
                                         Grade gradeRequire,
                                         Class<? extends GameState> gameStateRequire,
                                         int arguments,
                                         int argumentsRequire,
                                         String otherPlayer) {
        return printCommandFeedback(Arrays.asList(
                hasNotGameStateRequire(gameStateRequire),
                hasNotNumberArgumentRequire(arguments, argumentsRequire),
                hasNotGradeRequire(gamePlayer, gradeRequire),
                isNotPlayerOnline(otherPlayer)
        ), gamePlayer);
    }

    public static boolean commandChecker(GamePlayer gamePlayer,
                                         Grade gradeRequire,
                                         int arguments,
                                         int argumentsRequire,
                                         String otherPlayer) {
        return printCommandFeedback(Arrays.asList(
                hasNotNumberArgumentRequire(arguments, argumentsRequire),
                hasNotGradeRequire(gamePlayer, gradeRequire),
                isNotPlayerOnline(otherPlayer)
        ), gamePlayer);
    }

    public static boolean commandChecker(GamePlayer gamePlayer,
                                         Grade gradeRequire,
                                         int arguments,
                                         int argumentsRequire) {
        return printCommandFeedback(Arrays.asList(
                hasNotNumberArgumentRequire(arguments, argumentsRequire),
                hasNotGradeRequire(gamePlayer, gradeRequire)
        ), gamePlayer);
    }

    public static boolean commandChecker(GamePlayer gamePlayer,
                                         Grade gradeRequire,
                                         Class<? extends GameState> gameStateRequire,
                                         int arguments,
                                         int argumentsRequire) {
        return printCommandFeedback(Arrays.asList(
                hasNotGameStateRequire(gameStateRequire),
                hasNotNumberArgumentRequire(arguments, argumentsRequire),
                hasNotGradeRequire(gamePlayer, gradeRequire)
        ), gamePlayer);
    }

    public static boolean commandChecker(GamePlayer gamePlayer,
                                         Class<? extends GameState> gameStateRequire) {
        return printCommandFeedback(Collections.singletonList(
                hasNotGameStateRequire(gameStateRequire)
        ), gamePlayer);
    }

    private static boolean printCommandFeedback(List<String> exception, GamePlayer gamePlayer) {
        String expected = "";
        for (String text :
                exception) {
            if (text.isEmpty()) continue;
            expected = text;
            break;
        }
        if (expected.isEmpty()) return false;
        gamePlayer.getPlayer().sendMessage(ConfigUHC.getExpected(expected));
        return true;
    }
}
