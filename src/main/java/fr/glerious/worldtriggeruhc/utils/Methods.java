package fr.glerious.worldtriggeruhc.utils;

import fr.glerious.worldtriggeruhc.addons.Gameplayered;
import net.minecraft.server.v1_8_R3.Tuple;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Methods {

    public static <K, V> HashMap<K, V> MergeList(List<K> l1, List<V> l2) {
        HashMap<K, V> h = new HashMap<>();
        Iterator<K> i1  = l1.iterator();
        Iterator<V> i2 = l2.iterator();
        while (i1.hasNext() && i2.hasNext()) {
            h.put(i1.next(), i2.next());
        }
        return h;
    }

    @SafeVarargs
    public static <A> boolean isOneOf(A a, A... othersA) {
        return Arrays.asList(othersA).contains(a);
    }

    public static String stylized(String string) {
        return string.replace("&", "§");
    }

    public static List<Integer> rangedList(int start, int end) {
        return IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

    public static <A> List<A> concatList(List<A> list, List<A> otherList) {
        return Stream.concat(list.stream(), otherList.stream()).collect(Collectors.toList());
    }

    public static Integer seconds2ticks(float seconds) {
        float returned = 20 * seconds;
        return Math.round(returned);
    }

    public static Integer seconds2ticks(double seconds) {
        double returned = 20 * seconds;
        return Math.toIntExact(Math.round(returned));
    }

    public static <A extends Gameplayered> A getByUUID(List<A> list, UUID uuid) {
        for (A a : list) {
            if (fr.glerious.uhcmanagerapi.Main.getGamePlayer(uuid)
                    .equals(a.getGamePlayer()))
                return a;
        } return null;
    }

    public static String cooldownMessage(int cooldownTick) {
        if (cooldownTick < 20) return "Un instant.";
        else {
            int hour = (int) ((double) (cooldownTick / fr.glerious.javautils.Methods.seconds2ticks(3600)));
            int minute = (int) ((double) (cooldownTick
                    / fr.glerious.javautils.Methods.seconds2ticks(3600)) - 60 * hour);
            int seconde = (int) ((double) (cooldownTick
                    /fr.glerious.javautils.Methods.seconds2ticks(1)) - 3600 * hour - 60 * minute);

            StringBuilder timer = getStringBuilder(hour, minute, seconde);
            return timer.toString();
        }
    }

    private static StringBuilder getStringBuilder(int hour, int minute, int seconde) {
        StringBuilder timer = new StringBuilder();
        if (hour != 0) {
            timer.append(hour);
            if (hour == 1) timer.append(" heure ");
            else timer.append(" heures ");
        }
        if (minute != 0) {
            timer.append(minute);
            if (minute == 1) timer.append(" minute ");
            else timer.append(" minutes ");
        }
        if (seconde != 0) {
            timer.append(seconde);
            if (seconde == 1) timer.append(" seconde.");
            else timer.append(" secondes.");
        }
        return timer;
    }
}
