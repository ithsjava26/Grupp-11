package com.example.combat;

public class Hero extends GameCharacter {

    private static final int DAMAGE_PER_LEVEL = 3;
    private static final int HEALTH_PER_LEVEL = 10;

    private int level;

    public Hero(String name, int level, int maxHealth, int currentHealth, int baseDamage) {
        super(name, maxHealth, currentHealth, baseDamage);

        if (level <= 0) {
            throw new IllegalArgumentException("Level cannot be negative or zero");
        }
        this.level = level;
    }

    public Hero(String name, int level, int maxHealth, int baseDamage) {
        this(name, level, maxHealth, maxHealth, baseDamage);
    }

    public int getLevel() {
        return level;
    }

    @Override
    public int getAttackDamage() {
        return getBaseDamage() + (level - 1) * DAMAGE_PER_LEVEL;
    }

    public void levelUp() {
        level++;
        increaseMaxHealth(HEALTH_PER_LEVEL);
    }
}
