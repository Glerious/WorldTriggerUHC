package fr.glerious.worldtriggeruhc.neighbors;

import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.uhcmanagerapi.timeline.Event;
import fr.glerious.javautils.Methods;
import fr.glerious.worldtriggeruhc.Main;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NeighborEvent extends Event {

    public NeighborEvent() {
        super(Methods.seconds2ticks(30));
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
        final List<GamePlayer> allGamePlayer = new ArrayList<>(fr.glerious.uhcmanagerapi.Main.getGamePlayers());
        for (int i = 0; i < allGamePlayer.size(); i++) {
            int choice = new Random().nextInt(allGamePlayer.size());
            GamePlayer gamePlayer = allGamePlayer.get(choice);
            allGamePlayer.remove(gamePlayer);
            Main.getNeighbors().add(new Neighbor(gamePlayer));
        }
        Bukkit.broadcastMessage("§7Attribution des §6§lNeighbors§r§7.");
    }
}
