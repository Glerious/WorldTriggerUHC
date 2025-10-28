package fr.glerious.worldtriggeruhc.faction;

import fr.glerious.uhcmanagerapi.timeline.Event;
import fr.glerious.uhcmanagerapi.utils.Methods;
import fr.glerious.worldtriggeruhc.Main;
import fr.glerious.worldtriggeruhc.faction.faction.Kido;
import fr.glerious.worldtriggeruhc.faction.faction.Shinoda;
import fr.glerious.worldtriggeruhc.faction.faction.Tamakoma;
import org.bukkit.scoreboard.Team;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FactionEvent extends Event {

    public FactionEvent()
    {
        super(Methods.seconds2ticks(60));
    }

    @Override
    public boolean condition() {
        return false;
    }

    @Override
    public void exit() {

    }

    @Override
    public void action() {
        for (Team team : fr.glerious.uhcmanagerapi.Main.getTeamManager().getTeams()) {
            List<Faction> factionList = Arrays.asList(new Tamakoma(team), new Shinoda(team), new Kido(team));

            int choice = new Random().nextInt(factionList.size());
            Faction faction = (Faction) factionList.toArray()[choice];

            Main.getFactionList().add(faction);
            faction.annonce();
        }
    }
}
