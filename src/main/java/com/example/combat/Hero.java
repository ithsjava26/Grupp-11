package com.example.combat;

public class Hero extends GameCharacter {

    private static final int DAMAGE_PER_LEVEL = 3;
    private static final int HEALTH_PER_LEVEL = 10;

    public Hero(String name, int level, int maxHealth, int currentHealth, int baseDamage) {
        super(name, level, maxHealth, currentHealth, baseDamage);
    }

    public Hero(String name, int level, int maxHealth, int baseDamage) {
        this(name, level, maxHealth, maxHealth, baseDamage);
    }

    @Override
    public int getAttackDamage() {
        return getBaseDamage() + (getLevel() - 1) * DAMAGE_PER_LEVEL;
    }

    public void levelUp() {
        increaseMaxHealth(HEALTH_PER_LEVEL);
        increaseLevel();
    }
}
