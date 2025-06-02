package fr.glerious.worldtriggeruhc.faction.faction;

import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.worldtriggeruhc.Main;
import fr.glerious.worldtriggeruhc.faction.Faction;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;

public class Kido extends Faction{


    public Kido(Team teamUHC)
    {
        super(teamUHC);
    }

    @Override
    public void annonce() {
        for (GamePlayer gamePlayer : getTeamGamePlayer()) {
            gamePlayer.getPlayer().sendMessage(
                    "§7§m-----------------------------------------" + "\n" +
                    "§7Vous êtes de la faction §6§lKido§7." + "\n" +
                    "§7Vous obtenez §4Force§7 proche d'un Neighbor." + "\n" +
                    "§7Vous obtenez §4Force§7 permanent si vous éliminez un Neighbor." + "\n" +
                    "§7§m-----------------------------------------"
            );
            power();
        }
    }

    @Override
    public void ambient() {
        PotionEffectType strength = PotionEffectType.INCREASE_DAMAGE;

        for (GamePlayer gamePlayer : getTeamGamePlayer()) {
            Player player = gamePlayer.getPlayer();
            if (player.hasPotionEffect(strength)) return;

            for (GamePlayer otherGamePlayer : fr.glerious.uhcmanagerapi.Main.getGamePlayers()) {
                if(otherGamePlayer.equals(gamePlayer)) continue;
                if (Main.getNeighbor(otherGamePlayer.getUuid()) == null) continue;

                Location loc1 = player.getLocation();
                Location loc2 = otherGamePlayer.getPlayer().getLocation();
                if(loc1.distance(loc2) > 20) continue;

                PotionEffect givenPotion = strength.createEffect(20 * 2, 0);
                player.addPotionEffect(givenPotion);
            }
        }
    }

    @Override
    public void power() {

    }
}
