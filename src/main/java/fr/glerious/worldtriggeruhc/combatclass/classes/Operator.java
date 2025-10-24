package fr.glerious.worldtriggeruhc.combatclass.classes;

import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.worldtriggeruhc.combatclass.CombatClass;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Operator extends CombatClass {

    public Operator(GamePlayer gamePlayer) {
        super(gamePlayer);
    }

    @Override
    public String name() {
        return "Operator";
    }

    @Override
    public List<ItemStack> items() {
        return null;
    }

    @Override
    public void annonce() {
        super.annonce();
    }

    @Override
    public void ambient() {

    }

    @Override
    public void power() {

    }
}
