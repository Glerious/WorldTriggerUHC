package fr.glerious.worldtriggeruhc;

import fr.glerious.javautils.ConfigAPI;

public class ConfigWT extends ConfigAPI {

    public static String getExpected(String string) {
        return getFromPath("Commande Impossible","expected", string, Main.getMain());
    }

    public static String getCommandsFeedback(String string) {
        return getFromPath("","commands_feedback", string, Main.getMain());
    }

    public static String getInformation(String string) {
        return getFromPath("","information", string, Main.getMain());
    }

}
