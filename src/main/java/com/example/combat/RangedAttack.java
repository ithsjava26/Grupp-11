package com.example.combat;

public class RangedAttack implements AttackStrategy {
    private static final double MULTIPLIER = 1.0;

    @Override
    public int calculateDamage(GameCharacter attacker) {
        return (int) Math.round(attacker.getAttackDamage() * MULTIPLIER);
    }
}
