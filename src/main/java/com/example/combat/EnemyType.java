package com.example.combat;

public enum EnemyType {
    //          baseHealth, baseAttack, healthPerLevel, attackPerLevel, spawnWeight
    SLIME(20, 2, 3, 0, 20),
    GOBLIN(24, 4, 3, 1, 18),
    SKELETON(20, 7, 2, 1, 17),
    BANDIT(30, 6, 4, 1, 15),
    SOLDIER(40, 6, 5, 1, 12),
    ORC(45, 8, 6, 1, 9),
    TROLL(60, 6, 8, 1, 5),
    OGRE(40, 10, 5, 1, 4);

    private final int baseHealth;
    private final int baseDamage;
    private final int healthPerLevel;
    private final int damagePerLevel;
    private final int spawnWeight;

    EnemyType(int baseHealth, int baseDamage, int healthPerLevel, int damagePerLevel, int spawnWeight) {
        this.baseHealth = baseHealth;
        this.baseDamage = baseDamage;
        this.healthPerLevel = healthPerLevel;
        this.damagePerLevel = damagePerLevel;
        this.spawnWeight = spawnWeight;
    }

    public int getHealth(int level) {
        return baseHealth + healthPerLevel * (level - 1);
    }

    public int getDamage(int level) {
        return baseDamage + damagePerLevel * (level - 1);
    }

    public int getSpawnWeight() {
        return spawnWeight;
    }

    public String getDisplayName() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
