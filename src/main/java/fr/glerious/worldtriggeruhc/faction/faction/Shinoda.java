package fr.glerious.worldtriggeruhc.faction.faction;

import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.worldtriggeruhc.faction.Faction;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;

public class Shinoda extends Faction {


    public Shinoda(Team team)
    {
        super(team);
    }

    @Override
    public void annonce()
    {
        for (GamePlayer gamePlayer : getTeamGamePlayer())
        {
            gamePlayer.getPlayer().sendMessage(
                    "§7§m-----------------------------------------" + "\n" +
                    "§7Vous êtes de la faction §6§lShinoda§7." + "\n" +
                    "§7Vous obtenez §bResistance§7 proche de vos aliers." + "\n" +
                    "§7Vous obtenez §1Faiblesse§7 pour 30 secondes à la mort d'un alier." + "\n" +
                    "§7§m-----------------------------------------"
            );
            power();
        }
    }

    @Override
    public void ambient()
    {
        PotionEffectType resistance = PotionEffectType.DAMAGE_RESISTANCE;

        for (GamePlayer gamePlayer: getTeamGamePlayer())
        {
            Player player = gamePlayer.getPlayer();
            if (player.hasPotionEffect(resistance)) continue;

            for (GamePlayer otherGamePlayer: getTeamGamePlayer())
            {
                if (otherGamePlayer.equals(gamePlayer)) continue;

                Location loc1 = player.getLocation();
                Location loc2 = otherGamePlayer.getPlayer().getLocation();
                if (loc1.distance(loc2) > 10) continue;

                PotionEffect givenPotion = resistance.createEffect(20 * 2, 0);
                player.addPotionEffect(givenPotion);
            }
        }
    }

    @Override
    public void power() {
        for (GamePlayer gamePlayer: getTeamGamePlayer())
        {
            PotionEffect givenPotion = PotionEffectType.WEAKNESS.createEffect(20 * 2, 0);
            gamePlayer.getPlayer().addPotionEffect(givenPotion);
        }
    }
}
