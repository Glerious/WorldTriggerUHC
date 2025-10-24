package fr.glerious.worldtriggeruhc.addons;

import fr.glerious.uhcmanagerapi.timeline.Event;
import fr.glerious.worldtriggeruhc.Main;
import fr.glerious.worldtriggeruhc.combatclass.CombatClass;

public class AttributeEvent extends Event {

    public AttributeEvent() {
        super(0);
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
        Main.getCombatClasses().forEach(CombatClass::annonce);
    }
}
