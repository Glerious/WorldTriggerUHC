package fr.glerious.worldtriggeruhc.addons;

import fr.glerious.uhcmanagerapi.timeline.Runnable;
import fr.glerious.uhcmanagerapi.timeline.gamestates.InGame;
import fr.glerious.uhcmanagerapi.timeline.gamestates.Waiting;
import fr.glerious.uhcmanagerapi.utils.Methods;
import fr.glerious.worldtriggeruhc.Main;
import fr.glerious.worldtriggeruhc.faction.FactionFeatures;
import fr.glerious.worldtriggeruhc.neighbors.Neighbor;

public class RoleEffects extends Runnable {

    public RoleEffects()
    {
        super(0L, Methods.seconds2ticks(2));
    }

    @Override
    public boolean condition() {
        return !(fr.glerious.uhcmanagerapi.Main.getGameState() instanceof InGame);
    }

    @Override
    public void exit() {

    }

    @Override
    public void action() {
        Main.getNeighbors().forEach(Neighbor::ambient);
        Main.getFactionList().forEach(FactionFeatures::ambient);
    }
}
