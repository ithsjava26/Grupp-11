package com.example.combat;

public class Enemy extends GameCharacter {
    private final EnemyType type;

    public Enemy(String name, int level, EnemyType type, int maxHealth, int currentHealth, int baseDamage) {
        super(name, level, maxHealth, currentHealth, baseDamage);
        this.type = type;
    }

    public Enemy(String name, int level, EnemyType type, int maxHealth, int baseDamage) {
        this(name, level, type, maxHealth, maxHealth, baseDamage);
    }

    public EnemyType getType() {
        return type;
    }

    @Override
    public int getAttackDamage() {
        return getBaseDamage();
    }
}
