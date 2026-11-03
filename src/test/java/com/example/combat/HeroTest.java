package com.example.combat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    @Test
    void takeDamage_reducesHealth() {
        // Arrange
        Hero hero = new Hero("Test", 1, 100, 10);

        // Act
        hero.takeDamage(30);

        // Assert
        assertEquals(70, hero.getCurrentHealth());
    }

    @Test
    void takeDamage_takesMoreDamageThanHealth_healthStopsAtZeroAndHeroDies() {
        // Arrange
        Hero hero = new Hero("Test", 1, 100, 10);

        // Act
        hero.takeDamage(500);

        // Assert
        assertEquals(0, hero.getCurrentHealth());
        assertFalse(hero.isAlive());
    }

    @Test
    void takeDamage_negativeDamage_throwsIllegalArgumentException() {
        // Arrange
        Hero hero = new Hero("Test", 1, 100, 10);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> hero.takeDamage(-1));
    }

    @Test
    void heal_healsMoreThenMaxHealth_doesNotExceedMaxHealth() {
        // Arrange
        Hero hero = new Hero("Test", 1, 100, 10);

        // Act
        hero.takeDamage(20);
        hero.heal(500);

        // Assert
        assertEquals(100, hero.getCurrentHealth());
    }

    @Test
    void levelUp_IncreasesLevelHealthAndAttackDamage() {
        // Arrange
        Hero hero = new Hero("Test", 1, 100, 10);

        // Act
        hero.levelUp();

        // Assert
        assertEquals(2, hero.getLevel());
        assertEquals(110, hero.getMaxHealth());
        assertEquals(13, hero.getAttackDamage());
    }

    @Test
    void creatingHero_invalidConstructor_throwsIllegalArgumentException() {
        // Arrange + Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new Hero(" ", 1, 100, 10));
        assertThrows(IllegalArgumentException.class, () -> new Hero("Test", 0, 100, 10));
    }

}