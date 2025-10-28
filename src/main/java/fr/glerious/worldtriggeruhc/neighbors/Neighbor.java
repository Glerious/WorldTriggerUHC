package fr.glerious.worldtriggeruhc.neighbors;

import fr.glerious.uhcmanagerapi.gameplayer.GamePlayer;
import fr.glerious.uhcmanagerapi.utils.Methods;
import fr.glerious.worldtriggeruhc.addons.Gameplayered;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Neighbor extends Gameplayered {

    private final PotionEffect neighborsEffect;

    public Neighbor(GamePlayer gamePlayer) {
        super(gamePlayer);
        List<PotionEffect> potionEffects = Arrays.asList(
                PotionEffectType.INCREASE_DAMAGE.createEffect(Methods.seconds2ticks(2), 0),
                PotionEffectType.DAMAGE_RESISTANCE.createEffect(Methods.seconds2ticks(2), 0)
        );
        int choice = new Random().nextInt(potionEffects.size());
        this.neighborsEffect = potionEffects.get(choice);
        annonce();
    }

    public void annonce() {
        gamePlayer.getPlayer().sendMessage(
                "§7§m-----------------------------------------" + "\n" +
                "§7Vous êtes un §6§lNeighbors§7." + "\n" +
                "§7Vous obtenez un effet aléatoire entre §4Force§7 et §bResistance§7." + "\n" +
                "§7Selon votre faction, vous obtiendrez divers atouts." + "\n" +
                "§7§m-----------------------------------------"
        );
        ambient();
    }

    public void ambient() {
        if (gamePlayer.getPlayer().hasPotionEffect(neighborsEffect.getType())) return;
        gamePlayer.getPlayer().addPotionEffect(neighborsEffect);
    }
}
