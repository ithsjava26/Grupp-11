package com.example.combat;

public class MeleeAttack implements AttackStrategy {
    private static final double MULTIPLIER = 1.2;

    @Override
    public int calculateDamage(GameCharacter attacker) {
        return (int) Math.round(attacker.getAttackDamage() * MULTIPLIER);
    }
}
