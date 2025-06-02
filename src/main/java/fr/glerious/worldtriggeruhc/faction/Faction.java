package fr.glerious.worldtriggeruhc.faction;

import fr.glerious.uhcmanagerapi.Main;
import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public abstract class Faction implements FactionFeatures {

    private final Team team;

    public Faction(Team team)
    {
        this.team = team;
    }

    public Team getTeam()
    {
        return team;
    }

    public List<GamePlayer> getTeamGamePlayer() {
        List<GamePlayer> teamGamePlayer = new ArrayList<>();
        for (String string: team.getEntries()) {
            GamePlayer gamePlayer = Main.getGamePlayer(Bukkit.getPlayer(string).getUniqueId());
            teamGamePlayer.add(gamePlayer);
        }
        return teamGamePlayer;
    }
}
