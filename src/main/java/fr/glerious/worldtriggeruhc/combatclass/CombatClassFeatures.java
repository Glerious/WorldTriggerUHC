package fr.glerious.worldtriggeruhc.combatclass;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface CombatClassFeatures {

    List<ItemStack> items();

    void annonce();

    void ambient();

    void power();

    String name();
}
