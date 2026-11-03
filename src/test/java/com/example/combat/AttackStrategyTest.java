package com.example.combat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttackStrategyTest {

    private final Hero hero = new Hero("Test", 1, 100, 10);

    @Test
    void meleeAttack_calculatesDamage() {
        // Arrange: private hero object

        // Act + Assert
        assertEquals(12, new MeleeAttack().calculateDamage(hero));
    }

    @Test
    void magicAttack_calculatesDamage() {
        // Arrange: private hero object

        // Act + Assert
        assertEquals(8, new MagicAttack().calculateDamage(hero));
    }

    @Test
    void rangedAttack_calculatesDamage() {
        // Arrange: private hero object

        // Act + Assert
        assertEquals(10, new RangedAttack().calculateDamage(hero));
    }

    @Test
    void character_defaultStrategyIsMeleeAttack() {
        // Arrange: private hero object

        // Act + Assert
        assertInstanceOf(MeleeAttack.class, hero.getAttackStrategy());
    }

    @Test
    void setAttackStrategy_null_throwsNullPointerException() {
        // Arrange: private hero object

        // Act + Assert
        assertThrows(NullPointerException.class, () -> hero.setAttackStrategy(null));
    }

    @Test
    void setAttackStrategy_lambda_isUsed() {
        // Arrange: private hero object

        // Act
        hero.setAttackStrategy(attacker -> 5);

        // Assert
        assertEquals(5, hero.getAttackStrategy().calculateDamage(hero));
    }
}