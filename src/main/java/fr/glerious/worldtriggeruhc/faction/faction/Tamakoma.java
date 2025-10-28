package fr.glerious.worldtriggeruhc.faction.faction;

import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.uhcmanagerapi.utils.Methods;
import fr.glerious.worldtriggeruhc.Main;
import fr.glerious.worldtriggeruhc.faction.Faction;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;

public class Tamakoma extends Faction {

    public Tamakoma(Team team)
    {
        super(team);
    }

    @Override
    public void annonce() {
        for (GamePlayer gamePlayer : getTeamGamePlayer()) {
            String message = "§7§m-----------------------------------------" + "\n" +
                    "§7Vous êtes de la faction §6§lTomakoma§7." + "\n" +
                    "§7Vous obtenez §eVitesse§7 proche d'un Neighbor." + "\n" +
                    "§7Si un Neighbor est dans votre équipe, il obtient §eVitesse II§7 permanant." + "\n" +
                    "§7§m-----------------------------------------";
            gamePlayer.getPlayer().sendMessage(message);
        }
        power();
    }

    @Override
    public void ambient() {
        PotionEffectType speed = PotionEffectType.SPEED;

        for (GamePlayer gamePlayer : getTeamGamePlayer()) {
            Player player = gamePlayer.getPlayer();
            if (player.hasPotionEffect(speed)) continue;

            for (GamePlayer otherGamePlayer : fr.glerious.uhcmanagerapi.Main.getGamePlayers()) {
                if (otherGamePlayer.equals(gamePlayer)) continue;
                if (Main.getNeighbor(otherGamePlayer.getUuid()) == null) continue;

                Location loc1 = player.getLocation();
                Location loc2 = otherGamePlayer.getPlayer().getLocation();
                if (loc1.distance(loc2) > 20) continue;

                PotionEffect givenPotion = speed.createEffect(Methods.seconds2ticks(2), 0);
                player.addPotionEffect(givenPotion);
            }
        }
    }

    @Override
    public void power() {
        for (GamePlayer gamePlayer : getTeamGamePlayer()) {
            if (Main.getNeighbor(gamePlayer.getUuid()) == null) continue;
            PotionEffect speed = PotionEffectType.SPEED.createEffect(Methods.seconds2ticks(10000000), 1);
            gamePlayer.getPlayer().addPotionEffect(speed);
        }
    }
}
