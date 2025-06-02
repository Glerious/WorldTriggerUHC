package fr.glerious.worldtriggeruhc.addons;

import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;

public abstract class Gameplayered {

    protected final GamePlayer gamePlayer;

    protected Gameplayered(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
}
