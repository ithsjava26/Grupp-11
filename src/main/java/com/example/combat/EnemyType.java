package com.example.combat;

public enum EnemyType {
    //          baseHealth, baseAttack, healthPerLevel, attackPerLevel
    BANDIT(15, 4, 10, 3),
    SOLDIER(20, 5, 12, 3),
    SLIME(10, 2, 12, 1),
    GOBLIN(12, 3, 6, 3),
    SKELETON(10, 4, 5, 4),
    ORC(25, 6, 15, 4),
    OGRE(40, 8, 20, 5),
    TROLL(50, 5, 25, 3);

    private final int baseHealth;
    private final int baseDamage;
    private final int healthPerLevel;
    private final int damagePerLevel;

    EnemyType(int baseHealth, int baseDamage, int healthPerLevel, int damagePerLevel) {
        this.baseHealth = baseHealth;
        this.baseDamage = baseDamage;
        this.healthPerLevel = healthPerLevel;
        this.damagePerLevel = damagePerLevel;
    }

    public int getHealth(int level) {
        return baseHealth + healthPerLevel * (level - 1);
    }

    public int getDamage(int level) {
        return baseDamage + damagePerLevel * (level - 1);
    }
}
