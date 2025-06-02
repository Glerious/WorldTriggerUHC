package fr.glerious.worldtriggeruhc.addons;

import fr.glerious.uhcmanagerapi.timeline.Runnables;
import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.uhcmanagerapi.permission.Grade;
import fr.glerious.worldtriggeruhc.Main;
import fr.glerious.worldtriggeruhc.utils.ConfigAPI;

import java.util.List;

public class StartRunnable extends Runnables {

    private final List<GamePlayer> allGamePlayer = fr.glerious.uhcmanagerapi.Main.getGamePlayers();

    public StartRunnable() {
        super(0, 0);
    }

    @Override
    public boolean condition() {
        if (allGamePlayer.size() != Main.getCombatClasses().size()) return true;
        for (GamePlayer gamePlayer:
                allGamePlayer) {
            if (gamePlayer.hasGarde(Grade.HOST))
                gamePlayer.getPlayer().sendMessage(ConfigAPI.getToConfig("expected.role_require"));
        }
        return false;
    }

    @Override
    public void exit() {

    }

    @Override
    public void action() {
        for (GamePlayer gamePlayer :
                allGamePlayer) {
            gamePlayer.getPlayer().getInventory().clear();
            Main.getCombatClass(gamePlayer.getUuid()).annonce();
        }
    }
}
