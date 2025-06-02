package fr.glerious.worldtriggeruhc.addons;

import fr.glerious.uhcmanagerapi.timeline.Runnables;
import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.uhcmanagerapi.timeline.gamestates.Waiting;
import fr.glerious.worldtriggeruhc.Main;
import fr.glerious.worldtriggeruhc.combatclass.CombatClass;

public class Cooldown extends Runnables {


    public Cooldown()
    {
        super(0, 1);
    }


    @Override
    public boolean condition() {
        return !(fr.glerious.uhcmanagerapi.Main.getGameState() instanceof Waiting);
    }

    @Override
    public void exit() {

    }

    @Override
    public void action() {
        for (GamePlayer gamePlayer :
                fr.glerious.uhcmanagerapi.Main.getGamePlayers()) {
            CombatClass combatClass = Main.getCombatClass(gamePlayer.getUuid());
            assert combatClass != null;
            if (combatClass.getCooldown() > 0) combatClass.decrementCooldown();
        }
    }
}
