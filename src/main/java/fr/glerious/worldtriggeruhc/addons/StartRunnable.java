package fr.glerious.worldtriggeruhc.addons;

import fr.glerious.uhcmanagerapi.timeline.Event;
import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.javautils.Grade;
import fr.glerious.worldtriggeruhc.Main;
import fr.glerious.worldtriggeruhc.ConfigWT;

import java.util.List;

public class StartRunnable extends Event {

    private final List<GamePlayer> allGamePlayer = fr.glerious.uhcmanagerapi.Main.getGamePlayers();

    public StartRunnable() {
        super(0);
    }

    @Override
    public boolean condition() {
        if (allGamePlayer.size() == Main.getCombatClasses().size()) return true;
        for (GamePlayer gamePlayer:
                allGamePlayer) {
            if (gamePlayer.hasGarde(Grade.HOST))
                gamePlayer.getPlayer().sendMessage(ConfigWT.getExpected("role_require"));
        }
        return false;
    }

    @Override
    public void exit() {

    }

    @Override
    public void action() {

    }
}
