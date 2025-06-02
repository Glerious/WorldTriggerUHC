package fr.glerious.worldtriggeruhc;

import fr.glerious.uhcmanagerapi.permission.Grade;
import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.uhcmanagerapi.timeline.gamestates.InGame;
import fr.glerious.worldtriggeruhc.combatclass.CombatClassMenu;
import fr.glerious.worldtriggeruhc.combatclass.classes.*;
import fr.glerious.worldtriggeruhc.utils.ConfigAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        GamePlayer gamePlayer = fr.glerious.uhcmanagerapi.Main.getGamePlayer(player.getUniqueId());
        if (command.getName().equalsIgnoreCase( "wt")) {
            if (args.length == 0) {
                player.performCommand("wt help");
                return true;
            }
            switch (args[0]) {
                case "help": {
                    player.sendMessage(ConfigAPI.getCommandsFeedback("help"));
                    break;
                }
                case "role": {
                    int argRequire = 2;
                    if (ConfigAPI.commandChecker(gamePlayer, Grade.HOST, InGame.class, args.length, argRequire, args[1])) return true;
                    Player otherPlayer = Bukkit.getPlayer(args[1]);
                    GamePlayer otherGamePlayer = fr.glerious.uhcmanagerapi.Main.getGamePlayer(otherPlayer.getUniqueId());
                    switch (args[2]) {
                        case "attacker": Main.getCombatClasses().add(new Attacker(otherGamePlayer));break;
                        case "gunner": Main.getCombatClasses().add(new Gunner(otherGamePlayer)); break;
                        case "shooter": Main.getCombatClasses().add(new Shooter(otherGamePlayer));break;
                        case "sniper": Main.getCombatClasses().add(new Sniper(otherGamePlayer));break;
                        case "allrounder": Main.getCombatClasses().add(new AllRounder(otherGamePlayer)); break;
                        case "operator": Main.getCombatClasses().add(new Operator(otherGamePlayer));break;
                        default:
                            gamePlayer.getPlayer().sendMessage(ConfigAPI.getExpected("type_require"));
                    }
                    sender.sendMessage("§7Assignement de la classe §6" + args[2] + "§7 au joueur§6 " + otherPlayer.getName());
                    break;
                }
                case "choose": {
                    CombatClassMenu menu = new CombatClassMenu();
                    menu.openInventory(player);
                    return true;
                }
            }
        }
        return false;
    }
}
