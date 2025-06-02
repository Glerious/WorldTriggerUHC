package fr.glerious.worldtriggeruhc.utils;

import fr.glerious.worldtriggeruhc.addons.Gameplayered;

import java.util.List;
import java.util.UUID;

public class Methods {

    public static <A extends Gameplayered> A getByUUID(List<A> list, UUID uuid) {
        for (A a : list) {
            if (fr.glerious.uhcmanagerapi.Main.getGamePlayer(uuid)
                    .equals(a.getGamePlayer()))
                return a;
        } return null;
    }
}
