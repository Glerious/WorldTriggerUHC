package fr.glerious.worldtriggeruhc.combatclass;

public enum Stuff {

    ATTACKER(true, 4, false, 0),
    GUNNER(false, 3, true, 2),
    SHOOTER(false, 3, false, 0),
    SNIPER(false, 3, true, 0),
    ALLROUNDER(true, 3, true, 1),
    OPERATOR(false, 0, false, 0)
    ;

    private final Boolean hasDiamondSword;
    private final Integer levelSword;
    private final Boolean hasBow;
    private final Integer levelBow;

    Stuff(Boolean hasDiamondSword, Integer levelSword, Boolean hasBow, Integer levelBow){
        this.hasDiamondSword = hasDiamondSword;
        this.levelSword = levelSword;
        this.hasBow = hasBow;
        this.levelBow = levelBow;
    }

    public Boolean getHasDiamondSword() {
        return hasDiamondSword;
    }

    public Integer getLevelSword() {
        return levelSword;
    }

    public Boolean getHasBow() {
        return hasBow;
    }

    public Integer getLevelBow() {
        return levelBow;
    }
}
