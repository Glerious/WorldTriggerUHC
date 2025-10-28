package fr.glerious.worldtriggeruhc;

import fr.glerious.uhcmanagerapi.utils.Grade;
import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.uhcmanagerapi.timeline.gamestates.InGame;
import fr.glerious.worldtriggeruhc.combatclass.CombatClassMenu;
import fr.glerious.worldtriggeruhc.combatclass.classes.*;
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
                    player.sendMessage(ConfigWT.getCommandsFeedback("help"));
                    break;
                }
                case "role": {
                    int argRequire = 2;
                    if (Main.commandChecker(gamePlayer, Grade.HOST, InGame.class, args.length, argRequire, args[1])) return true;
                    Player otherPlayer = Bukkit.getPlayer(args[1]);
                    switch (args[2]) {
                        case "attacker" : CombatClassMenu.addCombatClass(gamePlayer, Attacker.class); break;
                        case "gunner" : CombatClassMenu.addCombatClass(gamePlayer, Gunner.class); break;
                        case "shooter" : CombatClassMenu.addCombatClass(gamePlayer, Shooter.class); break;
                        case "sniper" : CombatClassMenu.addCombatClass(gamePlayer, Sniper.class); break;
                        case "allrounder" : CombatClassMenu.addCombatClass(gamePlayer, AllRounder.class); break;
                        case "operator" : CombatClassMenu.addCombatClass(gamePlayer, Operator.class); break;
                        default:
                            gamePlayer.getPlayer().sendMessage(ConfigWT.getExpected("type_require"));
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
