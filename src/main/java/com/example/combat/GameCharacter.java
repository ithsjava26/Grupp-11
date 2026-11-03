package com.example.combat;

import java.util.Objects;

public abstract class GameCharacter {

    private AttackStrategy attackStrategy = new MeleeAttack();

    private final String name;
    private int maxHealth;
    private int currentHealth;
    private int baseDamage;
    private int level;

    protected GameCharacter(String name, int level, int maxHealth, int currentHealth, int baseDamage) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Invalid name, must not be empty");
        }
        if (level <= 0) {
            throw new IllegalArgumentException("Level cannot be negative or zero");
        }
        if (maxHealth <= 0) {
            throw new IllegalArgumentException("Invalid max health, must be positive and more than 0");
        }
        if (currentHealth <= 0 || currentHealth > maxHealth) {
            throw new IllegalArgumentException("Invalid current health, must be positive, more than 0 and not more than maxHealth");
        }
        if (baseDamage <= 0) {
            throw new IllegalArgumentException("Invalid base damage, must be positive and more than 0");
        }

        this.name = name;
        this.level = level;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.baseDamage = baseDamage;
    }

    protected GameCharacter(String name, int level, int maxHealth, int baseDamage) {
        this(name, level, maxHealth, maxHealth, baseDamage);
    }

    // Abstract methods
    public abstract int getAttackDamage();


    // Get methods
    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    // Set methods
    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = Objects.requireNonNull(attackStrategy, "Attack strategy must not be null");
    }

    // Other methods

    public boolean isAlive() {
        return getCurrentHealth() > 0;
    }

    public void takeDamage(int damageAmount) {
        if (damageAmount < 0) {
            throw new IllegalArgumentException("Invalid damageAmount, cannot be negative");
        }
        currentHealth = Math.max(0, currentHealth - damageAmount);
    }

    public void heal(int healAmount) {
        if (healAmount < 0) {
            throw new IllegalArgumentException("Invalid healAmount, cannot be negative");
        }
        currentHealth += Math.min(healAmount, maxHealth - currentHealth);
    }

    protected void increaseMaxHealth(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount, cannot be negative or zero");
        }
        if (maxHealth > Integer.MAX_VALUE - amount || currentHealth > Integer.MAX_VALUE - amount) {
            throw new ArithmeticException("Health overflow");
        }

        maxHealth += amount;
        currentHealth += amount;
    }

    protected void increaseLevel() {
        level++;
    }

}
