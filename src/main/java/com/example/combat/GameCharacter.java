package com.example.combat;

public abstract class GameCharacter {

    private final String name;
    private final int maxHealth;
    private int currentHealth;

    protected GameCharacter(String name, int maxHealth) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Invalid name, must not be empty");
        }
        if (maxHealth <= 0) {
            throw new IllegalArgumentException("Invalid max health, must be more than 0");
        }

        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }


    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

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
        currentHealth = Math.min(currentHealth + healAmount, maxHealth);
    }
}
