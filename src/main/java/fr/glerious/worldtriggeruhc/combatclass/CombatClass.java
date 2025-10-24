package fr.glerious.worldtriggeruhc.combatclass;

import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.worldtriggeruhc.addons.Gameplayered;
import fr.glerious.worldtriggeruhc.ConfigWT;
import fr.glerious.worldtriggeruhc.utils.Methods;
import org.bukkit.entity.Player;

public abstract class CombatClass extends Gameplayered implements CombatClassFeatures{

    protected int cooldown = 0;

    public CombatClass(GamePlayer gamePlayer) {
        super(gamePlayer);
        gamePlayer.getPlayer().sendMessage(ConfigWT.getInformation("announce") + name());
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

    public void annonce() {
        getGamePlayer().giveItems(items());
    }

    @Override
    public void power() {
        Player player = gamePlayer.getPlayer();
        player.sendMessage(ConfigWT.getInformation("cooldown") + Methods.cooldownMessage(cooldown));
    }
}
