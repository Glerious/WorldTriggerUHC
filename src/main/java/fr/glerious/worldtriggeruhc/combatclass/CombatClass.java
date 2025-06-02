package fr.glerious.worldtriggeruhc.combatclass;

import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.worldtriggeruhc.addons.Gameplayered;
import fr.glerious.worldtriggeruhc.utils.ConfigAPI;

public abstract class CombatClass extends Gameplayered implements CombatClassFeatures{

    protected int cooldown = 0;

    public CombatClass(GamePlayer gamePlayer) {
        super(gamePlayer);
        gamePlayer.getPlayer().sendMessage(ConfigAPI.getInformation("announce") + name());
        getGamePlayer().giveItems(items());

    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void decrementCooldown() {
        cooldown--;
    }

    public void updateRoleLine() {
        gamePlayer.getSideBar().updateMoreLine("§7» §6Classe: §7" + this.name(), 0);
    }
}
